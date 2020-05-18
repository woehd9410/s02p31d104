<template>
  <v-app>
    <!-- <appbar /> -->
    <!-- <vview /> -->
    <!-- <vfooter /> -->
    <!-- <v-container class="red"> -->
    <!-- <v-layout class="blue"> -->
    <Login></Login>
    <!-- </v-layout> -->
    <!-- </v-container> -->
  </v-app>
</template>

<script>
// import appbar from "@/components/Appbar";
// import vfooter from "@/components/Footer";
// import vview from "@/components/View";
import Login from "@/components/Login";
export default {
  name: "App",
  data() {
    return {};
  },
  components: {
    // appbar,
    // vfooter,
    // vview,
    Login,
  },
  created() {
    this.$vuetify.theme.dark = true;
  },
};
</script>
