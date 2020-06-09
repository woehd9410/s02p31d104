import http from "@/api/v1/http-common.js";
export default {
  searchImgAll(success, error) {
    http
      .get(`profile-img`)
      .then((res) => success(res))
      .catch((err) => error(err));
  },
};
