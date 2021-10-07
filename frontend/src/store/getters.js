// getter

import state from "./state";

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

// 리뷰 정보 가져오기
export function getReviewInfo(state) {
  return state.reviewInfo;
}

// 최근 본 상품 목록 가져오기
export function getProductHistory(state){
  // 최근 본 상품 배열 앞에 추가
  return state.productHistory;
}

// 검색한 상품 목록 가져오기
export function getSearchProductListInfo(state) {
  return state.searchProductList;
}

// 선택한 체크픽 카테고리 이름 가져오기
export function getMainCheckPickCategory(state) {
  return state.mainCheckPickCategory;
}

// 선택한 탭 이름 가져오기
export function getSearchTab(state) {
  return state.searchTab;
}

// 선택한 필터링 목록 가져오기
export function getSelectedFilter(state) {
  return state.selectedFilter;
}
// 검색바 카테고리 이름 가져오기
export function getSearchCategory(state) {
  return state.searchCategory;
}

// 최근검색어창 변수 가져오기
export function getSearchHistoryView(state) {
  return state.searchHistoryView;
}

// 베스트상품 3개
export function getBestProductList(state) {
  return state.bestProductList;
}

// 체크픽 선정 상품 10개
export function getCheckPickList(state) {
  return state.checkPickList;
}
