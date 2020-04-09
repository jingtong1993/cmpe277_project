package com.example.web.chatEats.push.factory;

import com.example.web.chatEats.push.bean.db.User;
import com.example.web.chatEats.push.bean.db.UserFollow;
import com.example.web.chatEats.push.utils.Hib;
import com.example.web.chatEats.push.utils.TextUtil;
import com.google.common.base.Strings;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserFactory {
    public static User findByToken(String token) {

        return Hib.query(session -> {
            User user = (User) session.createQuery("from User where token=:token")
                    .setParameter("token", token).uniqueResult();
            return user;
        });
    }

    public static User findByPhone(String phone) {

        return Hib.query(session -> {
            User user = (User) session.createQuery("from User where phone=:inPhone")
                    .setParameter("inPhone", phone).uniqueResult();
            return user;
        });
    }

    public static User findByName(String name) {

        return Hib.query(session -> {
            User user = (User) session.createQuery("from User where name=:inName")
                    .setParameter("inName", name).uniqueResult();
            return user;
        });
    }

    public static User findById(String id) {
        // 通过Id查询，更方便
        return Hib.query(session -> session.get(User.class, id));
    }

    public static User update(User user) {
        return Hib.query(session -> {
            session.saveOrUpdate(user);
            return user;
        });
    }

    public static User bindPushId(User user, String pushId) {
        if (Strings.isNullOrEmpty(pushId)) {
            return null;
        }
        Hib.queryOnly(session -> {
            @SuppressWarnings("unchecked")
            List<User> userList = (List<User>) session
                    .createQuery("from User where lower(pushId)=:pushId and id!=:userId")
                    .setParameter("pushId", pushId.toLowerCase())
                    .setParameter("userId", user.getId())
                    .list();

            for (User u : userList) {
                u.setPushId(null);
                session.saveOrUpdate(u);
            }

        });

        if (pushId.equalsIgnoreCase(user.getPushId())) {
            return user;
        }
        else {
            if (Strings.isNullOrEmpty(user.getPushId())) {
                // TODO
            }
            user.setPushId(pushId);
            return update(user);
        }
    }

    public static User login(String account, String password) {
        final String accountStr = account.trim();
        final String encodePassword = encodePassword(password);

        User user = Hib.query(session -> (User) session
                .createQuery("from User where phone=:phone and password=:password")
                .setParameter("phone", accountStr)
                .setParameter("password", encodePassword)
                .uniqueResult());

        if (user != null) {
            user = login(user);
        }
        return user;
    }


    public static User register(String account, String password, String name) {
        account = account.trim();
        password = encodePassword(password);

        User user = createUser(account, password, name);

        if (user != null) {
            user = login(user);
        }
        return user;

    }




    private static User createUser(String account, String password, String name) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setPhone(account);
        return Hib.query(session -> {
            session.save(user);
            return user;
        });
    }

    private static User login(User user) {
        String newToken = UUID.randomUUID().toString();
        newToken = TextUtil.encodeBase64(newToken);

        user.setToken(newToken);
        return update(user);
    }

    private static String encodePassword(String password) {
        password = password.trim();
        password = TextUtil.getMD5(password);
        return TextUtil.encodeBase64(password);
    }

    public static List<User> contacts(User self) {
        return Hib.query(session -> {
            // 重新加载一次用户信息到self中，和当前的session绑定
            session.load(self, self.getId());

            // 获取我关注的人
            Set<UserFollow> flows = self.getFollowing();

            // 使用简写方式
            return flows.stream()
                    .map(UserFollow::getTarget)
                    .collect(Collectors.toList());

        });
    }

    public static User follow(final User origin, final User target, final String alias) {
        UserFollow follow = getUserFollow(origin, target);
        if (follow != null) {
            // 已关注，直接返回
            return follow.getTarget();
        }

        return Hib.query(session -> {
            // 想要操作懒加载的数据，需要重新load一次
            session.load(origin, origin.getId());
            session.load(target, target.getId());

            // 我关注人的时候，同时他也关注我，
            // 所有需要添加两条UserFollow数据
            UserFollow originFollow = new UserFollow();
            originFollow.setOrigin(origin);
            originFollow.setTarget(target);
            // 备注是我对他的备注，他对我默认没有备注
            originFollow.setAlias(alias);

            // 发起者是他，我是被关注的人的记录
            UserFollow targetFollow = new UserFollow();
            targetFollow.setOrigin(target);
            targetFollow.setTarget(origin);

            // 保存数据库
            session.save(originFollow);
            session.save(targetFollow);

            return target;
        });
    }

    public static UserFollow getUserFollow(final User origin, final User target) {
        return Hib.query(session -> (UserFollow) session
                .createQuery("from UserFollow where originId = :originId and targetId = :targetId")
                .setParameter("originId", origin.getId())
                .setParameter("targetId", target.getId())
                .setMaxResults(1)
                // 唯一查询返回
                .uniqueResult());
    }

    @SuppressWarnings("unchecked")
    public static List<User> search(String name) {
        if (Strings.isNullOrEmpty(name))
            name = ""; // 保证不能为null的情况，减少后面的一下判断和额外的错误
        final String searchName = "%" + name + "%"; // 模糊匹配

        return Hib.query(session -> {
            // 查询的条件：name忽略大小写，并且使用like（模糊）查询；
            // 头像和描述必须完善才能查询到
            return (List<User>) session.createQuery("from User where lower(name) like :name and portrait is not null and description is not null")
                    .setParameter("name", searchName)
                    .setMaxResults(20) // 至多20条
                    .list();

        });

    }


}
