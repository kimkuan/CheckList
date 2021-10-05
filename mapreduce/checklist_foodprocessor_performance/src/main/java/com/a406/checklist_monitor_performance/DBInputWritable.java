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
    private String process_type;
    private String process_time;
    private String decrease;
    private String size;
    private String sound;
    private String spec;
    private int ranking;

    public void write(PreparedStatement statement) throws SQLException {
        statement.setLong(1, pcode);
        statement.setLong(2, price);
        statement.setString(3, process_type);
        statement.setString(4, process_time);
        statement.setString(5, decrease);
        statement.setString(6, size);
        statement.setString(7, sound);
        statement.setObject(8, spec);
        statement.setInt(9, ranking);
    }
    public void readFields(ResultSet resultSet) throws SQLException {
        pcode = resultSet.getLong(1);
        price = resultSet.getLong(2);
        process_type = resultSet.getString(3);
        process_time = resultSet.getString(4);
        decrease = resultSet.getString(5);
        size = resultSet.getString(6);
        sound = resultSet.getString(7);
        spec = resultSet.getString(8);
        ranking = resultSet.getInt(9);
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

    public String getProcess_type() {
        return process_type;
    }

    public String getProcess_time() {
        return process_time;
    }

    public String getDecrease() {
        return decrease;
    }

    public String getSize() {
        return size;
    }

    public String getSound() {
        return sound;
    }

    public String getSpec() {
        return spec;
    }

    public int getRanking() {
        return ranking;
    }

}
