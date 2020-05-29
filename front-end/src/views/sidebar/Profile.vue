@@ -0,0 +1,54 @@
<template>
  <v-navigation-drawer
    :color="themeColor"
    v-model="$store.state.ui.drawer"
    app
    clipped
  >
    <template v-slot:prepend>
      <v-list-item
        two-line
        @click="showMyProfile"
        class="viewClick"
        :inactive="isMyProfile"
      >
        <v-list-item-avatar>
          <img :src="userInfo.img" />
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>{{ userInfo.name }}</v-list-item-title>
          <v-list-item-subtitle>{{ userInfo.type }}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </template>
    <v-divider></v-divider>

    <FavoriteList :items="favorites" />

    <template v-slot:append>
      <div class="pa-2">
        <v-btn @click="logout" block>Logout</v-btn>
      </div>
    </template>
  </v-navigation-drawer>
</template>

<script>
import FavoriteList from "@/components/profile/FavoriteList.vue";
import axiosScript from "@/api/axiosScript.js";
export default {
  data() {
    return {
      isMyProfile: true,
      favorites: [],
    };
  },
  mounted() {
    this.getFavoriteList();
  },
  components: {
    FavoriteList,
  },
  methods: {
    showMyProfile() {
      if (this.$route.path == `/profile`) return;
      this.$router.push({ name: "Profile" });
    },
    logout() {
      console.log(`logout`);
      this.$store.commit("logout");
    },
    getFavoriteList() {
      axiosScript.searchFavoriteByToId(
        this.userInfo.id,
        (res) => console.log((this.favorites = res.data)),
        (err) => console.log(err)
      );
    },
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
    themeColor() {
      return this.$store.getters.getThemeColor;
    },
  },
};
</script>

<style>
.viewClick {
  cursor: pointer;
}
</style>
