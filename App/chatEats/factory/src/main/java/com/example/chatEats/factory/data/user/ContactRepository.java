package com.example.chatEats.factory.data.user;

import com.example.chatEats.factory.data.helper.DbHelper;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import com.example.chatEats.factory.data.BaseDbRepository;
import com.example.chatEats.factory.data.DataSource;
import com.example.chatEats.factory.model.db.User;
import com.example.chatEats.factory.model.db.User_Table;
import com.example.chatEats.factory.persistence.Account;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import androidx.annotation.NonNull;

/**
 * 联系人仓库
 */
public class ContactRepository extends BaseDbRepository<User> implements ContactDataSource {
    @Override
    public void load(DataSource.SucceedCallback<List<User>> callback) {
        super.load(callback);

        // 加载本地数据库数据
        SQLite.select()
                .from(User.class)
                .where(User_Table.isFollow.eq(true))
                .and(User_Table.id.notEq(Account.getUserId()))
                .orderBy(User_Table.name, true)
                .limit(100)
                .async()
                .queryListResultCallback(this)
                .execute();
    }

    @Override
    protected boolean isRequired(User user) {
        return user.isFollow() && !user.getId().equals(Account.getUserId());
    }
}
