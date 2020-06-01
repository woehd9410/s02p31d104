<template>
  <v-card class="mx-auto">
    <v-window v-model="step">
      <v-icon class="m-5" @click="goLoginView">mdi-arrow-left-circle</v-icon>
      <v-window-item :value="1">
        <Join @selectJoinType="selectJoinType" />
      </v-window-item>
      <v-window-item :value="2">
        <Join_Person @joinSuccess="join" class="pb-1" />
      </v-window-item>
    </v-window>
  </v-card>
</template>

<script>
import Join from "@/components/Join.vue";
import Join_Person from "@/components/Join_Person.vue";
import axiosScript from "@/api/axiosScript.js";
export default {
  data() {
    return {
      step: 1,
    };
  },
  components: {
    Join,
    Join_Person,
  },

  computed: {
    currentTitle() {
      switch (this.step) {
        case 1:
          return "Sign-up";
        case 2:
          return "Create a password";
        default:
          return "Account created";
      }
    },
  },
  methods: {
    join(payload) {
      axiosScript.signUp(
        payload,
        (res) => {
          let loginInfo = res.data;
          loginInfo.img = payload.img;
          loginInfo.type = payload.type;
          this.$store.commit("login", loginInfo);
          this.$emit("joinSuccess");
        },
        (err) => console.log(err)
      );
    },
    goLoginView() {
      console.log(`go Login view`);
      this.$emit("joinCancle");
    },
    selectJoinType(payload) {
      if (payload === "Person") this.step++;
    },
  },
};
</script>
