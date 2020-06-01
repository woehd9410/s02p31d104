<template>
  <v-content class="mt-12">
    <v-container fluid wrap>
      <v-layout v-if="profileInfo" row my-10 class="justify-center">
        <v-flex lg6 md8 xs12 class="text-center">
          <v-avatar size="164"
            ><v-img style="border-radius:70px" :src="profileInfo.url"></v-img>
          </v-avatar>
          <v-list>
            <v-list-item>
              <v-list-item-content>
                <v-list-item class="justify-center">
                  <div class="headline font-weight-bold mb-3">
                    <div class="">
                      {{ profileInfo.name }}
                    </div>
                  </div>
                </v-list-item>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>
                  <v-icon>{{ email_icon }}</v-icon> 이메일
                </v-list-item-title>
              </v-list-item-content>
              <v-list-item-content>
                <v-list-item-title>
                  {{ profileInfo.email }}
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>
                  <v-icon>{{ phone_icon }}</v-icon> 전화번호
                </v-list-item-title>
              </v-list-item-content>
              <v-list-item-content>
                <v-list-item-title>
                  {{ profileInfo.phone_number | phoneNumberFilter }}
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>
                  <v-icon>{{ recommend_icon }}</v-icon> 친구 추천 코드
                </v-list-item-title>
              </v-list-item-content>
              <v-list-item-content>
                <v-list-item-title>
                  {{ profileInfo.name }}#{{ userInfo.id }}
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-icon @click="updateUser()">mdi-cog-outline</v-icon>
          </v-list>
        </v-flex>
      </v-layout>
    </v-container>
  </v-content>
</template>

<script>
import { mdiEmailOutline, mdiKey, mdiPhone } from "@mdi/js";
import phoneNumberFilter from "@/utils/filters/phoneNumberFilter.js";
import axiosScript from "@/api/axiosScript.js";
export default {
  filters: {
    phoneNumberFilter: phoneNumberFilter,
  },
  mounted() {
    console.log(`Profile mounted route parms.id : ${this.$route.params.id}`);
    console.log(`Profile mounted userInfo.id : ${this.userInfo.id}`);

    if (this.$route.params.id == this.userInfo.id) {
      console.log(`My Profile page`);
      this.profileInfo = this.userInfo;
    }
  },
  watch: {
    $route(to) {
      if (to.params.id == this.userInfo.id) {
        console.log("Profile watch route eq to.params.id and userInfo.id");
        this.profileInfo = this.userInfo;
        return;
      }
      console.log(`Profile watch route to.params.id : ${to.params.id}`);
      this.bringProfile(to.params.id);
    },
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },
  data: () => ({
    profileInfo: null,
    email_icon: mdiEmailOutline,
    recommend_icon: mdiKey,
    phone_icon: mdiPhone,
  }),
  methods: {
    updateUser() {
      console.log("회원정보 수정하기");
      alert("서비스 준비중입니다.");
    },
    bringProfile(id) {
      axiosScript.searchUserByOptions(
        id,
        (res) => {
          this.profileInfo = res.data[0];
          console.log("Profile bringProfile method (bring favorite info) ");
          console.log(res.data[0]);
        },
        (err) => console.log(err)
      );
    },
  },
};
</script>
<style scoped>
.underlined {
  text-decoration: none;
  font-weight: bold;
  position: relative;
  z-index: 1;
  display: inline-flex;
  padding-left: 10px;
  padding-bottom: 5px;
  padding-right: 10px;
}
.underlined::before {
  content: "";
  width: 100%;
  height: 80%;
  background-image: linear-gradient(to top, #85b9f5 25%, rgba(0, 0, 0, 0) 40%);
  position: absolute;
  left: 0;
  bottom: 2px;
  z-index: -1;
  will-change: width;
  transform: rotate(-2deg);
  transform-origin: left bottom;
}
</style>
