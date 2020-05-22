<template>
  <v-content>
    <v-container>
      <TodoList :items="items" @addEvent="getTodo"/>
    </v-container>
  </v-content>
</template>

<style>
.image img {
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
</style>
<script>
export default {
  name: "Login",

  data: () => ({
    name: "",
    email: "",
    emailRules: [(v) => !!v || "E-mail is required", (v) => /.+@.+\..+/.test(v) || "E-mail must be valid"],
  }),
  methods: {
    login() {
      console.log("로그인 !!!");
      alert("로그인!!!");
    },
    backpage() {
      this.$router.go(-1);
    },
    signup() {
      console.log("회원가입 페이지 이동");
      alert("회원가입 페이지 이동");
      // this.$router.push("/signup");
    },
    kakaologin() {
      console.log("카카오 로그인 연결");
      alert("카카오 로그인 연결");
    },
    naverlogin() {
      console.log("네이버 로그인 연결");
      alert("네이버 로그인 연결");
    },
    searchPW() {
      console.log("비밀번호 찾기");
      alert("비밀번호 찾기");
    },
  },
};
</script>
