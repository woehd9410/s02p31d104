<template>
  <div>
    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="1100">
        <login @loginEvent="loginEvent" />
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="dialog = false">강제 로그인</v-btn>
        </v-card-actions>
      </v-dialog>
    </v-row>
    <router-view name="sidebar" />
    <router-view />
  </div>
</template>

<script>
import login from "@/components/Login.vue";
export default {
  data() {
    return {
      dialog: true,
    };
  },
  mounted() {
    this.dialog = !this.userAuth;
  },
  methods: {
    loginEvent(payload) {
      console.log(`${payload} Login Event`);
      this.dialog = false;
    },
  },
  computed: {
    usreAuth() {
      return this.$store.getters.userAuth;
    },
  },
  components: {
    login,
  },
};
</script>

<style></style>
