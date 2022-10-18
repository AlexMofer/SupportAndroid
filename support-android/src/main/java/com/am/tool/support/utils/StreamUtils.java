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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 流工具
 * Created by Alex on 2022/3/25.
 */
public class StreamUtils {

    private StreamUtils() {
        //no instance
    }

    /**
     * 复制
     *
     * @param input  输入
     * @param output 输出
     * @throws IOException 读写错误
     */
    public static void copy(InputStream input, OutputStream output) throws IOException {
        final byte[] buffer = new byte[1024];
        int count;
        while ((count = input.read(buffer)) != -1) {
            if (count == 0) {
                count = input.read();
                if (count < 0)
                    break;
                output.write(count);
                continue;
            }
            output.write(buffer, 0, count);
            output.flush();
        }
    }
}
