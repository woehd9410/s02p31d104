<template>
  <div>
    <v-content>
      <div id="memberWrap">
        <div id="memberList">
          <div id="memberHeader">채팅 멤버</div>
          <div v-for="member in members" v-bind:key="member.user_id">
            {{ member.user.name }}
          </div>
        </div>
      </div>
    </v-content>
  </div>
</template>

<script>
import groupAxios from "../../api/v1/groupAxiosScript";
// import { EventBus } from "../../plugins/eventBus.js";

export default {
  props: ["selected_roodId"],

  data() {
    return {
      members: [],
    };
  },
  mounted() {
    console.log(`in chating component select rood id : ${this.selected_roodId}`);
  },
  created() {
    console.log("----------------------채팅멤버 뷰--------------------");
    console.log(this.selected_roodId);
    this.findGroupUser(this.selected_roodId);
    // EventBus.$on("chatDetail-eventbus", (selected_roomId) => {
    //   this.roomId = selected_roomId;
    //   console.log(this.roomId);

    //   this.findGroupUser(this.roomId);
    // });
  },
  methods: {
    findGroupUser(roomId) {
      groupAxios.searchGroupUserListByGid(
        roomId,
        (res) => {
          console.log("---------------------------");
          console.log("그룹 멤버 가져오기 성공");
          console.log(res.data);
          console.log("---------------------------");
          this.members = res.data;
          console.log("---------------------------");
          console.log("멤버들 찍어보기");
          console.log(this.members);
          console.log("---------------------------");
        },
        (err) => {
          console.log(err);
        },
        () => {}
      );
    },
  },
};
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
