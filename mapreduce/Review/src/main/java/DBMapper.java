import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DBMapper extends Mapper<LongWritable, Text, LongWritable, Text> {

	private long id;
	private int increment;

	@Override
	protected void setup(Mapper<LongWritable, Text, LongWritable, Text>.Context context) throws IOException, InterruptedException {
		super.setup(context);

		id = context.getTaskAttemptID().getTaskID().getId();
		increment = context.getConfiguration().getInt("mapred.map.tasks", 0);

		if(increment == 0)
			throw new IllegalArgumentException("mapred.map.tasks is zero");
	}

	@Override
	protected void map(LongWritable key, Text value, Context context) {
		try 
		{
			// 15242777	AirFryer	리빙메이트 GLA-506	간편합니다	용량도 크고 LED터치형에 디자인도 깔끔한데 좋은가격에 잘 구매했어요.제일 좋았던 점은 세척을 깨끗하게 할 수 있다는 거였어요. 바스켓이 분리가 되니까 깔끔하게 닦을 수 있으니까 위생걱정 전혀없어서 좋아요.그리고 용량이 넉넉해서 삼겹살 구울때도 나눠서 조리하지 않아도 되니까 시간도 훨씬 절약되고열선 커버가 잘 되있어서 종이호일 사용해도 안전합니다 탄내도 안나고 바스켓꺼내면 자동으로 꺼지니까 안심이됩니다. 아이들 간식해줄때도 간편하게 잘 쓰고있어요.
			// 100	G마켓	2021.08.19.	au****
			// "['<img src=""http://bampic.gmarket.co.kr/v1/885/195/2189195885/00877/2189195885356985687700.jpg"" alt=""용량도 크고 LED터치형에 디자인도 깔..."" onerror=""this.src=\'http://img.danawa.com/new/noData/img/noImg_160.gif\'"">']"

			id += increment;

			StringTokenizer token = new StringTokenizer(value.toString(), "\t");
			StringBuilder sb = new StringBuilder();
			String review[] = new String[9];

			for (int i = 0; i < 9; i++) {
				review[i] = token.nextToken();
			}

			for (int i = 0; i < 9; i++){
				sb.append(review[i] + "\t");
			}

			context.write(new LongWritable(id), new Text(sb.toString()));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}