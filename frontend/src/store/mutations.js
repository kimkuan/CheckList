// setter

import { configs } from "eslint-plugin-prettier";

/*
export function setIsbookmarked(state, isbookmarked) {
  state.board.isbookmarked = isbookmarked;
}
*/

// 상품 아이디 저장
export function setProductId(state, productId) {
  state.productId = productId;
}

// 현재 클릭한 모달 이름 저장
export function setModal(state, modal) {
  state.modal = modal;
}

// 상단 검색어 저장
export function setSearchWord(state, searchWord) {
  console.log(searchWord)
  state.searchWord = searchWord;
  // 최근 검색 히스토리 배열 앞에 추가
  state.searchHistory.unshift(searchWord);
}

// 검색어 리스트 전체 삭제
export function deleteHistoryAll(state) {
  state.searchHistory = [];
}

// 특정 검색어 리스트 전체 삭제
export function deleteWord(state, index) {
  state.searchHistory.splice(index, 1);
}

// 메인페이지에서 카테고리 선택 -> 카테고리 이름 저장
export function setSelectCategoryName(state, id) {
  state.selectCategoryName = id;
}

// 상품의 정보 저장
export function setProductInfo(state, productInfo) {
  state.productInfo = productInfo;
}

// 최근 본 상품 저장
export function setProductHistory(state, history){
  // 가장 최근에 본 상품과 다를 때만, 최근 본 상품 배열 앞에 추가
  state.productHistory.unshift(history);
}

// 메인 체크픽 카테고리 저장
export function setMainCheckPickCategory(state, name) {
  state.mainCheckPickCategory = name;
}
