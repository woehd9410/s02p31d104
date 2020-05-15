<template>
  <v-footer app :color="themeColor">
    <span>&copy; 2020</span>
  </v-footer>
</template>

<script>
export default {
  computed: {
    themeColor() {
      return this.$store.getters.getThemeColor;
    },
  },
};
</script>

<style></style>
