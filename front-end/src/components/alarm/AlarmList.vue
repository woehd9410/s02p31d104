<template>
  <v-content>
    <v-container>
      <v-list flat subheader three-line>
        <v-subheader>Notifications</v-subheader>

        <v-list-item-group v-model="settings" multiple active-class="">
          <AlarmItem :title="item.title" :pushDate="item.pushDate" />
          <AlarmItem :title="item.title" :pushDate="item.pushDate" />
        </v-list-item-group>
      </v-list>

      <v-expansion-panels popout>
        <v-expansion-panel v-for="(item, i) in 5" :key="i">
          <v-expansion-panel-header>Item</v-expansion-panel-header>
          <v-expansion-panel-content>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat.
            <div>
              <v-btn color="primary">수락</v-btn>
            </div>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-container>
  </v-content>
</template>

<script>
import AlarmItem from "@/components/alarm/AlarmItem.vue";
export default {
  components: {
    AlarmItem,
  },
  data() {
    return {
      item: {
        id: 0,
        type: 0,
        pushDate: "2020.05.27",
        title: "타이틀 입니다.",
        content: "내용입니다.",
        isRead: 0,
        isAccept: 0,
        toUserId: 3,
        fromUserId: 5,
      },
      settings: [],
    };
  },
};
</script>
