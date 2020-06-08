import http from "@/api/v1/http-common.js";

export default {
  // /api/v1/user? id or name or email = param
  searchUserByOptions: (param, success, error) => {
    console.log(`axios in ... user search by options`);
    let paramType;
    if (typeof param == "number") paramType = { id: param };
    else if (param.split("@").length >= 2) paramType = { email: param };
    else paramType = { name: param };
    console.log(paramType);

    http
      .get(`user`, {
        params: paramType,
      })
      .then((res) => {
        success(res);
      })
      .catch((err) => {
        error(err);
      });
  },

  signUp: (param, success, error) => {
    console.log(`axios in ... sign up`);
    console.log(param);

    http
      .post(`user`, param)
      .then((res) => success(res))
      .catch((err) => error(err));
  },

  updateUser: (param, success, error) => {
    console.log(`axios in ... update user`);
    console.log(param);
    http
      .put(`user`, param)
      .then((res) => success(res))
      .catch((err) => error(err));
  },

  updateUserPassword: (param, success, error) => {
    http
      .put(`user/password`, param)
      .then((res) => success(res))
      .catch((err) => error(err));
  },

  kakaoLogin: (param, success, error) => {
    console.log(`axios in ... kakao login`);
    console.log(param);
    http
      .get(`user/kakao-login`, { params: { email: param } })
      .then((res) => success(res))
      .catch((err) => error(err));
  },

  login: (param, success, error, finall) => {
    console.log(`axios in ... login`);
    console.log(param);

    http
      .post(`user/login`, param)
      .then((res) => success(res))
      .catch((err) => error(err))
      .finally(() => finall());
  },

  emailAuth: (param, success, error) => {
    console.log(param);
    http
      .post(`user/email/${param}`)
      .then((res) => success(res))
      .catch((err) => error(err));
  },

  serachGroupByUserId: (param, success, error, finall) => {
    http
      .get(`user/${param}/group`)
      .then((res) => success(res))
      .catch((err) => error(err))
      .finally(() => finall());
  },
};
