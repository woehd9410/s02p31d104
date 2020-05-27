<template>
  <v-navigation-drawer :color="themeColor" v-model="$store.state.ui.drawer" app clipped>
    <v-subheader>Board</v-subheader>
    <WriteBoardButton />

    <SimpleScheduleCalendar />
    <v-divider></v-divider>
  </v-navigation-drawer>
</template>

<script>
import SimpleScheduleCalendar from "@/components/home/SimpleScheduleCalendar.vue";
import WriteBoardButton from "@/components/modalButton/WriteBoardButton.vue";
export default {
  components: {
    SimpleScheduleCalendar,
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
