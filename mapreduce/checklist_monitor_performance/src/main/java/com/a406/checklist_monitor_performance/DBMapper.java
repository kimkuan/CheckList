package com.a406.checklist_monitor_performance;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DBMapper extends Mapper<LongWritable, DBInputWritable, Text, Text> {
	@Override
	protected void map(LongWritable id, DBInputWritable value, Context context) {
		try {

			// getPcode 이후는 각자 수정
			String result = value.getRanking() + "\t" + value.getPcode() + "\t" + "100" + "\t" + "100" + "\t"
					+  value.getHz() + "\t" + "100" + "\t";

			// 하나의 reducer에 모아줘야 가격 백분율 계산에 필요한 총 등수를 알 수 있음
			context.write(new Text("1"), new Text(result));

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
