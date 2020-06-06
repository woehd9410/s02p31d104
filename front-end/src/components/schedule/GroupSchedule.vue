<template>
  <div>
    <v-layout row wrap align-center>
      <v-flex xs6>
        <Schedule />
      </v-flex>
      <v-flex xs6>
        <Chat />
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
import Schedule from "@/components/schedule/group/Schedule.vue";
import Chat from "@/components/schedule/group/Chat.vue";
export default {
  components: {
    Schedule,
    Chat,
  },
};
</script>

<style></style>
