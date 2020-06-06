<template>
  <v-card max-width="500" class="mx-auto">
    <v-list shaped dense avatar>
      <v-subheader>Group</v-subheader>
      <v-list-item-group color="primary">
        <v-list-item v-for="item in showItems" :key="item.id">
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
      showItems: [
        { url: "https://cdn.vuetifyjs.com/images/lists/1.jpg", name: "TEST" },
        { url: "https://cdn.vuetifyjs.com/images/lists/1.jpg", name: "TEST" },
        { url: "https://cdn.vuetifyjs.com/images/lists/1.jpg", name: "TEST" },
        { url: "https://cdn.vuetifyjs.com/images/lists/1.jpg", name: "TEST" },
      ],
    };
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },
  mounted () {
    this.serachGroupById();
  },
  methods: {
    serachGroupById() {
      console.log("GroupList searchGroupById axios");

      this.$store.commit("taskCntUp");
      axiosScript.serachGroupByUserId(
        this.userInfo.id,
        (res) => {
          if(res.status == 200){
            console.log(res.data);
            this.showItems = res.data;
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
