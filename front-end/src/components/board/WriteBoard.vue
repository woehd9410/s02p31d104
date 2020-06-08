<template>
  <v-container>
    <form>
      <br />

      <div>
        <v-text-field v-bind:readonly="updateInfo.flag == 2" v-model="updateInfo.board.title" label="제목" required></v-text-field>
        <br />
        <v-textarea v-bind:readonly="updateInfo.flag == 2" solo counter v-model="updateInfo.board.content" label="자유롭게 기록하세요!"></v-textarea>
      </div>

      <v-row>
        <v-col cols="12">
          <v-combobox v-bind:readonly="updateInfo.flag == 2" v-model="select" label="태그" multiple chips></v-combobox>
        </v-col>
      </v-row>

      <v-spacer> </v-spacer>
      <v-btn class="mr-3" @click="submit()">확인</v-btn>
      <v-btn @click="cancel()">취소</v-btn>
    </form>
  </v-container>
</template>

<script>
import { EventBus } from "../../plugins/eventBus.js";

import BoardApi from "../../api/v1/boardAxiosScript.js";

export default {
  props: ["updateInfo"],

  data: () => ({
    title: "",
    content: "",
    // select: ["a", "b", "c"]
    select: "",
  }),

  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },

  methods: {
    submit() {
      let date = new Date();
      let create_time =
        date.getFullYear() + "-" + date.getMonth() + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
      var board = {
        title: this.updateInfo.board.title,
        content: this.updateInfo.board.content,
        created_time: create_time,
        user_id: this.userInfo.id,
        id: this.updateInfo.board.id,
      };

      if (this.updateInfo.flag == 0) {
        console.log("글쓰기!!");
        alert("글쓰기!!");

        BoardApi.postBoard(
          board,
          (res) => {
            if (res.status != 200) return;
            console.log("게시판 등록 성공");
            console.log(res);

            let dialog = false;
            EventBus.$emit("use-eventbus", dialog);
            console.log("이벤트 버스 완료");
          },
          (error) => {
            console.log("게시판 등록 실패");
            console.log(error);
          }
        );
      } else if (this.updateInfo.flag == 1) {
        console.log("수정하기");

        BoardApi.updateBoard(
          board,
          (res) => {
            console.log(res);
            console.log("수정하기 성공!");

            let dialog = false;
            EventBus.$emit("use-eventbus", dialog);
            console.log("이벤트 버스 완료");
          },
          (error) => {
            console.log(error);
            console.log("수정하기 실패ㅜㅜㅜ");
          }
        );
      }
    },
    cancel() {
      let dialog = false;
      EventBus.$emit("use-eventbus", dialog);
      console.log("이벤트 버스 완료");
    },
  },
};
</script>
