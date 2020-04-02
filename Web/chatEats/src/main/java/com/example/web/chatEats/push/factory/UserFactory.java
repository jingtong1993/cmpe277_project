package com.example.web.chatEats.push.factory;

import com.example.web.chatEats.push.bean.db.User;
import com.example.web.chatEats.push.utils.Hib;
import com.example.web.chatEats.push.utils.TextUtil;
import com.google.common.base.Strings;

import java.util.List;
import java.util.UUID;

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
}
