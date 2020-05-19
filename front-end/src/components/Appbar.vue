<template>
  <v-app-bar
    id="appbar"
    app
    :clipped-left="$vuetify.breakpoint.lgAndUp"
    :color="themeColor"
    absolute
    dark
    src="
    https://images.unsplash.com/photo-1583078156135-8e04f60c2606?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60
    "
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
    <v-toolbar-title id="appbarTitle" @click="goHome"
      >Helpromise</v-toolbar-title
    >
    <v-spacer></v-spacer>
    <div id="sign">
      <v-tooltip bottom>
        <template v-slot:activator="{ on }">
          <v-avatar v-on="on" style="cursor: pointer;">
            <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
          </v-avatar>
        </template>
        <span>User Name</span>
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
    goRoute(page) {
      console.log(`go to route ${page}.vue page`);

      // this.$router.push({ name: page });
    },
    login() {
      console.log(`show login modal`);
    },
    goHome() {
      console.log(`go Home page`);

      if (this.$route.path != "/") this.$router.push({ name: "Home" });
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
