# -*- coding: utf-8 -*-

# danawa_cralwer.py
# sammy310


from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from scrapy.selector import Selector

import datetime
from datetime import timedelta
import csv
import os
import os.path
import shutil
import time
from multiprocessing import Pool

PROCESS_COUNT = 5

CRAWLING_DATA_CSV_FILE = 'ReviewCrawlingCategory.csv' # 커피머신의 리뷰데이터 가져올 것
# DATA_PATH 까지는 폴더를 생성해야 정상적으로 파일이 들어감
DATA_PATH = 'review_crawl_data'
DATA_REFRESH_PATH = f'{DATA_PATH}/Last_Data'

# CHROMEDRIVER_PATH = 'chromedriver_92.exe'
CHROMEDRIVER_PATH = 'chromedriver.exe'

UTC_TIME = 9

DATA_DIVIDER = '---'
DATA_REMARK = '//'
DATA_ROW_DIVIDER = '_'
DATA_PRODUCT_DIVIDER = '|'

STR_ID = 'id'
STR_NAME = 'name'
STR_URL = 'url'
STR_CRAWLING_PAGE_SIZE = 'crawlingPageSize'

# 크롤링 페이지 설정 시 90개로 선택한 후의 페이지 개수를 넣어야함
class DanawaCrawler:

    # 카테고리 csv 파일 읽는 작업 (카테고리 5개 다 넣을 수 있을듯)
    def __init__(self):
        self.crawlingCategory = list()
        with open(CRAWLING_DATA_CSV_FILE, 'r', newline='') as file:
            for crawlingValues in csv.reader(file, skipinitialspace=True):
                
                if not crawlingValues[0].startswith(DATA_REMARK):
                    self.crawlingCategory.append({STR_NAME: crawlingValues[0], STR_URL: crawlingValues[1], STR_CRAWLING_PAGE_SIZE: int(crawlingValues[2])})

    def StartCrawling(self):
        # 셀레니움 구동 옵션 설정
        self.chrome_option = webdriver.ChromeOptions()
        self.chrome_option.add_argument('--headless')
        self.chrome_option.add_argument('--window-size=1920,1080')
        self.chrome_option.add_argument('--start-maximized')
        self.chrome_option.add_argument('--disable-gpu')
        self.chrome_option.add_argument('lang=ko=KR')

        # 병렬처리해주는 작업 -> 우리는 PROCESS_COUNT를 5개로 지정한다. 
        if __name__ == '__main__':
            pool = Pool(processes=PROCESS_COUNT)
            pool.map(self.CrawlingCategory, self.crawlingCategory)
            pool.close()
            pool.join()
 
    def CrawlingCategory(self, categoryValue):

        # 이전에 진행한 상품번호를 배열에 받아옴
        storedPcode = []
        with open('storedPcode.csv', 'r', encoding='utf8') as f:
            reader = csv.reader(f)
            storedPcode = list(reader)[0:]

        print("이미 저장한 상품 번호 리스트")
        print(storedPcode)

        crawlingName = categoryValue[STR_NAME]
        crawlingURL = categoryValue[STR_URL]
        crawlingSize = categoryValue[STR_CRAWLING_PAGE_SIZE]

        # data
        crawlingFile = open(f'{crawlingName}.csv', 'a', newline='', encoding='utf8')
        crawlingData_csvWriter = csv.writer(crawlingFile)
        # crawlingData_csvWriter.writerow([(datetime.datetime.now() + timedelta(hours=UTC_TIME)).strftime('%Y-%m-%d %H:%M:%S')])

        # stored pcode
        storedPcodeFile = open(f'storedPcode.csv', 'a', newline='', encoding='utf8')
        storedPcode_cvsWriter = csv.writer(storedPcodeFile)

        print('Crawling Start Category: ' + crawlingName)

        browser = webdriver.Chrome(CHROMEDRIVER_PATH, chrome_options=self.chrome_option)
        browser.implicitly_wait(3) # 웹페이지 로딩 기다리는 것 -> 5초로 늘려도 괜찮을 듯
        browser.get(crawlingURL)

        # 90개씩 상품이 뜨도록
        browser.find_element_by_xpath('//option[@value="90"]').click()
    
        wait = WebDriverWait(browser,3)
        # 'product_list_cover' : 로딩되기 전 페이지 -> 이게 안 보일 때까지 기다린다
        wait.until(EC.invisibility_of_element((By.CLASS_NAME, 'product_list_cover')))
        
        # 커피머신 전체 목록 가져오는 작업
        for i in range(1, crawlingSize):
            if i % 10 == 0:
                browser.find_element_by_xpath('//a[@class="edge_nav nav_next"]').click()
            elif i % 10 != 1:
                browser.find_element_by_xpath('//a[@class="num "][%d]'%(i)).click()
            elif i == 1:
                browser.find_element_by_xpath('//li[@data-sort-method="NEW"]').click()
            wait.until(EC.invisibility_of_element((By.CLASS_NAME, 'product_list_cover')))
            
            # html = browser.find_element_by_xpath('//div[@class="main_prodlist main_prodlist_list"]').get_attribute('outerHTML')
            # selector = Selector(text=html)
            
            # 목록에 있는 아이디 뽑아내기 & 리뷰 없는 건 그냥 건너뜀
            productList = browser.find_elements_by_xpath('//li[@class="prod_item prod_layer "]')

            productIds = []
            productNames = []
            count = 0
            for product in productList :
                productHtml = product.get_attribute('outerHTML')
                selector = Selector(text = productHtml)

                productReviews = selector.xpath('//dl[@class="meta_item mt_comment"]').get()
                if productReviews != None :
                    count += 1
                    productHtml = product.get_attribute('outerHTML')
                    # print(selector.xpath('//@id').get())
                    # print(selector.xpath('//a[@name="productName"]/text()').get())
                    # print("----------------------------끝--------------------------------")
                    productIds.append(selector.xpath('//@id').get())
                    if not productIds:
                         productIds.append(selector.xpath('//li[@class="prod_item prod_layer width_change"]/@id').get())
                    productNames.append(selector.xpath('//a[@name="productName"]/text()').get())


            print("최종 >>", count)
            # productIds = product.xpath('//@id').getall()
            # if not productIds:
            #     productIds = product.xpath('//li[@class="prod_item prod_layer width_change"]/@id').getall()
            # productNames = product.xpath('//a[@name="productName"]/text()').getall()

            # 상품별 로직
            for j in range(len(productIds)) :
                productId = productIds[j][11:]
                productIdURL = f'&pcode={productId}'
                productName = productNames[j].strip()
                
                # 이미 리뷰가 저장된 상품이면 다음 상품 진행
                exit = False
                for pcode in storedPcode :
                    if productId == pcode[0] :
                        exit = True
                        break
                
                if exit == True :
                    print(productId + "는 이미 저장되었습니다 -> PASS")
                    continue

                browser = webdriver.Chrome(CHROMEDRIVER_PATH, chrome_options=self.chrome_option)
                browser.implicitly_wait(5) # 웹페이지 로딩 기다리는 것 -> 5초로 늘려도 괜찮을 듯
                crawlingURL = categoryValue[STR_URL].replace('list', 'info')

                crawlingURL += productIdURL
                browser.get(crawlingURL)

                wait = WebDriverWait(browser,5)
                
                print(crawlingURL)
                
                if crawlingName == "Monitor" :
                    browser.find_element_by_xpath('//a[@id="danawa-prodBlog-productOpinion-button-tab-companyReview"]').click()
                
                time.sleep(5)

                # 상품
                htmlProduct = browser.find_element_by_xpath('//*[@id="blog_content"]/div[2]/div[1]/div/div[1]/div/dl/dd/div/div/span[1]').get_attribute('outerHTML')
                selectorProduct = Selector(text=htmlProduct)
                productCategory = selectorProduct.xpath('//span/u/text()').get()

                print(productCategory)

                if productCategory == '액세서리' :
                    continue

                # 현재 저장하는 상품의 ID를 storedPcode.csv에 저장
                storedPcode_cvsWriter.writerow([productId])
                storedPcodeFile.flush() # 실시간 업데이트
                
                htmlReview = browser.find_element_by_xpath('//div[@class="mall_review"]').get_attribute('outerHTML')
                selectorReview = Selector(text=htmlReview)
                productCrawlingSize = selectorReview.xpath('//div[@class="point_num"]/span[@class="cen_w"]/strong/text()').getall()
                # print('>>> productId : ' + productId)
                # print('>>> productName : ' + productName)
                for k in range(len(productCrawlingSize)) :
                    # print('>>> 해당 상품 리뷰 데이터 개수 : ' + productCrawlingSize[k])
                    # 1000 단위로 ','이 들어오므로 해당 문자 제거
                    productCrawlingSize[k] = productCrawlingSize[k].replace(",", "") 

                print(productId)
                if productCrawlingSize :
                    # 리뷰 페이지마다
                    # 리뷰 페이지 개수만큼
                    reviewCrawlingSize = int(int(productCrawlingSize[0]) / 10)
                    if int(productCrawlingSize[0]) % 10 != 0:
                        reviewCrawlingSize += 1
                    
                    # 리뷰 171개 -> reviewCrawlingSize : 18개
                    # 크롤링할 페이지 개수가 100개가 넘으면 100개 이하로만 수집
                    if reviewCrawlingSize > 100 : 
                        reviewCrawlingSize = 100
                    print("크롤링할 페이지 개수 >>> ", reviewCrawlingSize)

                    for k in range(1, reviewCrawlingSize+1) :
                        # print("********** cur page >> {}".format(k), '***********')
                        if k % 10 == 0:
                            browser.find_element_by_xpath('//a[@class="nav_edge nav_edge_next nav_edge_on"]').click()
                        elif k % 10 != 1:
                            # 한 페이지 클릭
                            browser.find_element_by_xpath(f'/html/body/div[2]/div[3]/div[2]/div[4]/div[4]/div/div[3]/div[2]/div[2]/div[2]/div[5]/div/div/a[{(k-1) % 10}]').click()

                        # 한 페이지 내에서 최대 10개씩 리뷰 있음
                        wait = WebDriverWait(browser,5)                        

                        time.sleep(5) 

                        # 이동한 페이지의 리뷰를 새로 읽어옴
                        htmlReview = browser.find_element_by_xpath('//div[@class="mall_review"]').get_attribute('outerHTML')
                        selectorReview = Selector(text=htmlReview)
                        reviewList = selectorReview.xpath('//ul[@class="rvw_list"]')
                        reviewTitleList = reviewList.xpath('//div[@class="tit_W"]/p[@class="tit"]/text()').getall()
                        reviewContentList = reviewList.xpath('//div[@class="atc"]/text()').getall()
                        # reviewImgsList = reviewList.xpath('//div[@class="thumb_wrap"]').getall()
                        # reviewScoreList = reviewList.xpath('//span[@class="point_type_s"]/span[@class="star_mask"]/text()').getall()
                        reviewTimeList = reviewList.xpath('//span[@class="date"]/text()').getall()
                        reviewWriterList = reviewList.xpath('//span[@class="name"]/text()').getall()
                        reviewMallList = reviewList.xpath('//span[@class="mall"]/text()').getall()

                        for l in range(len(reviewTitleList)) :
                            reviewTitle = reviewTitleList[l]
                            reviewContent = reviewContentList[l]
                                
                            reviewImgList = reviewList.xpath(f'//li[@id="danawa-prodBlog-companyReview-content-wrap-{l}"]/div[@class="rvw_atc"]/div[@class="pto_list"]/ul/li/div/div/div/img').getall()
                            reviewScore = reviewList.xpath(f'//li[@id="danawa-prodBlog-companyReview-content-wrap-{l}"]/div[@class="top_info"]/span[@class="point_type_s"]/span[@class="star_mask"]/text()').getall()
                            # reviewScore = reviewScoreList[l]
                            reviewScore[0] = reviewScore[0].replace("점", "")
                            reviewTime = reviewTimeList[l]
                            reviewWriter = reviewWriterList[l]
                            reviewMall = reviewMallList[l]
                            print(reviewTitle)
                            #print(reviewScore[0])
                            # for m in range(len(reviewImgList)) : 
                            #     print(reviewImgList[m])
                            print("---------------------------------------")
                            crawlingData_csvWriter.writerow([productId, crawlingName, productName, reviewTitle, reviewContent, reviewScore[0], reviewMall, reviewTime, reviewWriter, reviewImgList])
                # 리뷰 제목 : <div class="tit_W"> <p class="tit">
                # 내용 : <div class="atc">
                # 이미지 : <div class="thumb_wrap">

                #  productNames = selector.xpath('//a[@name="productName"]/text()').getall() 이렇게 /text() 이용해서 span 태그 안의 내용 가져오기
                # 평점 (100점 만점) : <span class="star_mask">
                # 시간 (년월일) : <span class="date">2021.01.08.</span>
                # 사이트정보(사이트 이름만) : <span class="mall">SSG.COM</span>
                # 아이디(아이디 앞 2자 + ***로 처리) : <span class="name">yc****</span>
                # for k in range(len(reviewList)) :
                #     print(">> reviewList : " + reviewList[k])
                # productId, 카테고리, 모델명, 평점, 등록시간, 작성자 아이디, 사이트정보, 제목, 내용, 사진, 사진
                
        storedPcodeFile.close()
        crawlingFile.close()
        print('Crawling Finish : ' + crawlingName)





if __name__ == '__main__':
    crawler = DanawaCrawler()
    crawler.StartCrawling()
