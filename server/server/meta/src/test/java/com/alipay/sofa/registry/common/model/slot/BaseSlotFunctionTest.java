/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.registry.common.model.slot;

import com.alipay.sofa.registry.server.meta.AbstractTest;
import com.alipay.sofa.registry.util.FileUtils;
import com.alipay.sofa.registry.util.JsonUtils;
import org.junit.Before;

import java.io.File;
import java.io.IOException;

/**
 * @author chen.zhu
 * <p>
 * Jan 13, 2021
 */
public class BaseSlotFunctionTest extends AbstractTest {

    public String[] getDataInfoIds() throws IOException {
        String fileContent = new String(FileUtils.readFileToByteArray(new File(
            "src/test/resources/test/data_info_ids.json")));
        return JsonUtils.read(fileContent, String[].class);
    }
}
