<template>
  <v-content class="mt-12">
      <v-container fluid wrap>
          <v-row class="fill-height">
            <v-col>
                <v-sheet height="64">
                    <v-toolbar flat color="white">
                    <v-btn outlined class="mr-4" color="grey darken-2" @click="setToday">
                        Today
                    </v-btn>
                    <v-btn fab text small color="grey darken-2" @click="prev">
                        <v-icon small>mdi-chevron-left</v-icon>
                    </v-btn>
                    <v-btn fab text small color="grey darken-2" @click="next">
                        <v-icon small>mdi-chevron-right</v-icon>
                    </v-btn>
                    <v-toolbar-title>{{ head }}</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-menu bottom right>
                        <template v-slot:activator="{ on }">
                        <v-btn
                            outlined
                            color="grey darken-2"
                            v-on="on"
                        >
                            <span>{{ typeToLabel[type] }}</span>
                            <v-icon right>mdi-menu-down</v-icon>
                        </v-btn>
                        </template>
                        <v-list>
                        <v-list-item @click="type = 'day'">
                            <v-list-item-title>Day</v-list-item-title>
                        </v-list-item>
                        <v-list-item @click="type = 'week'">
                            <v-list-item-title>Week</v-list-item-title>
                        </v-list-item>
                        <v-list-item @click="type = 'month'">
                            <v-list-item-title>Month</v-list-item-title>
                        </v-list-item>
                        <v-list-item @click="type = '4day'">
                            <v-list-item-title>4 days</v-list-item-title>
                        </v-list-item>
                        </v-list>
                    </v-menu>
                    </v-toolbar>
                </v-sheet>
                <v-sheet height="600">
                    <v-calendar
                    ref="calendar"
                    v-model="focus"
                    color="primary"
                    :events="events"
                    :event-color="getEventColor"
                    :now="today"
                    :type="type"
                    @click:event="showEvent"
                    @click:more="viewDay"
                    @click:date="viewDay"
                    @change="updateRange"
                    ></v-calendar>
                    <v-menu
                    v-model="selectedOpen"
                    :close-on-content-click="false"
                    :activator="selectedElement"
                    offset-x
                    >
                    <v-card
                        color="grey lighten-4"
                        min-width="250px"
                        flat
                    >
                        <v-toolbar
                        :color="selectedEvent.color"
                        dark
                        >
                        <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
                        <v-spacer></v-spacer>
                        <v-dialog  v-model="toScheduleModal" persistent max-width="450px">
                          <template v-slot:activator="{ on }">
                            <v-btn icon dark v-on="on">
                              <v-icon>mdi-pencil</v-icon>
                            </v-btn>
                          </template>
                            <v-card>
                              <v-card-title>
                                <span class="headline" style="padding-left=20px;"
                                  >Update Schedule</span
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
                                <v-btn color="blue darken-1" text @click="updateSchedule(selectedEvent.id)">Save</v-btn>
                              </v-card-actions>
                            </v-card>
                          </v-dialog>
                        <v-dialog v-model="detailSchedule" persistent max-width="450px">
                          <template v-slot:activator="{ on }">
                            <v-btn icon v-on="on">
                              <v-icon>mdi-dots-vertical</v-icon>
                            </v-btn>
                          </template>
                          
                          <v-card>
                            <v-card-title>
                              <span class="headline" style="padding-left=20px;"
                                >Schedule Detail</span
                              >
                            </v-card-title>
                            <v-list-item>
                              <v-list-item-content>
                                <v-list-item-title>Title : </v-list-item-title>
                              </v-list-item-content>
                              <v-list-item-content>
                                <v-list-item-title v-text="selectedEvent.name"></v-list-item-title>
                              </v-list-item-content>
                            </v-list-item>
                            <v-list-item>
                              <v-list-item-content>
                                <v-list-item-title>content : </v-list-item-title>
                              </v-list-item-content>
                              <v-list-item-content>
                                <v-list-item-title v-text="selectedEvent.content"></v-list-item-title>
                              </v-list-item-content>
                            </v-list-item>
                              <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="blue darken-1" text @click="detailSchedule = false"
                                  >Cancle</v-btn
                                >
                              </v-card-actions>
                          </v-card>
                        </v-dialog>
                        </v-toolbar>
                         <v-card-text>
                            <span v-html="selectedEvent.details"></span>
                         </v-card-text>
                        <v-card-actions>
                        <v-btn
                            text
                            color="secondary"
                            @click="selectedOpen = false"
                        >
                            Cancel
                        </v-btn>
                        </v-card-actions>
                    </v-card>
                    </v-menu>
                </v-sheet>
            </v-col>
        </v-row>
      </v-container>
  </v-content>
