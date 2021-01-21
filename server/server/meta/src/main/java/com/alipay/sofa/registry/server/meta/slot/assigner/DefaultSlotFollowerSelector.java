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
package com.alipay.sofa.registry.server.meta.slot.assigner;

import com.alipay.sofa.registry.common.model.slot.DataNodeSlot;
import com.alipay.sofa.registry.server.meta.slot.util.SlotTableBuilder;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * @author chen.zhu
 * <p>
 * Jan 15, 2021
 */
public class DefaultSlotFollowerSelector implements Selector<String> {

    private DefaultSlotAssigner assigner;

    public DefaultSlotFollowerSelector(DefaultSlotAssigner defaultSlotAssigner) {
        assigner = defaultSlotAssigner;
    }

    @Override
    public String select(Collection<String> candidates) {
        List<String> sortedCandidates = Lists.newArrayList(candidates);
        SlotTableBuilder slotTableBuilder = assigner.getSlotTableBuilder();
        sortedCandidates.sort(new Comparator<String>() {
            @Override
            public int compare(String dataServer1, String dataServer2) {
                DataNodeSlot dataNodeSlot1 = slotTableBuilder.getDataNodeSlot(dataServer1);
                DataNodeSlot dataNodeSlot2 = slotTableBuilder.getDataNodeSlot(dataServer2);
                if (dataNodeSlot1.getLeaders().size() < dataNodeSlot2.getLeaders().size()) {
                    return -1;
                } else if (dataNodeSlot1.getLeaders().size() > dataNodeSlot2.getLeaders().size()) {
                    return 1;
                }
                return dataNodeSlot1.getFollowers().size() - dataNodeSlot2.getFollowers().size();
            }
        });
        return sortedCandidates.isEmpty() ? null : sortedCandidates.get(0);
    }
}
