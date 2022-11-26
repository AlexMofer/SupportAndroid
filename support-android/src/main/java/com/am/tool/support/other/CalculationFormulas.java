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
     * 获取点到直线的最短距离
     *
     * @param x  X坐标
     * @param y  Y坐标
     * @param x1 直线经过的点1的X坐标
     * @param y1 直线经过的点1的Y坐标
     * @param x2 直线经过的点2的X坐标
     * @param y2 直线经过的点2的Y坐标
     * @return 最短距离
     */
    public static double getDistanceLine(double x, double y,
                                         double x1, double y1, double x2, double y2) {
        return Math.abs((y2 - y1) * x + (x1 - x2) * y + ((x2 * y1) - (x1 * y2)))
                / (Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x1 - x2, 2)));
    }

    // 线段与X轴正方向顺时针形成的夹角角度（0~360）
    private static double getDegreesXPositiveCW(double x, double y) {
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

    // 线段与X轴正方向逆时针形成的夹角角度（0~360）
    private static double getDegreesXPositiveCCW(double x, double y) {
        if (y == 0) {
            if (x >= 0)
                return 0;
            return 180;
        }
        if (x == 0) {
            if (y > 0)
                return 270;
            return 90;
        }
        if (x > 0 && y < 0) {
            // 右上
            if (x == -y)
                return 45;
            return Math.toDegrees(Math.atan(-y / x));
        }
        if (x < 0 && y < 0) {
            // 左上
            if (x == y)
                return 135;
            return Math.toDegrees(Math.PI - Math.atan(y / x));
        }
        if (x < 0 && y > 0) {
            // 左下
            if (-x == y)
                return 225;

            return Math.toDegrees(Math.PI + Math.atan(-y / x));
        }
        if (x > 0 && y > 0) {
            // 右下
            if (x == y)
                return 315;
            return Math.toDegrees(Math.PI * 2 - Math.atan(y / x));
        }
        return 0;
    }

    // 线段与X轴反方向顺时针形成的夹角角度（0~360）
    private static double getDegreesXNegativeCW(double x, double y) {
        if (y == 0) {
            if (x <= 0)
                return 0;
            return 180;
        }
        if (x == 0) {
            if (y < 0)
                return 90;
            return 270;
        }
        if (x < 0 && y < 0) {
            // 左上
            if (x == y)
                return 45;
            return Math.toDegrees(Math.atan(y / x));
        }
        if (x > 0 && y < 0) {
            // 右上
            if (x == -y)
                return 135;
            return Math.toDegrees(Math.PI - Math.atan(-y / x));
        }
        if (x > 0 && y > 0) {
            // 右下
            if (x == y)
                return 225;
            return Math.toDegrees(Math.PI + Math.atan(y / x));
        }
        if (x < 0 && y > 0) {
            // 左下
            if (-x == y)
                return 315;
            return Math.toDegrees(Math.PI * 2 - Math.atan(-y / x));
        }
        return 0;
    }

    // 线段与X轴反方向逆时针形成的夹角角度（0~360）
    private static double getDegreesXNegativeCCW(double x, double y) {
        if (y == 0) {
            if (x <= 0)
                return 0;
            return 180;
        }
        if (x == 0) {
            if (y > 0)
                return 90;
            return 270;
        }
        if (x < 0 && y > 0) {
            // 左下
            if (-x == y)
                return 45;
            return Math.toDegrees(Math.atan(-y / x));
        }
        if (x > 0 && y > 0) {
            // 右下
            if (x == y)
                return 135;
            return Math.toDegrees(Math.PI - Math.atan(y / x));
        }
        if (x > 0 && y < 0) {
            // 右上
            if (x == -y)
                return 225;
            return Math.toDegrees(Math.PI + Math.atan(-y / x));
        }
        if (x < 0 && y < 0) {
            // 左上
            if (x == y)
                return 315;
            return Math.toDegrees(Math.PI * 2 - Math.atan(y / x));
        }
        return 0;
    }

    // 线段与Y轴正方向顺时针形成的夹角角度（0~360）
    private static double getDegreesYPositiveCW(double x, double y) {
        double degrees = 90 + getDegreesXPositiveCW(x, y);
        while (degrees < 0) {
            degrees += 360;
        }
        while (degrees > 360) {
            degrees -= 360;
        }
        return degrees;
    }

    // 线段与X轴反方向顺时针形成的夹角角度（0~360）
    private static double getDegreesYNegativeCW(double x, double y) {
        double degrees = 90 + getDegreesXNegativeCW(x, y);
        while (degrees < 0) {
            degrees += 360;
        }
        while (degrees > 360) {
            degrees -= 360;
        }
        return degrees;
    }

    // 线段与X轴正方向逆时针形成的夹角角度（0~360）
    private static double getDegreesYPositiveCCW(double x, double y) {
        double degrees = getDegreesXPositiveCCW(x, y) - 90;
        while (degrees < 0) {
            degrees += 360;
        }
        while (degrees > 360) {
            degrees -= 360;
        }
        return degrees;
    }

    // 线段与X轴反方向逆时针形成的夹角角度（0~360）
    private static double getDegreesYNegativeCCW(double x, double y) {
        double degrees = getDegreesXNegativeCCW(x, y) - 90;
        while (degrees < 0) {
            degrees += 360;
        }
        while (degrees > 360) {
            degrees -= 360;
        }
        return degrees;
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
        if (xAxis) {
            // X轴
            if (positiveDirection) {
                // X轴正方向
                if (clockwise) {
                    // 顺时针
                    return getDegreesXPositiveCW(x, y);
                } else {
                    // 逆时针
                    return getDegreesXPositiveCCW(x, y);
                }
            } else {
                // X轴反方向
                if (clockwise) {
                    // 顺时针
                    return getDegreesXNegativeCW(x, y);
                } else {
                    // 逆时针
                    return getDegreesXNegativeCCW(x, y);
                }
            }
        } else {
            // Y轴
            if (positiveDirection) {
                // Y轴正方向
                if (clockwise) {
                    // 顺时针
                    return getDegreesYPositiveCW(x, y);
                } else {
                    // 逆时针
                    return getDegreesYPositiveCCW(x, y);
                }
            } else {
                // Y轴反方向
                if (clockwise) {
                    // 顺时针
                    return getDegreesYNegativeCW(x, y);
                } else {
                    // 逆时针
                    return getDegreesYNegativeCCW(x, y);
                }
            }
        }
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
        return getDegrees(xAxis, positiveDirection, clockwise, x - ox, y - oy);
    }
}
