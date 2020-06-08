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
            >{{item.name}}</div>
            <div class="roomEl" data-id="2">VueJS</div>
            <div class="roomEl" data-id="3">ReactJS</div>
            <div class="roomEl" data-id="4">AngularJS</div>
          </div>
        </div>
      </div>
    </v-content>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      room_name: "",
      chatrooms: [],
      groups: []
    };
  },
  created() {
    this.findGroup();
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    }
  },
  methods: {
    findGroup: async function() {
      await axios
        .get("http://localhost:9000/api/v1/group-user/id/" + this.userInfo.id)
        .then(response => {
          this.groups = response.data;
          console.log(this.groups);
        });
      if (this.groups != null) {
        for (var i = 0; i < this.groups.length; i++) {
          await axios
            .get("http://localhost:8082/chat/room/" + this.groups[i].group_id)
            .then(response => {
              this.chatrooms.push(response.data);
              console.log(this.chatrooms);
            });
        }
      }
    },
    findAllRoom: async function() {
      await axios.get("http://localhost:8082/chat/rooms").then(response => {
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
          roomId: 6
        };
        axios.post("http://localhost:8082/chat/room", params).then(response => {
          alert(response.data.name + "방 개설에 성공하였습니다.");
          this.room_name = "";
          this.findAllRoom();
        });
      }
    },
    enterRoom: function(roomId) {
      var sender = prompt("대화명을 입력해 주세요.");
      if (sender != "") {
        this.$router.push({
          path: `/chatroom/${roomId}`
        });
      }
    },
    deleteroom: function(roomId) {
      axios
        .delete("http://localhost:8082/chat/room/" + roomId)
        .then(response => {
          this.chatrooms = response.data;
        });
    }
  }
};
</script>

<style scoped>
#roomWrap {
  width: 200px;
}

#roomList {
  border: 1px solid #0084ff;
  border-radius: 5px;
}

#roomHeader {
  background-color: #0084ff;
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