<template>
  <v-row justify="center">
    <v-col>
      <v-dialog persistent max-width="600px">
        <template v-slot:activator="{ on }">
          <v-btn block tile color="grey darken-1" dark v-on="on"
            ><v-icon>mdi-calendar-month-outline</v-icon>&nbsp;&nbsp;Add
            Schedule</v-btn
          >
        </template>
        <v-card> </v-card>
      </v-dialog>
    </v-col>
  </v-row>
</template>

<script>
export default {};
</script>

<style></style>
