package com.fx.repository;

import com.fx.model.AutoClassificationLabel;
import com.fx.util.ResultMessage;

import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 19:29 2018/6/12/012
 */
public interface AutoClassificationLabelRepository {

    public ResultMessage addAutoCaptionLabel(int autoMissionID, AutoClassificationLabel autoClassificationLabel);

    public ResultMessage updateAutoCaptionLabel(int autoMissionID, AutoClassificationLabel autoClassificationLabel);

    public List<AutoClassificationLabel> findAutoCaptionLabel(int autoMissionID);
}
