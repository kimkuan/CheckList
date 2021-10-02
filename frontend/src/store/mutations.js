// setter

/*
export function setIsbookmarked(state, isbookmarked) {
  state.board.isbookmarked = isbookmarked;
}
*/

// 상품 아이디 저장
export function setProductId(state, productId) {
  state.productId = productId;
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
export function setSelectCategoryName(state, name) {
  state.selectCategoryName = name;
}
