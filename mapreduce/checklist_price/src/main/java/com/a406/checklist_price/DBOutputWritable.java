package com.a406.checklist_price;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class DBOutputWritable implements Writable, DBWritable {

//	private int quantity;
//	private String stockCode;
//	private String description;

	private String category;
	private long pcode;
	private String site;
	private long price;
	private String link;
	private String img;

	public DBOutputWritable() {
	}

	public DBOutputWritable(String category, long pcode, String site, long price, String link, String img) {
		this.category = category;
		this.pcode = pcode;
		this.site = site;
		this.price = price;
		this.link = link;
		this.img = img;
	}

	public void write(PreparedStatement statement) throws SQLException {

		statement.setString(1, category);
		statement.setLong(2, pcode);
		statement.setString(3, site);
		statement.setLong(4, price);
		statement.setString(5, link);
		statement.setString(6, img);
		
	}

	public void readFields(ResultSet resultSet) throws SQLException {

		this.category = resultSet.getString(1);
		this.pcode = resultSet.getLong(2);
		this.site = resultSet.getString(3);
		this.price = resultSet.getLong(4);
		this.link = resultSet.getString(5);
		this.img = resultSet.getString(6);
		
	}

	public void write(DataOutput out) throws IOException {
	}

	public void readFields(DataInput in) throws IOException {
	}
}
