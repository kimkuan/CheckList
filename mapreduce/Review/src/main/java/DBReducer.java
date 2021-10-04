import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DBReducer extends Reducer<LongWritable,Text,DBOutputWritable,NullWritable>{
	
	@Override
	protected void reduce(LongWritable key, Iterable<Text> values,
			Reducer<LongWritable, Text, DBOutputWritable, NullWritable>.Context context) throws IOException, InterruptedException {

		Text finalValue = null;
		for(Text value : values){
			finalValue = value;
		}

		String[] reviewValues = finalValue.toString().split("\t");

		// new String[] {"pcode", "category", "name", "title", "content", "score", "site", "date", "user_id"} //table columns
		DBOutputWritable productRecord = new DBOutputWritable(
				Long.valueOf(reviewValues[0]),
				reviewValues[1],
				reviewValues[2],
				reviewValues[3],
				reviewValues[4],
				Integer.valueOf(reviewValues[5]),
				reviewValues[6],
				reviewValues[7],
				reviewValues[8]
				);
		
		context.write(productRecord, NullWritable.get());
	}
}
