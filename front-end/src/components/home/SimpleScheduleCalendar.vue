<template>
  <v-row justify="center">
    <v-date-picker v-model="picker" dark></v-date-picker>
  </v-row>
</template>

<script>
export default {
  data() {
    return {
      picker: new Date().toISOString().substr(0, 10),
    };
  },
  computed: {
    themeColor() {
      return this.$store.getters.getThemeColor;
    },
  },
};
</script>

<style></style>
