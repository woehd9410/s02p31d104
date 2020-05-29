<template>
  <div>
    <v-row justify="center">
      <v-dialog v-model="showDialog" persistent max-width="1100">
        <login />
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
    return {};
  },
  methods: {
  },
  computed: {
    showDialog() {
      return !this.$store.getters.userAuth;
    },
  },
  components: {
    login,
  },
};
</script>

<style></style>
