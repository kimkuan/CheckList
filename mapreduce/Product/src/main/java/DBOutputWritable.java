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
	private String img;
	private long price;
	private String process_type;
	private String process_time;
	private String decrease;
	private String size;
	private String sound;
	private String spec;

	// new String[] {"pcode", "name", "brand", "price", "img", "type", "volume", "size", "power", "spec"} //table columns

	public DBOutputWritable(){}
	public DBOutputWritable(long pcode, String name, String brand, String img, long price, String process_type, String process_time,
							String decrease, String size, String sound, String spec)
	{
		this.pcode = pcode;
		this.name = name;
		this.brand = brand;
		this.img = img;
		this.price = price;
		this.process_type = process_type;
		this.process_time = process_time;
		this.decrease = decrease;
		this.size = size;
		this.sound = sound;
		this.spec = spec;
	}

	public void write(PreparedStatement statement) throws SQLException {
		
		statement.setLong(1, pcode);
		statement.setString(2, name);
		statement.setString(3, brand);
		statement.setString(4, img);
		statement.setLong(5, price);
		statement.setString(6, process_type);
		statement.setString(7, process_time);
		statement.setString(8, decrease);
		statement.setString(9, size);
		statement.setString(10, sound);
		statement.setString(11, spec);
	}

	public void readFields(ResultSet resultSet) throws SQLException {
		
		this.pcode = resultSet.getLong(1);
		this.name = resultSet.getString(2);
		this.brand = resultSet.getString(3);
		this.img = resultSet.getString(4);
		this.price =resultSet.getLong(5);
		this.process_type = resultSet.getString(6);
		this.process_time = resultSet.getString(7);
		this.decrease = resultSet.getString(8);
		this.size = resultSet.getString(9);
		this.sound = resultSet.getString(10);
		this.spec = resultSet.getString(11);
	}

	public void write(DataOutput out) throws IOException {
	}

	public void readFields(DataInput in) throws IOException {
	}
}
