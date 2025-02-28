package com.goal.test.tag;

import com.goal.service.repository.IRedisService;
import com.goal.service.tag.ITagService;
import com.goal.service.tag.adapter.ITagAdapter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBitSet;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class TagServiceTest {

    @Resource
    private ITagService tagService;

    @Resource
    private ITagAdapter tagAdapter;

    @Resource
    private IRedisService redisService;

    @Test
    public void test() {
        tagService.executeTagBatchJob("RQ_KJHKL98UU78H66554GFDV", "10001");
    }

    @Test
    public void test_get_tag_bitmap() {
        RBitSet bitSet = redisService.getBitSet("RQ_KJHKL98UU78H66554GFDV");

        log.info("测试结果：{}", bitSet.get(tagAdapter.getIndexFromUserId("xiaofuge")));
        log.info("测试结果：{}", bitSet.get(tagAdapter.getIndexFromUserId("gudebai")));
    }

}
