package com.a406.checklist_monitor_performance;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DBMapper extends Mapper<LongWritable, DBInputWritable, Text, Text> {
	@Override
	protected void map(LongWritable id, DBInputWritable value, Context context) {
		try {

			// 추출점수
			int brewingPoint = getBrewingPoint(value.getPressure(), value.getHeatTime(), value);

			// 용량 점수
			int volumePoint = getVolumePoint(value.getWaterVolume());

			// 편의 점수
			int convPoint = getConvPoint(value.getSpec());
					// getPcode 이후는 각자 수정
			String result = value.getRanking() + "\t" + value.getPcode() + "\t" + brewingPoint + "\t" + volumePoint + "\t"
					+  convPoint + "\t";

			// 하나의 reducer에 모아줘야 가격 백분율 계산에 필요한 총 등수를 알 수 있음
			context.write(new Text("1"), new Text(result));

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private int getConvPoint(String spec) {
		int convPoint = 0;

		int count = 0;
		try {
			JSONParser parser = new JSONParser();

			JSONObject jsonObject = (JSONObject) parser.parse(spec);

			if(jsonObject.containsKey("부가기능")) {
				JSONObject subJson = (JSONObject) jsonObject.get("추출기능");
				count = subJson.size();
			}
			if(count == 0) {
				convPoint += 1;
			} else if(0 < count && count < 2) {
				convPoint += 20;
			} else if(count < 3) {
				convPoint += 40;
			} else if( count < 5 ) {
				convPoint += 60;
			} else if( count < 6 ) {
				convPoint += 80;
			} else {
				convPoint += 100;
			}
		} catch (ParseException e) {
			System.err.println(e);
		} finally {
			return convPoint;
		}

	}

	private int getVolumePoint(String waterVolume) {
		int volumePoint = 0;
		if(waterVolume.equals("null")) return 0;

		double volume = Double.parseDouble(waterVolume.replace("L",""));

		if(volume <= 0.5) {
			volumePoint += 20;
		} else if(volume < 1) {
			volumePoint += 40;
		} else if(volume < 2) {
			volumePoint += 60;
		} else if(volume < 4) {
			volumePoint += 80;
		} else {
			volumePoint = 100;
		}
		return volumePoint;
	}

	private int getBrewingPoint(String pressure, String heatTime, DBInputWritable value) {
		// 펌프압력
		int brewingPoint = 0;
		double pressureValue = pressure.equals("null") ? 0 : Double.parseDouble(pressure.replace("bar", ""));
		if(pressureValue == 0) {
			brewingPoint += 0;
		} else if( pressureValue < 15) {
			brewingPoint += 5;
		} else if(pressureValue == 15) {
			brewingPoint += 10;
		} else if(pressureValue == 17) {
			brewingPoint += 15;
		} else {
			brewingPoint += 20;
		}

		// 예열시간
		if(heatTime.equals("null")) {

		} else {
			int heatTimeValue = 0;
			if(heatTime.equals("3초")) {
				heatTimeValue = 3;
			} else {
				heatTimeValue = heatTime.contains("초") ? Integer.parseInt(heatTime.replace("초", "").substring(0,2)) : Integer.parseInt(heatTime.replace("분", "")) * 60;
			}
			
			if(heatTimeValue < 20) {
				brewingPoint += 30;
			} else if(heatTimeValue < 30) {
				brewingPoint += 20;
			} else if(heatTimeValue <= 60) {
				brewingPoint += 10;
			} else {
				brewingPoint += 5;
			}
		}

		// 추출 기능
		int count = 0;
		try {
			JSONParser parser = new JSONParser();

			JSONObject jsonObject = (JSONObject) parser.parse(value.getSpec());

			if(jsonObject.containsKey("추출기능")) {
				JSONObject subJson = (JSONObject) jsonObject.get("추출기능");
				count = subJson.size();
			}

			if(0 < count && count < 3) {
				brewingPoint += 10;
			} else if(count < 5) {
				brewingPoint += 20;
			} else if( count < 7 ) {
				brewingPoint += 30;
			} else if( count < 9 ) {
				brewingPoint += 40;
			} else {
				brewingPoint += 50;
			}
		} catch (ParseException e) {
			System.err.println(e);
		} finally {
			return brewingPoint;
		}
	}

}
