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
