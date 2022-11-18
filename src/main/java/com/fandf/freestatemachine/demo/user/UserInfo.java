package com.fandf.freestatemachine.demo.user;

import com.fandf.freestatemachine.core.BasicUser;
import com.fandf.freestatemachine.core.UserInterface;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author fandongfeng
 * @date 2022-11-18 9:47
 */
@Data
@AllArgsConstructor(staticName = "of")
public class UserInfo implements UserInterface {

    private String userId;
    private String userName;

    @Override
    public List<BasicUser> listUser() {
        return Arrays.asList(BasicUser.of(userId));
    }
}
