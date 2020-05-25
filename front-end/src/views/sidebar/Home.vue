<template>
  <v-navigation-drawer
    :color="themeColor"
    v-model="$store.state.ui.drawer"
    app
    clipped
  >
    <SimpleScheduleCalendar />
  </v-navigation-drawer>
</template>

<script>
import SimpleScheduleCalendar from "@/components/home/SimpleScheduleCalendar.vue";
export default {
  components: {
    SimpleScheduleCalendar,
  },
  computed: {
    themeColor() {
      return this.$store.getters.getThemeColor;
    },
  },
};
</script>

<style></style>
