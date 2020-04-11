package com.example.chatEats.factory.data.message;

import com.example.chatEats.factory.model.card.MessageCard;


public interface MessageCenter {
    void dispatch(MessageCard... cards);
}
