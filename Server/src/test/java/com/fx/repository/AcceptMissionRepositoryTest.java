package com.fx.repository;

import com.fx.model.AcceptedMission;
import com.fx.model.Mission;
import com.fx.repository.impl.AcceptMissionRepositoryImpl;
import com.fx.repository.impl.MissionRepositoryImpl;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Description:
 * Created by Hanxinhu at 23:50 2018/6/21/021
 */
public class AcceptMissionRepositoryTest {
    MissionRepository missionRepository = new MissionRepositoryImpl();
    AcceptMissionRepository acceptMissionRepository = new AcceptMissionRepositoryImpl();
    @Test
    @Ignore
    public void createAcceptMission(){
        List<Mission> missions = missionRepository.getAllMission();

        for (int i = 0; i < 400; i++) {
            String username = "test" + (i+1);
            int number  =(int) (Math.random() * 20) + 10;
            for (int j = 0; j < number; j++) {
                int random = (int)(Math.random() * missions.size());
                int recommend = (int)(Math.random() * 4) +1;
                AcceptedMission acceptedMission =new AcceptedMission(username,missions.get(random),recommend);
                acceptedMission.setScore(2+(int)(Math.random() * 9));
                acceptMissionRepository.addAcceptMission(acceptedMission);
            }
        }
    }
}