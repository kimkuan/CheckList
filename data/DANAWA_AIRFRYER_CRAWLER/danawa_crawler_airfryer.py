# -*- coding: utf-8 -*-

# danawa_cralwer.py
# sammy310
#// Name, URL, Crawling Page Size
# foodprocessor, "http://prod.danawa.com/list/?cate=1032269", 5
# coffeemachine, "http://prod.danawa.com/list/?cate=1032061", 2
# airfryer, "http://prod.danawa.com/list/?cate=10338814", 14
# monitor, "http://prod.danawa.com/list/?cate=112757", 38
# aircleaner, "http://prod.danawa.com/list/?cate=10331612", 19
import time

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from scrapy.selector import Selector

import csv

import happybase

from multiprocessing import Pool

connection = happybase.Connection('j5checklist.p.ssafy.io', 9090)

PROCESS_COUNT = 1

CRAWLING_DATA_CSV_FILE = 'CrawlingAirfryer.csv'
DATA_PATH = 'crawl_data'
DATA_REFRESH_PATH = f'{DATA_PATH}/Last_Data'

CHROMEDRIVER_PATH = 'chromedriver.exe'

UTC_TIME = 9

DATA_DIVIDER = '---'
DATA_REMARK = '//'
DATA_ROW_DIVIDER = '_'
DATA_PRODUCT_DIVIDER = '|'

STR_NAME = 'name'
STR_URL = 'url'
STR_CRAWLING_PAGE_SIZE = 'crawlingPageSize'


