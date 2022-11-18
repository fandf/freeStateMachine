package com.fandf.freestatemachine.demo;

import com.fandf.freestatemachine.core.*;
import com.fandf.freestatemachine.demo.handler.*;
import com.fandf.freestatemachine.demo.model.ArchiveBehavior;
import com.fandf.freestatemachine.demo.model.ArchiveContext;
import com.fandf.freestatemachine.demo.model.ArchivePermit;
import com.fandf.freestatemachine.demo.model.ArchiveState;
import com.fandf.freestatemachine.demo.user.UserInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author fandongfeng
 * @date 2022-11-17 20:24
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        //初始化实例
        init();
        UserInfo userInfo = UserInfo.of("123", "张三");

        log.info("发起档案流程 草稿 --> 核验 --> 签署 --> 审批 --> 完成");
        log.info("草稿--》 核验");
        DataContext dataContext = new DataContext();
        dataContext.setData(ArchiveContext.DRAFT, ArchivePermit.of(userInfo.getUserId(), ArchiveState.DRAFT));
        dataContext.setData(BaseStateMachine.CURRENT_STATE, ArchiveState.DRAFT);
        StateMachineFactory.getInstance("ARCHIVE_DEMO").trigger(ArchiveBehavior.VERIFY, dataContext, userInfo.getUserId());

        log.info("核验--》 核验通过 ---》 签署");
        dataContext.setData(BaseStateMachine.CURRENT_STATE, ArchiveState.VERIFY);
        dataContext.setData(ArchiveContext.VERYING, ArchivePermit.of(userInfo.getUserId(), ArchiveState.VERIFY_PASS));
        StateMachineFactory.getInstance("ARCHIVE_DEMO").trigger(ArchiveBehavior.VERIFY_PASS, dataContext, userInfo.getUserId());

        log.info("签署--》 签署通过--》 审批");
        dataContext.setData(BaseStateMachine.CURRENT_STATE, ArchiveState.SIGN);
        dataContext.setData(ArchiveContext.SIGNING, ArchivePermit.of(userInfo.getUserId(), ArchiveState.SIGN_PASS));
        StateMachineFactory.getInstance("ARCHIVE_DEMO").trigger(ArchiveBehavior.SIGN_PASS, dataContext, userInfo.getUserId());

        log.info("审批--》 审批通过--》 完成");
        dataContext.setData(BaseStateMachine.CURRENT_STATE, ArchiveState.APPROVE);
        dataContext.setData(ArchiveContext.APPROVEING, ArchivePermit.of(userInfo.getUserId(), ArchiveState.APPROVE_PASS));
        StateMachineFactory.getInstance("ARCHIVE_DEMO").trigger(ArchiveBehavior.APPROVE_PASS, dataContext, userInfo.getUserId());

        log.info("------------------------------------------------------------------------------------------------");

        log.info("发起档案流程 草稿 -->  签署 --> 审批 --> 核验 --> 完成");
        log.info("草稿--》 签署");
        dataContext = new DataContext();
        dataContext.setData(ArchiveContext.DRAFT, ArchivePermit.of(userInfo.getUserId(), ArchiveState.DRAFT));
        dataContext.setData(BaseStateMachine.CURRENT_STATE, ArchiveState.DRAFT);
        StateMachineFactory.getInstance("ARCHIVE_DEMO2").trigger(ArchiveBehavior.SIGN, dataContext, userInfo.getUserId());

        log.info("签署--》 签署通过--》 审批");
        dataContext.setData(BaseStateMachine.CURRENT_STATE, ArchiveState.SIGN);
        dataContext.setData(ArchiveContext.SIGNING, ArchivePermit.of(userInfo.getUserId(), ArchiveState.SIGN_PASS));
        StateMachineFactory.getInstance("ARCHIVE_DEMO2").trigger(ArchiveBehavior.SIGN_PASS, dataContext, userInfo.getUserId());

        log.info("审批--》 审批通过--》 核验");
        dataContext.setData(BaseStateMachine.CURRENT_STATE, ArchiveState.APPROVE);
        dataContext.setData(ArchiveContext.APPROVEING, ArchivePermit.of(userInfo.getUserId(), ArchiveState.APPROVE_PASS));
        StateMachineFactory.getInstance("ARCHIVE_DEMO2").trigger(ArchiveBehavior.APPROVE_PASS, dataContext, userInfo.getUserId());

        log.info("核验--》 核验通过 ---》 完成");
        dataContext.setData(BaseStateMachine.CURRENT_STATE, ArchiveState.VERIFY);
        dataContext.setData(ArchiveContext.VERYING, ArchivePermit.of(userInfo.getUserId(), ArchiveState.VERIFY_PASS));
        StateMachineFactory.getInstance("ARCHIVE_DEMO2").trigger(ArchiveBehavior.VERIFY_PASS, dataContext, userInfo.getUserId());


    }

    public static void init() {
        StateMachineFactory.register("ARCHIVE_DEMO", buildArchiveStateMachine());
        StateMachineFactory.register("ARCHIVE_DEMO2", buildArchiveStateMachine2());
    }

    private static StateMachineInstance buildArchiveStateMachine() {
        StateMachineConfig<ArchiveState, ArchiveBehavior, ActionHandler, UserInterface> stateMachineConfig = new StateMachineConfig<>();
        //初始状态，草稿 --> 核验 --> 签署 --> 审批 --> 完成

        stateMachineConfig.from(ArchiveState.DRAFT)
                .behavior(ArchiveBehavior.VERIFY)
                .action(new VerifyHandler())
                .user(UserInfo.of("123", "张三"))
                .to(ArchiveState.VERIFY)
                .build();

        stateMachineConfig.from(ArchiveState.VERIFY)
                .behavior(ArchiveBehavior.VERIFY_PASS)
                .action(new VerifyPassHandler())
                .user(UserInfo.of("123", "张三"))
                .to(ArchiveState.SIGN)
                .build();


        stateMachineConfig.from(ArchiveState.SIGN)
                .behavior(ArchiveBehavior.SIGN_PASS)
                .action(new SignPassHandler())
                .user(UserInfo.of("123", "张三"))
                .to(ArchiveState.APPROVE)
                .build();

        stateMachineConfig.from(ArchiveState.APPROVE)
                .behavior(ArchiveBehavior.APPROVE_PASS)
                .action(new ApprovePassHandler())
                .user(UserInfo.of("123", "张三"))
                .to(ArchiveState.FINISH)
                .build();

        return new StateMachineInstance<>(stateMachineConfig);
    }

    private static StateMachineInstance buildArchiveStateMachine2() {
        StateMachineConfig<ArchiveState, ArchiveBehavior, ActionHandler, UserInterface> stateMachineConfig = new StateMachineConfig<>();
        //初始状态，草稿 -->  签署 --> 审批 --> 核验 --> 完成

        stateMachineConfig.from(ArchiveState.DRAFT)
                .behavior(ArchiveBehavior.SIGN)
                .action(new VerifyHandler())
                .user(UserInfo.of("123", "张三"))
                .to(ArchiveState.SIGN)
                .build();

        stateMachineConfig.from(ArchiveState.SIGN)
                .behavior(ArchiveBehavior.SIGN_PASS)
                .action(new SignPassHandler())
                .user(UserInfo.of("123", "张三"))
                .to(ArchiveState.APPROVE)
                .build();

        stateMachineConfig.from(ArchiveState.APPROVE)
                .behavior(ArchiveBehavior.APPROVE_PASS)
                .action(new ApprovePassHandler())
                .user(UserInfo.of("123", "张三"))
                .to(ArchiveState.VERIFY)
                .build();

        stateMachineConfig.from(ArchiveState.VERIFY)
                .behavior(ArchiveBehavior.VERIFY_PASS)
                .action(new VerifyPassHandler())
                .user(UserInfo.of("123", "张三"))
                .to(ArchiveState.FINISH)
                .build();

        return new StateMachineInstance<>(stateMachineConfig);
    }

}
