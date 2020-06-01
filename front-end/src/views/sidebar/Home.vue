<template>
  <v-navigation-drawer
    :color="themeColor"
    v-model="$store.state.ui.drawer"
    app
    clipped
  >
    <v-subheader>Calendar</v-subheader>
    <AddScheduleButton />
    <SimpleScheduleCalendar />
  </v-navigation-drawer>
</template>

<script>
import SimpleScheduleCalendar from "@/components/home/SimpleScheduleCalendar.vue";
import AddScheduleButton from "@/components/modalButton/AddScheduleButton.vue";
export default {
  components: {
    SimpleScheduleCalendar,
    AddScheduleButton,
  },
  computed: {
    themeColor() {
      return this.$store.getters.getThemeColor;
    },
  },
};
</script>

<style></style>
