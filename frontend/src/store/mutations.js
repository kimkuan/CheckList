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

// 현재 클릭한 모달 이름 저장
export function setModal(state, modal) {
  state.modal = modal;
}

// 상단 검색어 저장
export function setSearchWord(state, searchWord) {
  state.searchWord = searchWord;
  var object = {
    category: state.searchCategory,
    word: state.searchWord,
  }
  // 최근 검색 히스토리 배열 앞에 추가
  state.searchHistory.unshift(object);
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

// 메인 체크픽 카테고리 저장
export function setMainCheckPickCategory(state, name) {
  state.mainCheckPickCategory = name;
}

// 검색바 카테고리 저장
export function setSearchCategory(state, name) {
  state.searchCategory = name;
}

// 최근검색어창 변수 저장
export function setSearchHistoryView(state, history) {
  state.searchHistoryView = history;
}
