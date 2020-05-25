<template>
  <v-content class="mt-12">
    <v-container fluid wrap>
      <v-layout row my-10 class="justify-center">
        <v-flex lg6 md8 xs12 class="text-center">
          <v-avatar v-if="userInfo.img" size="164"
            ><v-img style="border-radius:70px" :src="userInfo.img"></v-img>
          </v-avatar>
          <v-list>
            <v-list-item>
              <v-list-item-content>
                <v-list-item class="justify-center">
                  <h2 class="headline font-weight-bold mb-3">
                    <div class="underlined">{{ userInfo.name }}</div>
                  </h2>
                  <v-tooltip bottom>
                    <template v-slot:activator="{ on }">
                      <v-icon @click="updateUser()" v-on="on"
                        >mdi-cog-outline</v-icon
                      >
                    </template>
                    <span>프로필을 수정하세요!</span>
                  </v-tooltip>
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
                  {{ userInfo.email }}
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
                  {{ userInfo.phoneNumber }}
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
                  {{ userInfo.name }}#{{ userInfo.id }}
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-flex>
      </v-layout>
    </v-container>
  </v-content>
</template>

<script>
import { mdiEmailOutline, mdiKey, mdiPhone } from "@mdi/js";
export default {
  name: "HelloWorld",
  mounted() {
    if (this.$route.params.id == null) {
      console.log(`My Profile page`);
    }
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },
  data: () => ({
    name: "애용",
    email: "dodyd@naver.com",
    number: "010-1234-1234",
    id: "A-yong",
    email_icon: mdiEmailOutline,
    recommend_icon: mdiKey,
    phone_icon: mdiPhone,
  }),
  methods: {
    updateUser() {
      console.log("회원정보 수정하기");
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
