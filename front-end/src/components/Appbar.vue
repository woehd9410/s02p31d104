<template>
  <v-app-bar
    id="appbar"
    app
    :clipped-left="$vuetify.breakpoint.lgAndUp"
    :color="themeColor"
    dark
    src="https://images.unsplash.com/photo-1583078156135-8e04f60c2606?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
  >
    <v-app-bar-nav-icon @click.stop="switchSidebar"></v-app-bar-nav-icon>

    <template v-slot:extension>
      <v-tabs align-with-title>
        <v-tab
          v-for="category in categorys"
          :key="category.id"
          @click="goRoute(category)"
          >{{ category }}</v-tab
        >
      </v-tabs>
    </template>
    <v-toolbar-title id="appbarTitle" @click="goRoute()"
      >Helpromise</v-toolbar-title
    >
    <v-spacer></v-spacer>
    <div>
      <v-tooltip bottom>
        <template v-slot:activator="{ on }">
          <v-avatar v-on="on" style="cursor: pointer;">
            <img :src="userInfo.img" :alt="userInfo.name" @click="goRoute('profile')" />
          </v-avatar>
        </template>
        <span>{{userInfo.name}}</span>
      </v-tooltip>
    </div>
  </v-app-bar>
</template>

<script>
export default {
  data() {
    return {
      categorys: ["Home", "Schedule", "Board", "Alarm"],
      dialog: false,
    };
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
    themeColor() {
      return this.$store.getters.getThemeColor;
    },
    drawer() {
      return this.$store.getters.getDrawer;
    },
    userAuth() {
      return this.$store.getters.userAuth;
    },
  },
  methods: {
    switchSidebar() {
      this.$store.commit("switchDrawer");
      console.log(`sidebar ${this.drawer ? "open" : "close"}`);
    },
    goRoute(page = "") {
      if (this.$route.path == `/${page}`) return;
      console.log(`go to route ${page}.vue page`);
      if (page == "") this.$router.push("/");
      if (page == "profile") this.$router.push("/profile");

      // this.$router.push({ name: page });
    },
    login() {
      console.log(`show login modal`);
    },
  },
};
</script>

<style>
@font-face {
  font-family: 배민주아체;
  src: url("../assets/fonts/배민주아체.ttf");
}
#appbarTitle {
  font-family: 배민주아체;
  cursor: pointer;
}
</style>
