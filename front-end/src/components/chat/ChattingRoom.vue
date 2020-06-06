<template>
  <div>
    <v-content>
      <div id="chatWrap">
        <div id="chatHeader">채팅방 이름 or 그룹 이름</div>
        <div id="chatLog">
          <div class="anotherMsg">
            <span class="anotherName">Jo</span>
            <span class="msg">Hello, Nice to meet you.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
          <div class="myMsg">
            <span class="msg">Nice to meet you, too.</span>
          </div>
        </div>
        <form id="chatForm">
          <input type="text" autocomplete="off" size="30" id="message" placeholder="메시지를 입력하세요" />
          <input type="submit" value="보내기" />
        </form>
      </div>
    </v-content>
  </div>
</template>

<script>
export default {};
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
  background-color: #f1f0f0;
}

.myMsg > .msg {
  background-color: #0084ff;
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