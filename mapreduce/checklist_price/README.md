# checklist_price.jar 실행하는 방법

1. `hdfs dfs -put 파일명 /input/카테고리명`

ex) `hdfs dfs -put monitor_detail.txt /input/monitor`

→ 크롤링 결과 텍스트 파일을 /input/각카테고리 경로에 복사

<br>

2. `hadoop jar checklist_price.jar /input/카테고리명 카테고리명`

ex) `hadoop jar checklist_price.jar /input/monitor monitor`

→ 텍스트 파일을 복사해놓은 경로 + 카테고리명 쓰고  jar 파일 실행
