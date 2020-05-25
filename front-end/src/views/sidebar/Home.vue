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
    <v-divider></v-divider>
    <v-subheader>Board</v-subheader>
    <WriteBoardButton />
  </v-navigation-drawer>
</template>

<script>
import SimpleScheduleCalendar from "@/components/home/SimpleScheduleCalendar.vue";
import AddScheduleButton from "@/components/modalButton/AddScheduleButton.vue";
import WriteBoardButton from "@/components/modalButton/WriteBoardButton.vue";
export default {
  components: {
    SimpleScheduleCalendar,
    AddScheduleButton,
    WriteBoardButton,
  },
  computed: {
    themeColor() {
      return this.$store.getters.getThemeColor;
    },
  },
};
</script>

<style></style>
