package com.fx.service.impl;

import com.fx.machinelearning.PBServer;
import com.fx.service.MLService;
import org.springframework.stereotype.Service;

/**
 * Created by thinkpad on 2018/6/6.
 */
@Service
public class MLServiceImpl implements MLService {

    PBServer pbServer = new PBServer();

    @Override
    public void predictLabel() {

    }
}
