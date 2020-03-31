package com.example.web.chatEats.push.service;


import com.example.web.chatEats.push.bean.api.base.ResponseModel;
import com.example.web.chatEats.push.bean.api.user.UpdateInfoModel;
import com.example.web.chatEats.push.bean.card.UserCard;
import com.example.web.chatEats.push.bean.db.User;
import com.example.web.chatEats.push.factory.UserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserService extends BaseService{

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<UserCard> update(UpdateInfoModel model) {
        if (!UpdateInfoModel.check(model)) {
            return ResponseModel.buildParameterError();
        }
        User self = getSelf();

        self = model.updateToUser(self);
        self = UserFactory.update(self);
        UserCard card = new UserCard(self, true);
        return ResponseModel.buildOk(card);

    }
}
