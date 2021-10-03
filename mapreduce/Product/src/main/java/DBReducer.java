import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DBReducer extends Reducer<Text,Text,DBOutputWritable,NullWritable>{
	
	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, DBOutputWritable, NullWritable>.Context context) throws IOException, InterruptedException {

		Text finalValue = null;
		for(Text value : values){
			finalValue = value;
		}

		 String[] productValues = finalValue.toString().split("\t");
		
		DBOutputWritable productRecord = new DBOutputWritable(
				Long.valueOf(productValues[0]),
				productValues[1],
				productValues[2],
				Long.valueOf(productValues[3]),
				productValues[4],
				productValues[5],
				productValues[6],
				productValues[7],
				productValues[8],
				productValues[9]
				);
		
		context.write(productRecord, NullWritable.get());
	}
}
