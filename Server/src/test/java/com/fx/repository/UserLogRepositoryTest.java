package com.fx.repository;

import com.fx.model.UserLog;
import com.fx.repository.impl.UserLogRepositoryImpl;
import com.fx.util.TimeUtil;
import org.junit.Test;

/**
 * Description:
 * Created by Hanxinhu at 20:04 2018/6/21/021
 */
public class UserLogRepositoryTest {
    UserLogRepository userLogRepository = new UserLogRepositoryImpl();

    @Test
    public void create1() {
        UserLog userLog = new UserLog();
        for (int i = 0; i < 400; i++) {
            String username = "test" + i;
            String action = UserLog.REGISTER;
            String time = new TimeUtil().minusDay((int) (Math.random() * 30)).toString();
            userLog.setAction(action);
            userLog.setTime(time);
            userLog.setUsername(username);
            userLogRepository.addUserLog(userLog);
        }
    }

    @Test
    public void create2() {
        UserLog userLog = new UserLog();
        for (int i = 0; i < 400; i++) {
            String username = "test" + i;
            String action = UserLog.SIGN_IN;
            String time = new TimeUtil().minusDay((int) (Math.random() * 30)).toString();
            userLog.setAction(action);
            userLog.setTime(time);
            userLog.setUsername(username);
            userLogRepository.addUserLog(userLog);
        }
    }

    @Test
    public void create3() {
        UserLog userLog = new UserLog();
        for (int i = 0; i < 400; i++) {
            String username = "test" + i;
            String action = UserLog.WORK;
            String time = new TimeUtil().minusDay((int) (Math.random() * 30)).toString();
            userLog.setAction(action);
            userLog.setTime(time);
            userLog.setUsername(username);
            userLogRepository.addUserLog(userLog);
        }
    }
    @Test
    public void create4(){
        UserLog userLog = new UserLog();
        for (int i = 0; i < 400; i++) {
            String username = "test" + i;
            String action = UserLog.RELEASE;
            String time = new TimeUtil().minusDay((int) (Math.random() * 30)).toString();
            userLog.setAction(action);
            userLog.setTime(time);
            userLog.setUsername(username);
            userLogRepository.addUserLog(userLog);
        }
    }
}