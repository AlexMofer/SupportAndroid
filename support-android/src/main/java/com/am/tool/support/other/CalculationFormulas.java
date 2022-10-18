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

/**
 * 数学公式
 * Created by Alex on 2022/8/9.
 */
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
        final double dx = x2 - x1;
        final double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
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
        final double cross = (x2 - x1) * (x - x1) + (y2 - y1) * (y - y1);
        if (cross <= 0) {
            return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
        }
        final double d2 = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
        if (cross >= d2) {
            return Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2));
        }
        final double r = cross / d2;
        final double px = x1 + (x2 - x1) * r;
        final double py = y1 + (y2 - y1) * r;
        return Math.sqrt((x - px) * (x - px) + (py - y) * (py - y));
    }

    /**
     * 计算在Android坐标系中X轴正方向与由原点与该点连线形成的线段在顺时针方向上形成的夹角角度（0~360）
     *
     * @param x 直角坐标系X轴坐标
     * @param y 直角坐标系Y轴坐标
     * @return 角度
     */
    public static double getDegrees(double x, double y) {
        if (y == 0) {
            if (x >= 0)
                return 0;
            return 180;
        }
        if (x == 0) {
            if (y > 0)
                return 90;
            return 270;
        }
        if (x > 0 && y > 0) {
            // 右下
            if (x == y)
                return 45;
            return Math.toDegrees(Math.atan(y / x));
        }
        if (x < 0 && y > 0) {
            // 左下
            if (-x == y)
                return 135;

            return Math.toDegrees(Math.PI - Math.atan(-y / x));
        }
        if (x < 0 && y < 0) {
            // 左上
            if (x == y)
                return 225;
            return Math.toDegrees(Math.PI + Math.atan(y / x));
        }
        if (x > 0 && y < 0) {
            // 右上
            if (x == -y)
                return 315;
            return Math.toDegrees(Math.PI * 2 - Math.atan(-y / x));
        }
        return 0;
    }

    /**
     * 计算在Android坐标系中以(x1, y1)为原点从X轴正方向线段顺时针形成的夹角角度（0~360）
     *
     * @param x1 x1
     * @param y1 y1
     * @param x2 x2
     * @param y2 y2
     * @return 夹角
     */
    public static double getDegrees(double x1, double y1, double x2, double y2) {
        return getDegrees(x2 - x1, y2 - y1);
    }
}
