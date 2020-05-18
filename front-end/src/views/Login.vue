<template>
  <div>
    sdsdsd
    <!-- 로그인 컴포넌트 -->
    <v-container class="red">
      <v-layout>
        <Login></Login>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import Login from "../components/Login.vue";
export default {
  name: "Login",
  component: { Login },
};
</script>

<style></style>
