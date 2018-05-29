package com.fx.bean;

import java.util.HashMap;

/**
 * Description:
 * Created by Hanxinhu at 16:30 2018/5/29/029
 */
public class CaptionResult {
    /**
     * 结果对应的文件名
     */
    public String filename;
    /**
     * 关键字+频率
     */
    public HashMap<String,Double> result;

    public CaptionResult() {
    }

    public CaptionResult(String filename, HashMap<String, Double> result) {
        this.filename = filename;
        this.result = result;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public HashMap<String, Double> getResult() {
        return result;
    }

    public void setResult(HashMap<String, Double> result) {
        this.result = result;
    }
}
