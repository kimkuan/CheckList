// 비동기 API
import $axios from "axios";

const axiosService = $axios.create({
  baseURL: 'http://j5checklist.p.ssafy.io:8082/api/',
});

// 카테고리별 필터링 정보 가져오기
export function requestFilteringInfo(category) {
  return axiosService.get("/main/filter/"+category);
}

// 카테고리별 체크픽 상품 목록 가져오기
export function requestCheckPickProducts(category) {
  return axiosService.get(category);
}

// 카테고리별 필터링 상품 목록 가져오기
export function requestProducts(category) {
  return axiosService.post(category);
}

// 카테고리별 체크픽 가져오기
export function requestCheckPick({state}, category) {
  console.log(" 체크픽 가져오기 >>> ")
  console.log(category)
  const url = category + "/checkpick";
  return $axios.get(url);
}

// 상품의 상세 정보 가져오기
export function requestProductInfo({ state }, payload) {
  const url = payload.category + "/" + payload.pcode;
  return $axios.get(url);
}

// 베스트 상품 3개 가져오기
export function requestBestProduct() {
  const url = "main/best";
  return $axios.get(url);
}

// 상품의 리뷰 정보 가져오기
export function requestProductReview({ state }, payload) {
  const url = "review/" + payload.pcode + "/" + payload.page;
  return $axios.get(url);
}

// 키워드 검색 결과 가져오기
export function requestGetSearchProducts({ state }, payload) {
  const category = payload.category
  const keywordValue = payload.keywordValue
  const pageValue = payload.pageValue
  const url = category + "/search/" + pageValue + "/" + keywordValue
  return $axios.get(url);
}

// 워드클라우드 정보 가져오기
export function requestWordcloud({ state }, payload) {
  const url = "/review/wordcloud/" + payload.pcode
  console.log("url : "+url)
  return $axios.get(url);
}

// 필터링 검색 결과 가져오기
export function requestPostFilteringProducts({ state }, payload) {
  const category = payload.category;
  const filters = payload.filters;
  const pageValue = payload.pageValue;
  const url = category + "/filters?page=" + pageValue;
  console.log("category:filter:pageValue: " + category + " : "+ filters + " : " + pageValue);
  return axiosService.post(url, filters);
}

// word를 포함한 리뷰 리스트 가져오기
export function requestReviewListByWord({ state }, payload) {
  console.log("payload 정보 :")
  console.log(payload)
  const url = "/review/list/" + payload.pcode + "/" + payload.word
  return $axios.get(url);
}