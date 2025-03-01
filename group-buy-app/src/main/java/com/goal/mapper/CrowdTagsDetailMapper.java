package com.goal.mapper;

import com.goal.model.CrowdTagsDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrowdTagsDetailMapper {

    void addCrowdTagsUserId(CrowdTagsDetail crowdTagsDetailReq);

    List<CrowdTagsDetail> queryCrowdTagDetailByDetail(@Param("crowdTagsDetail") CrowdTagsDetail crowdTagsDetail);
}
