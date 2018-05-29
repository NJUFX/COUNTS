package com.fx.service.impl;

import com.fx.bean.LocalLabelBean;
import com.fx.bean.OptMessage;
import com.fx.model.*;
import com.fx.repository.*;
import com.fx.repository.impl.*;
import com.fx.service.LabelService;
import com.fx.util.ResultMessage;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.List;

/**
 * Created by thinkpad on 2018/3/17.
 */

@Service
public class LabelServiceImpl implements LabelService{
    MissionRepository missionRepository = new MissionRepositoryImpl();
    LocalLabelRepository localLabelRepository = new LocalLabelRepositoryImpl();
    WholeLabelRepository wholeLabelRepository = new WholeLabelRepositoryImpl();

    AttributeLabelRepository attributeLabelRepository = new AttributeLabelRepositoryImpl();
    CaptionLabelRepository captionLabelRepository = new CaptionLabelRepositoryImpl();
    ClassificationLabelRepository classificationLabelRepository = new ClassificationLabelRepositoryImpl();
    DetectionLabelRepository detectionLabelRepository = new DetectionLabelRepositoryImpl();
    SegmentationLabelRepository segmentationLabelRepository = new SegmentationLabelRepositoryImpl();
    public static final String canvasdir = "data/canvas/";

    /**
     * 更新一张图片的整体标注
     *
     * @param userID
     * @param location
     * @param wholeLabel
     * @return 是否成功
     */
    @Override
    public ResultMessage updateWholeLabel(String userID, String location, String wholeLabel) {
        String[] str = location.split("/");
        String mission = str[0];
        String fileName = str[1];
        return wholeLabelRepository.updateWholeLabel(userID, mission, fileName, wholeLabel);
    }

    /**
     * 根据用户id 和 location查询一张图片的标注
     *
     * @param userID
     * @param location
     * @return
     */
    @Override
    public String getWholeLabelByUserIDAndLocation(String userID, String location) {
        String[] str = location.split("/");
        String mission = str[0];
        String fileName = str[1];
        return wholeLabelRepository.getWholeLabelByUserIDAndLocation(userID, mission, fileName);
    }

    /**
     * 对一张图片整体标注
     *
     * @param userID     用户ID location 图片位置 wholeLabel 图片标注 location采用 ：任务名/图片名 的格式
     * @param location
     * @param wholeLabel
     * @return 结果
     */

    @Override
    public ResultMessage addWholeLabel(String userID, String location, String wholeLabel) {
        String[] str = location.split("/");
        String mission = str[0];
        String fileName = str[1];
        return wholeLabelRepository.addWholeLabel(userID, mission, fileName, wholeLabel);

    }

    /**
     * 添加局部标注
     *
     * @param userID    用户ID location 图片位置 采用任务名/图片名 的格式
     * @param location
     * @param labelList bean列表
     * @return
     */

    @Override
    public ResultMessage LocalLabel(String userID, String location, List<LocalLabelBean> labelList) {
        String[] str = location.split("/");
        String mission = str[0];
        String fileName = str[1];
        return localLabelRepository.addLocalLabel(userID, mission, fileName, labelList);

    }



