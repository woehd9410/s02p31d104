<template>
  <v-row justify="center">
    <v-col>
      <!-- <v-dialog persistent max-width="600px">
        <template v-slot:activator="{ on }">
          <v-btn block tile color="grey darken-1" dark v-on="on"
            ><v-icon>mdi-calendar-month-outline</v-icon>&nbsp;&nbsp;Add
            Schedule</v-btn
          >
        </template>
        <v-card> </v-card>
      </v-dialog> -->
      <v-dialog v-model="toScheduleModal" dark persistent max-width="450px">
        <template v-slot:activator="{ on }">
          <v-btn block tile color="grey darken-1" dark v-on="on"
            ><v-icon>mdi-calendar-month-outline</v-icon>&nbsp;&nbsp;Add
            Schedule</v-btn
          >
        </template>
        <WriteSchedule @cancleButtonEvent="toScheduleModal = false" />
      </v-dialog>
    </v-col>
  </v-row>
</template>

<script>
import WriteSchedule from "@/components/schedule/WriteSchedule.vue";
export default {
  data() {
    return {
      toScheduleModal: false,
    };
  },
  components: {
    WriteSchedule,
  },
  methods: {
    cancleScheduleModal() {
      this.toScheduleModal = false;
    },
  },
};
</script>

<style></style>
