<template>
  <v-content>
    <v-container>
      <div>임시 카카오 로그인 성공, 백엔드랑 연결해야함</div>
    </v-container>
  </v-content>
</template>

<script>
import axios from "axios";

export default {
  mounted() {
    this.getAccessToken();
  },
  methods: {
    getAccessToken() {
      axios.get(`${process.env.VUE_APP_REST_SERVER}user/kakao-login?code=${this.$route.query.code}`).then((res) => {
        console.log(res.data);
      });
    },
  },
};
</script>
