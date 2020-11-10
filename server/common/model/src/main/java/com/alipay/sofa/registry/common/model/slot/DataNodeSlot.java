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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author yuzhi.lyz
 * @version v 0.1 2020-11-03 11:27 yuzhi.lyz Exp $
 */
public final class DataNodeSlot implements Serializable {
    private static final long        serialVersionUID = -4418378966762753298L;
    private final        String      dataNode;
    private final        List<Short> leaders          = new ArrayList<>();
    private final        List<Short> followers        = new ArrayList<>();

    public DataNodeSlot(String dataNode) {
        this.dataNode = dataNode;
    }

    /**
     * Getter method for property <tt>dataNode</tt>.
     * @return property value of dataNode
     */
    public String getDataNode() {
        return dataNode;
    }

    /**
     * Getter method for property <tt>leaders</tt>.
     * @return property value of leaders
     */
    public List<Short> getLeaders() {
        return leaders;
    }

    /**
     * Getter method for property <tt>followers</tt>.
     * @return property value of followers
     */
    public List<Short> getFollowers() {
        return followers;
    }
}