package com.fx.repository.impl;

import com.fx.model.AutoDetectionLabel;
import com.fx.model.AutoPercentDot;
import com.fx.repository.AutoDetectionLabelReposity;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AutoDetectionLabelRepositoryImplTest {
    AutoDetectionLabelReposity autoDetectionLabelReposity = new AutoDetectionLabelRepositoryImpl();
    @Test
    public void addAutoDetectionLabel() throws Exception {
        AutoDetectionLabel la = new AutoDetectionLabel();
        la.setFileName("Da");
        AutoPercentDot dot = new AutoPercentDot();
        dot.setX(1);
        dot.setY(1);
        ArrayList<AutoPercentDot> dots = new ArrayList<>();
        la.setDots(dots);
        dots.add(dot);

        autoDetectionLabelReposity.addAutoDetectionLabel(8,la);
    }

    @Test
    public void updateAutoDetectionLabel() throws Exception {
    }

    @Test
    public void findAutoDetectionLabel() throws Exception {
    }

    @Test
    public void findAutoDetectionLabelBymissionIDAndfilename() throws Exception {
    }

}