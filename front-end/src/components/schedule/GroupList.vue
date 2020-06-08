<template>
  <v-card max-width="500" class="mx-auto">
    <v-list shaped dense avatar>
      <v-subheader>Group</v-subheader>
      <v-list-item-group color="primary">
        <v-list-item
          v-for="item in showItems"
          :key="item.id"
          @click="groupClickEvent(item.id)"
        >
          <v-list-item-avatar>
            <v-img :src="item.url"></v-img>
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title v-text="item.name"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-card>
</template>

<script>
import axiosScript from "@/api/axiosScript.js";
export default {
  data() {
    return {
      showItems: [],
    };
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },
  mounted() {
    this.serachGroupById();
  },
  methods: {
    serachGroupById() {
      console.log("GroupList searchGroupById axios");

      this.$store.commit("taskCntUp");
      axiosScript.serachGroupByUserId(
        this.userInfo.id,
        (res) => {
          if (res.status == 200) {
            console.log(res.data);
            this.showItems = res.data;
          }
        },
        (err) => console.log(err),
        () => this.$store.commit("taskCntDown")
      );
    },
    groupClickEvent(gid) {
      console.log("GroupList groupClickEvent");
      if (this.$route.name == "Schedule" && this.$route.params.id == gid) {
        return;
      }
      this.$router.push({ name: "Schedule", params: { id: gid } });
    },
  },
};
</script>

<style></style>
