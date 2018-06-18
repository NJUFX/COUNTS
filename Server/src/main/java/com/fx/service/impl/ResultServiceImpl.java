package com.fx.service.impl;

import com.fx.model.CaptionClassificationResult;
import com.fx.service.ResultService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 17:52 2018/5/29/029
 */
@Service
public class ResultServiceImpl  implements ResultService {
    public ResultServiceImpl() {
    }
   private OSSHelper ossHelper = new OSSHelper();
    private ZipHelper zipHelper = new ZipHelper();
   private static final String autoDir = "../data/autoImage/";
   private static final String autoFilename = "/label.txt";
   private static final String dir = "../data/";

    /**
     * 得到整体描述的结果
     *
     * @param missionID 任务ID
     * @return 整体描述的结果列表
     */
    @Override
    public List<CaptionClassificationResult> getCapAndClaResult(int missionID) {
        return null;
    }

    /**
     * 返回url
     *
     * @param missionID
     * @param type
     * @return
     */
    @Override
    public String getResult(int missionID, int type) {
        if (type==1)
            return getAutoResult(missionID);
        if (type==0)
            return getOrdinaryResult(missionID);
        return null;
    }

    private String getAutoResult(int missionID){
        String filename = autoDir + missionID + autoFilename;
        File file = new File(filename);
        if (!file.exists())
        return null;
        String key = "auto_"+missionID;
        String s =  ossHelper.upload(file,key);
        return s;
    }
    private String getOrdinaryResult(int missionID){
        String filename = dir + missionID;
        String zipName = filename + "/result.zip";
        zipHelper.compress(filename,filename+".zip");
        File file = new File(zipName);
        String key = "result" + missionID;

        if (!file.exists()) {
            System.out.println("file not exist");
            return null;
        }
        return ossHelper.upload(file,key);
    }
}
