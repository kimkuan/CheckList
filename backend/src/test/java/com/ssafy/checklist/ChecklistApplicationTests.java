package com.ssafy.checklist;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

import java.io.IOException;

@SpringBootTest
class ChecklistApplicationTests {

    @Test
    void contextLoads() {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "3.36.131.86"); // j5checklist.p.ssafy.io public IP
        conf.set("hbase.zookeeper.property.clientport", "2181");
        conf.set("zookeeper.znode.parent", "/hbase");
        conf.set("hbase.rootdir", "/hbase");

        try {
            System.out.println("연결완료??");
            Connection conn = ConnectionFactory.createConnection(conf);
            System.out.println("연결완료!");

            HBaseAdmin.available(conf);

            TableName tableName = TableName.valueOf("test");
            Table table = conn.getTable(tableName);
            String family1 = "data";
            byte[] row1 = Bytes.toBytes("row1"); // PK 값 = row

//            Get get = new Get(row1);
//            Result result = table.get(get);
//            byte[] value = result.getValue(family1.getBytes(), "data");

            Scan scan = new Scan();
            ResultScanner scanner = table.getScanner(scan);

            for(Result res : scanner){
                System.out.println("Found Row : " + res);
            }

        }catch (IOException e){
            System.out.print(e.toString());
        }

    }
}
