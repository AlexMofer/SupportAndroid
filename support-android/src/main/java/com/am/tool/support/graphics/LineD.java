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

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * 直线
 * Created by Alex on 2022/11/26.
 */
public class LineD implements Parcelable {
    public static final Creator<LineD> CREATOR = new Creator<LineD>() {
        /**
         * Return a new point from the data in the specified parcel.
         */
        @Override
        public LineD createFromParcel(Parcel in) {
            LineD r = new LineD(0, 0);
            r.readFromParcel(in);
            return r;
        }

        /**
         * Return an array of rectangles of the specified size.
         */
        @Override
        public LineD[] newArray(int size) {
            return new LineD[size];
        }
    };
    public double k;
    public double b;

    public LineD(double k, double b) {
        this.k = k;
        this.b = b;
    }

    /**
     * 点斜式
     *
     * @param k 斜率
     * @param p 直线经过的点
     */
    public LineD(double k, PointD p) {
        this.k = k;
        b = p.y - k * p.x;
    }

    /**
     * 两点式
     *
     * @param p1 直线经过的点1
     * @param p2 直线经过的点2
     */
    public LineD(PointD p1, PointD p2) {
        final double v = p1.x - p2.x;
        if (v != 0) {
            this.k = (p1.y - p2.y) / v;
            this.b = (p1.y - p1.x * k);
        } else {
            k = Double.NaN;
            b = p1.x;
        }
    }

    /**
     * 直线垂直于X轴
     *
     * @param x X轴值
     */
    public LineD(double x) {
        this.k = Double.NaN;
        b = x;
    }


    /**
     * 判断是否垂直于X轴（斜率不存在）
     *
     * @return 垂直于X轴时返回true
     */
    public boolean isPerpendicularToX() {
        return Double.isNaN(k);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineD l = (LineD) o;

        if (Double.isNaN(l.k)) {
            if (Double.isNaN(k)) {
                return Double.compare(l.b, b) == 0;
            } else {
                return false;
            }
        }

        if (Double.compare(l.k, k) != 0) return false;
        return Double.compare(l.b, b) == 0;
    }

    @Override
    public int hashCode() {
        int result = Double.valueOf(k).hashCode();
        result = 31 * result + Double.valueOf(b).hashCode();
        return result;
    }

    @NonNull
    @Override
    public String toString() {
        return "LineD(" + k + ", " + b + ")";
    }

    /**
     * Parcelable interface methods
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Write this point to the specified parcel. To restore a point from
     * a parcel, use readFromParcel()
     *
     * @param out The parcel to write the point's coordinates into
     */
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeDouble(k);
        out.writeDouble(b);
    }

    /**
     * Set the point's coordinates from the data stored in the specified
     * parcel. To write a point to a parcel, call writeToParcel().
     *
     * @param in The parcel to read the point's coordinates from
     */
    public void readFromParcel(@NonNull Parcel in) {
        k = in.readDouble();
        b = in.readDouble();
    }
}
