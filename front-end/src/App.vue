<template>
  <v-app>
    <div v-if="serviceGuide">
      <firstGuide @startButtonEvent="serviceStart" />
      <secondGuide />
      <thirdGuide />
      <fourthGuide />
    </div>
    <div v-else :class="{ 'fade-in': !serviceGuide }">
      <appbar />
      <vview />
      <vfooter />
    </div>
  </v-app>
</template>

<script>
import appbar from "@/components/Appbar";
import vfooter from "@/components/Footer";
import vview from "@/components/View";
import firstGuide from "@/components/guide/Firstguide.vue";
import secondGuide from "@/components/guide/Secondguide.vue";
import thirdGuide from "@/components/guide/Thirdguide.vue";
import fourthGuide from "@/components/guide/Fourthguide.vue";
export default {
  name: "App",
  data() {
    return {
      serviceGuide: true,
    };
  },
  components: {
    appbar,
    vfooter,
    vview,
    firstGuide,
    secondGuide,
    thirdGuide,
    fourthGuide,
  },
  methods: {
    serviceStart() {
      console.log("App serviceStart method");
      localStorage.setItem("serviceGuide", "already one click event");
      this.serviceGuide = false;
    },
  },
  mounted() {
    this.$store.state.ui.drawer = !(
      this.$vuetify.breakpoint.xs || this.$vuetify.breakpoint.sm
    );
    let sg = localStorage.getItem("serviceGuide");
    this.serviceGuide = sg == null;
  },
};
</script>
