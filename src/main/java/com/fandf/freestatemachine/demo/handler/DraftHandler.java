package com.fandf.freestatemachine.demo.handler;

import com.fandf.freestatemachine.core.ActionHandler;
import com.fandf.freestatemachine.core.BaseStateMachine;
import com.fandf.freestatemachine.core.DataContext;
import com.fandf.freestatemachine.core.StateMachineInstance;
import com.fandf.freestatemachine.demo.model.ArchiveContext;
import com.fandf.freestatemachine.demo.model.ArchiveState;
import lombok.extern.slf4j.Slf4j;

/**
 * 提交草稿动作实现类
 *
 * @author fandongfeng
 * @date 2022-11-18 9:22
 */
@Slf4j
public class DraftHandler implements ActionHandler {

    @Override
    public void handler(DataContext context, StateMachineInstance stateMachineInstance, String userId) {
//        ArchivePermit archivePermit = new ArchivePermit();
//        archivePermit.setUserId(userId);
//        archivePermit.setStatus(ArchiveState.DRAFT);
//        log.info("[{}],permit=[{}]", this.getClass().getSimpleName(), archivePermit);
//        context.setData(ArchiveContext.DRAFT, archivePermit);
        ArchiveState archiveState = (ArchiveState) context.getData(BaseStateMachine.CURRENT_STATE);
        log.info("[{}], 将档案状态修改为 {} ", this.getClass().getSimpleName(), archiveState);
        log.info("[{}], 草稿已提交  ---- do something   ", this.getClass().getSimpleName());
        stateMachineInstance.trigger(ArchiveContext.DRAFT, context, userId);
    }
}
