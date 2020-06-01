import axios from "axios";

export default axios.create({
  baseURL: "http://3.34.73.112:9000/api/v1/",

  headers: {
    "Content-type": "application/json; charset=utf-8",
  },
});
