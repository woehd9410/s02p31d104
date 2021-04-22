<template>
  <div>
    <v-content>
      <div id="chatWrap">
        <div id="chatHeader">{{ selected_chatRoom.name }}</div>
        <div id="chatLog">
          <div v-for="(item, idx) in messages" :key="idx">
            <div
              class="anotherMsg"
              v-if="item.email != userInfo.email && item.type != enter"
            >
              <span class="anotherName">{{ item.sender }}</span>
              <span class="msg">{{ item.message }}</span>
            </div>

            <div v-if="item.type == enter" style="text-align: center">
              <span class="msg">{{ item.message }}</span>
            </div>

            <div
              class="myMsg"
              v-if="item.email == userInfo.email && item.type != enter"
            >
              <span class="msg">{{ item.message }}</span>
            </div>
          </div>
        </div>
        <div id="chatForm">
          <input
            type="text"
            autocomplete="off"
            size="30"
            id="message"
            placeholder="메시지를 입력하세요"
            v-model="message"
            v-on:keypress.enter="sendMessage"
          />
          <button type="button" @click="sendMessage">보내기</button>
        </div>
      </div>
    </v-content>
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import axios from "axios";
// import { EventBus } from "../../plugins/eventBus.js";

export default {
  props: ["selected_chatRoom"],
  data() {
    return {
      room: {},
      sender: "테스트중",
      message: "",
      messages: [],
      reconnect: 0,
      connected: false,
      status: "disconnected",
      roomId: "",
      enter: "ENTER",
    };
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },
  created() {
    console.log("-------------------------------------");
    console.log("--------------채팅방 입장~~!!---------------");
    console.log("-------------------------------------");

    console.log(this.selected_chatRoom);
    console.log("-------------------------------------");
    this.roomId = this.selected_chatRoom.roomId;
    this.findAllChattings();
    this.connect();
  },
  methods: {
    findAllChattings: async function () {
      await axios
        .get("http://localhost:8082/chat/chattings/" + this.roomId)
        .then((response) => {
          if (response.data != "") {
            this.messages = response.data;
            console.log("------------메세지 불러오기 성공--------------");
            console.log("불러온 메세지:::");
            console.log(this.messages);
          }
        });
    },
    connect: async function () {
      this.socket = new SockJS("http://localhost:8082//ws-stomp");
      this.stompClient = Stomp.over(this.socket);
      await this.stompClient.connect(
        {},
        (frame) => {
          console.log("연결요");
          this.status = "connected";
          this.connected = true;
          console.log(frame);
          this.stompClient.subscribe(
            "/sub/chat/room/" + this.roomId,
            (tick) => {
              console.log(JSON.parse(tick.body));
              var recv = JSON.parse(tick.body);
              this.recvMessage(recv);
            }
          );
          this.stompClient.send(
            "/pub/chat/message",
            JSON.stringify({
              type: "ENTER",
              roomId: this.roomId,
              email: this.userInfo.email,
              sender: this.userInfo.name,
            }),
            {}
          );
        },
        (error) => {
          console.log("에러요");
          console.log(error);
          this.connected = false;
        }
      );
    },
    findRoom: function () {
      axios
        .get("http://localhost:8082/chat/room/" + this.roomId)
        .then((response) => {
          this.room = response.data;
        });
    },
    sendMessage: function () {
      this.stompClient.send(
        "/pub/chat/message",
        JSON.stringify({
          type: "TALK",
          roomId: this.roomId,
          sender: this.userInfo.name,
          email: this.userInfo.email,
          message: this.message,
        }),
        {}
      );
      this.message = "";
    },
    recvMessage: async function (recv) {
      console.log(recv);
      var temp = {
        type: recv.type,
        sender: recv.type == "ENTER" ? "[알림]" : recv.sender,
        email: recv.email,
        message: recv.message,
      };
      console.log(temp);
      console.log("받기라고요");
      await this.messages.push(temp);
      console.log(this.messages);
    },
  },
};
</script>

<style scoped>
#chatWrap {
  width: 600px;
  border: 1px solid #ddd;
}

#chatHeader {
  height: 60px;
  text-align: center;
  line-height: 60px;
  font-size: 25px;
  font-weight: 900;
  border-bottom: 1px solid #ddd;
}

#chatLog {
  height: 700px;
  overflow-y: auto;
  padding: 10px;
}

.myMsg {
  text-align: right;
}

.anotherMsg {
  text-align: left;
  margin-bottom: 5px;
}

.msg {
  display: inline-block;
  border-radius: 15px;
  padding: 7px 15px;
  margin-bottom: 10px;
  margin-top: 5px;
}

.anotherMsg > .msg {
  background-color: #b0bec5;
}

.myMsg > .msg {
  background-color: #546e7a;
  color: #fff;
}

.anotherName {
  font-size: 12px;
  display: block;
}

#chatForm {
  display: block;
  width: 100%;
  height: 50px;
  border-top: 2px solid #f0f0f0;
}

#message {
  width: 85%;
  height: calc(100% - 1px);
  border: none;
  padding-bottom: 0;
}

#message:focus {
  outline: none;
}

#chatForm > input[type="submit"] {
  outline: none;
  border: none;
  background: none;
  color: #0084ff;
  font-size: 17px;
}
</style>
