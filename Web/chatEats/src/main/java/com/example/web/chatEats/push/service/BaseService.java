package com.example.web.chatEats.push.service;

import com.example.web.chatEats.push.bean.db.User;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

public class BaseService {
    @Context
    protected SecurityContext securityContext;

    protected User getSelf() {
        return (User)securityContext.getUserPrincipal();
    }
}
