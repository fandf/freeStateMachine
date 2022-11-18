package com.fandf.freestatemachine.demo.model;

/**
 * 档案数据上下文，存储状态
 *
 * @author fandongfeng
 * @date 2022-11-17 18:47
 */
public class ArchiveContext {

    /**
     * 草稿
     */
    public static final String DRAFT = "DRAFT";
    /**
     * 核验中
     */
    public static final String VERYING = "START_VERY";
    /**
     * 审批中
     */
    public static final String APPROVEING = "APPROVEING";
    /**
     * 签署中
     */
    public static final String SIGNING = "SIGNING";

}
