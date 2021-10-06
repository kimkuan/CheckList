// getter

// // 현재 로그인 된 UserInfo 가져오기
// export function getLoginUserInfo(state) {
//   return state.loginUserInfo;
// }

// 현재 선택된 상품의 모델명 가져오기
export function getProductId(state) {
  return state.productId;
}

// 현재 클릭한 모달 정보 가져오기
export function getModal(state) {
  return state.modal;
}

// 상단 검색바에 친 검색어 가져오기
export function getSearchWord(state) {
  return state.searchWord;
}

// 검색어 히스토리 가져오기
export function getSearchHistory(state) {
  return state.searchHistory;
}

// 선택한 카테고리 이름 가져오기
export function getSelectCategoryName(state) {
  return state.selectCategoryName;
}

// 현재 상품의 정보 가져오기
export function getProductInfo(state) {
  return state.productInfo;
}

// 최근 본 상품 목록 가져오기
export function getProductHistory(state){
  // 최근 본 상품 배열 앞에 추가
  return state.productHistory;
}
