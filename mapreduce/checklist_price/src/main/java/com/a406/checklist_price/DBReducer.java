package com.a406.checklist_price;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Reducer;

public class DBReducer extends Reducer<Text, Text, DBOutputWritable, NullWritable> {

	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, DBOutputWritable, NullWritable>.Context context)
			throws IOException, InterruptedException {

		System.out.println("reducer 들어는 오는지?");
		Text finalValue = null;

		for (Text value : values) {
			System.out.println("finalValue >>>>>>>>> ");
			finalValue = value;
			System.out.println(finalValue);

			String[] productValues = finalValue.toString().split("\t");
			DBOutputWritable productRecord = new DBOutputWritable(productValues[0], Long.parseLong(productValues[1]),
					productValues[2], Long.parseLong(productValues[3]), productValues[4], productValues[5]);

			context.write(productRecord, NullWritable.get());
		}

//		String[] productValues = finalValue.toString().split(",");
//		String[] productValues = finalValue.toString().split("\t");
//
//		System.out.println("reducer >>>>>>>>>>");
//		for(int i=0; i<productValues.length; i++) {
//			System.out.println(productValues[i]);
//		}
//		System.out.println();
//
//		DBOutputWritable productRecord = new DBOutputWritable(productValues[0], Long.parseLong(productValues[1]),
//				productValues[2], Long.parseLong(productValues[3]), productValues[4], productValues[5]);
//
//		context.write(productRecord, NullWritable.get());
	}

}
