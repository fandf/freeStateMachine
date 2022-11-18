package com.fandf.freestatemachine.core;

import java.util.List;

/**
 * 用户接口
 *
 * @author fandongfeng
 * @date 2022-11-18 9:38
 */
public interface UserInterface {

    /**
     * 获取可执行用户
     *
     * @return List<BasicUser>
     */
    List<BasicUser> listUser();

}
