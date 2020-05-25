<template>
  <v-card>
    <div class="container-fluid">
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
                        id="inputEmail"
                        class="form-control"
                        placeholder="Email address"
                        required
                        autofocus
                      />
                    </div>
                    <br />
                    <div class="form-label-group">
                      <input
                        type="password"
                        id="inputPassword"
                        class="form-control"
                        placeholder="Password"
                        required
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
export default {
  name: "Login",

  data: () => ({
    name: "",
    email: "",
    emailRules: [
      (v) => !!v || "E-mail is required",
      (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
    ],
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
