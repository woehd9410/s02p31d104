<template>
  <v-row justify="center">
    <v-col>
      <v-dialog v-model="toScheduleModal" dark persistent max-width="450px">
        <template v-slot:activator="{ on }">
          <v-btn block tile color="grey darken-1" dark v-on="on"
            ><v-icon class="mr-1">mdi-calendar-month-outline</v-icon>Add
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
