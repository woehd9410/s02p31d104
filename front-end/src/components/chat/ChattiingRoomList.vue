<template>
  <div>
    <v-content>
      <div id="roomWrap">
        <div id="roomList">
          <div id="roomHeader">채팅 방 목록</div>
          <div id="roomSelect">
            <div class="roomEl active" data-id="1">Everyone</div>
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
export default {};
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