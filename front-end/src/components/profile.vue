<template>
  <v-content>
    <v-container>
      <v-row class="text-center">
        <v-col cols="12">
          <div class="box">
            <img
              style="border-radius:70px"
              src="https://search.pstatic.net/common/?src=http%3A%2F%2Fpost.phinf.naver.net%2FMjAxODAxMDlfMjgy%2FMDAxNTE1NDcxOTgwNzMy.SwBdmOqNPVBGl00FcD_Qt1A7-oQ4Z91Y-vxcVYBcEB8g.1WACHs_iAy_reXQCyG7kNSPejearErbneyu0h122L6Ag.JPEG%2FIRhV1J37bSVke3lLZNsdWwj1aNGE.jpg&type=b400"
              height="200"
            />
          </div>
        </v-col>

        <v-col class="mb-5" cols="12">
          <h2 class="headline font-weight-bold mb-3">{{ name }}</h2>

          이메일 : {{ email }}
          <br />
          전화번호 : {{ number }}
          <br />
          친구 추천 코드 : {{ name }}#{{ id }} <br /><br />
          <v-btn color="primary" large @click="updateUser()">수정하기</v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-content>
</template>

<style></style>

<script>
export default {
  name: "Profile",

  data: () => ({
    name: "애용",
    email: "dodyd@naver.com",
    number: "010-1234-1234",
    id: "A-yong",
  }),
  methods: {
    updateUser() {
      console.log("회원정보 수정하기");
      alert("회원정보 수정하기");
    },
  },
};
</script>
