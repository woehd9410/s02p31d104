<template>
  <v-card color="#385F73" dark>
    <v-card-title class="headline">Current Time</v-card-title>

    <v-card-actions>
      <v-spacer/>
      {{ curTime }}
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      curTime: "",
    };
  },
  mounted() {
    this.calCurTime();
  },
  methods: {
    calCurTime() {
      let date = new Date();
      this.curTime = `${date.getFullYear()}.${date.getMonth()}.${date.getDate()}`;
    },
  },
};
</script>

<style></style>
