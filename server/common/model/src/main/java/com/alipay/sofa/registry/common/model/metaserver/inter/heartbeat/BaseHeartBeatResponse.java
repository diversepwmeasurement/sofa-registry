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
package com.alipay.sofa.registry.common.model.metaserver.inter.heartbeat;

import com.alipay.sofa.registry.common.model.metaserver.cluster.VersionedList;
import com.alipay.sofa.registry.common.model.metaserver.nodes.MetaNode;
import com.alipay.sofa.registry.common.model.metaserver.nodes.SessionNode;
import com.alipay.sofa.registry.common.model.slot.SlotTable;
import com.google.common.collect.Sets;
import java.io.Serializable;
import java.util.*;

/**
 * @author chen.zhu
 *     <p>Nov 27, 2020
 */
public class BaseHeartBeatResponse implements Serializable {

  private static final long INIT_META_LEADER_EPOCH = -1L;

  private final boolean heartbeatOnLeader;

  private final SlotTable slotTable;

  private final VersionedList<MetaNode> metaNodes;

  private final VersionedList<SessionNode> sessionNodes;

  private final String metaLeader;

  private final long metaLeaderEpoch;

  public BaseHeartBeatResponse(boolean heartbeatOnLeader, String metaLeader) {
    this(heartbeatOnLeader, null, null, metaLeader, INIT_META_LEADER_EPOCH);
  }

  public BaseHeartBeatResponse(
      boolean heartbeatOnLeader,
      VersionedList<MetaNode> metaNodes,
      SlotTable slotTable,
      String metaLeader,
      long metaLeaderEpoch) {
    this(heartbeatOnLeader, metaNodes, slotTable, VersionedList.EMPTY, metaLeader, metaLeaderEpoch);
  }

  public BaseHeartBeatResponse(
      boolean heartbeatOnLeader,
      VersionedList<MetaNode> metaNodes,
      SlotTable slotTable,
      VersionedList<SessionNode> sessionNodes,
      String metaLeader,
      long metaLeaderEpoch) {
    this.heartbeatOnLeader = heartbeatOnLeader;
    this.slotTable = slotTable;
    this.metaNodes = metaNodes;
    this.sessionNodes = sessionNodes;
    this.metaLeader = metaLeader;
    this.metaLeaderEpoch = metaLeaderEpoch;
  }

  public SlotTable getSlotTable() {
    return slotTable;
  }

  public List<MetaNode> getMetaNodes() {
    return metaNodes.getClusterMembers();
  }

  public String getMetaLeader() {
    return metaLeader;
  }

  public long getMetaLeaderEpoch() {
    return metaLeaderEpoch;
  }

  public Map<String, SessionNode> getSessionNodesMap() {
    final Map<String, SessionNode> m = new HashMap<>(sessionNodes.getClusterMembers().size());
    sessionNodes.getClusterMembers().forEach(s -> m.put(s.getIp(), s));
    return m;
  }

  public Set<String> getDataCentersFromMetaNodes() {
    Set<String> dcs = Sets.newHashSet();
    metaNodes.getClusterMembers().forEach(m -> dcs.add(m.getDataCenter()));
    return dcs;
  }

  public long getSessionServerEpoch() {
    return sessionNodes.getEpoch();
  }

  public long getMetaServerEpoch() {
    return metaNodes.getEpoch();
  }

  public boolean isHeartbeatOnLeader() {
    return this.heartbeatOnLeader;
  }
}
