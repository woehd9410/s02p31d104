<template>
  <div>
    <v-row justify="center">
      <v-dialog v-model="showDialog" persistent max-width="1100">
        <login />
      </v-dialog>
    </v-row>
    <v-overlay :value="$store.state.ui.progress > 0">
      <v-progress-circular
        :width="4"
        :size="40"
        color="red"
        indeterminate
      ></v-progress-circular>
    </v-overlay>
    <router-view name="sidebar"> </router-view>
    <router-view></router-view>
  </div>
</template>

<script>
import login from "@/components/Login.vue";

export default {
  data() {
    return {};
  },
  methods: {},
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

<style scoped>
.v-progress-circular {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 1000;
}
</style>
