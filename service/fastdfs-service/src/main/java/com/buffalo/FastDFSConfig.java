package com.buffalo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="fdfs")
public class FastDFSConfig {
    private String trackerList;

    public String getTrackerList() {
        return trackerList;
    }

    public void setTrackerList(String trackerList) {
        this.trackerList = trackerList;
    }
}
