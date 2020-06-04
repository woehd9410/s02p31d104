<template>
  <v-content >
    <v-container fluid wrap>
      <MySchedule />
    </v-container>
  </v-content>
</template>

<script>
import MySchedule from "@/components/schedule/MySchedule.vue";
export default {
  components: {
    MySchedule,
  },
};
</script>

<style></style>
