/*
 * Copyright (C) 2022 AlexMofer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.am.tool.support.other;

import com.am.tool.support.formulas.DegreesFormulas;
import com.am.tool.support.formulas.DistanceFormulas;

/**
 * 数学公式
 * 下一版本去除，使用formulas包里面的对应方法
 * Created by Alex on 2022/8/9.
 */
@Deprecated
public class CalculationFormulas {

    private CalculationFormulas() {
        //no instance
    }

    /**
     * 获取间距
     *
     * @param x1 x1
     * @param y1 y1
     * @param x2 x2
     * @param y2 y2
     * @return 间距
     */
    public static double getDistance(double x1, double y1, double x2, double y2) {
        return DistanceFormulas.calculatePointToPoint(x1, y1, x2, y2);
    }

    /**
     * 获取点到线段的最短距离
     *
     * @param x  X坐标
     * @param y  Y坐标
     * @param x1 起点X坐标
     * @param y1 起点Y坐标
     * @param x2 终点X坐标
     * @param y2 终点Y坐标
     * @return 最短距离
     */
    public static double getDistance(double x, double y,
                                     double x1, double y1, double x2, double y2) {
        return DistanceFormulas.calculatePointToLineSegment(x, y, x1, y1, x2, y2);
    }

    /**
     * 计算在屏幕坐标系中，从(0, 0)到(x, y)连成线段，
     * 该线段与X或Y轴的正反方向的顺逆时针形成的夹角角度（0~360）
     *
     * @param xAxis             X轴为true，Y轴为false
     * @param positiveDirection 正方向为true，反方向为false
     * @param clockwise         顺时针为true，逆时针为false
     * @param x                 屏幕坐标系X轴坐标
     * @param y                 屏幕坐标系Y轴坐标
     * @return 夹角角度（0~360）
     */
    public static double getDegrees(boolean xAxis, boolean positiveDirection, boolean clockwise,
                                    double x, double y) {
        return DegreesFormulas.calculate(xAxis, positiveDirection, clockwise, x, y);
    }

    /**
     * 计算在屏幕坐标系中，从(ox, oy)到(x, y)连成线段，
     * 该线段与X或Y轴的正反方向的顺逆时针形成的夹角角度（0~360）
     *
     * @param xAxis             X轴为true，Y轴为false
     * @param positiveDirection 正方向为true，反方向为false
     * @param clockwise         顺时针为true，逆时针为false
     * @param ox                屏幕坐标系ox
     * @param oy                屏幕坐标系oy
     * @param x                 屏幕坐标系x
     * @param y                 屏幕坐标系y
     * @return 夹角角度（0~360）
     */
    public static double getDegrees(boolean xAxis, boolean positiveDirection, boolean clockwise,
                                    double ox, double oy, double x, double y) {
        return DegreesFormulas.calculate(xAxis, positiveDirection, clockwise, ox, oy, x, y);
    }
}
