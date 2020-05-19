<template>
  <v-content>
    <v-container fluid wrap>
      <v-layout row my-10 class="justify-center">
        <v-flex lg6 md8 xs12 class="text-center">
          <v-avatar size="164"
            ><v-img
              style="border-radius:70px"
              src="https://search.pstatic.net/common/?src=http%3A%2F%2Fpost.phinf.naver.net%2FMjAxODAxMDlfMjgy%2FMDAxNTE1NDcxOTgwNzMy.SwBdmOqNPVBGl00FcD_Qt1A7-oQ4Z91Y-vxcVYBcEB8g.1WACHs_iAy_reXQCyG7kNSPejearErbneyu0h122L6Ag.JPEG%2FIRhV1J37bSVke3lLZNsdWwj1aNGE.jpg&type=b400"
            >
            </v-img>
          </v-avatar>
          <v-list>
            <v-list-item>
              <v-list-item-content>
                <h2 class="headline font-weight-bold mb-3">
                  <div class="underlined">{{ name }}</div>
                </h2>
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
                  {{ email }}
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
                  {{ number }}
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
                <v-list-item-title> {{ name }}#{{ id }} </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-list-item class="justify-center">
              <v-tooltip bottom>
                <template v-slot:activator="{ on }">
                  <v-btn large @click="updateUser()" v-on="on" text>
                    <img src="https://cdn.icon-icons.com/icons2/906/PNG/512/cogwheel-1_icon-icons.com_70236.png" height="30px" />
                  </v-btn>
                </template>
                <span>프로필을 수정하세요!</span>
              </v-tooltip>
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
      alert("회원정보 수정하기");
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
