<template>
  <v-content>
    <v-container fluid wrap  v-if="isMySchedule" >
      <MySchedule/>
    </v-container>
      <GroupSchedule v-else class="ml-10 mr-10 mt-4"/>
  </v-content>
</template>

<script>
import GroupSchedule from "@/components/schedule/GroupSchedule.vue";
import MySchedule from "@/components/schedule/MySchedule.vue";
export default {
  data() {
    return {
      isMySchedule: true,
    };
  },
  components: {
    MySchedule,
    GroupSchedule,
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },
  watch: {
    $route(to) {
      if (to.params.id == 0) {
        console.log("Schedule watch route eq to.params.id and userInfo.id");
        this.isMySchedule = true;
        return;
      }
      console.log(`Schedule watch route to.params.id : ${to.params.id}`);
      return (this.isMySchedule = false);
    },
  },
};
</script>

<style></style>
