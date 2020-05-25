<template>
  <v-row justify="center">
    <v-col>
      <v-dialog persistent max-width="600px">
        <template v-slot:activator="{ on }">
          <v-btn block tile color="grey darken-1" dark v-on="on"
            ><v-icon>mdi-lead-pencil </v-icon>Write Board</v-btn
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
