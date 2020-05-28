import axios from "axios";
import userAxiosScript from '@/api/v1/userAxiosScript.js';
// 테스트 rest api사용
const testAxios = axios.create({
  baseURL: "https://jsonplaceholder.typicode.com/users/",
});

// get store/{name}
const getTest = (success, error) => {
  testAxios
    .get()
    .then((res) => {
      success(res);
    })
    .catch((err) => {
      error(err);
    });
};

const axiosFunction = {
  getTest: (success, error) => getTest(success, error),
  userSearchByOptions: userAxiosScript.userSearchByOptions,
  signUp: userAxiosScript.signUp,
  updateUser: userAxiosScript.updateUser,
  kakaoLogin: userAxiosScript.kakaoLogin,
  login: userAxiosScript.login,
};

export default axiosFunction;
