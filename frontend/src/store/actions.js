// 비동기 API
import $axios from "axios";

// Kakao 서버로 받은 code로 AccessToken 가져오기
// export function requestAccessToken({ state }, payload) {
//   const url = "/kakao/oauth?code=" + payload;
//   return $axios.get(url);
// }

// 카테고리별 체크픽 가져오기
export function requestCheckPick(category) {
  const url = "/api/main/rank/" + category;
  return $axios.get(url);
}
