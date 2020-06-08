<template>
  <div>
    <v-content>
      <div id="roomWrap">
        <div id="roomList">
          <div id="roomHeader">채팅 방 목록</div>
          <div id="roomSelect">
            <div
              class="list-group-item list-group-item-action"
              v-for="item in chatrooms"
              v-bind:key="item.roomId"
              v-on:click="enterRoom(item.roomId)"
            >
              {{ item.name }}
            </div>
          </div>
        </div>
      </div>

      <chattingmemberlist :selected_roodId="selected_roomId" v-if="flag != 0" :key="flag"></chattingmemberlist>
      <chattingroom :selected_chatRoom="selected_chatRoom" v-if="flag != 0" :key="flag + 1"></chattingroom>
    </v-content>
  </div>
</template>

<script>
import axios from "axios";
import chattingroom from "@/components/chat/ChattingRoom";
import chattingmemberlist from "@/components/chat/ChattingMemberList";

export default {
  components: {
    chattingroom,
    chattingmemberlist,
  },
  data() {
    return {
      room_name: "",
      chatrooms: [],
      groups: [],
      flag: 0,
      selected_roomId: "",
      selected_chatRoom: [],
    };
  },
  mounted() {
    this.findGroup();
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },
  methods: {
    findGroup: async function() {
      await axios.get("http://localhost:9000/api/v1/group-user/id/" + this.userInfo.id).then((response) => {
        this.groups = response.data;
        console.log("그룹 :::::");
        console.log(this.groups);
      });
      if (this.groups != null) {
        for (var i = 0; i < this.groups.length; i++) {
          await axios.get("http://localhost:8082/chat/room/" + this.groups[i].group_id).then((response) => {
            console.log(response.data);
            // this.chatrooms.push(response.data);
            this.chatrooms.push({ name: "임시 " + (i + 1) + "번째 방 제목", roomId: i });

            console.log("chatroom" + (i + 1) + "번쩨 :::::");
            console.log(this.chatrooms[i].name);
          });
        }
      }
    },
    findAllRoom: async function() {
      await axios.get("http://localhost:8082/chat/rooms").then((response) => {
        this.chatrooms = response.data;
        console.log(this.chatrooms);
      });
    },
    createRoom: function() {
      if ("" === this.room_name) {
        alert("방 제목을 입력해 주십시요.");
        return;
      } else {
        var params = {
          name: "test용입니다.",
          roomId: 6,
        };
        axios.post("http://localhost:8082/chat/room", params).then((response) => {
          alert(response.data.name + "방 개설에 성공하였습니다.");
          this.room_name = "";
          this.findAllRoom();
        });
      }
    },
    enterRoom: function(roomId) {
      console.log(roomId + "번방 입장");
      this.selected_roomId = roomId;

      console.log(this.chatrooms[roomId].name);
      this.selected_chatRoom = this.chatrooms[roomId];
      console.log(`select room : ${this.selected_roomId}`);

      this.flag++;

      // EventBus.$emit("chatDetail-eventbus", selected_roomId);

      // console.log("이벤트 버스 완료");

      // var sender = prompt("대화명을 입력해 주세요.");
      // if (sender != "") {
      //   this.$router.push({
      //     path: `/chatroom/${roomId}`,
      //   });
      // } else {
      //   alert("ㅁㅁ");
      // }
    },
    deleteroom: function(roomId) {
      axios.delete("http://localhost:8082/chat/room/" + roomId).then((response) => {
        this.chatrooms = response.data;
      });
    },
  },
};
</script>

<style scoped>
#roomWrap {
  width: 200px;
}

#roomList {
  border: 1px solid #b0bec5;
  border-radius: 5px;
}

#roomHeader {
  background-color: #546e7a;
  color: #fff;
  height: 40px;
  font-size: 18px;
  line-height: 40px;
  text-align: center;
  border-radius: 5px;
}

.roomEl {
  text-align: center;
  border-bottom: 1px solid #f0f0f0;
  padding: 10px 0px;
  cursor: pointer;
}

.roomEl:hover {
  background: #f0f0f0;
}

.roomEl.active {
  background: #f0f0f0;
}
</style>
