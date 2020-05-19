<template>
  <v-navigation-drawer
    :color="themeColor"
    v-model="$store.state.ui.drawer"
    app
    clipped
  >
    <template v-slot:prepend>
      <v-list-item two-line>
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

    <FavoriteList />
    <template v-slot:append>
      <div class="pa-2">
        <v-btn @click="logout" block>Logout</v-btn>
      </div>
    </template>
  </v-navigation-drawer>
</template>

<script>
import FavoriteList from "@/components/FavoriteList.vue";
export default {
  components: {
    FavoriteList,
  },
  methods: {
    logout() {
      console.log(`logout`);
      this.$store.commit("logout");
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

<style></style>
