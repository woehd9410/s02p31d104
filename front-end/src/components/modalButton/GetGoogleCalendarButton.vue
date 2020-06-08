<template>
  <v-row justify="center">
    <v-col>
      <v-dialog v-model="modal" dark persistent max-width="450px">
        <template v-slot:activator="{ on }">
          <v-btn block tile color="grey darken-1" dark v-on="on"
            ><v-icon class="mr-1">mdi-calendar-import</v-icon>Apply Google
            Calendar
          </v-btn>
        </template>
        <v-card>
          <v-form>
            <v-card-title>
              <span class="headline" style="padding-left=20px;">
                Get Google Calendar Infomation</span
              ></v-card-title
            >
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col>
                    <v-text-field
                      prepend-icon="mdi-link-box-variant-outline"
                      label="iCal 형식의 주소"
                      v-model="url"
                      required
                    >
                    </v-text-field>
                  </v-col>
                  <v-col cols="1">
                    <v-menu>
                      <template #activator="{ on: onMenu }">
                        <v-tooltip top>
                          <template #activator="{on:onTooltip}">
                            <v-icon
                              :color="color"
                              v-on="{ ...onMenu, ...onTooltip }"
                              >mdi-circle</v-icon
                            >
                          </template>
                          <span>Schedule Color</span>
                        </v-tooltip>
                      </template>
                      <v-card max-width="150">
                        <v-container>
                          <v-card-text>
                            <v-row>
                              <v-col
                                v-for="item in colors"
                                :key="item.id"
                                cols="4"
                              >
                                <v-icon :color="item" @click="chooseColor(item)"
                                  >mdi-circle</v-icon
                                >
                              </v-col>
                            </v-row>
                          </v-card-text>
                        </v-container>
                      </v-card>
                    </v-menu>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-btn @click="showGuide" text color="green darken-1">Help</v-btn>
              <v-spacer></v-spacer>
              <v-btn @click="applyGoogleCalendar" text color="blue darken-1"
                >Apply</v-btn
              >
              <v-btn
                @click="RemoveGoogleCalendar"
                text
                color="blue-grey darken-1"
                >Remove</v-btn
              >
              <v-btn @click="modal = false" text color="red darken-1"
                >Cancle</v-btn
              >
            </v-card-actions>
          </v-form>
        </v-card>
      </v-dialog>
    </v-col>
  </v-row>
</template>

<script>
import axiosScript from "@/api/axiosScript.js";
export default {
  data() {
    return {
      modal: false,
      color: "blue",
      url: "",
      colors: [
        "blue",
        "indigo",
        "deep-purple",
        "cyan",
        "orange",
        "grey darken-1",
        "red",
        "green",
        "yellow",
      ],
    };
  },
  mounted() {
    this.url = this.iCal;
    this.color = this.iCalColor;
  },
  methods: {
    RemoveGoogleCalendar() {
      console.log(
        "modalButton GetGoogleCalendarButton removeGoogleCalendar method"
      );
      this.$store.commit("taskCntUp");
      localStorage.removeItem("iCal");
      localStorage.removeItem("iCalColor");
      axiosScript.deleteGoogleUrl(
        this.userInfo.id,
        (res) => {
          if (res.status == 200) {
            this.$store.commit("snackbar", {
              text: "새로고침 후 적용됩니다..",
              color: "primary",
            });
            this.modal = false;
            this.url = "";
          }
        },
        (err) => console.log(err),
        () => this.$store.commit("taskCntDown")
      );
    },
    applyGoogleCalendar() {
      console.log("GetGoogleCalendarButton applyGoogleCalendar");
      this.$store.commit("taskCntUp");
      axiosScript.applyGoogleUrl(
        {
          id: this.userInfo.id,
          url: this.url,
        },
        (res) => {
          console.log(res);

          if (res.status == 200) {
            console.log(res.data);
            let schedules = res.data;
            for (let s of schedules) {
              s.color = this.color;
              s.user_id = this.userInfo.id;
              this.$store.commit("pushScheduleInfo", s);
            }
            this.$store.commit("snackbar", {
              text: "구글 캘린더 적용 완료",
              color: "primary",
            });
            localStorage.setItem("iCal", this.url);
            localStorage.setItem("iCalColor", this.color);
            this.modal = false;
          }
        },
        (err) => {
          console.log(err);
          this.$store.commit("snackbar", {
            text: "올바르 않은 URL입니다..",
            color: "error",
          });
        },
        () => this.$store.commit("taskCntDown")
      );
    },
    showGuide() {
      console.log("GetGoogleCalendarButton showGuide");
      window.open("https://truesale.tistory.com/103", "ics가져오는 가이드 ");
    },
    chooseColor(item) {
      this.color = item;
    },
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
    iCal() {
      let iCal = localStorage.getItem("iCal");
      if (iCal == null) return "";
      return iCal;
    },
    iCalColor() {
      let iCalColor = localStorage.getItem("iCalColor");
      if (iCalColor == null) return "blue";
      return iCalColor;
    },
  },
};
</script>

<style></style>
