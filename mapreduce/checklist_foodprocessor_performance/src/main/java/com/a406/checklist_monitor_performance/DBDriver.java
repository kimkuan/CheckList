package com.a406.checklist_monitor_performance;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class DBDriver extends Configured implements Tool {

	public int lastRank=0;

	@Override
	public int run(String[] args) throws Exception {

		Configuration conf = new Configuration();

		DBConfiguration.configureDB(conf, "com.mysql.jdbc.Driver", // driver class
				"jdbc:mysql://localhost:3306/checklist?serverTimezone=UTC", // db url
				"root", // user name
				"ssafy406!@!@"); // password
		Job job = Job.getInstance(conf);
		job.setJarByClass(DBDriver.class);
		job.setMapperClass(com.a406.checklist_monitor_performance.DBMapper.class);
		job.setReducerClass(com.a406.checklist_monitor_performance.DBReducer.class);

		// job.setReducerClass(DBReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);

		job.setOutputKeyClass(com.a406.checklist_monitor_performance.DBOutputWritable.class);
		job.setOutputValueClass(NullWritable.class);

		job.setInputFormatClass(DBInputFormat.class);
		job.setOutputFormatClass(DBOutputFormat.class);

		// setInput 1 conditions?
//		DBInputFormat.setInput(job, DBInputWritable.class, "monitor_product", // input table name
//				null, null, new String[] { "pcode", "price", "size", "pixel", "hz", "spec" } // table columns
//		);

		// setInput 2 inputQuery
		// price 이후 ~ DENSE_RANK() 이전에 각 product에서 가져올 컬럼 작성
		DBInputFormat.setInput(job, DBInputWritable.class,
				"select pcode, price, process_type, process_time, decrease, size, sound, spec, DENSE_RANK() over (order by price desc) as ranking from foodprocessor_product",	// query
				"select count(pcode) from foodprocessor_product"	// null 주면 오류남
		);

		// DBOutputWritable 포맷에 맞게 string 배열 수정
		// db의 컬럼명과 일치해야 함
		DBOutputFormat.setOutput(job, "foodprocessor_performance", // output table name
				new String[] { "pcode", "price", "performance", "management", "processing", "convenience" } // table columns
		);
		System.exit(job.waitForCompletion(true) ? 0 : 1);

		return 0;
	}

	public static void main(String[] args) {

		try {

			int result = ToolRunner.run(new Configuration(), new DBDriver(), args);

			System.out.println("job status ::" + result);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

}