    /**
     * 保存base64格式的图片
     * @param localLabel
     * @return
     */
    public ResultMessage SaveCanvas(LocalLabel localLabel){

        String userID = localLabel.getUserName();
        //String location = localLabel.getLocation();
        String imgStr = localLabel.getUrl();
       // String overallLabel = localLabel.getOverallComment();
       // List<String> otherLabel = localLabel.getOtherComments();


        String picturename = localLabel.getFileName();
       // System.out.println(picturename+"！！！");
        String p = canvasdir+userID+"/"+localLabel.getMissionID()+"/"+picturename.split("[.]")[0];
        //System.out.println(p);
      //  String t = canvasdir+userID+"/"+localLabel.getMissionID()+"/"+picturename+"/label";


        //  String p = canvasdir+userID+"/"+location.split("/")[0];
        // String t = canvasdir+userID+"/"+location.split("/")[0];
       // System.out.println(p);
       // System.out.println(t);
        File root = new File(p);
       // File txtroot = new File(t);
        if(!root.exists()){
            root.mkdirs();
           // System.out.println("raw create");
        }

        /*
        if(!txtroot.exists()){
            txtroot.mkdirs();
            //System.out.println("txt create");
        }
        */


        String type = missionRepository.findMissionByID(Integer.parseInt(localLabel.getMissionID())).getType();

        System.out.println("type!!!!"+type);
        if(type.equals("Detection")){
            DetectionLabel detectionLabel = new DetectionLabel(picturename,localLabel.getDots());
            detectionLabelRepository.addDetectionLabel(Integer.parseInt(localLabel.getMissionID()),localLabel.getUserName(),detectionLabel);
        }else{
            SegmentationLabel segmentationLabel = new SegmentationLabel(picturename,localLabel.getDots());
            segmentationLabelRepository.addSegmentationLabel(Integer.parseInt(localLabel.getMissionID()),localLabel.getUserName(),segmentationLabel);
        }





        /*
        将String写入文件的方法 Code:
         */

        /*
        String overallFileName = t + "/overall.txt";
        String otherFileName = t + "/other.txt";
        try {
        */
         /*   File overallFile = new File(overallFileName);
            File otherFile = new File(otherFileName);
            overallFile.createNewFile();
            otherFile.createNewFile();
          */
         /*
            PrintWriter pw1 = new PrintWriter(overallFileName);
            pw1.println(overallLabel);
            pw1.close();

          PrintWriter  pw2 = new PrintWriter(otherFileName);
            for (int i = 0; i < otherLabel.size(); i++) {
                pw2.println(otherLabel.get(i));
            }
            pw2.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        */





/*
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = null;
        try{
            bytes = decoder.decodeBuffer(imgStr.substring(22));
        }catch (IOException e){
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        */
/*
        // 处理数据
for (int i = 0; i < bytes.length; ++i) {
if (bytes[i] < 0) {
bytes[i] += 256;
}
}
*/
        //没有判断文件夹是否存在，不知道可以不可以
       try {
           PrintWriter pw = new PrintWriter(p + "/img.txt");
            pw.println(imgStr);
            pw.close();
       }catch (Exception e){
           e.printStackTrace();
       }

             return ResultMessage.SUCCESS;
        }


    /**
     * 添加属性标注
     * @param userName
     * @param attributeLabel
     * @return
     */
    public ResultMessage addAttributeLabel(int missionID,String userName, AttributeLabel attributeLabel){

        addOne(missionID,userName,attributeLabel.getFileName());
        return attributeLabelRepository.addAttributeLabel(missionID,userName,attributeLabel);
    }

    /**
     * 添加整体标注
     * @param userName
     * @param captionLabel
     * @return
     */
    public ResultMessage addCaptionLabel(int missionID,String userName,  CaptionLabel captionLabel){
        addOne(missionID,userName,captionLabel.getFileName());
        return captionLabelRepository.addCaptionLabel(missionID, userName, captionLabel);
    }

    /**
     * 添加分类标注
     * @param userName
     * @param classificationLabel
     * @return
     */
    public ResultMessage addClassificationLabel(int missionID,String userName,  ClassificationLabel classificationLabel){
        addOne(missionID,userName,classificationLabel.getFileName());
        return classificationLabelRepository.addClassificationLabel(missionID, userName, classificationLabel);
    }

    /**
     * 添加矩形标注
     * @param userName
     * @param detectionLabel
     * @return
     */
    public ResultMessage addDetectionLabel(int missionID,String userName,  DetectionLabel detectionLabel){
        addOne(missionID,userName,detectionLabel.getFileName());
        return detectionLabelRepository.addDetectionLabel(missionID, userName, detectionLabel);
    }

    /**
     * 添加描边轮廓标注
     * @param userName
     * @param segmentationLabel
     * @return
     */
    public ResultMessage addSegmentationLabel(int missionID,String userName,SegmentationLabel segmentationLabel){
        addOne(missionID,userName,segmentationLabel.getFileName());
        return segmentationLabelRepository.addSegmentationLabel(missionID, userName, segmentationLabel);
    }

    /**
     * 查找属性标注
     * @param missionID
     * @param userName
     * @return
     */
    public List<AttributeLabel> getAttributeLabel(int missionID,String userName){
        return attributeLabelRepository.findAttributeLabelByMissionIDAndUsername(missionID,userName);
    }

