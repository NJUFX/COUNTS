package com.fx.service;

import com.fx.bean.CaptionResult;
import com.fx.bean.ClassificationResult;

import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 16:48 2018/5/29/029
 */
public interface ResultService {
    /**
     * 得到整体描述的结果
     * @param missionID 任务ID
     * @return 整体描述的结果列表
     */
    public List<CaptionResult> getCaptionResult(int missionID);

    /**
     * 得到分类标注的结果
     */
    public List<ClassificationResult> getClassificationResult(int missionID);

}
