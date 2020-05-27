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
      <v-dialog  v-model="toScheduleModal" persistent max-width="450px">
        <template v-slot:activator="{ on }">
          <v-btn block tile color="grey darken-1" dark v-on="on"
            ><v-icon>mdi-calendar-month-outline</v-icon>&nbsp;&nbsp;Add
            Schedule</v-btn
          >
        </template>
          <v-card>
            <v-card-title>
              <span class="headline" style="padding-left=20px;"
                >To Schedule</span
              >
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="8" style="40px !important;">
                    <v-text-field
                      label="Title"
                      v-model="title"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="4">
                    <v-select
                      :items="['private', 'public']"
                      label="Can see all or only you"
                      v-model="typeName"
                      required
                    ></v-select>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="8">
                    <v-menu
                      ref="menu"
                      v-model="menu"
                      :close-on-content-click="false"
                      transition="scale-transition"
                      offset-y
                      min-width="290px"
                    >
                      <template v-slot:activator="{ on }">
                        <v-text-field
                          v-model="startDate"
                          label="Start Date"
                          readonly
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-date-picker v-model="startDate" no-title scrollable>
                        <v-spacer></v-spacer>
                        <v-btn text color="grey" @click="menu = false"
                          >OK</v-btn
                        >
                      </v-date-picker>
                    </v-menu>
                  </v-col>
                  <v-col cols="4">
                    <v-menu
                      ref="startClock"
                      v-model="startClock"
                      :close-on-content-click="false"
                      :nudge-right="40"
                      :return-value.sync="startTime"
                      transition="scale-transition"
                      offset-y
                      max-width="290px"
                      min-width="290px"
                    >
                      <template v-slot:activator="{ on }">
                        <v-text-field
                          v-model="startTime"
                          label="Time"
                          readonly
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-time-picker
                        v-if="startClock"
                        v-model="startTime"
                        full-width
                        @click:minute="$refs.startClock.save(startTime)"
                      ></v-time-picker>
                    </v-menu>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="8">
                    <v-menu
                      ref="menu"
                      v-model="endCalendar"
                      :close-on-content-click="false"
                      transition="scale-transition"
                      offset-y
                      min-width="290px"
                    >
                      <template v-slot:activator="{ on }">
                        <v-text-field
                          v-model="endDate"
                          label="End Date"
                          readonly
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-date-picker v-model="endDate" no-title scrollable>
                        <v-spacer></v-spacer>
                        <v-btn text color="grey" @click="endCalendar = false"
                          >OK</v-btn
                        >
                      </v-date-picker>
                    </v-menu>
                  </v-col>
                  <v-col cols="4">
                    <v-menu
                      ref="endClock"
                      v-model="endClock"
                      :close-on-content-click="false"
                      :nudge-right="40"
                      :return-value.sync="endTime"
                      transition="scale-transition"
                      offset-y
                      max-width="290px"
                      min-width="290px"
                    >
                      <template v-slot:activator="{ on }">
                        <v-text-field
                          v-model="endTime"
                          label="Time"
                          readonly
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-time-picker
                        v-if="endClock"
                        v-model="endTime"
                        full-width
                        @click:minute="$refs.endClock.save(endTime)"
                      ></v-time-picker>
                    </v-menu>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="12">
                    <v-text-field
                      label="address"
                      v-model="address"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-textarea
                      v-model="content"
                      label="Content"
                      auto-grow
                      outlined
                      rows="1"
                      row-height="15"
                    ></v-textarea>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="toScheduleModal = false"
                >Cancle</v-btn
              >
              <v-btn color="blue darken-1" text @click="update()">Save</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
    </v-col>
  </v-row>
</template>

<script>
export default {
  data() {
    return {
      menu: false,
      startCalendar: false,
      startClock: false,
      endCalendar: false,
      endClock: false,
      toScheduleModal: false,
      title: "",
      content: "",
      startDate: "",
      startTime: "",
      endDate: "",
      endTime: "",
      address: "",
      typeName: "",
    }
  },
  methods: {
    updateSchedule() {
      console.log("updateSchedule")
    }
  },
};
</script>

<style></style>
