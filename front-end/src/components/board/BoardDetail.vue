<template>
  <v-content>
    <v-container>
      <v-card-title class="headline mb-10">{{ title }}</v-card-title>

      <v-card-subtitle>{{ content }}</v-card-subtitle>

      <v-card-actions>
        <v-btn text @click="cancel()">취소</v-btn>
      </v-card-actions>
    </v-container>
  </v-content>
</template>

<script>
import { EventBus } from "../../plugins/eventBus.js";

export default {
  data: () => ({
    title: "임시 타이틀 입니다.",
    content:
      "하얀 바다에 빠져 까만 선율이 번져 나의 너를 그려내 잊혀졌던 memory 조각 같은 눈물이 되감아 지길 바래 두 눈 감아 oh na na na" +
      " 꿈이라도 좋으니 너를 내게 보여줘 널 불러 oh na na na 긴 시간 넘어 다시 이곳으로 I want you 눈앞에 니가 다시 다가와 그때와 다른 결말이" +
      "오길 (oh) I can't let you go 매일 나 그려왔던 바로 이 순간 I said I want you I can't let you go 끝없이 기다려온 바로 이 순간" +
      "I said I want you 푸르게 번진 이 장면 속에 세상엔 오직 너와 나뿐 눈을 봐도 알 수 있겠지 이제 네 맘 놓치지 않기 달콤히 속삭인 노래들과" +
      " It's you (you, you) 날 깨우면 oh na na na (oh na na) 저무는 내 미소가 현실을 또 마주해 (또 다시 난) 다시 난 oh na na na",
  }),

  methods: {
    cancel() {
      let updateDialog = false;
      EventBus.$emit("use-eventbus", updateDialog);
      console.log("이벤트 버스 완료");
    },
  },
};
</script>
