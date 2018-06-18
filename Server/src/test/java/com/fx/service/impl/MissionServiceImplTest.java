package com.fx.service.impl;

import com.fx.model.Mission;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Description:
 * Created by Hanxinhu at 9:19 2018/6/14/014
 */
public class MissionServiceImplTest {
    MissionServiceImpl missionService = new MissionServiceImpl();
    @Test
    public void mkdirsForAutoCaption() {
        missionService.mkdirsForAutoCaption(1);
    }

    @Test
    public void mkdirsForAutoDetection() {
        missionService.mkdirsForAutoDetection(2);

    }

    @Test
    public void mkdirsForAutoClassification() {
        List<String> tags = new ArrayList<>();
        tags.add("strawberry");
        missionService.mkdirsForAutoClassification(3,tags);
    }
    @Ignore
    @Test
    public void testUnfinished(){
        List<Mission> missions = missionService.findUnfinishedMission();
        System.out.println(missions.size());
    }
}