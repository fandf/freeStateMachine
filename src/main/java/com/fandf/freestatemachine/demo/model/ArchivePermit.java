package com.fandf.freestatemachine.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 档案审批流转实体类
 *
 * @author fandongfeng
 * @date 2022-11-18 9:30
 */
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ArchivePermit {
    //审批用户id
    private String userId;
    //审批状态
    private ArchiveState status;

}
