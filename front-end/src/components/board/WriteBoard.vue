<template>
  <v-container>
    <form>
      <br />
      <v-text-field v-model="title" label="제목" required></v-text-field>
      <br />
      <v-textarea solo counter v-model="content" label="자유롭게 기록하세요!"></v-textarea>
      <v-row>
        <v-col cols="12">
          <v-combobox v-model="select" label="태그" multiple chips></v-combobox>
        </v-col>
      </v-row>
      <v-btn @click="cancel()">취소</v-btn> &nbsp;
      <v-btn @click="submit()">글쓰기</v-btn>
    </form>
  </v-container>
</template>

<script>
import { EventBus } from "../../plugins/eventBus.js";

export default {
  data: () => ({
    title: "",
    content: "",
    // select: ["a", "b", "c"]
    select: "",
  }),

  methods: {
    submit() {
      console.log("글쓰기!!");
      alert("글쓰기!!");
    },
    cancel() {
      let dialog = false;
      EventBus.$emit("use-eventbus", dialog);
      console.log("이벤트 버스 완료");
    },
  },
};
</script>
