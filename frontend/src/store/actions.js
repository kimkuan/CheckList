// 비동기 API
import $axios from "axios";

const axiosService = $axios.create({
  baseURL: 'http://localhost:8082/api/',
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