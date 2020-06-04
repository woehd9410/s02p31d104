<template>
  <v-row justify="center">
    <v-col>
      <v-dialog persistent max-width="600px" v-model="dialog">
        <template v-slot:activator="{ on }">
          <v-btn block tile color="grey darken-1" dark v-on="on" @click="write()"><v-icon>mdi-lead-pencil </v-icon>Write Board</v-btn>
        </template>
        <v-card>
          <v-card-title>
            <span class="headline">글쓰기</span>
          </v-card-title>

          <writeBoard :updateInfo="updateInfo"></writeBoard>
        </v-card>
      </v-dialog>
    </v-col>
  </v-row>
</template>

<script>
import writeBoard from "@/components/board/WriteBoard.vue";
import { EventBus } from "../../plugins/eventBus.js";

export default {
  components: {
    writeBoard,
  },

  data: () => ({
    dialog: false,
    updateInfo: null,
    flag: null,
    board: null,
  }),

  mounted() {
    EventBus.$on("use-eventbus", (dialog) => {
      this.dialog = dialog;
    });
  },
  computed: {},

  methods: {
    write() {
      console.log("글쓰기 시작");

      // 0이면 글쓰기, 1이면 수정하기
      this.flag = 0;
      this.board = {
        title: "",
        content: "",
      };

      console.log("board 정보");
      console.log(this.board);

      this.updateInfo = {
        flag: this.flag,
        board: this.board,
      };

      console.log("updateInfo 정보");
      console.log(this.updateInfo);

      this.dialog = true;
    },
  },
};
</script>

<style></style>
