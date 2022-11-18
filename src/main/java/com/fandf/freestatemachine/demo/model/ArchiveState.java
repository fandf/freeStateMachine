package com.fandf.freestatemachine.demo.model;

/**
 * 档案状态枚举
 *
 * @author fandongfeng
 * @date 2022-11-17 18:46
 */
public enum ArchiveState {
    /**
     * 档案状态
     */
    DRAFT("草稿"),
    VERIFY("核验中"),
    VERIFY_PASS("核验通过"),
    VERIFY_FAIL("核验失败"),
    APPROVE("审批中"),
    APPROVE_PASS("审批通过"),
    APPROVE_FAIL("审批失败"),
    SIGN("签署中"),
    SIGN_PASS("签署通过"),
    SIGN_FAIL("签署失败"),
    FINISH("完成"),

    ;
    private final String value;


    ArchiveState(String value) {
        this.value = value;
    }

}
