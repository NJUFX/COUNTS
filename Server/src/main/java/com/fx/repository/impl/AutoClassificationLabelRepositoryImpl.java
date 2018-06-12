package com.fx.repository.impl;

import com.fx.model.AutoClassificationLabel;
import com.fx.repository.AutoCaptionLabelRepository;
import com.fx.repository.AutoClassificationLabelRepository;
import com.fx.util.ResultMessage;

import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 19:49 2018/6/12/012
 */
public class AutoClassificationLabelRepositoryImpl implements AutoClassificationLabelRepository {
    @Override
    public ResultMessage addAutoClassificationLabel(int autoMissionID, AutoClassificationLabel autoClassificationLabel) {
        return null;
    }

    @Override
    public ResultMessage updateAutoClassificationLabel(int autoMissionID, AutoClassificationLabel autoClassificationLabel) {
        return null;
    }

    @Override
    public List<AutoClassificationLabel> findAutoClassificationLabel(int autoMissionID) {
        return null;
    }
}
