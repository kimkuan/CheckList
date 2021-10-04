package com.a406.checklist_monitor_performance;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class DBMapper extends Mapper<LongWritable, DBInputWritable, Text, Text> {
	private static final double total = 35.0;

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

			int sizePoint = size(value.getSize());
			int pixelPoint = pixel(value.getPixel());
			int hzPoint = hertz(value.getHz());
			int convenPoint = conven(value.getSpec());

			// getPcode 이후는 각자 수정
			String result = value.getRanking() + "\t" + value.getPcode() + "\t" + sizePoint + "\t" + pixelPoint + "\t"
					+  hzPoint + "\t" + convenPoint + "\t";

			// 하나의 reducer에 모아줘야 가격 백분율 계산에 필요한 총 등수를 알 수 있음
			context.write(new Text("1"), new Text(result));

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	// 화면크기 size 점수
	private int size(String inputSize) {
		int idx1 = inputSize.indexOf("(");
		int idx2 = inputSize.indexOf("인");
		int inch = Integer.parseInt(inputSize.substring(idx1+1, idx2));

		int score = 0;

		if(inch < 17)	score = 20;
		else if(inch < 20)	score = 40;
		else if(inch < 25)	score = 60;
		else if(inch < 32)	score = 80;
		else	score = 100;

		return score;
	}

	// 화질/해상도 pixel 점수
	private int pixel(String inputSize) {
		int idx = inputSize.indexOf("x");
		int pixel = Integer.parseInt(inputSize.substring(0, idx-1));

		int score = 0;

		if(pixel < 1920)	score = 20;
		else if(pixel <= 2560)	score = 40;
		else if(pixel < 3840)	score = 60;
		else if(pixel <= 4096)	score = 80;
		else	score = 100;

		return score;
	}

	// 주사율 screenChange 점수
	private int hertz(int hz) {
		int score = 0;

		if(hz <= 60)	score = 20;
		else if(hz <= 144)	score = 40;
		else if(hz < 180)	score = 60;
		else if(hz < 280)	score = 80;
		else	score = 100;

		return score;
	}

	// 부가기능 convenience 점수
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

		// 20
		if (obj.containsKey("부가 기능")) {
			JSONObject tmp = (JSONObject) obj.get("부가 기능");
			cnt += tmp.size();
		}

		// 7
		if (obj.containsKey("스탠드 기능")) {
			JSONObject tmp = (JSONObject) obj.get("스탠드 기능");
			cnt += tmp.size();

			if (tmp.containsKey("무게")) cnt--;
		}

		// 3
		if (obj.containsKey("시력보호 기능")) {
			JSONObject tmp = (JSONObject) obj.get("시력보호 기능");
			cnt += tmp.size();
		}

		// 5
		if (obj.containsKey("화면 기능")) {
			JSONObject tmp = (JSONObject) obj.get("화면 기능");
			cnt += tmp.size();
		}

//		System.out.println("계산 !!" + cnt/total);

		return (int) (cnt/total*100*1.5);
	}

}
