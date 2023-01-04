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
package com.am.tool.support.graphics;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.Region;

/**
 * 图形工具
 * Created by Alex on 2023/1/4.
 */
public class GraphicsUtils {

    private GraphicsUtils() {
        //no instance
    }

    /**
     * 判断点是否在路径内部
     *
     * @param path 闭合路径
     * @param x    点X轴坐标
     * @param y    点Y轴坐标
     * @return 点在路径内部时返回true
     */
    public static boolean contains(Path path, int x, int y) {
        if (path == null) {
            return false;
        }
        final RectF rect = new RectF();
        path.computeBounds(rect, true);
        final int left = Math.round(rect.left - 0.5f);
        final int top = Math.round(rect.top - 0.5f);
        final int right = Math.round(rect.right + 0.5f);
        final int bottom = Math.round(rect.bottom + 0.5f);
        final Region region = new Region();
        region.setPath(path, new Region(left, top, right, bottom));
        return region.contains(x, y);
    }

    /**
     * 获取椭圆分割点
     *
     * @param left     左
     * @param top      上
     * @param right    右
     * @param bottom   下
     * @param distance 分割距离
     * @param start    起始单元百分比，传0则从0开始，传0.5则为从半个分割距离开始，传1则为从1个分割距离开始。
     * @return 分割点
     */
    public static float[] getOvalSplitPoints(float left, float top, float right, float bottom,
                                             float distance, float start) {
        final Path path = new Path();
        path.addOval(left, top, right, bottom, Path.Direction.CCW);
        final PathMeasure measure = new PathMeasure(path, true);
        final float length = measure.getLength();
        final int count = (int) Math.ceil(length / distance);
        if (count < 2) {
            return null;
        }
        final float unit = length / count;
        final float[] pos = new float[2];
        final float[] tan = new float[2];
        final float[] points = new float[count * 2];
        float l = unit * Math.max(0f, Math.min(1f, start));
        int index = 0;
        while (l < length) {
            if (measure.getPosTan(l, pos, tan)) {
                points[index] = pos[0];
                points[index + 1] = pos[1];
            }
            index += 2;
            l += unit;
        }
        return points;
    }
}
