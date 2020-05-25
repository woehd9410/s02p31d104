<template>
  <v-card>
    <div class="container-fluid">
      <div class="row no-gutter">
        <div class="image col-lg-6 col-md-12 col-sm-12 pa-0">
          <img
            src="https://search.pstatic.net/common/?src=http%3A%2F%2Fpost.phinf.naver.net%2FMjAxODAxMDlfMjgy%2FMDAxNTE1NDcxOTgwNzMy.SwBdmOqNPVBGl00FcD_Qt1A7-oQ4Z91Y-vxcVYBcEB8g.1WACHs_iAy_reXQCyG7kNSPejearErbneyu0h122L6Ag.JPEG%2FIRhV1J37bSVke3lLZNsdWwj1aNGE.jpg&type=b400"
          />
        </div>
        <div class="col-md-12 col-lg-6 col-sm-12">
          <div class="login d-flex align-items-center py-5">
            <div class="container">
              <div class="row">
                <div class="col-md-9 col-lg-8 mx-auto">
                  <h3 class="login-heading mb-4">Login</h3>
                  <form>
                    <div class="form-label-group">
                      <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus />
                    </div>
                    <br />
                    <div class="form-label-group">
                      <input type="password" id="inputPassword" class="form-control" placeholder="Password" required />
                    </div>

                    <br />
                    <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="button" @click="login()">
                      Login
                    </button>
                    <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="button" @click="signup()">
                      Sign in
                    </button>
                    <div class="text-center">
                      <button @click="searchPW()">
                        <a class="small">Forgot password?</a>
                      </button>
                      <!-- <a class="small"><button @click="searchPW()"></button>Forgot password?</a> -->
                    </div>
                    <hr />
                    <div style="text-align: center;">SNS Login</div>
                    <br />
                    <v-card-actions>
                      <v-spacer />
                      <v-btn color="yellow" large @click="kakaologin()">Kakao Login</v-btn>
                      <v-btn color="success" large @click="naverlogin()">Naver Login</v-btn>
                      <v-spacer />
                    </v-card-actions>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </v-card>
</template>

<style>
.image img {
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
</style>
<script>
export default {
  name: "Login",

  data: () => ({
    name: "",
    email: "",
    emailRules: [(v) => !!v || "E-mail is required", (v) => /.+@.+\..+/.test(v) || "E-mail must be valid"],
  }),
  methods: {
    closeDialog(type) {
      this.$emit("loginEvent", type);
    },
    login() {
      this.closeDialog("Nomal");
    },
    backpage() {
      this.$router.go(-1);
    },
    signup() {
      console.log("회원가입 페이지 이동");
      // this.$router.push("/signup");
    },
    kakaologin() {
      this.closeDialog("Kakao");
    },
    naverlogin() {
      this.closeDialog("Naver");
    },
    searchPW() {
      console.log("비밀번호 찾기");
      alert("비밀번호 찾기");
    },
  },
};
</script>
