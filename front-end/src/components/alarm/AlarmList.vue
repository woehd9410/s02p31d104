<template>
  <v-content>
    <v-container>
      <v-layout class="justify-center mt-5 ">
        <!-- <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12"> -->
        <!-- <v-flex class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> -->
        <v-row>
          <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
            <v-col>
              <v-card>
                <v-list>
                  <v-card-title class="headline justify-center mt-5 mb-5 ">새로운 알림</v-card-title>

                  <template v-for="(item, index) in alarmArray">
                    <!-- <v-subheader v-if="item.header" :key="item.header" v-text="item.header"></v-subheader> -->

                    <v-divider :key="index"></v-divider>

                    <v-list-item :key="item.title" @click="test">
                      <v-list-item-avatar width="10vh" height="10vh">
                        <v-img :src="item.avatar"></v-img>
                      </v-list-item-avatar>
                      <v-list-item-content>
                        <v-list-item-subtitle class="mb-10" v-html="item.pushDate"></v-list-item-subtitle>
                        <v-list-item-title v-html="item.title"></v-list-item-title>
                        <v-list-item-subtitle v-html="item.content"></v-list-item-subtitle>
                        <v-card-actions>
                          <v-btn color="primary" @click="accept(item)">수락</v-btn> <v-btn @click="refuse(item)">거절</v-btn>
                        </v-card-actions>
                      </v-list-item-content>
                    </v-list-item>
                  </template>
                </v-list>
              </v-card>
            </v-col>
          </div>
          <!-- </v-flex> -->

          <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
            <v-col>
              <v-card>
                <v-list three-line>
                  <v-card-title class="headline justify-center mt-5 mb-5">읽은 알림</v-card-title>
                  <template v-for="(item, index) in alarmArray_read">
                    <!-- <v-subheader v-if="item.header" :key="item.header" v-text="item.header"></v-subheader> -->

                    <v-divider :key="index"></v-divider>

                    <v-list-item :key="item.id" @click="test">
                      <v-list-item-avatar width="10vh" height="10vh">
                        <v-img :src="item.avatar"></v-img>
                      </v-list-item-avatar>
                      <v-list-item-content>
                        <v-list-item-subtitle class="mb-10" v-html="item.pushDate"></v-list-item-subtitle>
                        <v-list-item-title v-html="item.title"></v-list-item-title>
                        <v-list-item-subtitle v-html="item.content"></v-list-item-subtitle>
                        <v-card-actions v-if="item.isAccept == 0">
                          <v-btn color="primary" @click="accept(item)">수락</v-btn> <v-btn @click="refuse(item)">거절</v-btn>
                        </v-card-actions>
                      </v-list-item-content>
                    </v-list-item>
                  </template>
                </v-list>
              </v-card>
            </v-col>
          </div>
        </v-row>

        <!-- 
        <v-list flat subheader three-line>
          <v-subheader>Notifications</v-subheader>

          <v-list-item-group v-model="settings" multiple active-class="">
            <AlarmItem :title="item.title" :pushDate="item.pushDate" />
            <AlarmItem :title="item.title" :pushDate="item.pushDate" />
          </v-list-item-group>
        </v-list>

        <v-expansion-panels popout>
          <v-expansion-panel v-for="(item, i) in 10" :key="i">
            <v-expansion-panel-header>Item</v-expansion-panel-header>
            <v-expansion-panel-content>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
              minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
              <div>
                <v-btn color="primary">수락</v-btn>
              </div>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels> -->
      </v-layout>
    </v-container>
  </v-content>
</template>

<script>
// import AlarmItem from "@/components/alarm/AlarmItem.vue";
export default {
  components: {
    // AlarmItem,
  },
  data() {
    return {
      selected: [2],
      items: [
        { icon: true, title: "Jason Oner", avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg" },
        { title: "Travis Howard", avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg" },
        { title: "Ali Connors", avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg" },
        { title: "Cindy Baker", avatar: "https://cdn.vuetifyjs.com/images/lists/4.jpg" },
      ],
      alarmArray: [
        {
          id: 0,
          type: 0,
          pushDate: "2020.05.27",
          title: "타이틀1.",
          content: "내용입니다.",
          isRead: 0,
          isAccept: 0,
          toUserId: 3,
          fromUserId: 5,
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
        },
        {
          id: 1,
          type: 0,
          pushDate: "2020.05.27",
          title: "타이틀2.",
          content: "내용입니다.",
          isRead: 0,
          isAccept: 0,
          toUserId: 3,
          fromUserId: 5,
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
        },
        {
          id: 2,
          type: 0,
          pushDate: "2020.05.27",
          title: "타이틀3",
          content: "내용입니다.",
          isRead: 0,
          isAccept: 0,
          toUserId: 3,
          fromUserId: 5,
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
        },
        {
          id: 3,
          type: 0,
          pushDate: "2020.05.27",
          title: "타이틀4.",
          content: "내용입니다.",
          isRead: 0,
          isAccept: 0,
          toUserId: 3,
          fromUserId: 5,
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
        },
      ],

      alarmArray_read: [
        {
          id: 4,
          type: 0,
          pushDate: "2020.05.27",
          title: "타이틀8.",
          content: "내용입니다.",
          isRead: 1,
          isAccept: 1,
          toUserId: 3,
          fromUserId: 5,
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
        },

        {
          id: 5,
          type: 0,
          pushDate: "2020.05.27",
          title: "타이틀8.",
          content: "내용입니다.",
          isRead: 1,
          isAccept: 1,
          toUserId: 3,
          fromUserId: 5,
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
        },

        {
          id: 6,
          type: 0,
          pushDate: "2020.05.27",
          title: "타이틀8.",
          content: "내용입니다.",
          isRead: 1,
          isAccept: 0,
          toUserId: 3,
          fromUserId: 5,
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
        },
      ],
      settings: [],
    };
  },

  mounted() {
    console.log("알림 모두 불러오기");
    console.log("알림 전부 읽음으로 디비 변경");
  },

  methods: {
    accept(i) {
      console.log("수락하기");
      console.log(i);
      i.isRead = 1;
      i.isAccept = 1;

      console.log("디비에 읽음,수락 값 바꾸기");
    },

    refuse(i) {
      console.log("거절하기");
      console.log(i);
      i.isRead = 1;
      i.isAccept = 1;

      console.log("디비에 읽음,수락 값 바꾸기");
    },

    test() {},
  },
};
</script>
