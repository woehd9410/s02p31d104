<template>
  <v-app>
    <appbar />
    <vview />
    <vfooter />

    <!-- <Login></Login> -->
  </v-app>
</template>

<script>
import appbar from "@/components/Appbar";
import vfooter from "@/components/Footer";
import vview from "@/components/View";
// import Login from "@/components/Login";
export default {
  name: "App",
  data() {
    return {};
  },
  components: {
    appbar,
    vfooter,
    vview,
  },
  created() {
    this.$vuetify.theme.dark = true;
  },
};
</script>
