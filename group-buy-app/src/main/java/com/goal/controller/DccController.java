package com.goal.controller;

import com.goal.common.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class DccController {

    @Resource
    private RTopic dccTopic;

    /**
     * http://localhost:8080/api/v1/update_config?key=downgradeSwitch&value=1
     * http://localhost:8080/api/v1/update_config?key=cutRange&value=0
     */
    @GetMapping("/update_config")
    public Result<Boolean> updateConfig(@RequestParam String key, @RequestParam String value) {

        try {
            log.info("DCC 动态配置值变更 key:{} value:{}", key, value);
            dccTopic.publish(key + "," + value);
            return Result.success(true);
        } catch (Exception e) {
            log.error("DCC 动态配置值变更失败 key:{} value:{}", key, value, e);
            return Result.fail();
        }

    }

}
