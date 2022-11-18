package com.fandf.freestatemachine.demo.handler;

import com.fandf.freestatemachine.core.ActionHandler;
import com.fandf.freestatemachine.core.BaseStateMachine;
import com.fandf.freestatemachine.core.DataContext;
import com.fandf.freestatemachine.core.StateMachineInstance;
import com.fandf.freestatemachine.demo.model.ArchiveContext;
import com.fandf.freestatemachine.demo.model.ArchivePermit;
import com.fandf.freestatemachine.demo.model.ArchiveState;
import lombok.extern.slf4j.Slf4j;

/**
 * 发起核验动作实现类
 *
 * @author fandongfeng
 * @date 2022-11-18 9:24
 */
@Slf4j
public class VerifyHandler implements ActionHandler {
    @Override
    public void handler(DataContext context, StateMachineInstance stateMachineInstance,String userId) {
        ArchiveState archiveState = (ArchiveState) context.getData(BaseStateMachine.CURRENT_STATE);
        log.info("[{}], 将档案状态修改为 {} ", this.getClass().getSimpleName(), archiveState);
        log.info("[{}], 开启核验，等待核验审批结果  ---- do something   ", this.getClass().getSimpleName());
        log.info("[{}], 开启核验，等待核验审批结果  ---- 通知用户赶紧来核验   ", this.getClass().getSimpleName());
    }
}
