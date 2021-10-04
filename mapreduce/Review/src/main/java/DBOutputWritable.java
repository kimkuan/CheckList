import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOutputWritable implements Writable, DBWritable{

	private long pcode;
	private String category;
	private String name;
	private String title;
	private String content;
	private int score;
	private String site;
	private String date;
	private String userId;
	private String image;

	// new String[] {"pcode", "category", "name", "title", "content", "score", "site", "date", "user_id"} //table columns

	public DBOutputWritable(){}
	public DBOutputWritable(long pcode, String category, String name, String title, String content, int score, String site, String date, String userId, String image)
	{
		this.pcode = pcode;
		this.category = category;
		this.name = name;
		this.title = title;
		this.content = content;
		this.date = date;
		this.score = score;
		this.site = site;
		this.userId = userId;
		this.image = image;
	}

	public void write(PreparedStatement statement) throws SQLException {
		
		statement.setLong(1, pcode);
		statement.setString(2, category);
		statement.setString(3, name);
		statement.setString(4, title);
		statement.setString(5, content);
		statement.setInt(6, score);
		statement.setString(7, site);
		statement.setString(8, date);
		statement.setString(9, userId);
		statement.setString(10, image);
	}

	public void readFields(ResultSet resultSet) throws SQLException {

		this.pcode = resultSet.getLong(1);
		this.category = resultSet.getString(2);
		this.name = resultSet.getString(3);
		this.title = resultSet.getString(4);
		this.content = resultSet.getString(5);
		this.score = resultSet.getInt(6);
		this.site = resultSet.getString(7);
		this.date =resultSet.getString(8);
		this.userId = resultSet.getString(9);
		this.image = resultSet.getString(10);
	}

	public void write(DataOutput out) throws IOException {
	}

	public void readFields(DataInput in) throws IOException {
	}
}
