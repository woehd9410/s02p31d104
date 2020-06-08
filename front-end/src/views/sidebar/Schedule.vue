<template>
  <v-navigation-drawer
    :color="themeColor"
    v-model="$store.state.ui.drawer"
    app
    clipped
  >
    <v-subheader>Calendar</v-subheader>
    <AddScheduleButton />
    <AddGroupScheduleButton @makeGroupEvent="makeGroup" />
    <SimpleScheduleCalendar class="mt-5" />
    <GroupList :showItems="groupList" :key="groupList.rerender"></GroupList>
    <GetGoogleCalendarButton />
  </v-navigation-drawer>
</template>

<script>
import SimpleScheduleCalendar from "@/components/home/SimpleScheduleCalendar.vue";
import AddScheduleButton from "@/components/modalButton/AddScheduleButton.vue";
import AddGroupScheduleButton from "@/components/modalButton/AddGroupScheduleButton.vue";
import GroupList from "@/components/schedule/GroupList.vue";
import GetGoogleCalendarButton from "@/components/modalButton/GetGoogleCalendarButton.vue";
import axiosScript from "@/api/axiosScript.js";

export default {
  data() {
    return {
      groupList: [],
      rerender: 0,
      chattingRoomList: []
    };
  },
  components: {
    AddScheduleButton,
    SimpleScheduleCalendar,
    AddGroupScheduleButton,
    GroupList,
    GetGoogleCalendarButton,
  },
  computed: {
    themeColor() {
      return this.$store.getters.getThemeColor;
    },
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },
  mounted() {
    this.serachGroupById();
  },
  methods: {
    makeGroup(payload) {
      console.log("schedule sidebar makeGroup method");
      this.groupList.push(payload);
      this.rerender = this.groupList.length;
    },
    serachGroupById() {
      console.log("GroupList searchGroupById axios");
      this.$store.commit("taskCntUp");
      axiosScript.serachGroupByUserId(
        this.userInfo.id,
        (res) => {
          if (res.status == 200) {
            console.log(res.data);
            this.groupList = res.data;
          }
        },
        (err) => console.log(err),
        () => this.$store.commit("taskCntDown")
      );
    },

  },
};
</script>

<style></style>
