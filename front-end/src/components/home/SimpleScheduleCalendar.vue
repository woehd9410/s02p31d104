<template>
  <v-row justify="center">
    <v-date-picker v-model="picker" dark></v-date-picker>
  </v-row>
</template>

<script>
export default {
  data() {
    return {
      picker: "",
    };
  },
  mounted() {
    var d = new Date(),
      month = "" + (d.getMonth() + 1),
      day = "" + d.getDate(),
      year = d.getFullYear();

    if (month.length < 2) month = "0" + month;
    if (day.length < 2) day = "0" + day;

    this.picker = [year, month, day].join("-");
  },
  watch: {
    picker(newValue) {
      this.$store.state.schedule.now = newValue;
    },
    now(newValue) {
      this.picker = newValue;
    },
  },
  computed: {
    themeColor() {
      return this.$store.getters.getThemeColor;
    },
    now() {
      return this.$store.getters.now;
    },
  },
};
</script>

<style></style>
