package com.fx.repository;

import com.fx.model.Mission;
import com.fx.repository.impl.MissionRepositoryImpl;
import com.fx.util.TimeUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 23:08 2018/6/21/021
 */
public class MissionRepositoryTest {
    MissionRepository missionRepository = new MissionRepositoryImpl();
    @Ignore
    @Test
    public void createMission() {
        Mission mission = new Mission();
        int maxNumberMin = 10;
        int maxNumberMax = 20;
        String[] types = {"Attribute", "Classification", "Caption", "Detection", "Segmentation"};
        List<String> tags = new ArrayList<>();
        String [] hotTags = {"电脑","花","植物","动物","轮船","人像","美女","自动步枪","美食","风景","图书","建筑","自然景观","学生","明星","狗","猫","树山","日落","食物","汽车","飞机","兔子"};

        String missionName = "test";
        String description = "only for test";
        String requestorID;
        int sizeMax = 30;
        int sizeMin = 10;
        ArrayList<String> selects = new ArrayList<>();
        selects.add("testA");
        selects.add("testB");
        selects.add("testC");
        selects.add("testD");
        selects.add("testE");
        mission.setDescription(description);
        for (int i = 0; i < 100; i++) {
            mission.setMissionName(missionName + (i + 1));
            mission.setPoints(1 +(int) (10 * Math.random()));
            requestorID = "test" + (int) (Math.random() * 400);
            mission.setRequestorID(requestorID);
            mission.setSum(sizeMin + (int) (Math.random() * 20));
            mission.setBegin(new TimeUtil().minusDay((int) (15 * Math.random() + 1)).toString());
            mission.setEnd(new TimeUtil().addDay((int) (15 * Math.random() + 2)).toString());
            int random = (int) (Math.random() * 10 + maxNumberMin);
            mission.setMaxNumber(20);
            mission.setCurrentNumber((int) (random * Math.random()));
            random = (int) (Math.random() * 5);
            if (random == 0 || random == 1)
                mission.setSelects(selects);
            mission.setType(types[random]);
            random =(int) (Math.random() * 8);
            for (int j = 0; j < random; j++) {
                int index =(int) (Math.random() * hotTags.length);
                if(!tags.contains(hotTags[index]))
                    tags.add(hotTags[index]);
            }
            mission.setTags(tags);
            tags.clear();
            missionRepository.addMission(mission);

        }

    }

    @Ignore
    @Test
    public void copyFile(){
        String filename = "../data/";
        for (int i = 0; i < 100; i++) {
            File file = new File(filename+(i+1));
            if(!file.exists())
                file.mkdir();
        }

    }
    @Ignore
    @Test
    public void copyPNG(){
        String filename = "D:\\test2017";
        File file = new File(filename);
        File[] files = file.listFiles();
        for (int i = 0; i < 100; i++) {
            String destDir = "../data/"+(i+1)+"/";
            for (int j = 0; j < 20; j++) {
                int index =(int) (Math.random() * files.length);
                String name = files[index].getName();
                File newFile = new File(destDir+name);
                try{
                    copyFile(files[index],newFile);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    private void copyFile(File fromFile,File toFile) throws IOException {
        FileInputStream ins = new FileInputStream(fromFile);
        FileOutputStream out = new FileOutputStream(toFile);
        byte[] b = new byte[1024];
        int n=0;
        while((n=ins.read(b))!=-1){
            out.write(b, 0, n);
        }

        ins.close();
        out.close();
    }

}