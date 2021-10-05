package com.a406.checklist_monitor_performance;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.Iterator;

public class DBMapper extends Mapper<LongWritable, DBInputWritable, Text, Text> {
	@Override
	protected void map(LongWritable id, DBInputWritable value, Context context) {
		try {

			// Input >>>>>>>>
			// pcode	910037
			// price	298000
			// size		55cm(21.5인치)
			// pixel	1920 x 1080(FHD)
			// hz		60
			// spec		{"부가 기능": {"스피커": "○", "터치스크린": "○"}, "스탠드 기능": {"틸트(상하)": "○"}, "영상입력 단자": {"DVI": "○", "D-SUB": "○"}}
			// ranking

			// Output >>>>>>>>> price size pixel screenChange convenience

			int performancePoint = performance(value.getProcess_type(), value.getDecrease());
			int managementPoint = management(value.getProcess_type(), value.getDecrease());
			int processingPoint = processing(value.getProcess_time(), value.getSound());
			int convenPoint = conven(value.getSpec());

			// getPcode 이후는 각자 수정
			String result = value.getRanking() + "\t" + value.getPcode() + "\t" + performancePoint + "\t" + managementPoint + "\t"
					+  processingPoint + "\t" + convenPoint + "\t";

			// 하나의 reducer에 모아줘야 가격 백분율 계산에 필요한 총 등수를 알 수 있음
			context.write(new Text("1"), new Text(result));

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	// 처리성능 performance 점수
	private int performance(String process_type, String decrease) {
		int score = 0;

		if (process_type == null || process_type.equals("null")) {
			score += 25;
		} else {
			if (process_type.equals("습식분쇄")) {
				score += 30;
			} else if (process_type.equals("미생물발효")) {
				score += 40;
			} else if (process_type.equals("분쇄건조")) {
				score += 35;
			} else if (process_type.equals("건조")) {
				score += 25;
			} else {
				score += 25;
			}
		}

		int result = 0;

		if (decrease == null || decrease.equals("null")) {
			score += 25;
		} else {
			// 30~40% , 50%, "35.5"
			String first_filter = decrease.split("%")[0];
			if (first_filter.contains("~")) {
				result = Integer.parseInt(first_filter.split("~")[1]);
			} else {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> result : "+ result);
				result = Integer.parseInt(first_filter);
			}
		}

		if (result == 100) {
			score += 50;
		} else if (result >= 90) {
			score += 45;
		} else if (result >= 80) {
			score += 40;
		} else if (result >= 70) {
			score += 35;
		} else {
			score += 30;
		}

		return score;
	}

	// 세척관리 management 점수
	private int management(String process_type, String decrease) {

		int score = 0;

		if (process_type == null || process_type.equals("null")) {
			score += 25;
		} else {
			if (process_type.equals("습식분쇄")) {
				score += 30;
			} else if (process_type.equals("미생물발효")) {
				score += 40;
			} else if (process_type.equals("분쇄건조")) {
				score += 35;
			} else if (process_type.equals("건조")) {
				score += 25;
			} else {
				score += 25;
			}
		}

		int result = 0;

		if (decrease == null || decrease.equals("null")) {
			score += 25;
		} else {
			String first_filter = decrease.split("%")[0];
			if (first_filter.contains("~")) {
				result = Integer.parseInt(first_filter.split("~")[1]);
			} else {
				result = Integer.parseInt(first_filter);
			}
		}

		if (result == 100) {
			score += 50;
		} else if (result >= 90) {
			score += 45;
		} else if (result >= 80) {
			score += 40;
		} else if (result >= 70) {
			score += 35;
		} else {
			score += 30;
		}

		return score;
	}

	// 처리과정 processing 점수
	private int processing(String process_time, String sound) {
		int score = 0;

		int result = 0;

		if (sound == null || sound.equals("null")) {
			score += 25;
		} else {
			String first_filter = sound.split("dB")[0];
			if (first_filter.contains("~")) {
				result = Integer.parseInt(first_filter.split("~")[1]);
			} else {
				result = Integer.parseInt(first_filter);
			}
		}

		if (result <= 25) {
			score += 50;
		} else if (result <= 30) {
			score += 45;
		} else if (result <= 40) {
			score += 35;
		} else if (result <= 50) {
			score += 30;
		} else {
			score += 25;
		}

		return score;
	}

	// 사용편의 convenience 점수
	private int conven(String spec) throws ParseException {
		int cnt = 0;

		// JSONParser와 JSONObject 모두 json-simple에서 임포트 해야함!!!!!
		// import org.json.simple.parser.JSONParser;
		// import org.json.simple.JSONObject;
		//
		// import org.json.simple.parser.JSONParser;
		// import org.json.JSONObject;				---------> XXXXX

		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(spec);
		System.out.println("object >>>>> " + obj.toString());

		// 50
		if (obj.containsKey("부가기능")) {
			JSONObject tmp = (JSONObject) obj.get("부가기능");
			cnt += tmp.size();
		}

		// 30
		if (obj.containsKey("처리방식")) {
			JSONObject tmp = (JSONObject) obj.get("처리방식");
			cnt += tmp.size();
		}

//		System.out.println("계산 !!" + cnt/total);

		return (int) (cnt/(double)80*100*4);
	}

}
