import axios from "axios";
import userAxiosScript from "@/api/v1/userAxiosScript.js";
import favoriteAxiosScript from "@/api/v1/favoriteAxiosScript.js";
// import groupAxiosScript from "@/api/v1/groupAxiosScript.js";
import boardAxiosScript from "@/api/v1/boardAxiosScript.js";
import profileImgAxiosScript from "@/api/v1/profileImgAxiosScript.js";
import scheduleAxiosScript from "@/api/v1/scheduleAxiosScript.js"
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

  // user
  searchUserByOptions: userAxiosScript.searchUserByOptions,
  signUp: userAxiosScript.signUp,
  updateUser: userAxiosScript.updateUser,
  kakaoLogin: userAxiosScript.kakaoLogin,
  login: userAxiosScript.login,

  // follow
  follow: favoriteAxiosScript.follow,
  unfollow: favoriteAxiosScript.unfollow,
  searchFavoriteByToId: favoriteAxiosScript.searchFavoriteByToId,

  // // group
  // createGroup: groupAxiosScript.createGroup,
  // searchGroupUserListByGid: groupAxiosScript.searchGroupUserListByGid,
  // deleteGroupById: groupAxiosScript.deleteGroupById,
  // updateGroup: groupAxiosScript.updateGroup,
  // // ----------- group-user
  // addGroupUser: groupAxiosScript.updateGroup,
  // searchGroupByGid: groupAxiosScript.searchGroupByGid,
  // // addGroupUser: groupAxiosScript.updateGroup, // host 변경 api 필요
  // deleteGroupUserByGidAndUid: groupAxiosScript.deleteGroupUserByGidAndUid,

  // board
  postBoard: boardAxiosScript.postBoard,
  searchBoardById: boardAxiosScript.searchBoardById,
  updateBoard: boardAxiosScript.updateBoard,
  deleteBaord: boardAxiosScript.deleteBaord,
  searchBoardAll: boardAxiosScript.searchBoardAll,
  searchBoardByEmail: boardAxiosScript.searchBoardByEmail,

  // schedule
  getToDo: scheduleAxiosScript.getToDo,
  addToDo: scheduleAxiosScript.addToDo,
  deleteToDo: scheduleAxiosScript.deleteToDo,
  todoToSchedule: scheduleAxiosScript.todoToSchedule,
  updateToDoState: scheduleAxiosScript.updateToDoState,
  // img
  searchImgAll: profileImgAxiosScript.searchImgAll,
};

export default axiosFunction;
