package com.fx.counting;

/**
 * Created by thinkpad on 2018/6/20.
 */
public class NormalDistribution {
    /**
     * 根据分割积分法来求得积分值
     * -3.89～3.89区间外的积分面积 小于 0.0001，
     * 所以确定有效的积分区间为-3.89～3.89
     * 在实现分割的时候精度定为0.0001，得到的结果和查表得到的结果误差在-0.0002～+0.0002之间（已经检验）
     *
     * @param u      积分上限
     * @return       积分值
     */
    public static float selfCaculate(float u){
        float ret  = 0;
        if(u < -3.89){
            return 0;
        }
        else if(u > 3.89){
            return 1;
        }
        float temp = -3.89f;
        while(temp <= u){
            ret += 0.0001f * fx(temp);
            temp += 0.0001f;
        }
        return ret;
    }
    /**
     * 求被积函数的函数值    (1/(2 * PI)^(0.5))e^(-t^2/2)
     * @param x      变量x
     * @return       函数值
     */
    public static float fx(float x){
        float ret = 0;
        double a = 1.0 / Math.sqrt(Math.PI * 2);
        a  = a * Math.pow(Math.E, -0.5 * Math.pow(x, 2));
        ret = (float) a;
        return ret;
    }


    /************************************/
/************************************/
}
