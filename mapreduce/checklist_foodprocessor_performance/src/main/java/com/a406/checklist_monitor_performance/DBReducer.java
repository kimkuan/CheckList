package com.a406.checklist_monitor_performance;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DBReducer extends Reducer<Text, Text, DBOutputWritable, NullWritable> {

	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, DBOutputWritable, NullWritable>.Context context)
			throws IOException, InterruptedException {

		// 총 등수
		int lastRank=0;
		Text finalValue = null;

		for (Text value : values) {
			System.out.println("finalValue >>>>>>>>> ");
			finalValue = value;
			
			String[] productValues = finalValue.toString().split("\t");

			// productValues 첫번째 값이 rank, 가장먼저 들어오는 rank가 총 등수(꼴등)이므로 max함수를 통해 for-each 돌면서 값이 변하지 않도록 고정
			lastRank = Math.max(Integer.parseInt(productValues[0]), lastRank);
			System.out.println(finalValue + " >>>> " + lastRank);

			// price 등수 백분율 산정 
			int pricePoint = (int)(Double.parseDouble(productValues[0])/lastRank*100);
			System.out.println("pricePoint >>>>>> " + pricePoint);

			// pcode, 가격점수, ... 순서로 저장됨
			DBOutputWritable productRecord = new DBOutputWritable(Long.parseLong(productValues[1]), pricePoint,
					Integer.parseInt(productValues[2]), Integer.parseInt(productValues[3]), Integer.parseInt(productValues[4]), Integer.parseInt(productValues[5]));

			context.write(productRecord, NullWritable.get());
		}

	}

}
