package com.a406.checklist_price;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import org.apache.hadoop.conf.Configuration;

public class DBMapper extends Mapper<LongWritable, Text, Text, Text> {
	@Override
	protected void map(LongWritable id, Text value, Context context) {
		try {
			String[] oneSplit = value.toString().split("가격");
			String[] twoSplit = new String[3];
			StringBuilder result = new StringBuilder();

			System.out.println("oneSplit >>>>>>>>>>>>>");
			for(int i=0; i<oneSplit.length; i++)
				System.out.println(oneSplit[i]);

			String[] pcodeOneSplit = oneSplit[0].split("\t");
			String[] pcodeTwoSplit = pcodeOneSplit[0].split(":");
			String[] pnameTwoSplit = pcodeOneSplit[1].split(":");
			String pcode = pcodeTwoSplit[1];
			String pname = pnameTwoSplit[1];
			System.out.println("pcode >>>> " + pcode);
			
			for(int i=1; i<6; i++) {

//				1:[{'site_name': '투림컴'}
//				{'site_img_src': '//img.danawa.com/cmpny_info/images/PU101_logo.gif'}
//				{'price': '190000'}]

				System.out.println(oneSplit[i]);
				String[] tmp = oneSplit[i].split(",");
				String siteLink;

				for(int j=0; j<3; j++) {
					String[] tmp2 = tmp[j].split(":");
					String content = tmp2[1];

					if(j==0) {
						twoSplit[j] = tmp2[2].substring(2, tmp2[2].length()-2);
					} else if(j==2)	twoSplit[j] = content.substring(2, content.length()-4);
					else	twoSplit[j] = content.substring(2, content.length()-2);
				}

				switch (twoSplit[0]) {
					case "투림컴":
						siteLink = "https://xn--4y2bx06b.kr/";
						break;
					case "샵다나와":
						siteLink = "http://shop.danawa.com/main/?controller=goods&methods=search&keyword=" + pname;
						break;
					case "SSG.COM":
						siteLink = "http://www.ssg.com/search.ssg?target=all&query=" + pname;
						break;
					case "위메프":
						siteLink = "https://search.wemakeprice.com/search?search_cate=top&keyword=" + pname;
						break;
					case "G마켓":
						siteLink = "https://browse.gmarket.co.kr/search?keyword=" + pname;
						break;
					case "11번가":
						siteLink = "https://search.11st.co.kr/Search.tmall?kwd=" + pname;
						break;
					case "쿠팡":
						siteLink = "https://www.coupang.com/np/search?component=&q=" + pname;
						break;
					case "티몬":
						siteLink = "https://search.tmon.co.kr/search/?keyword=" + pname;
						break;
					case "하이마트 쇼핑몰":
						siteLink = "https://www.e-himart.co.kr/app/search/totalSearch?query=" + pname;
						break;
					case "옥션":
						siteLink = "http://browse.auction.co.kr/search?keyword=" + pname;
						break;
					case "인터파크":
						siteLink = "http://isearch.interpark.com/isearch?q=" + pname;
						break;
					case "GS샵":
						siteLink = "https://with.gsshop.com/shop/search/main.gs?lseq=392814&tq=" + pname;
						break;
					case "신세계몰":
						siteLink = "http://shinsegaemall.ssg.com/search.ssg?target=all&query=" + pname;
						break;
					default:
						siteLink = "https://www.google.com/search?q=" + twoSplit[0] + " " + pname;
				}

				result.append(category).append("\t").append(pcode).append("\t").append(twoSplit[0]).append("\t")
						.append(twoSplit[2]).append("\t").append(siteLink).append("\t").append(twoSplit[1]);

				int random = (int)((Math.random()*10000)%10) + 1;
				context.write(new Text(random+""), new Text(result.toString()));

				result.setLength(0);
			}

			System.out.println("map result >>>> " + result.toString());
			
//			String[] productValues = value.toString().split(",");
//			int random = (int)((Math.random()*10000)%10) + 1;
//
//			context.write(new Text(random+""), new Text(result.toString()));

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private String category;
	protected void setup(Context context) throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		category = conf.get("categoryName");
	}
}
