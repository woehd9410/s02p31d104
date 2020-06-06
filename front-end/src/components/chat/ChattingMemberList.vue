<template>
  <div>
    <v-content>
      <div id="memberWrap">
        <div id="memberList">
          <div id="memberHeader">채팅 멤버</div>
          <div id="memberSelect">홍성욱</div>
          <div id="memberSelect">이옥주</div>
          <div id="memberSelect">이응재</div>
          <div id="memberSelect">박재동</div>
          <div id="memberSelect">이성준</div>
        </div>
      </div>
    </v-content>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
#memberWrap {
  width: 200px;
}

#memberList {
  border: 1px solid #aaaaaa;
  border-radius: 5px;
}

#memberHeader {
  height: 40px;
  font-size: 18px;
  line-height: 40px;
  padding-left: 15px;
  border-bottom: 1px solid #f0f0f0;
  font-weight: 600;
}

.memberEl {
  border-bottom: 1px solid #f0f0f0;
  padding: 10px 20px;
  font-size: 14px;
}
</style>