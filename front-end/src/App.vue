<template>
  <v-app>
    <appbar />
    <vview />
    <vfooter />
  </v-app>
</template>

<script>
import appbar from "@/components/Appbar";
import vfooter from "@/components/Footer";
import vview from "@/components/View";
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
  mounted () {
    this.$store.state.ui.drawer = !(this.$vuetify.breakpoint.xs || this.$vuetify.breakpoint.sm);
  },
};
</script>

