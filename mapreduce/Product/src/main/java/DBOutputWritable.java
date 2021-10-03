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
	// Driver에 명시한  new String[] {"pcode", "name", "brand", "price", "img", "type", "volume", "size", "power", "spec"} 이 순서와 동일하게!
	private long pcode;
	private String name;
	private String brand;
	private long price;
	private String img;
	private String type;
	private String volume;
	private String size;
	private String power;
	private String spec;

	// new String[] {"pcode", "name", "brand", "price", "img", "type", "volume", "size", "power", "spec"} //table columns

	public DBOutputWritable(){}
	public DBOutputWritable(long pcode, String name, String brand, long price, String img, String type, String volume,
							String size, String power, String spec)
	{
		this.pcode = pcode;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.img = img;
		this.type = type;
		this.volume = volume;
		this.size = size;
		this.power = power;
		this.spec = spec;
	}

	public void write(PreparedStatement statement) throws SQLException {
		
		statement.setLong(1, pcode);
		statement.setString(2, name);
		statement.setString(3, brand);
		statement.setLong(4, price);
		statement.setString(5, img);
		statement.setString(6, type);
		statement.setString(7, volume);
		statement.setString(8, volume);
		statement.setString(9, power);
		statement.setString(10, spec);
	}

	public void readFields(ResultSet resultSet) throws SQLException {
		
		this.pcode = resultSet.getLong(1);
		this.name = resultSet.getString(2);
		this.brand = resultSet.getString(3);
		this.price =resultSet.getLong(4);
		this.img = resultSet.getString(5);
		this.type = resultSet.getString(6);
		this.volume = resultSet.getString(7);
		this.volume = resultSet.getString(8);
		this.power = resultSet.getString(9);
		this.spec = resultSet.getString(10);
	}

	public void write(DataOutput out) throws IOException {
	}

	public void readFields(DataInput in) throws IOException {
	}
}
