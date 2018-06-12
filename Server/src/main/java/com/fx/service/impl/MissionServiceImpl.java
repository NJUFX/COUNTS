package com.fx.service.impl;

import com.fx.bean.MissionPresentation;
import com.fx.controller.ImageController;
import com.fx.model.AcceptedMission;
import com.fx.model.AutoMission;
import com.fx.model.Mission;
import com.fx.model.User;
import com.fx.repository.AcceptMissionRepository;
import com.fx.repository.AutoMissionRepository;
import com.fx.repository.MissionRepository;
import com.fx.repository.UserRepository;
import com.fx.repository.impl.AcceptMissionRepositoryImpl;
import com.fx.repository.impl.AutoMissionRepositoryImpl;
import com.fx.repository.impl.MissionRepositoryImpl;
import com.fx.repository.impl.UserRepositoryImpl;
import com.fx.service.MissionService;
import com.fx.util.ResultMessage;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2018/4/10.
 */
@Service
public class MissionServiceImpl implements MissionService {
    MissionRepository missionRepository;
    AutoMissionRepository autoMissionRepository;
    AcceptMissionRepository acceptMissionRepository;

    @Override
    public ResultMessage addAcceptedMission(String username, int id, int recommendType) {
        Mission mission = findMissionByID(id);
        if (mission.getCurrentNumber() > mission.getMaxNumber())
            return ResultMessage.FALSE;
        AcceptedMission acceptedMission = new AcceptedMission(username, mission, recommendType);

        ResultMessage message = acceptMissionRepository.addAcceptMission(acceptedMission);
        if (message == ResultMessage.SUCCESS) {
            mission.setCurrentNumber(mission.getCurrentNumber() + 1);
            missionRepository.updateMission(mission);
            return ResultMessage.SUCCESS;
        } else
            return message;
    }


    @Override
    public ResultMessage updateAcceptedMission(AcceptedMission acceptedMission) {
        return acceptMissionRepository.updateAcceptMission(acceptedMission);
    }

    @Override
    public List<AcceptedMission> findAcceptedMissionByUsername(String username) {
        return acceptMissionRepository.findAcceptMissionByUsername(username);
    }

    public MissionServiceImpl() {
        missionRepository = new MissionRepositoryImpl();
        autoMissionRepository = new AutoMissionRepositoryImpl();
        acceptMissionRepository = new AcceptMissionRepositoryImpl();
    }

    /**
     * 获得所有任务，不区分发布者，工作者，是否结束等，其中MissionPresentation包含一个任务用于展示需要的基本信息
     *
     * @return
     */
    public List<Mission> getAllMission(int i) {
        List<Mission> missions = new ArrayList<>();
        List<Mission> results = missionRepository.getAllMission();
        for (int j = i; j <= i + 11 && j < results.size(); j++) {
            missions.add(results.get(j));
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

        requestor.setExp(requestor.getExp() + mission.getPoints());
        requestor.setPoints(requestor.getPoints() - mission.getPoints());
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

    /**
     * 根据发布者的用户名查询 所有的自动化标注
     *
     * @param username
     * @return
     */
    @Override
    public List<AutoMission> getAutoMissionByRequestorID(String username) {
        return autoMissionRepository.findAutoMissionByRequestorID(username);
    }

    @Override
    public ResultMessage addAutoMission(AutoMission autoMission) {
        ResultMessage message = autoMissionRepository.addAutoMission(autoMission);
        int id = autoMission.getId();
        switch (autoMission.getType()) {
            case "Classification":
                mkdirsForAutoClassification(id,autoMission.getTypes());
                break;
            case "Caption":
                mkdirsForAutoCaption(id);

                break;
            case "Detection":
                mkdirsForAutoDetection(id);
                break;
        }

        return message;

    }

    @Override
    public AutoMission findAutoMissionByID(int id) {
        return autoMissionRepository.findAutoMissionByID(id);
    }

    private void mkdirsForAutoCaption(int id) {
        String dirname = "../data/autoImage/" + id;
        File dir = new File(dirname);
        if (!dir.exists())
            dir.mkdir();

    }

    private void mkdirsForAutoDetection(int id) {
        String dirname = "../data/autoImage/" + id;
        File dir = new File(dirname);
        if (!dir.exists())
            dir.mkdir();
        File data = new File(dirname + "/data");
        data.mkdir();
        File images = new File(dirname + "/images");
        images.mkdir();
        File imagesTest = new File(dirname + "/images/test");
        imagesTest.mkdir();
        File imagesTrain = new File(dirname + "/images/train");
        imagesTrain.mkdir();
        File training = new File(dirname + "training");
        training.mkdir();
    }


    @Override
    public AcceptedMission findAcceptedMissionByUsernameAndMissionID(String username, int missionID) {
       List<AcceptedMission> missions = acceptMissionRepository.findAcceptMissionByUsername(username);
        for (int i = 0; i < missions.size(); i++) {
            AcceptedMission acceptedMission = missions.get(i);
            if (acceptedMission.getId() == missionID)
                return acceptedMission;
        }
       return null;
    }

    private void mkdirsForAutoClassification(int id, List<String> types) {
        String dirname = "../data/autoImage/" + id;
        File dir = new File(dirname);
        if (!dir.exists())
            dir.mkdir();
        File labels = new File(dirname + "/labels.txt");
        try {
            if (!labels.exists())
                labels.createNewFile();
            PrintWriter pw = new PrintWriter(labels);
            for (int i = 0; i < types.size(); i++) {
                pw.println(types.get(i));
            }
            pw.close();
            String images = dirname + "/images";
            File imagesDir = new File(images);
            if (!imagesDir.exists())
                imagesDir.mkdir();
            String trainImage = images + "/trainimage";
            File trainImageDir = new File(trainImage);
            if (!trainImageDir.exists())
                trainImageDir.mkdir();
            File labels_dir = new File(dirname + "image_labels_dir");
            if (!labels_dir.exists())
                labels_dir.mkdir();
            File autoMission = new File(dirname + "/autoMission.txt");
            if (!autoMission.exists())
                autoMission.createNewFile();
            File allimages = new File(dirname + "/allimage");
            if (!allimages.exists())
                allimages.mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
