package com.fandf.freestatemachine.demo.model;

import lombok.Getter;

/**
 * @author fandongfeng
 * @date 2022-11-17 18:45
 */
@Getter
public enum ArchiveBehavior {
    /**
     * 档案行为合集
     */
    DRAFT("提交草稿"),
    VERIFY("发起核验"),
    VERIFY_PASS("核验通过"),
    VERIFY_FAIL("核验失败"),
    APPROVE("发起审批"),
    APPROVE_PASS("审批通过"),
    APPROVE_FAIL("审批失败"),
    SIGN("发起签署"),
    SIGN_PASS("签署通过"),
    SIGN_FAIL("签署失败"),
    ;


    private final String value;


    ArchiveBehavior(String value) {
        this.value = value;
    }
}
