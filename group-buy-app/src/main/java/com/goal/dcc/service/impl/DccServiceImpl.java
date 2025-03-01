package com.goal.dcc.service.impl;

import com.goal.dcc.annotation.DccValue;
import com.goal.dcc.service.IDccService;
import org.springframework.stereotype.Service;

@Service
public class DccServiceImpl implements IDccService {

    @DccValue("downgradeSwitch:0")
    private String downgradeSwitch;

    @DccValue("cutRange:100")
    private String cutRange;

    @Override
    public boolean isDowngradeSwitch() {
        return "1".equals(downgradeSwitch);
    }

    @Override
    public boolean isCutRange(String userId) {

        int hashCode = Math.abs(userId.hashCode());

        int lowTwoDigits = hashCode % 100;

        // 判断是否在切量范围内
        if (lowTwoDigits <= Integer.parseInt(cutRange)) {
            return true;
        }

        return false;
    }

}
