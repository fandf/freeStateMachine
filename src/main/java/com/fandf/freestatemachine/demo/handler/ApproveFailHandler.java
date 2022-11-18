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
 * 审批失败动作实现类
 *
 * @author fandongfeng
 * @date 2022-11-18 9:24
 */
@Slf4j
public class ApproveFailHandler implements ActionHandler {
    @Override
    public void handler(DataContext context, StateMachineInstance stateMachineInstance, String userId) {
        ArchiveState archiveState = (ArchiveState) context.getData(BaseStateMachine.CURRENT_STATE);
        log.info("[{}], 将档案状态修改为 {} ", this.getClass().getSimpleName(), archiveState);
        log.info("[{}], 审批不通过实现业务逻辑  ---- do something", this.getClass().getSimpleName());
    }
}
