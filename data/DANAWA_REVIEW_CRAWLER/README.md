# 다나와 리뷰 크롤러

0. 본인의 크롬 버전에 맞게 chromedriver.exe를 다운받아서 변경한다. (설치된 chroedriver는 94버전입니다.) 
 & ```pip install scrapy```
 & ```pip install selenium```

1. ```ReviewCrawlingCategory.csv``` 파일에서 
```
CoffeeMachine, "http://prod.danawa.com/list/?cate=1032061", 2
Monitor, "http://prod.danawa.com/list/?cate=112757", 38
AirFryer, "http://prod.danawa.com/list/?cate=10338814", 13
AirCleaner, "http://prod.danawa.com/list/?cate=10331612", 19
FoodProcessor, "http://prod.danawa.com/list/?cate=1032269", 6
```
본인의 카테고리만 남기고, 모두 지운다. 위 주석은 제외하고 지우면 됩니다.

2. 이상태에서 돌리면 끝~~!






## [크롤링 데이터](https://github.com/sammy310/Danawa_Crawler/tree/master/crawl_data)
- [CPU](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=CPU) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/CPU.csv)
- [그래픽카드](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=VGA) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/VGA.csv)

- [마더보드](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=MBoard) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/MBoard.csv)
- [램](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=RAM) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/RAM.csv)

- [SSD](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=SSD) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/SSD.csv)
- [HDD](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=HDD) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/HDD.csv)

- [쿨러](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=Cooler) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/Cooler.csv)
- [케이스](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=Case) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/Case.csv)
- [파워](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=Power) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/Power.csv)

- [모니터](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=Monitor) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/Monitor.csv)

- [스피커](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=Speaker) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/Speaker.csv)
- [헤드폰](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=Headphone) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/Headphone.csv)
- [이어폰](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=Earphone) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/Earphone.csv)
- [헤드셋](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=Headset) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/Headset.csv)

- [키보드](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=Keyboard) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/Keyboard.csv)
- [마우스](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=Mouse) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/Mouse.csv)

- [노트북](https://sammy310.github.io/csv_viewer/CSV_Viewer.html?category=Laptop) / [(데이터 파일)](https://github.com/sammy310/Danawa_Crawler/blob/master/crawl_data/Laptop.csv)


---

### 제작에 사용된 것들

- Python : 3.7
- Scrapy : 2.1.0
- selenium : 3.141.0
- Chromedriver : 2.40 (window 32/64)
