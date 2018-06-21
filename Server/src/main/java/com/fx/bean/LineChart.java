package com.fx.bean;

import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 9:56 2018/6/21/021
 */
public class LineChart {
    private List<String> x;
    private List y;

    public LineChart() {
    }

    public List<String> getX() {
        return x;
    }

    public void setX(List<String> x) {
        this.x = x;
    }

    public List getY() {
        return y;
    }

    public void setY(List y) {
        this.y = y;
    }
}
