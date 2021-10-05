package com.a406.aircleaner_performance;

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

			// 사용면적 점수
			int areaPoint = getAreaPoint(value.getArea(), value);

			// 먼지센서 점수
			int sensorPoint = getSensorPoint(value.getDust(), value.getSpec());

			// 편의기능 점수
			int convPoint = getConvPoint(value.getSpec());
					// getPcode 이후는 각자 수정
			String result = value.getRanking() + "\t" + value.getPcode() + "\t"
					+ areaPoint + "\t"
					+ sensorPoint + "\t"
					+ convPoint + "\t";

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

			if(jsonObject.containsKey("간편기능")) {
				JSONObject subJson = (JSONObject) jsonObject.get("간편기능");
				count = subJson.size();
			}
			if(count <= 1) {
				convPoint += 20;
			} else if(count <= 2) {
				convPoint += 40;
			} else if(count <= 3) {
				convPoint += 50;
			} else if( count <= 4 ) {
				convPoint += 70;
			} else if( count <= 5 ) {
				convPoint += 80;
			} else if( count <= 6 ) {
				convPoint += 90;
			} else {
				convPoint += 100;
			}
		} catch (ParseException e) {
			System.err.println(e);
		} finally {
			return convPoint;
		}
	}

	private int getSensorPoint(String dust, String spec) {
		int sensorPoint = 0;
		int count = 0;

		if(!dust.equals("null")) count += 1;

		try {
			JSONParser parser = new JSONParser();

			JSONObject jsonObject = (JSONObject) parser.parse(spec);

			if (jsonObject.containsKey("필터정보")) {
				JSONObject subJson = (JSONObject) jsonObject.get("필터정보");
				count += subJson.size();
			}
			if (jsonObject.containsKey("인증마크")) {
				count += 1;
			}
			if (jsonObject.containsKey("헤파필터")) {
				count += 1;
			}

			if(count == 0) {
				sensorPoint += 10;
			} else if(count <= 1) {
				sensorPoint += 20;
			} else if(count <= 2) {
				sensorPoint += 40;
			} else if(count <= 3) {
				sensorPoint += 60;
			} else if(count <= 4) {
				sensorPoint += 80;
			} else {
				sensorPoint += 100;
			}
		} catch (ParseException e) {
			System.err.println(e);
		} finally {
			return sensorPoint;
		}
	}

	private int getAreaPoint(String area, DBInputWritable value) {
		int areaPoint = 0;
		int count = 0;

		area = area.replace("사용면적:", "");
		int lastIdx = area.lastIndexOf("㎡");

		double areaValue = area.equals("null") ? 0 : Double.parseDouble(area.substring(0, lastIdx));
		System.out.println(areaValue);

		if(areaValue < 10) {
			areaPoint += 10;
		} else if( areaValue < 20) {
			areaPoint += 20;
		} else if(areaValue < 30) {
			areaPoint += 30;
		} else if(areaValue < 40) {
			areaPoint += 40;
		}  else if(areaValue < 50) {
			areaPoint += 50;
		} else if(areaValue < 60) {
			areaPoint += 60;
		} else if(areaValue < 70) {
			areaPoint += 70;
		} else if(areaValue < 80) {
			areaPoint += 80;
		} else if(areaValue < 90) {
			areaPoint += 90;
		}else {
			areaPoint += 100;
		}
		return areaPoint;
	}

}
