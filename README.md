# freeStateMachine
- <h3>自由状态机

档案分为 草稿、核验、签署、审批、完成 五个流程， 开始步骤为草稿  
核验、签署、审批可以自由组装

``` java
[com.fandf.freestatemachine.demo.Main]-发起档案流程 草稿 --> 核验 --> 签署 --> 审批 --> 完成
[com.fandf.freestatemachine.demo.Main]-草稿--》 核验
[com.fandf.freestatemachine.core.StateMachineInstance]-状态机运行 当前状态=DRAFT, 行为=VERIFY, 动作=VerifyHandler, 下一个状态=VERIFY
[com.fandf.freestatemachine.demo.handler.VerifyHandler]-[VerifyHandler], 将档案状态修改为 VERIFY
[com.fandf.freestatemachine.demo.handler.VerifyHandler]-[VerifyHandler], 开启核验，等待核验审批结果  ---- do something   
[com.fandf.freestatemachine.demo.handler.VerifyHandler]-[VerifyHandler], 开启核验，等待核验审批结果  ---- 通知用户赶紧来核验   
[com.fandf.freestatemachine.demo.Main]-核验--》 核验通过 ---》 签署
[com.fandf.freestatemachine.core.StateMachineInstance]-状态机运行 当前状态=VERIFY, 行为=VERIFY_PASS, 动作=VerifyPassHandler, 下一个状态=SIGN
[com.fandf.freestatemachine.demo.handler.VerifyPassHandler]-[VerifyPassHandler], 将档案状态修改为 SIGN
[com.fandf.freestatemachine.demo.handler.VerifyPassHandler]-[VerifyPassHandler], 核验通过实现业务逻辑  ---- do something
[com.fandf.freestatemachine.demo.Main]-签署--》 签署通过--》 审批
[com.fandf.freestatemachine.core.StateMachineInstance]-状态机运行 当前状态=SIGN, 行为=SIGN_PASS, 动作=SignPassHandler, 下一个状态=APPROVE
[com.fandf.freestatemachine.demo.handler.SignPassHandler]-[SignPassHandler], 将档案状态修改为 APPROVE
[com.fandf.freestatemachine.demo.handler.SignPassHandler]-[SignPassHandler], 签署通过实现业务逻辑  ---- do something
[com.fandf.freestatemachine.demo.Main]-审批--》 审批通过--》 完成
[com.fandf.freestatemachine.core.StateMachineInstance]-状态机运行 当前状态=APPROVE, 行为=APPROVE_PASS, 动作=ApprovePassHandler, 下一个状态=FINISH
[com.fandf.freestatemachine.demo.handler.ApprovePassHandler]-[ApprovePassHandler], 将档案状态修改为 FINISH
[com.fandf.freestatemachine.demo.handler.ApprovePassHandler]-[ApprovePassHandler], 审批通过实现业务逻辑  ---- do something
[com.fandf.freestatemachine.demo.Main]-------------------------------------------------------------------------------------------------
[com.fandf.freestatemachine.demo.Main]-发起档案流程 草稿 -->  签署 --> 审批 --> 核验 --> 完成
[com.fandf.freestatemachine.demo.Main]-草稿--》 签署
[com.fandf.freestatemachine.core.StateMachineInstance]-状态机运行 当前状态=DRAFT, 行为=SIGN, 动作=VerifyHandler, 下一个状态=SIGN
[com.fandf.freestatemachine.demo.handler.VerifyHandler]-[VerifyHandler], 将档案状态修改为 SIGN
[com.fandf.freestatemachine.demo.handler.VerifyHandler]-[VerifyHandler], 开启核验，等待核验审批结果  ---- do something   
[com.fandf.freestatemachine.demo.handler.VerifyHandler]-[VerifyHandler], 开启核验，等待核验审批结果  ---- 通知用户赶紧来核验   
[com.fandf.freestatemachine.demo.Main]-签署--》 签署通过--》 审批
[com.fandf.freestatemachine.core.StateMachineInstance]-状态机运行 当前状态=SIGN, 行为=SIGN_PASS, 动作=SignPassHandler, 下一个状态=APPROVE
[com.fandf.freestatemachine.demo.handler.SignPassHandler]-[SignPassHandler], 将档案状态修改为 APPROVE
[com.fandf.freestatemachine.demo.handler.SignPassHandler]-[SignPassHandler], 签署通过实现业务逻辑  ---- do something
[com.fandf.freestatemachine.demo.Main]-审批--》 审批通过--》 核验
[com.fandf.freestatemachine.core.StateMachineInstance]-状态机运行 当前状态=APPROVE, 行为=APPROVE_PASS, 动作=ApprovePassHandler, 下一个状态=VERIFY
[com.fandf.freestatemachine.demo.handler.ApprovePassHandler]-[ApprovePassHandler], 将档案状态修改为 VERIFY
[com.fandf.freestatemachine.demo.handler.ApprovePassHandler]-[ApprovePassHandler], 审批通过实现业务逻辑  ---- do something
[com.fandf.freestatemachine.demo.Main]-核验--》 核验通过 ---》 完成
[com.fandf.freestatemachine.core.StateMachineInstance]-状态机运行 当前状态=VERIFY, 行为=VERIFY_PASS, 动作=VerifyPassHandler, 下一个状态=FINISH
[com.fandf.freestatemachine.demo.handler.VerifyPassHandler]-[VerifyPassHandler], 将档案状态修改为 FINISH
[com.fandf.freestatemachine.demo.handler.VerifyPassHandler]-[VerifyPassHandler], 核验通过实现业务逻辑  ---- do something
```