</template>

<script>
export default {
    data: () => ({
      focus: '',
      type: 'month',
      typeToLabel: {
        month: 'Month',
        week: 'Week',
        day: 'Day',
        '4day': '4 Days',
      },
      today: null,
      start: null,
      end: null,
      selectedEvent: {},
      selectedElement: null,
      selectedOpen: false,
      toScheduleModal: false,
      detailSchedule: false,
      menu: false,
      startCalendar: false,
      startClock: false,
      endCalendar: false,
      endClock: false,
      title: "",
      content: "",
      startDate: "",
      startTime: "",
      endDate: "",
      endTime: "",
      address: "",
      typeName: "",
      events: [
        { 
          id : 40,
          name: "운동",
          content: "배드민턴 하기",
          start: "2020-05-24 15:03:00",
          end: "2020-05-25 15:03:00",
          color: "primary"
        },
        { name: "여행",
          content: "1박2일 경주여행",
          start: "2020-05-26 15:03:00",
          end: "2020-05-27 15:03:00",
          color: "primary"
        },
        { name: "풋살",
          content: "배드민턴 하기",
          start: "2020-05-29 15:00:00",
          end: "2020-05-29 17:00:00",
          color: "primary"
        }
      ],
      colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
      names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
    }),
    computed: {
      head () {
        const { start, end } = this
        if (!start || !end) {
          return ''
        }

        const startMonth = this.monthFormatter(start)
        const endMonth = this.monthFormatter(end)
        const suffixMonth = startMonth === endMonth ? '' : endMonth

        const startYear = start.year
        const endYear = end.year
        const suffixYear = startYear === endYear ? '' : endYear

        const startDay = start.day + this.nth(start.day)
        const endDay = end.day + this.nth(end.day)

        switch (this.type) {
          case 'month':
            return `${startMonth} ${startYear}`
          case 'week':
          case '4day':
            return `${startMonth} ${startDay} ${startYear} - ${suffixMonth} ${endDay} ${suffixYear}`
          case 'day':
            return `${startMonth} ${startDay} ${startYear}`
        }
        return ''
      },
      monthFormatter () {
        return this.$refs.calendar.getFormatter({
          timeZone: 'UTC', month: 'long',
        })
      },
    },
    mounted () {
      this.$refs.calendar.checkChange()
    },
    methods: {
      viewDay ({ date }) {
        this.focus = date
        this.type = 'day'
      },
      getEventColor (event) {
        return event.color
      },
      setToday () {
        this.focus = this.today
      },
      prev () {
        this.$refs.calendar.prev()
      },
      next () {
        this.$refs.calendar.next()
      },
      showEvent ({ nativeEvent, event }) {
        const open = () => {
          this.selectedEvent = event
          this.selectedElement = nativeEvent.target
          setTimeout(() => this.selectedOpen = true, 10)
        }

        if (this.selectedOpen) {
          this.selectedOpen = false
          setTimeout(open, 10)
        } else {
          open()
        }

        nativeEvent.stopPropagation()
      },
      updateRange(){
         for(var i = 0; i < this.events.length; i++){
           this.events[i].color = this.colors[this.rnd(0, this.colors.length - 1)];
         }
      },
      updateSchedule(){
        console.log("update")
      },
      nth (d) {
        return d > 3 && d < 21
          ? 'th'
          : ['th', 'st', 'nd', 'rd', 'th', 'th', 'th', 'th', 'th', 'th'][d % 10]
      },
      rnd (a, b) {
        return Math.floor((b - a + 1) * Math.random()) + a
      },
      formatDate (a, withTime) {
        return withTime
          ? `${a.getFullYear()}-${a.getMonth() + 1}-${a.getDate()} ${a.getHours()}:${a.getMinutes()}`
          : `${a.getFullYear()}-${a.getMonth() + 1}-${a.getDate()}`
      },
    },
}
</script>

<style>

</style>