import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DBMapper extends Mapper<LongWritable, Text, Text, Text> {

	static class Spec {
		String key;
		String value;
		public Spec(String key, String value){
			this.key = key;
			this.value = value;
		}
	}

	@Override
	protected void map(LongWritable key, Text value, Context context) {
		try 
		{
			// pcode:7098889   상품이름:대우어플라이언스 DEF-DC333   상품이미지:http://img.danawa.com/prod_img/500000/889/098/img/7098889_1.jpg?shrink=330:330&_v=20201109161326
			// 가격1:[{'site_name': '티몬'}, {'site_img_src': '//img.danawa.com/cmpny_info/images/TN920_logo.gif'}, {'price': '73000'}]
			// 제조회사:대우어플라이언스   등록년월:2018년 06월    품목:에어프라이어   형태:바스켓형   용량:2.6L   소비전력:1350W
			// 대분류:조작부   버튼:○   디지털디스플레이:○   대분류:편의기능   분리형용기:○   열선보호망:○   온도조절:80~200℃   시간조절:1~30분   크기(가로x세로x깊이):270x315x300mm

			StringTokenizer token = new StringTokenizer(value.toString(), "\t");
			StringBuilder sb = new StringBuilder();
			String[] product = new String[10];
			ArrayList<Spec> specList = new ArrayList<>();
			boolean startSpec = false;

			while(token.hasMoreTokens()) {
				String keyValue = token.nextToken();
				int index = keyValue.indexOf(":"); // 첫 번째 ":"가 나오는 인덱스

				String[] str = new String[2];
				str[0] = keyValue.substring(0, index);
				str[1] = keyValue.substring(index + 1, keyValue.length());

				if (str[0].equals("대분류"))
					startSpec = true;

				if (str[0].equals("pcode")) {
					product[0] = str[1];
				} else if (str[0].equals("상품이름")) {
					product[1] = str[1];
				} else if (str[0].equals("제조회사")) {
					product[2] = str[1];
				} else if (str[0].equals("가격1")) {
					int priceStartIndex = str[1].lastIndexOf(":");
					String tmp = str[1].substring(priceStartIndex + 1, str[1].length()); // (공백)'73000'}]
					product[3] = tmp.split("'")[1]; // 작은 따옴표를 기준으로 split
				} else if (str[0].equals("상품이미지")) {
					product[4] = str[1];
				} else if (str[0].equals("형태")) {
					product[5] = str[1];
				} else if (str[0].equals("용량")) {
					product[6] = str[1];
				} else if (str[0].startsWith("크기")) {
					product[7] = str[1];
				} else if (str[0].equals("소비전력")) {
					product[8] = str[1];
				} else {
					if(startSpec) // 대분류 시작부터 specList에 담기
						specList.add(new Spec(str[0], str[1]));
				}
			}
			product[9] = makeSpecToJson(specList); // 전체 스펙

			for (int i = 0; i < 10; i++){
				sb.append(product[i] + "\t");
			}
			context.write(new Text(product[0]), new Text(sb.toString()));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private String makeSpecToJson(ArrayList<Spec> specList){

		// specList => ['대분류:조작부', '버튼:○', '디지털디스플레이:○', '대분류:편의기능', '분리형용기:○', '열선보호망:○', '온도조절:80~200℃', '시간조절:1~30분', '크기(가로x세로x깊이):270x315x300mm' ]
		// json =>  { "조작부":{"버튼": "○", "디지털디스플레이": "○"}, "편의기능":{"분리형용기": "○", "열선보호망":"○",  ... } }

		// 추가 스펙이 존재하지 않으면 빈 JSON 반환
		if(specList.size() == 0)
			return "{}";

		JSONObject rootJson = new JSONObject();
		JSONObject subJson = new JSONObject();
		String partName = "";

		for (Spec spec : specList) {
			if(spec.key.equals("대분류")) {
				if(!partName.equals("")) {
					// 만들어 subJSON을 rootJSON에 넣기
					rootJson.put(partName, subJson);
				}
				partName = spec.value;
				subJson = new JSONObject();
			}else {
				subJson.put(spec.key, spec.value);
			}
		}
		// 마지막에 만들어진 subJSON도 rootJSON에 넣기
		rootJson.put(partName, subJson);
		//System.out.println(rootJson.toString());

		return rootJson.toString();
	}
}