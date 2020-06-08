<template>
  <v-row justify="center">
    <v-col>
      <v-dialog v-model="toScheduleModal" dark persistent max-width="450px">
        <template v-slot:activator="{ on }">
          <v-btn block tile color="grey darken-1" dark v-on="on"
            ><v-icon class="mr-1">mdi-account-multiple-outline</v-icon>Add Group
            Schedule</v-btn
          >
        </template>
        <v-card>
          <v-form>
            <v-card-title>
              <span class="headline" style="padding-left=20px;">+ Group</span>
            </v-card-title>
            <v-card-text>
              <v-row>
                <v-col>
                  <v-text-field
                    prepend-icon="mdi-alpha-n-box-outline"
                    label="Name"
                    v-model="groupInfo.name"
                    required
                  >
                  </v-text-field>
                </v-col>
              </v-row>
              <v-row style="margin-left:1px; margin-right:1px;">
                <v-combobox
                  v-model="groupInfo.members"
                  :items="favoriteUserEmails"
                  :search-input.sync="search"
                  hide-selected
                  multiple
                  persistent-hint
                  small-chips
                  clearable
                  label="Invite Members"
                  hint="Email or Invite member code"
                  prepend-icon="mdi-alpha-m-box-outline"
                >
                  <template v-slot:no-data>
                    <v-list-item>
                      <v-list-item-content>
                        <v-list-item-title>
                          No results matching "<strong>{{ search }}</strong
                          >". Press <kbd>enter</kbd> to create a new one
                        </v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </template>
                </v-combobox>
              </v-row>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green lighten-2" text @click="makeGroupButtonEvetn"
                >Make</v-btn
              >
              <v-btn color="red darken-1" text @click="cancleScheduleModal"
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
      groupInfo: {
        name: "",
        members: [],
      },
      favoriteUserEmails: [],
      toScheduleModal: false,
      search: null,
    };
  },
  components: {},
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },
  mounted() {
    this.getFavoriteUserEmail();
  },
  methods: {
    cancleScheduleModal() {
      console.log("AddGroupScheduleButton cancleScheduleModal method");
      this.groupInfo.name = "";
      this.groupInfo.members = [];
      this.search = null;
      this.toScheduleModal = false;
    },
    makeGroupButtonEvetn() {
      console.log("AddGroupScheduleButton makeGroupButtonEvent method");
      this.$store.commit("snackbar", {
        text: "서비스 준비중입니다..",
        color: "warning",
      });
    },
    remove(item) {
      this.members.splice(this.members.indexOf(item), 1);
      this.members = [...this.members];
    },
    getFavoriteUserEmail() {
      axiosScript.searchFavoriteByToId(
        this.userInfo.id,
        (res) => {
          for (var i = 0; i < res.data.length; i++) {
            this.favoriteUserEmails.push(res.data[i].email);
          }
        },
        (error) => {
          console.log(error);
        },
        () => {}
      );
    },
  },
};
</script>

<style></style>
