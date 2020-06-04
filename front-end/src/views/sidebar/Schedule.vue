<template>
  <v-navigation-drawer
    :color="themeColor"
    v-model="$store.state.ui.drawer"
    app
    clipped
  >
    <v-subheader>Calendar</v-subheader>
    <AddScheduleButton />
    <AddGroupScheduleButton />
    <SimpleScheduleCalendar class="mt-5"/>
  </v-navigation-drawer>
</template>

<script>
import SimpleScheduleCalendar from "@/components/home/SimpleScheduleCalendar.vue";
import AddScheduleButton from "@/components/modalButton/AddScheduleButton.vue";
import AddGroupScheduleButton from "@/components/modalButton/AddGroupScheduleButton.vue";
export default {
  components: {
    AddScheduleButton,
    SimpleScheduleCalendar,
    AddGroupScheduleButton,
  },
  computed: {
    themeColor() {
      return this.$store.getters.getThemeColor;
    },
  },
};
</script>

<style></style>
