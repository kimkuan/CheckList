package com.a406.checklist_monitor_performance;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class DBInputWritable implements Writable, DBWritable {

    private Long pcode;
    private Long price;
    
    // 각자 product 테이블 컬럼명에 맞는 형태로 수정
    private String size;
    private String pixel;
    private int hz;
    private Object spec;
    private int ranking;

    public void write(PreparedStatement statement) throws SQLException {
        statement.setLong(1, pcode);
        statement.setLong(2, price);
        statement.setString(3, size);
        statement.setString(4, pixel);
        statement.setInt(5, hz);
        statement.setObject(6, spec);
        statement.setInt(7, ranking);
    }
    public void readFields(ResultSet resultSet) throws SQLException {
        pcode = resultSet.getLong(1);
        price = resultSet.getLong(2);
        size = resultSet.getString(3);
        pixel = resultSet.getString(4);
        hz = resultSet.getInt(5);
        spec = resultSet.getObject(6);
        ranking = resultSet.getInt(7);
    }
    public void write(DataOutput out) throws IOException {
    }
    public void readFields(DataInput in) throws IOException {
    }

    public Long getPcode() {
        return pcode;
    }

    public Long getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    public String getPixel() {
        return pixel;
    }

    public int getHz() {
        return hz;
    }

    public Object getSpec() {
        return spec;
    }

    public int getRanking() {
        return ranking;
    }

}
