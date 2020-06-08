// filters/longTextFilter.js

// 커스텀 필터 함수를 정의한다
export default (value) => {
  let val = new String(value);
  if (val.length > 15) return `${val.substr(0, 16)}...`;
  return val;
};