    /**
     * 添加整体标注
     * @param userName
     * @param missionID
     * @return
     */
    public List<CaptionLabel> getCaptionLabel(int missionID,String userName){
        return captionLabelRepository.findCaptionLabelByMissionIDAndUsername(missionID,userName);
    }

    /**
     * 添加分类标注
     * @param userName
     * @param missionID
     * @return
     */
    public List<ClassificationLabel> getClassificationLabel(int missionID,String userName){
        return classificationLabelRepository.findClassificationLabelByMissionIDAndUsername(missionID, userName);
    }


    /**
     * 添加矩形标注
     * @param userName
     * @param missionID
     * @return
     */
    public List<DetectionLabel> getDetectionLabel(int missionID,String userName){
        return detectionLabelRepository.findDetectionLabelByMissionIDAndUsername(missionID, userName);
    }

    /**
     * 添加描边轮廓标注
     * @param userName
     * @param missionID
     * @return
     */
    public List<SegmentationLabel> getSegmentationLabel(int missionID,String userName){
        return segmentationLabelRepository.findSegmentationLabelByMissionIDAndUsername(missionID, userName);
    }
    
    public ResultMessage addOne(int missionID,String userName,String FileName){
        
        UserRepository userRepository = new UserRepositoryImpl();
        
        User user = userRepository.findUserByUsername(userName);
    Mission mission =    missionRepository.findMissionByID(missionID);
    switch (mission.getType()){
        case "Detection":
           List<DetectionLabel> detectionLabels =  detectionLabelRepository.findDetectionLabelByMissionIDAndUsername(missionID,userName);
           for(int i=0;i<=detectionLabels.size()-1;i++){
               if(detectionLabels.get(i).getFileName().equals(FileName)){
                   if(detectionLabels.get(i).getRectangles()==null){
                       user.addAcceptedMission(missionID);
                   }
               }
           }
        case "Attribute":
            List<AttributeLabel> attributeLabels =  attributeLabelRepository.findAttributeLabelByMissionIDAndUsername(missionID,userName);
            for(int i=0;i<=attributeLabels.size()-1;i++){
                if(attributeLabels.get(i).getFileName().equals(FileName)){
                    if(attributeLabels.get(i).getAttributes()==null){
                        user.addAcceptedMission(missionID);
                    }else {
                        if (attributeLabels.get(i).getAttributes().get(0).equals(""))
                            user.addAcceptedMission(missionID);
                    }
                }
            }
        case "Classification":
            List<ClassificationLabel> classificationLabels =  classificationLabelRepository.findClassificationLabelByMissionIDAndUsername(missionID,userName);
            for(int i=0;i<=classificationLabels.size()-1;i++){
                if(classificationLabels.get(i).getFileName().equals(FileName)){
                    if(classificationLabels.get(i).getSelect()==0){
                        user.addAcceptedMission(missionID);
                    }
                }
            }

        case "Caption":
            List<CaptionLabel> captionLabels =  captionLabelRepository.findCaptionLabelByMissionIDAndUsername(missionID,userName);
            for(int i=0;i<=captionLabels.size()-1;i++){
                if(captionLabels.get(i).getFileName().equals(FileName)){
                    if(captionLabels.get(i).getCaption()==null){
                        user.addAcceptedMission(missionID);
                    }else {
                        if (captionLabels.get(i).getCaption().equals(""))
                            user.addAcceptedMission(missionID);
                    }
                }
            }
        case "Segmentation":
            List<SegmentationLabel> segmentationLabels =  segmentationLabelRepository.findSegmentationLabelByMissionIDAndUsername(missionID,userName);
            for(int i=0;i<=segmentationLabels.size()-1;i++){
                if(segmentationLabels.get(i).getFileName().equals(FileName)){
                    if(segmentationLabels.get(i).getDots()==null){

                        user.addAcceptedMission(missionID);
                    }else {
                        if (segmentationLabels.get(i).getDots().get(0).equals(""))
                            user.addAcceptedMission(missionID);
                    }
                }
            }

    }


    
    return userRepository.updateUser(user);
    }

}
