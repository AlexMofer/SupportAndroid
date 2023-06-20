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
package com.am.tool.support.utils;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * 文件工具类
 * Created by Alex on 2022/3/25.
 */
public class FileUtils {

    private FileUtils() {
        //no instance
    }

    /**
     * 复制文件
     *
     * @param source 源文件
     * @param target 目标文件
     * @throws IOException 输入输出异常
     */
    public static void copyOrThrow(File source, File target) throws IOException {
        try (final FileInputStream input = new FileInputStream(source);
             final FileOutputStream output = new FileOutputStream(target)) {
            StreamUtils.copy(input, output);
        }
    }

    /**
     * 复制文件
     *
     * @param source 源文件
     * @param target 目标文件
     * @throws IOException 输入输出异常
     */
    public static void copyOrThrow(InputStream source, File target) throws IOException {
        try (final FileOutputStream output = new FileOutputStream(target)) {
            StreamUtils.copy(source, output);
        }
    }

    /**
     * 复制文件
     *
     * @param source 源文件
     * @param target 目标文件
     * @throws IOException 输入输出异常
     */
    public static void copyOrThrow(File source, OutputStream target) throws IOException {
        try (final FileInputStream input = new FileInputStream(source)) {
            StreamUtils.copy(input, target);
        }
    }


    /**
     * 复制文件
     *
     * @param source 源文件
     * @param target 目标文件
     * @return 复制成功时返回true
     */
    public static boolean copyFile(File source, File target) {
        try (final FileInputStream input = new FileInputStream(source);
             final FileOutputStream output = new FileOutputStream(target)) {
            StreamUtils.copy(input, output);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    /**
     * 复制文件
     *
     * @param source 源文件
     * @param target 目标文件
     * @return 复制成功时返回true
     */
    public static boolean copyFile(InputStream source, File target) {
        try (final FileOutputStream output = new FileOutputStream(target)) {
            StreamUtils.copy(source, output);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    /**
     * 复制文件
     *
     * @param source 源文件
     * @param target 目标文件
     * @return 复制成功时返回true
     */
    public static boolean copyFile(File source, OutputStream target) {
        try (final FileInputStream input = new FileInputStream(source)) {
            StreamUtils.copy(input, target);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    /**
     * 获取后缀名
     * 如：pdf
     *
     * @param name  文件名
     * @param lower 转化为小写字母
     * @return 后缀名
     */
    public static String getExtension(String name, boolean lower) {
        if (name == null) {
            return null;
        }
        final int index = name.lastIndexOf('.');
        if (index >= 0 && index < name.length() - 1) {
            if (lower) {
                return name.substring(index + 1).toLowerCase();
            } else {
                return name.substring(index + 1);
            }
        }
        return null;
    }

    /**
     * 获取后缀名
     * 如：pdf
     *
     * @param file  文件
     * @param lower 转化为小写字母
     * @return 后缀名
     */
    public static String getExtension(File file, boolean lower) {
        if (file != null && file.isFile()) {
            return getExtension(file.getName(), lower);
        }
        return null;
    }

    /**
     * 获取无后缀名的文件名
     *
     * @param name 文件名
     * @return 无后缀名的文件名
     */
    public static String getNameWithoutExtension(String name) {
        if (name == null) {
            return null;
        }
        final int index = name.lastIndexOf('.');
        if (index < 0) {
            return name;
        }
        if (index == 0) {
            return "";
        }
        return name.substring(0, index);
    }

    /**
     * 获取无后缀名的文件名
     *
     * @param file 文件
     * @return 无后缀名的文件名
     */
    public static String getNameWithoutExtension(File file) {
        if (file != null && file.isFile()) {
            return getNameWithoutExtension(file.getName());
        }
        return null;
    }

    /**
     * 写入字符串内容
     *
     * @param file    文件
     * @param content 字符串
     * @return 是否成功
     */
    public static boolean writeString(File file, String content) {
        if (content == null)
            return file.delete();
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 写入字符串内容
     *
     * @param file    文件
     * @param content 字符串
     * @param cs      字符集
     * @return 是否成功
     */
    public static boolean writeString(File file, String content, Charset cs) {
        if (content == null)
            return file.delete();
        //noinspection IOStreamConstructor
        try (final BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), cs))) {
            writer.write(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 读取字符串内容
     *
     * @param file 文件
     * @return 字符串
     */
    public static String readString(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            return null;
        }
        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 读取字符串内容
     *
     * @param file 文件
     * @param cs   字符集
     * @return 字符串
     */
    public static String readString(File file, Charset cs) {
        if (file == null || !file.exists() || !file.isFile()) {
            return null;
        }
        //noinspection IOStreamConstructor
        try (final BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), cs))) {
            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 复制文件夹
     *
     * @param source 源文件夹
     * @param target 目标文件夹（已创建）
     * @return 复制成功时返回true
     */
    public static boolean copyDirectory(File source, File target) {
        final File[] children = source.listFiles();
        if (children != null) {
            for (File child : children) {
                final File file = new File(target, child.getName());
                if (child.isDirectory()) {
                    if (file.exists()) {
                        if (!file.isDirectory()) {
                            return false;
                        }
                    } else {
                        if (!file.mkdir()) {
                            return false;
                        }
                    }
                    if (!copyDirectory(child, file)) {
                        return false;
                    }
                }
                if (child.isFile()) {
                    if (file.exists()) {
                        return false;
                    }
                    if (!copyFile(child, file)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 删除文件及文件夹
     *
     * @param file 文件及文件夹
     * @return 删除成功时返回true
     */
    public static boolean delete(File file) {
        if (file == null) {
            return true;
        }
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (file.isDirectory()) {
            final File[] children = file.listFiles();
            if (children == null || children.length <= 0) {
                return file.delete();
            }
            boolean deleted = true;
            for (File child : children) {
                if (!delete(child)) {
                    deleted = false;
                }
            }
            return deleted && file.delete();
        }
        return false;
    }

    /**
     * 清空文件夹
     *
     * @param file 文件夹
     * @return 清空文件夹
     */
    public static boolean clearDirectory(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            final File[] children = file.listFiles();
            if (children == null || children.length <= 0) {
                return true;
            }
            boolean clear = true;
            for (File child : children) {
                if (!delete(child)) {
                    clear = false;
                }
            }
            return clear;
        }
        return false;
    }

    /**
     * 获取文件 MD5
     *
     * @param file      文件
     * @param minLength 最少长度，如：32，长度不够时最前面补0
     * @return MD5
     */
    @Nullable
    public static String getMD5(File file, int minLength) {
        if (file == null || !file.exists()) {
            return null;
        }
        //noinspection IOStreamConstructor
        try (final InputStream input = new FileInputStream(file)) {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            int len;
            final byte[] buffer = new byte[1024];
            while ((len = input.read(buffer, 0, buffer.length)) != -1) {
                md.update(buffer, 0, len);
            }
            final byte[] bytes = md.digest();
            final String str = new BigInteger(1, bytes).toString(16);
            final StringBuilder builder = new StringBuilder();
            if (str.length() < minLength) {
                final int number = minLength - str.length();
                for (int i = 0; i < number; i++) {
                    builder.append("0");
                }
            }
            builder.append(str);
            return builder.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取文件 MD5
     *
     * @param file 文件
     * @return MD5
     */
    @Nullable
    public static String getMD5(File file) {
        return getMD5(file, 0);
    }
}
