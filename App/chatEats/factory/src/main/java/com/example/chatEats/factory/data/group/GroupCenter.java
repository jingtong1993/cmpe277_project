package com.example.chatEats.factory.data.group;

import com.example.chatEats.factory.model.card.GroupCard;
import com.example.chatEats.factory.model.card.GroupMemberCard;

/**
 * 群中心的接口定义
 */
public interface GroupCenter {
    // 群卡片的处理
    void dispatch(GroupCard... cards);

    // 群成员的处理
    void dispatch(GroupMemberCard... cards);
}
