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
export function requestCheckPick(payload) {
  console.log(" 체크픽 가져오기 >>> ")
  console.log(payload.category)
  const url = payload.category + "/checkpick";
  return $axios.get(url);
}

// 상품의 상세 정보 가져오기
export function requestProductInfo({ state }, payload) {
  const url = payload.category + "/" + payload.pcode;
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
