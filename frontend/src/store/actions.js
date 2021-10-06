// 비동기 API
import $axios from "axios";

const axiosService = $axios.create({
  baseURL: 'http://j5checklist.p.ssafy.io:8082/api/',
});

// 카테고리별 전체 상품 목록 가져오기
function requestProducts(category) {
  return axiosService.get(category);
}

// 카테고리별 체크픽 가져오기
function requestCheckPick(category) {
  const url = "/api/main/rank/" + category;
  return $axios.get(url);
}

export {
  requestProducts,
  requestCheckPick
}
