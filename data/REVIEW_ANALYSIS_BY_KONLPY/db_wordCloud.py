#!/usr/local/bin/python3
# -*- coding: utf-8 -*-


from konlpy.tag import Okt 
from collections import Counter
import pymysql
import json
# DB 연결에 대한 정보는 class로 묶어주기
class db_con:
    host = 'j5checklist.p.ssafy.io'
    user = 'root'
    passwd = '비밀번호'
    db = 'checklist'
    char = 'utf8'
 
 
if __name__ == '__main__':
    # DB를 연결할때 autocommit 부분을 True로 설정해주면, 별도의 커밋없이 자동으로 커밋처리가 되므로 편리합니다.
    conn = pymysql.connect(host=db_con.host, user=db_con.user, password=db_con.passwd, db=db_con.db, charset=db_con.char, autocommit=True)
    curs = conn.cursor(pymysql.cursors.DictCursor)
 
    # 쿼리문을 아래 sql 변수안에 삽입
    sql = "select * from review"
    curs.execute(sql)
    
    # SELECT 했을 경우에 SELECT 데이터를 모두 불러와 저장하는 부분
    rows = curs.fetchall()
    
    # 딕셔너리 생성
    product = {}
    # FOR문을 사용하여 해당 데이터를 차례대로 하나씩 불러오는 부분
    for data in rows:
        pcode = data['pcode']
        content = data['content']

        if pcode in product:
            string = product.get(pcode)
            string += content
            product[pcode] = string
        else:
            product[pcode] = content
        # SELECT 한 데이터에서 가져오고자 하는 컬럼명을 대괄호 안에 넣어줍니다.
        
        # 내가 원하는 데이터가 맞는지 한번 확인합니다.
 
    print("DB 연결 성공")

    # 1. db 연결 => pcode 별로 리뷰 내용 묶기

    # 하나의 상품에 여러 개의 리뷰 => KEY(str) , LIST([str])
 
    # 

    okt = Okt() 

    sql = "insert into review_wordcloud values(%s, %s)"

    
    for p in product.items():
        noun = okt.nouns(p[1]) 

        for i,v in enumerate(noun): 
            if len(v)<2: 
                noun.pop(i) 

        count = Counter(noun) 
        noun_list = count.most_common(100000) 

        json_result = {}
        print("----------------- %d ----------------------"%p[0])
        for v in noun_list:
            data = " ".join(map(str,v))
            print(data)
            result = data.split(" ")
            json_result[result[0]] = result[1]

        print(json.dumps(json_result, indent = 4, ensure_ascii=False))
        val = (p[0], json.dumps(json_result, ensure_ascii=False))

        curs.execute(sql, val)

    print(curs.rowcount, "record inserted")
        


            
            

