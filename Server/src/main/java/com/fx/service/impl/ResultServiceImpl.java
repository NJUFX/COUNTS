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
   private static final String autoDir = "../data/autoImage/";
   private static final String autoFilename = "/label.txt";


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
        if (type==0)
            return getAutoResult(missionID);
        if (type==1)
            return null;
        return null;
    }

    private String getAutoResult(int missionID){
        String filename = autoDir + missionID + autoFilename;
        File file = new File(filename);
        if (!file.exists())
        return null;
        String key = "auto_"+missionID;
        ossHelper.upload(file,key);

        return ossHelper.getUrl(key);
    }
    private String getOrdinaryResult(int missionID){
        return null;
    }
}
