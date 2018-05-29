package com.fx.service.impl;

import com.fx.bean.MissionPresentation;
import com.fx.bean.OptMessage;
import com.fx.controller.ImageController;
import com.fx.model.Mission;
import com.fx.model.User;
import com.fx.repository.MissionRepository;
import com.fx.repository.UserRepository;
import com.fx.repository.impl.MissionRepositoryImpl;
import com.fx.repository.impl.UserRepositoryImpl;
import com.fx.service.MissionService;
import com.fx.util.ResultMessage;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2018/4/10.
 */
@Service
public class MissionServiceImpl implements MissionService {
    MissionRepository missionRepository;

    public MissionServiceImpl() {
        missionRepository = new MissionRepositoryImpl();
    }

    /**
     * 获得所有任务，不区分发布者，工作者，是否结束等，其中MissionPresentation包含一个任务用于展示需要的基本信息
     *
     * @return
     */
    public List<Mission> getAllMission(int i) {
        List<Mission> missions = new ArrayList<>();

        for (int j = i; j <= i + 11; j++) {
            if (missionRepository.findMissionByID(j) != null)
                missions.add(missionRepository.findMissionByID(j));
        }
        return missions;
    }

    /**
     * 获得某个发布者的所有任务，其中MissionPresentation包含一个任务用于展示需要的基本信息
     *
     * @param userName
     * @return
     */
    public List<MissionPresentation> getMissionByWorker(String userName) {
        return null;
    }

    /**
     * 获得一个工作者的所有任务信息，其中MissionPresentation包含一个任务用于展示需要的基本信息
     *
     * @param userName
     * @return
     */
    public List<MissionPresentation> getMissionByRequestor(String userName) {
        return null;
    }

    /**
     * 获得一个用户的所有任务数，如果userID == "" 则返回所有任务,不限用户
     *
     * @param userName
     * @return
     */

    public int countWholeMissions(String userName) {
        return 0;
    }

    /**
     * 获得一个用户的已经完成所有任务数，如果userID == "" 则返回所有已经完成的任务,不限用户
     *
     * @param userName
     * @return
     */

    public int countFinishedMissions(String userName) {
        return 0;
    }

    /**
     * 获得一个用户的未完成所有任务数，如果userID == "" 则返回所有未完成的任务,不限用户
     *
     * @param userName
     * @return
     */

    public int countUnfinishedMissions(String userName) {
        return 0;
    }

    /**
     * 添加Mission
     *
     * @param mission
     * @return
     */
    public ResultMessage addMission(Mission mission) {

        String id = mission.getRequestorID();
        UserRepository userRepository = new UserRepositoryImpl();

        User requestor = userRepository.findUserByUsername(id);

        requestor.setExp(requestor.getExp()+mission.getPoints());
        requestor.setPoints(requestor.getPoints()-mission.getPoints());
        userRepository.updateUser(requestor);

        return missionRepository.addMission(mission);
    }

    /**
     * 更新Mission的信息
     *
     * @param mission
     * @return
     */
    public ResultMessage updateMission(Mission mission) {

        return missionRepository.updateMission(mission);
    }

    /**
     * 根据id删除mission
     *
     * @param id
     * @return
     */
    public ResultMessage deleteMission(int id) {

        return missionRepository.deleteMission(id);
    }

    /**
     * 根据ID查找对应的任务
     *
     * @param id
     * @return
     */
    public Mission findMissionByID(int id) {

        return missionRepository.findMissionByID(id);
    }

    /**
     * 根据起始日期以及结束日期选择
     *
     * @param start
     * @param end
     * @return
     */
    public List<Mission> findMissionByBeginAndEnd(String start, String end) {

        return missionRepository.findMissionByBeginAndEnd(start, end);
    }

    /**
     * 根据任务类型查找
     *
     * @param type
     * @return
     */
    public List<Mission> findMissionByType(String type) {
        return missionRepository.findMissionByType(type);
    }

    /**
     * 根据发布者的id(实际为username)查找发布的任务
     *
     * @param requestorID
     * @return
     */
    public List<Mission> findMissionByRequestorID(String requestorID) {
        return missionRepository.findMissionByRequestorID(requestorID);
    }

    public String getFirstImage(int missionid) {
        String url = ImageController.imageURL;
        url = url + missionid;
        File file = new File(url);
        File first;
        if (file.listFiles().length != 0) {
            first = file.listFiles()[0];
        } else {
            return null;
        }

        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(first);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        BASE64Encoder encoder = new BASE64Encoder();


        String head = "data:image/" + first.getName().split("[.]")[1] + ";base64,";

        String result = head + encoder.encode(data);
        return result;
    }
}
