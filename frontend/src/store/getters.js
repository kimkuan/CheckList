// getter

// // 현재 로그인 된 UserInfo 가져오기
// export function getLoginUserInfo(state) {
//   return state.loginUserInfo;
// }

// 현재 선택된 상품의 모델명 가져오기
export function getProductId(state) {
  return state.productId;
}

// 상단 검색바에 친 검색어 가져오기
export function getSearchWord(state) {
  return state.searchWord;
}

// 검색어 히스토리 가져오기
export function getSearchHistory(state) {
  return state.searchHistory;
}
