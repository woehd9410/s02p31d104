<template>
  <div>
    <v-layout row wrap align-center>
      <v-flex xs6>
        <Schedule />
      </v-flex>
      <v-flex xs6>
        <ParticipateUserList />
        <Chat />
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
import Schedule from "@/components/schedule/group/Schedule.vue";
import Chat from "@/components/schedule/group/Chat.vue";
import ParticipateUserList from "@/components/schedule/group/ParticipateUserList.vue";
export default {
  components: {
    Schedule,
    Chat,
    ParticipateUserList,
  },
};
</script>

<style></style>
