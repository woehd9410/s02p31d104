<template>
  <v-content>
    <v-container>
      <div class="container" id="app" v-cloak>
        <div>
          <h2>{{room.name}}</h2>
        </div>
        <div class="input-group">
          <div class="input-group-prepend">
            <label class="input-group-text">내용</label>
          </div>
          <input
            type="text"
            class="form-control"
            v-model="message"
            v-on:keypress.enter="sendMessage"
          />
          <div class="input-group-append">
            <button class="btn btn-primary" type="button" @click="sendMessage">보내기</button>
          </div>
        </div>
        <ul class="list-group">
          <li
            class="list-group-item"
            v-for="  (m, idx) in messages"
            :key="idx"
          >{{m.sender}} - {{m.message}}</li>
        </ul>
        <div></div>
      </div>
    </v-container>
  </v-content>
</template>
    
<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import axios from "axios";
export default {
  data() {
    return {
      roomId: "",
      room: {},
      sender: "테스트중",
      message: "",
      messages: [],
      reconnect: 0,
      connected: false,
      status: "disconnected"
    };
  },
  mounted() {
    this.roomId = this.$route.params.roomId;
    this.findAllChattings();
    this.connect();
  },
  methods: {
    findAllChattings: async function() {
      await axios
        .get("http://localhost:8082/chat/chattings/" + this.roomId)
        .then(response => {
          if (response.data != "") {
            this.messages = response.data;
          }
        });
    },
    connect: async function() {
      this.socket = new SockJS("http://localhost:8082//ws-stomp");
      this.stompClient = Stomp.over(this.socket);
      await this.stompClient.connect(
        {},
        frame => {
          console.log("연결요");
          this.status = "connected";
          this.connected = true;
          console.log(frame);
          this.stompClient.subscribe("/sub/chat/room/" + this.roomId, tick => {
            console.log(JSON.parse(tick.body));
            var recv = JSON.parse(tick.body);
            this.recvMessage(recv);
          });
          this.stompClient.send(
            "/pub/chat/message",
            JSON.stringify({
              type: "ENTER",
              roomId: this.roomId,
              sender: "테스트"
            }),
            {}
          );
        },
        error => {
          console.log("에러요");
          console.log(error);
          this.connected = false;
        }
      );
    },
    findRoom: function() {
      axios
        .get("http://localhost:8082/chat/room/" + this.roomId)
        .then(response => {
          this.room = response.data;
        });
    },
    sendMessage: function() {
      this.stompClient.send(
        "/pub/chat/message",
        JSON.stringify({
          type: "TALK",
          roomId: this.roomId,
          sender: this.sender,
          message: this.message
        }),
        {}
      );
      this.message = "";
    },
    recvMessage: async function(recv) {
      console.log(recv);
      var temp = {
        type: recv.type,
        sender: recv.type == "ENTER" ? "[알림]" : recv.sender,
        message: recv.message
      };
      console.log(temp);
      console.log("받기라고요");
      await this.messages.push(temp);
      console.log(this.messages);
    }
  }
};
</script>
