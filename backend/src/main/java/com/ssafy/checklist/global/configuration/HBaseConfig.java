package com.ssafy.checklist.global.configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

@Configuration
public class HBaseConfig {

    public HbaseTemplate hbaseTemplate() {
        org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "IP");
        configuration.set("hbase.rootdir", "/home/hbase");
        configuration.set("zookeeper.znode.parent", "/hbase");
        return new HbaseTemplate(configuration);
    }
}
