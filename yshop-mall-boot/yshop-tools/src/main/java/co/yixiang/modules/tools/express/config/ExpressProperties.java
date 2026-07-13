/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.tools.express.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpressProperties {
    private boolean enable;
    private String appId;
    private String appKey;
    private List<Map<String, String>> vendors = new ArrayList<>();

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<Map<String, String>> getVendors() {
        return vendors;
    }

    public void setVendors(List<Map<String, String>> vendors) {
        this.vendors = vendors;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
