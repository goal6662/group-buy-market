package com.goal.mapper;

import com.goal.model.GroupBuyActivity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupBuyActivityMapper {

    List<GroupBuyActivity> queryList();

    GroupBuyActivity queryValidGroupBuyActivity(@Param("groupBuyActivity") GroupBuyActivity groupBuyActivity);
}
