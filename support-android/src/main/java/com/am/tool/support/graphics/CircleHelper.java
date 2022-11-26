package com.am.tool.support.graphics;

import androidx.annotation.Nullable;

/**
 * 圆辅助
 * Created by Alex on 2022/11/26.
 */
public class CircleHelper {

    private CircleHelper() {
        //no instance
    }

    private static double getDistance(double x1, double y1, double x2, double y2) {
        final double dx = x2 - x1;
        final double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double getDistanceLine(double x0, double y0, double x1, double y1,
                                         double x2, double y2) {
        return Math.abs((y2 - y1) * x0 + (x1 - x2) * y0 + ((x2 * y1) - (x1 * y2)))
                / (Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x1 - x2, 2)));
    }

    /**
     * 获取直线到圆的交点
     *
     * @param p1     直线进过的点1
     * @param p2     直线进过的点2
     * @param c      圆心
     * @param radius 半径
     * @return 交点
     */
    @Nullable
    public static PointD[] getIntersectionLine(PointD p1, PointD p2, PointD c, double radius) {
        final LineD line = new LineD(p1, p2);
        if (line.isPerpendicularToX()) {
            // 直线X轴垂直
            final double x = line.b;
            final double dis = Math.abs(c.x - x);
            if (dis == 0) {
                return new PointD[]{new PointD(c.x, c.y - radius),
                        new PointD(c.x, c.y + radius)};
            } else if (dis > radius) {
                // 无交点
                return null;
            } else if (dis == radius) {
                // 一个交点
                return new PointD[]{new PointD(x, c.y)};
            } else {
                // 两个交点
                final double dy = Math.sin(Math.acos(dis / radius)) * radius;
                return new PointD[]{new PointD(x, c.y - dy),
                        new PointD(x, c.y + dy)};
            }
        } else {
            if (line.k == 0) {
                // 直线Y轴垂直
                final double y = line.b;
                final double dis = Math.abs(c.y - y);
                if (dis == 0) {
                    return new PointD[]{new PointD(c.x - radius, c.y),
                            new PointD(c.x + radius, c.y)};
                } else if (dis > radius) {
                    // 无交点
                    return null;
                } else if (dis == radius) {
                    // 一个交点
                    return new PointD[]{new PointD(c.x, y)};
                } else {
                    // 两个交点
                    final double dx = Math.sin(Math.acos(dis / radius)) * radius;
                    return new PointD[]{new PointD(c.x - dx, y),
                            new PointD(c.x + dx, y)};
                }
            } else {
                // 倾斜直线，计算圆心到直线距离
                final double dis = getDistanceLine(c.x, c.y, p1.x, p1.y, p2.x, p2.y);
                if (dis > radius) {
                    // 距离大于半径，无交点
                    return null;
                }
                final double A = Math.pow(line.k, 2) + 1;
                final double B = 2 * (line.k * line.b - line.k * c.y - c.x);
                final double C = Math.pow(c.x, 2) + Math.pow((line.b - c.y), 2) -
                        Math.pow(radius, 2);
                final double delta = Math.pow(B, 2) - 4 * A * C;
                if (delta < 0) {
                    // 算法精度问题，无法求解
                    return null;
                } else {
                    final double x1 = (-B - Math.sqrt(delta)) / (2 * A);
                    final double y1 = line.k * x1 + line.b;
                    final double x2 = (-B + Math.sqrt(delta)) / (2 * A);
                    final double y2 = line.k * x2 + line.b;
                    if (x1 == x2 && y1 == y2) {
                        return new PointD[]{new PointD(x1, y1)};
                    }
                    return new PointD[]{new PointD(x1, y1), new PointD(x2, y2)};
                }
            }
        }
    }

    @Nullable
    public static double[] getIntersectionLine(double px1, double py1, double px2, double py2,
                                               double pcx, double pcy, double radius) {
        final PointD[] ps = getIntersectionLine(
                new PointD(px1, py1), new PointD(px2, py2), new PointD(pcx, pcy), radius);
        if (ps == null) {
            return null;
        }
        if (ps.length == 1) {
            return new double[]{ps[0].x, ps[0].y};
        }
        if (ps.length == 2) {
            return new double[]{ps[0].x, ps[0].y, ps[1].x, ps[1].y};
        }
        return null;
    }

    /**
     * 获取线段到圆的交点
     *
     * @param p1     线段起点
     * @param p2     线段终点
     * @param c      圆心
     * @param radius 半径
     * @return 交点
     */
    @Nullable
    public static PointD[] getIntersection(PointD p1, PointD p2, PointD c, double radius) {
        // 优化算法
        final PointD[] ps = getIntersectionLine(p1, p2, c, radius);
        if (ps == null) {
            return null;
        }
        double left = p1.x, top = p1.y, right = p1.x, bottom = p1.y;
        left = Math.min(left, p2.x);
        top = Math.min(top, p2.y);
        right = Math.max(right, p2.x);
        bottom = Math.max(bottom, p2.y);
        if (ps.length == 1) {
            final PointD p = ps[0];
            if (left <= p.x && p.x <= right && top <= p.y && p.y <= bottom) {
                return ps;
            }
            return null;
        } else if (ps.length == 2) {
            PointD r1 = null, r2 = null;
            if (left <= ps[0].x && ps[0].x <= right && top <= ps[0].y && ps[0].y <= bottom) {
                r1 = ps[0];
            }
            if (left <= ps[1].x && ps[1].x <= right && top <= ps[1].y && ps[1].y <= bottom) {
                r2 = ps[1];
            }
            if (r1 == null && r2 == null) {
                return null;
            }
            if (r1 != null && r2 == null) {
                return new PointD[]{r1};
            }
            if (r1 == null) {
                return new PointD[]{r2};
            }
            // 因为线段有方向
            final double d1 = getDistance(p1.x, p1.y, r1.x, r1.y);
            final double d2 = getDistance(p1.x, p1.y, r2.x, r2.y);
            if (d1 < d2) {
                return new PointD[]{r1, r2};
            } else {
                return new PointD[]{r2, r1};
            }
        }
        return ps;
    }

    @Nullable
    public static double[] getIntersection(double px1, double py1, double px2, double py2,
                                           double pcx, double pcy, double radius) {
        final PointD[] ps = getIntersection(
                new PointD(px1, py1), new PointD(px2, py2), new PointD(pcx, pcy), radius);
        if (ps == null) {
            return null;
        }
        if (ps.length == 1) {
            return new double[]{ps[0].x, ps[0].y};
        }
        if (ps.length == 2) {
            return new double[]{ps[0].x, ps[0].y, ps[1].x, ps[1].y};
        }
        return null;
    }
}