class DanawaCrawler:
    def __init__(self):
        self.crawlingCategory = list()
        with open(CRAWLING_DATA_CSV_FILE, 'r', newline='') as file:
            for crawlingValues in csv.reader(file, skipinitialspace=True):
                if not crawlingValues[0].startswith(DATA_REMARK):
                    self.crawlingCategory.append({STR_NAME: crawlingValues[0], STR_URL: crawlingValues[1], STR_CRAWLING_PAGE_SIZE: int(crawlingValues[2])})

    def StartCrawling(self):
        self.chrome_option = webdriver.ChromeOptions()
        self.chrome_option.add_argument('--headless')
        self.chrome_option.add_argument('--window-size=1920,1080')
        self.chrome_option.add_argument('--start-maximized')
        self.chrome_option.add_argument('--disable-gpu')
        self.chrome_option.add_argument('lang=ko=KR')

        if __name__ == '__main__':
            pool = Pool(processes=PROCESS_COUNT)
            pool.map(self.CrawlingCategory, self.crawlingCategory)
            pool.close()
            # pool.join()

    def CrawlingCategory(self, categoryValue):
        crawlingName = categoryValue[STR_NAME]
        crawlingURL = categoryValue[STR_URL]
        crawlingSize = categoryValue[STR_CRAWLING_PAGE_SIZE]

        # 크롤링 해야 할 정보
        # 상품 브랜드
        # 상품 모델병
        # 상품 가격 목록
        # 상품 사진

        print('Crawling Start : ' + crawlingName)
        # 크롤링할 url가져와서 webdriver객체에 넣기
        browser = webdriver.Chrome(CHROMEDRIVER_PATH, chrome_options=self.chrome_option)
        browser.implicitly_wait(20)
        browser.get(crawlingURL)

        # 상품리스트 90개까지 클릭
        # browser.find_element_by_xpath('//option[@value="90"]').click()
        wait = WebDriverWait(browser,30)
        wait.until(EC.invisibility_of_element((By.CLASS_NAME, 'product_list_cover')))

        # 1페이지~마지막 페이지별로 반복
        product_info_list = list()
        cnt = 1 # 몇번째 상품인지 보려고걍 만들어봄
        print("총"+str(crawlingSize)+"페이지 크롤링 시작")
        for page in range(1, crawlingSize+1):
            if page % 10 == 0:
                while True:
                    try:
                        browser.find_element_by_xpath('//a[@class="edge_nav nav_next"]').click()
                        break
                    except:
                        browser.quit()
                        browser = webdriver.Chrome(CHROMEDRIVER_PATH, chrome_options=self.chrome_option)
                        browser.implicitly_wait(20)
                        browser.get(crawlingURL)
            else:
                while True:
                    try:
                        browser.find_element_by_css_selector('#productListArea > div.prod_num_nav > div > div > a:nth-child('+str(page % 10)+')').click()
                        break
                    except:
                        print("CrawlingCategory > for > else문에서 예외 발생 리로딩")
                        browser.quit()
                        browser = webdriver.Chrome(CHROMEDRIVER_PATH, chrome_options=self.chrome_option)
                        browser.implicitly_wait(20)
                        browser.get(crawlingURL)
                        continue
            wait.until(EC.invisibility_of_element((By.CLASS_NAME, 'product_list_cover')))
            # 품목 리스트의 상품코드, 상품링크가 들어있는 html 가져오기
            product_list_html = browser.find_elements_by_css_selector(
                'div.main_prodlist_list > ul.product_list > li.prod_item')
            # 품목 리스트를 처음부터 돌면서 상품코드와 상품링크를 긁은다음 CrawlingProduct에 매개변수로 넘겨주어 상세정보 크롤링하고 반환받기
            for product in product_list_html:
                product_html = product.get_attribute('outerHTML')
                product_selector = Selector(text=product_html)
                # 마지막 유령상품에 도착했을 때 break
                try:
                    pcode = str(product_selector.xpath('//li').attrib['id'])[11:]
                except:
                    break
                product_url = product_selector.xpath('//li/div/div[2]/p/a').attrib['href']
                # 해당 상품 크롤링
                product_info = self.CrawlingProduct(product_url, pcode)
                if product_info == 0: # 0을 리턴했다는 것은 품절일 경우
                    continue
                print(str(cnt)+" :",product_info)
                cnt += 1
                product_info_list.append(product_info)

        print("크롤링 결과")

        f = open('airfryer_detail', 'w', encoding='UTF-8')
        for row in product_info_list:
            print(row)
            for column in row:
                for key, value in column.items():
                    print("key :", key, ", value :", value, ", len(value) :", len(value))
                    if type(value) is list:
                        for i in range(1, len(value) + 1):
                            f.write(str(key) + str(i) + ':' + str(value[i - 1]) + '\t')
                    else:
                        f.write(str(key) + ':' + str(value) + '\t')
            f.write('\n')

        print('Crawling Finish : ' + crawlingName)
        browser.quit()


    def CrawlingProduct(self, product_url, pcode):
        url = product_url
        driver = webdriver.Chrome(CHROMEDRIVER_PATH, chrome_options=self.chrome_option)
        WebDriverWait(driver, 15)
        driver.implicitly_wait(15)
        while True:
            try:
                driver.get(url)
                break
            except:
                print("driver.get(url)예외 발생, 리로딩")
                driver.quit()
                driver = webdriver.Chrome(CHROMEDRIVER_PATH, chrome_options=self.chrome_option)
                WebDriverWait(driver, 15)
                driver.implicitly_wait(15)
                driver.get(url)

        spec_info_list = list()  # 최종 상품 정보를 담을 리스트
        # 상품코드 append
        spec_info_list.append({'pcode' : pcode})
        while True:
            try:
                product_name_html = driver.find_element_by_css_selector('div.top_summary > h3').get_attribute(
                    'innerHTML')
                break
            except:
                print("예외 발생, 리로딩")
                driver.quit()
                driver = webdriver.Chrome(CHROMEDRIVER_PATH, chrome_options=self.chrome_option)
                WebDriverWait(driver, 15)
                driver.implicitly_wait(15)
                driver.get(url)

        name_selector = Selector(text=product_name_html)
        product_name = name_selector.xpath('//text()').get()

        # 상품 이미지 url
        product_img_html = driver.find_element_by_css_selector(
            'div.summary_left > #thumbArea > div.photo_w').get_attribute('innerHTML')
        img_selector = Selector(text=product_img_html)
        product_img_url = img_selector.xpath('//img').attrib['src']

        ##########################################가격 리스트##########################################
        # 품절일 경우 try except 문으로 건너뜀
        try:
            product_price_html = driver.find_element_by_css_selector(
                'div.lowest_list > table > tbody.high_list').get_attribute('innerHTML')
        except:
            print("품절로 건너뜀")
            return 0

        price_list_selector = Selector(text=product_price_html)
        price_list_html = price_list_selector.xpath('//tr').getall()
        price_list = list()
        for plh in price_list_html:
            # print(item)
            price_selector = Selector(text=plh)
            tr_class = price_selector.xpath('//tr').attrib['class']
            if tr_class == '' or tr_class == 'lowest' or tr_class == 'cash_lowest':
                # print(plh)
                # 상품 판매처 로고 이미지, 판매처 이름 크롤링
                try:
                    site_img_src = Selector(text=plh).xpath('//a/img').attrib['src']
                    site_img_alt = Selector(text=plh).xpath('//a/img').attrib['alt']
                except:
                    # 로고 이미지가 없을 땐 a태그의 text에있는 값으로 대체
                    try:
                        site_img_src = Selector(text=plh).xpath('//a').attrib['title']
                    except:
                        try:
                            site_img_src = Selector(text=plh).xpath('//a/font/b/text()').get()
                        except:
                            print("가격에서 또 오류남 이 쇼핑몰 가격 정보는 건너뜀")
                            continue
                    site_img_alt = site_img_src
                # print("판매처 : "+site_img_alt+", brand_img_src :", site_img_src)
                price_selector = Selector(text=plh).css('tr > td.price > a').xpath('//span/em/text()').get()
                price = (str(price_selector)).replace(",", "")
                # print(price)
                price_list.append([{'site_name': site_img_alt}, {'site_img_src': site_img_src}, {'price': price}])
        # print("price_list :", price_list)

        spec_info_list.append({'상품이름': product_name})
        spec_info_list.append({'상품이미지': product_img_url})
        spec_info_list.append({'가격': price_list})
        ##########################################상품 테이블 정보################################################
        # 상품정보 테이블 전체 Selector로 가져오기
        product_detail_html = driver.find_element_by_css_selector('div.prod_spec > table').get_attribute('innerHTML')
        selector = Selector(text=product_detail_html)

        # 상품테이블 행별로 뽑기(text, html형식)
        get_all_rows_html = selector.xpath('//tbody/tr').getall()

        #
        for row in get_all_rows_html:
            # row : 테이블 행 단위 html
            row_selector = Selector(text=row)
            # 인증의 경우 맨 마지막이고 그 값은 필요없으니 스킵
            get_th_text = row_selector.xpath('//tr/th/text()').getall()
            if len(get_th_text) > 0 and get_th_text[0] == '인증':
                break
            # th만 있고 td가 없는 행
            td_html = row_selector.xpath('//tr/td').getall()
            if not td_html:
                spec_info_list.append({'대분류': get_th_text[0]})
                continue

            # th, td를 각각 모두 리스트화 해서 키벨류 쌍을 인덱스로 컨트롤
            columns_th = row_selector.xpath('//tr/th').getall()
            columns_td = row_selector.xpath('//tr/td').getall()
            for i in range(len(columns_th)):
                key = ''
                value = ''
                # ex) key : 제조회사, value : 한미프렉시블
                key_html = Selector(text=columns_th[i])
                value_html = Selector(text=columns_td[i])
                # th(key)의 text 뽑기
                if key_html.xpath('//th/a'):  # a태그가 있을 땐 a태그 안에 text()를 key에 넣고
                    key = key_html.xpath('//th/a/text()').get()
                else:  # a태그가 없을 땐 바로 th 안에 text()를 key에 넣음
                    key = key_html.xpath('//th/text()').get()
                if key is None:  # 빈 상자를 가져왔다면 넘김
                    continue
                # td(value)의 text 뽑기
                if value_html.xpath('//td/a'):  # a태그가 있을 땐 a태그 안에 text()를 key에 넣고
                    value = value_html.xpath('//td/a/text()').get()
                else:  # a태그가 없을 땐 바로 th 안에 text()를 key에 넣음
                    value = str(value_html.xpath('//td/text()').get())
                # 제조회사의 value값은 이상하게 자꾸 '\n\t\t\t\t\t린나이 비움\t\t\t\t\t' 형태로 저장이돼서 replace로 바꿔봄
                if str(key) == '제조회사':
                    value = value.replace('\t', '')
                    value = value.replace('\n', '')
                spec_info_list.append({key: value})

        # 상품 스펙 요약 정보(상품페이지 맨 위)의 부가 기능을 위 스펙 리스트에 추가
        spec_list_html = driver.find_element_by_css_selector(
            'div.spec_list > div').get_attribute('innerHTML')
        selector = Selector(text=spec_list_html)
        spec_list_text = selector.xpath('//text()').getall()
        additional_function = str(spec_list_text[len(spec_list_text) - 1]).replace(" ", "")
        additional_function_list = additional_function.split('/')
        for af in additional_function_list:
            if af == '':
                continue
            if af.find(':') > -1:
                key_value = af.split(':')
                spec_info_list.append({key_value[0]: key_value[1]})
            else:
                spec_info_list.append({af: '○'})

        return spec_info_list


if __name__ == '__main__':
    crawler = DanawaCrawler()
    crawler.StartCrawling()