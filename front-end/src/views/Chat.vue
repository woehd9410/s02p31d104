<template>
  <div>
    <v-container>
      <chattingroomlist></chattingroomlist>
      <chattingroom></chattingroom>
      <chattingmemberlist></chattingmemberlist>
    </v-container>
  </div>
</template>

<script>
import chattingroom from "../components/chat/ChattingRoom";
import chattingroomlist from "../components/chat/ChattiingRoomList";
import chattingmemberlist from "../components/chat/ChattingMemberList";
export default {
  components: {
    chattingroom,
    chattingroomlist,
    chattingmemberlist
  }
};
</script>

<style>
</style>