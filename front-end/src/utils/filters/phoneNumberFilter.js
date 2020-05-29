// filters/numberFormat.js

// 커스텀 필터 함수를 정의한다
export default (value = "") => {
  return `${value.substr(0, 3)}-${value.substr(3, 4)}-${value.substr(7, 4)}`;
};
