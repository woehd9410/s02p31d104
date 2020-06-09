<template>
  <v-content>
    <v-container>
      <v-card-title class="headline mb-10">{{ boardInfo.title }}</v-card-title>

      <v-card-subtitle>{{ boardInfo.content }}</v-card-subtitle>

      <v-card-actions>
        <v-btn text @click="cancel()">취소</v-btn>
      </v-card-actions>
    </v-container>
  </v-content>
</template>

<script>
import { EventBus } from "../../plugins/eventBus.js";

export default {
  props: ["boardInfo"],

  methods: {
    cancel() {
      let updateDialog = false;
      EventBus.$emit("use-eventbus", updateDialog);
      console.log("이벤트 버스 완료");
    },
  },

  created() {
    // EventBus.$on("eventbus", (board) => {
    //   console.log("Detail에서 받기");
    //   this.board = board;
    //   this.title = board.title;
    //   this.content = board.content;
    //   this.create_time = board.create_time;
    // });
  },
};
</script>
