import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class DBOutputWritable implements Writable, DBWritable{

	// (수정) 각 상품의 테이블 컬럼에 맞게 전체 수정
	// Driver에 명시한  new String[] {"pcode", "name", "brand", "price", "img", "pressure", "heat_time", "water_volume", "spec"} 이 순서와 동일하게!
	private long pcode;
	private String name;
	private String brand;
	private long price;
	private String img;
	private String pressure;
	private String heat_time;
	private String water_volume;
	private String spec;

	// new String[] {"pcode", "name", "brand", "price", "img", "pressure", "heat_time", "water_volume", "spec"} //table columns

	public DBOutputWritable(){}
	public DBOutputWritable(long pcode, String name, String brand, long price, String img, String pressure, String heat_time,
							String water_volume, String spec)
	{
		this.pcode = pcode;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.img = img;
		this.pressure = pressure;
		this.heat_time = heat_time;
		this.water_volume = water_volume;
		this.spec = spec;
	}

	public void write(PreparedStatement statement) throws SQLException {
		
		statement.setLong(1, pcode);
		statement.setString(2, name);
		statement.setString(3, brand);
		statement.setLong(4, price);
		statement.setString(5, img);
		statement.setString(6, pressure);
		statement.setString(7, heat_time);
		statement.setString(8, water_volume);
		statement.setString(9, spec);
	}

	public void readFields(ResultSet resultSet) throws SQLException {
		
		this.pcode = resultSet.getLong(1);
		this.name = resultSet.getString(2);
		this.brand = resultSet.getString(3);
		this.price =resultSet.getLong(4);
		this.img = resultSet.getString(5);
		this.pressure = resultSet.getString(6);
		this.heat_time = resultSet.getString(7);
		this.water_volume = resultSet.getString(8);
		this.spec = resultSet.getString(9);
	}

	public void write(DataOutput out) throws IOException {
	}

	public void readFields(DataInput in) throws IOException {
	}
}
