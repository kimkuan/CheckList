package com.a406.aircleaner_performance;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DBReducer extends Reducer<Text, Text, DBOutputWritable, NullWritable> {

	@Override
	protected void setup(Context context) throws IOException {

	}

	@Override
	protected void reduce(Text key, Iterable<Text> values,
						  Context context)
			throws IOException, InterruptedException {

		// 총 등수
		int lastRank=0;
		Text finalValue = null;

		for (Text value : values) {
			System.out.println("finalValue >>>>>>>>> ");

			// 들어온 값
			// 가격 순위 , 상품코드, 청정면적, 센서, 스펙(json)

			finalValue = value;
			System.out.println(finalValue);
			String[] productValues = finalValue.toString().split("\t");

			// productValues 첫번째 값이 rank, 가장먼저 들어오는 rank가 총 등수(꼴등)이므로 max함수를 통해 for-each 돌면서 값이 변하지 않도록 고정
			lastRank = Math.max(Integer.parseInt(productValues[0]), lastRank);
//			System.out.println(finalValue + " >>>> " + lastRank);

			// price 등수 백분율 산정
			int pricePoint = (int)(Double.parseDouble(productValues[0])/lastRank*100);
			System.out.println("pricePoint >>>>>> " + pricePoint);

			int areaPoint = Integer.parseInt(productValues[2]);
			int sensorPoint = Integer.parseInt(productValues[3]);
			int convPoint = Integer.parseInt(productValues[4]);

			// 스펙 점수
			int specPoint = 0;
			// pcode, 가격점수, ... 순서로 저장됨
			// pcode, 가격점수, 면적점수, 센서점수, 부가기능점수
			DBOutputWritable productRecord = new DBOutputWritable(Long.parseLong(productValues[1]), pricePoint,
					areaPoint, sensorPoint, convPoint);

			context.write(productRecord, NullWritable.get());
		}

	}

}