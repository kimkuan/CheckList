# 다나와 리뷰 크롤러

0️⃣ 본인의 크롬 버전에 맞게 chromedriver.exe를 다운받아서 변경한다. (설치된 chromedriver는 94버전입니다.) <br>
 & 파이썬은 반드시! 3.7 버전 사용 <br>
 & ```pip install scrapy``` <br>
 & ```pip install selenium``` <br>

1️⃣  ```ReviewCrawlingCategory.csv``` 파일에서 
```
CoffeeMachine, "http://prod.danawa.com/list/?cate=1032061", 2
Monitor, "http://prod.danawa.com/list/?cate=112757", 38
AirFryer, "http://prod.danawa.com/list/?cate=10338814", 13
AirCleaner, "http://prod.danawa.com/list/?cate=10331612", 19
FoodProcessor, "http://prod.danawa.com/list/?cate=1032269", 6
```
본인의 카테고리만 남기고, 모두 지운다. 위 주석은 제외하고 지우면 됩니다.

2️⃣ 이상태에서 돌리면 끝~~!



### 제작에 사용된 것들

- Python : 3.7
- Scrapy : 2.1.0
- selenium : 3.141.0
- Chromedriver : 2.40 (window 32/64)
