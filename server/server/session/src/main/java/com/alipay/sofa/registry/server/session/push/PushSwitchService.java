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
package com.alipay.sofa.registry.server.session.push;

import com.alipay.sofa.registry.server.session.provideData.FetchStopPushService;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import javax.annotation.Resource;

public class PushSwitchService {

  @Resource FetchStopPushService fetchStopPushService;

  private volatile Collection<String> openIps = Sets.newHashSet();

  public PushSwitchService() {}

  public void setOpenIPs(Collection<String> ips) {
    if (ips == null) {
      ips = Sets.newHashSet();
    }
    openIps = new HashSet<>(ips);
  }

  public Collection<String> getOpenIps() {
    return openIps;
  }

  public boolean isGlobalPushSwitchStopped() {
    return fetchStopPushService.isStopPushSwitch();
  }

  public boolean canPush() {
    return !fetchStopPushService.isStopPushSwitch() || openIps.size() > 0;
  }

  public boolean canIpPush(String ip) {
    return !fetchStopPushService.isStopPushSwitch() || openIps.contains(ip);
  }

  /**
   * Setter method for property <tt>fetchStopPushService</tt>.
   *
   * @param fetchStopPushService value to be assigned to property fetchStopPushService
   */
  @VisibleForTesting
  public void setFetchStopPushService(FetchStopPushService fetchStopPushService) {
    this.fetchStopPushService = fetchStopPushService;
  }

  /**
   * Getter method for property <tt>fetchStopPushService</tt>.
   *
   * @return property value of fetchStopPushService
   */
  @VisibleForTesting
  public FetchStopPushService getFetchStopPushService() {
    return fetchStopPushService;
  }
}