package com.goal.mapper;

import com.goal.model.GroupBuyActivity;

import java.util.List;

public interface GroupBuyActivityMapper {

    List<GroupBuyActivity> queryList();

    GroupBuyActivity queryValidGroupBuyActivity(GroupBuyActivity groupBuyActivity);
}
