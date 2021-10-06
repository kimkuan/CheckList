package com.a406.aircleaner_performance;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOutputWritable implements Writable, DBWritable {

	private long pcode;
	private int price;
	private int area;
	private int sensor;
	private int convenience;

	public DBOutputWritable() {
	}

	public DBOutputWritable(long pcode, int price, int area, int sensor, int convenience) {
		this.pcode = pcode;
		this.price = price;
		this.area = area;
		this.sensor = sensor;
		this.convenience = convenience;
	}

	public void write(PreparedStatement statement) throws SQLException {

		statement.setLong(1, pcode);
		statement.setInt(2, price);
		statement.setInt(3, area);
		statement.setInt(4, sensor);
		statement.setInt(5, convenience);

	}

	public void readFields(ResultSet resultSet) throws SQLException {

		this.pcode = resultSet.getLong(1);
		this.price = resultSet.getInt(2);
		this.area = resultSet.getInt(3);
		this.sensor = resultSet.getInt(4);
		this.convenience = resultSet.getInt(5);

	}

	public void write(DataOutput out) throws IOException {
	}

	public void readFields(DataInput in) throws IOException {
	}
}
