<template>
  <v-app-bar app clipped-left :color="themeColor">
    <v-app-bar-nav-icon @click.stop="switchSidebar"></v-app-bar-nav-icon>
    <v-toolbar-title>Application</v-toolbar-title>
  </v-app-bar>
</template>

<script>
export default {
  computed: {
    themeColor() {
      return this.$store.getters.getThemeColor;
    },
    drawer() {
      return this.$store.getters.getDrawer;
    },
  },
  methods: {
    switchSidebar() {
      this.$store.commit("switchDrawer");
      console.log(`sidebar state : ${this.drawer}`);
    },
  },
};
</script>

<style></style>
