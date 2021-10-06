// 비동기 API
import $axios from "axios";

const axiosService = $axios.create({
  baseURL: 'http://j5checklist.p.ssafy.io:8082/api/',
});

// 카테고리별 전체 상품 목록 가져오기
export function requestProducts(category) {
  return axiosService.get(category);
}

// 카테고리별 체크픽 가져오기
export function requestCheckPick(category) {
  const url = "/api/main/rank/" + category;
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
