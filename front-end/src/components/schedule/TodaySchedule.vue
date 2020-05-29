<template>
    <v-col style="margin-right:5vw">
      <v-card>
      <v-card-title
        height="56px"
      >
      <h3>Today's Schedule</h3>
      </v-card-title>
      <v-sheet height="400">
        <v-calendar
          ref="calendar"
          color="primary"
          type="day"
          :events="events"

        >
        </v-calendar>
      </v-sheet>
      </v-card>
    </v-col>
</template>

<script>
export default {
  data() {
    return {
      events:[
       {
          name: 'Weekly Meeting',
          start: '2020-05-28 09:00',
          end: '2020-05-28 10:00',
        },
        {
          name: 'Thomas\' Birthday',
          start: '2020-05-28',
        },
        {
          name: 'Mash Potatoes',
          start: '2020-05-28 12:30',
          end: '2020-05-28 15:30',
        },
      ],
    }
  },
}
</script>

<style>

</style>