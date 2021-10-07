// 저장소
export default {
  /*
        loginUserInfo: {
            userId: ""
        },
    */

  // 검색어
  searchWord: "",

  // 최근 검색 목록
  searchHistory: [],

  // 선택한 카테고리 이름
  selectCategoryName: "",

  // 클릭한 상품 모델명
  productId: "",

  // 클릭한 상품 설명 모달창
  modal: "",

   // 선택한 체크픽 카테고리 이름
  mainCheckPickCategory: "airfryer",

   // 검색바 카테고리 이름
   searchCategory: {},

   // 최근검색어 창 보여주는 변수
   searchHistoryView: false,

   // 조회수 베스트 상품 3개
   bestProductList: [],

   // 체크픽 10개
   checkPickList: [],
  // 리뷰 정보 (pcode, category, avgScore, reviewList<content>)
  reviewInfo: {
    pcode: 0,
    category: "",
    avgScore: 0,
    reviewList: {
      content: [
        {
          id: 0,
          category: "",
          pcode: "",
          title: "",
          content: "",
          date: "",
          score: 0,
          site: "",
          userId: "",
          name: "",
          image: "",
        },
      ],
      pageable: [],
      totalPages: 0,
      totalElements: 0,
      last: false,
      number: 0,
      size: 0,
      sort: [],
      numberOfElements: 0,
      first: false,
      empty: false,
    },
  },

  // 상품 정보 (가격, 이름, 브랜드, 성능 정보 + 최저가 정보)
  productInfo: {
    pcode: 0,
    name: "",
    brand: "",
    price: 0,
    img: "",
    type: "",
    volume: "",
    power: 0,
    size: "",
    spec: "",

    pricePoint: 0,
    volumePoint: 0,
    cookPoint: 0,
    controlPoint: 0,
    managementPoint: 0,
    conveniencePoint: 0,

    lowPriceInfoList: [],
  },

  // 최근 본 상품 목록
  productHistory: [],

  // 검색한 상품 리스트
  searchProductList: [],
};
