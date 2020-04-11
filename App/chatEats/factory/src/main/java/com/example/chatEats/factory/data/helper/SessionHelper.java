package com.example.chatEats.factory.data.helper;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import com.example.chatEats.factory.model.db.Session;
import com.example.chatEats.factory.model.db.Session_Table;


public class SessionHelper {
    // 从本地查询Session
    public static Session findFromLocal(String id) {
        return SQLite.select()
                .from(Session.class)
                .where(Session_Table.id.eq(id))
                .querySingle();
    }
}
