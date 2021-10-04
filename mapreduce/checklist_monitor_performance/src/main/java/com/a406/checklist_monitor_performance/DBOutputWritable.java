package com.a406.checklist_monitor_performance;

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
	private int size;
	private int pixel;
	private int screenChange;
	private int convenience;

	public DBOutputWritable() {
	}

	public DBOutputWritable(long pcode, int price, int size, int pixel, int screenChange, int convenience) {
		this.pcode = pcode;
		this.price = price;
		this.size = size;
		this.pixel = pixel;
		this.screenChange = screenChange;
		this.convenience = convenience;
	}

	public void write(PreparedStatement statement) throws SQLException {

		statement.setLong(1, pcode);
		statement.setInt(2, price);
		statement.setInt(3, size);
		statement.setInt(4, pixel);
		statement.setInt(5, screenChange);
		statement.setInt(6, convenience);

	}

	public void readFields(ResultSet resultSet) throws SQLException {

		this.pcode = resultSet.getLong(1);
		this.price = resultSet.getInt(2);
		this.size = resultSet.getInt(3);
		this.pixel = resultSet.getInt(4);
		this.screenChange = resultSet.getInt(5);
		this.convenience = resultSet.getInt(6);

	}

	public void write(DataOutput out) throws IOException {
	}

	public void readFields(DataInput in) throws IOException {
	}
}
