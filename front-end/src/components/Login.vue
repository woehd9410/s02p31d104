<template>
  <v-card>
    <JoinWindows
      v-if="joinState"
      @joinSuccess="joinState = false"
      @joinCancle="joinState = false"
    />
    <div class="container-fluid" v-else>
      <div class="row no-gutter">
        <div class="image col-lg-6 col-md-12 col-sm-12 pa-0">
          <v-img
            src="
            https://images.unsplash.com/photo-1513128034602-7814ccaddd4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60
            "
            max-width="700"
            max-height="560"
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
                      <input
                        type="email"
                        v-model="loginInfo.email"
                        id="inputEmail"
                        class="form-control"
                        placeholder="Email address"
                        required
                        autofocus
                        @keypress.enter="login"
                      />
                    </div>
                    <br />
                    <div class="form-label-group">
                      <input
                        v-model="loginInfo.password"
                        type="password"
                        id="inputPassword"
                        class="form-control"
                        placeholder="Password"
                        required
                        @keypress.enter="login"
                      />
                    </div>

                    <br />
                    <button
                      class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2"
                      type="button"
                      @click="login()"
                    >
                      Login
                    </button>
                    <button
                      class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2"
                      type="button"
                      @click="signup()"
                    >
                      Sign in
                    </button>
                    <div class="text-center">
                      <v-dialog
                        v-model="findPasswordModal"
                        persistent
                        width="500"
                        ><template v-slot:activator="{ on }">
                          <button
                            @click="findPasswordModal = true"
                            v-on="on"
                            type="button"
                          >
                            <span style="color:blue" class="small"
                              >Forgot password?</span
                            >
                          </button>
                        </template>
                        <FindPassword
                          @finishEvent="findPasswordModal = false"
                        />
                      </v-dialog>
                    </div>
                    <hr />
                    <div style="text-align: center;" @click="testLogin">
                      SNS Login
                    </div>
                    <br />
                    <v-card-actions>
                      <v-spacer />
                      <v-btn color="yellow" large @click="kakaologin()"
                        >Kakao Login</v-btn
                      >

                      <v-btn color="success" large @click="naverlogin()"
                        >Naver Login</v-btn
                      >
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
import FindPassword from "@/components/FindPassword.vue";
import JoinWindows from "@/components/JoinWindows.vue";
import axiosScript from "@/api/axiosScript.js";
export default {
  name: "Login",
  components: {
    JoinWindows,
    FindPassword,
  },

  data: () => ({
    joinState: false,
    findPasswordModal: false,
    loginInfo: {
      email: "",
      password: "",
    },
    emailRules: [
      (v) => !!v || "E-mail is required",
      (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
    ],
  }),
  methods: {
    testLogin() {
      this.loginInfo = {
        email: "test@naver.com",
        password: "test",
      };
      this.login();
    },
    closeDialog(type) {
      console.log(`login type : ${type}`);
      this.$emit("closeDialog");
    },
    login() {
      this.$store.commit("taskCntUp");
      axiosScript.login(
        this.loginInfo,
        (res) => {
          if (res.status != 200) {
            alert("올바르지않은 로그인 정보");
            return;
          }
          let loginInfo = res.data;
          loginInfo.type = "Person";
          console.log("Login login method in axios");
          console.log(loginInfo);

          this.$store.commit("login", loginInfo);
          this.closeDialog("Nomal");
        },
        (err) => console.log(err),
        () => this.$store.commit("taskCntDown")
      );
    },
    backpage() {
      this.$router.go(-1);
    },
    signup() {
      console.log("회원가입 페이지 이동");
      this.joinState = true;
      // this.$router.push("/signup");
    },
    kakaologin() {
      console.log("kakao login");
      this.$store.commit("snackbar", {
        text: "서비스 준비중입니다..",
        color: "warning",
      });
      // this.closeDialog("Kakao");
    },
    naverlogin() {
      console.log("naver login");
      this.$store.commit("snackbar", {
        text: "서비스 준비중입니다..",
        color: "warning",
      });
      // this.closeDialog("Naver");
    },
  },
};
</script>
