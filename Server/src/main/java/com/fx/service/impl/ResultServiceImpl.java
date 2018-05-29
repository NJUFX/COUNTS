package com.fx.service.impl;

import com.fx.bean.CaptionResult;
import com.fx.bean.ClassificationResult;
import com.fx.service.ResultService;

import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 17:52 2018/5/29/029
 */
public class ResultServiceImpl  implements ResultService {
    /**
     * 得到整体描述的结果
     *
     * @param missionID 任务ID
     * @return 整体描述的结果列表
     */
    @Override
    public List<CaptionResult> getCaptionResult(int missionID) {
        return null;
    }

    /**
     * 得到分类标注的结果
     *
     * @param missionID
     */
    @Override
    public List<ClassificationResult> getClassificationResult(int missionID) {
        return null;
    }
}
