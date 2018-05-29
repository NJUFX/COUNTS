package com.fx.service.impl;

import com.fx.bean.Image;
import com.fx.bean.MissionCompletion;
import com.fx.controller.ImageController;
import com.fx.model.LocalLabel;
import com.fx.service.ImageService;
import com.fx.util.DataConst;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2018/3/17.
 */
@Service
public class ImageServiceImpl implements ImageService{
    /**
     * 根据任务名称返回对应图片集
     * @param mission
     * @return 图片集
     */

    public List<String> getMission(String mission){
        File file = new File(ImageController.imageURL +"/"+ mission);
        System.out.println(file.getAbsolutePath());
        if (!file.exists())
            return null;

        File [] files = file.listFiles();
        /*
       try{
          MultipartFile [] multipartFile = new MultipartFile[files.length];
           for (int i = 0; i < files.length; i++) {
               FileInputStream inputStream = new FileInputStream(files[i]);
               multipartFile[i] = new MockMultipartFile(files[i].getName(),inputStream);
           }
            return multipartFile;
       }catch (Exception e){
            e.printStackTrace();
       }
        return null;
        */
        List<String> lists = new ArrayList<>();

        for(int i=0;i<=files.length-1;i++) {
            InputStream in = null;
            byte[] data = null;
            try {
                in = new FileInputStream(files[i]);
                data = new byte[in.available()];
                in.read(data);
                in.close();
                LocalLabel localLabel = new LocalLabel();
                BASE64Encoder encoder = new BASE64Encoder();
                String url = encoder.encode(data);
                String head = "data:image/" + files[i].getName().split("[.]")[1] + ";base64,";

               // System.out.println(head+url);
                lists.add(head+url);
                /*
                localLabel.setUrl(head + url);
                localLabel.setOtherComments(null);
                localLabel.setOverallComment(null);
                localLabel.setLocation(missionname + "/" + imgname);
                */


            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return lists;
    }



    /**
     * 上传文件的方法，前端无需使用
     * @param file：文件的字节
     * @param imgPath：文件的路径
     * @param imgName：文件的名字
     * @throws Exception
     */
    public void uploadFileUtil(byte[] file, String imgPath, String imgName) throws Exception {
        File targetFile = new File(imgPath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(imgPath + imgName);
        out.write(file);
        out.flush();
        out.close();
    }

    @Override
    public List<Image> getOriginPicture(String missionid) {
        List<Image> result = new ArrayList<>();

        File file = new File(ImageController.imageURL +"/"+ missionid);
        System.out.println(file.getAbsolutePath());
        if (!file.exists())
            return null;

        File [] files = file.listFiles();
        /*
       try{
          MultipartFile [] multipartFile = new MultipartFile[files.length];
           for (int i = 0; i < files.length; i++) {
               FileInputStream inputStream = new FileInputStream(files[i]);
               multipartFile[i] = new MockMultipartFile(files[i].getName(),inputStream);
           }
            return multipartFile;
       }catch (Exception e){
            e.printStackTrace();
       }
        return null;
        */
       // List<String> lists = new ArrayList<>();

        for(int i=0;i<=files.length-1;i++) {
            InputStream in = null;
            byte[] data = null;
            try {
                in = new FileInputStream(files[i]);
                data = new byte[in.available()];
                in.read(data);
                in.close();
                LocalLabel localLabel = new LocalLabel();
                BASE64Encoder encoder = new BASE64Encoder();
                String url = encoder.encode(data);
                String head = "data:image/" + files[i].getName().split("[.]")[1] + ";base64,";

                // System.out.println(head+url);
                result.add(new Image(files[i].getName(),head+url));
                /*
                localLabel.setUrl(head + url);
                localLabel.setOtherComments(null);
                localLabel.setOverallComment(null);
                localLabel.setLocation(missionname + "/" + imgname);
                */


            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return result;
    }

    /**************下面为迭代2新增方法**********/

    /**
     * 判断一个用户的已经进行的任务的准确率
     *
     * @param missionname
     * @param userId
     * @return 负数表示没有该用户没有接这个任务，0.0<= n && n>=1.0表示准确率的小数表示
     */
    public double judgeAccuracyRate(String missionname,String userId){
        return 0.0;
    }


    /**
     * 返回用户所接的任务以及完成度
     *
     * @param userid
     * @return
     */
    public List<MissionCompletion> degreeOfCompletion(String userid){
        return null;
    }

}
