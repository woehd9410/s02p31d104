<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="4">
        <v-card>
          <v-card-text>
            <v-form>
              <v-text-field v-model="email" :rules="emailRules" prepend-icon="mdi-account" label="E-mail" required></v-text-field>
              <v-text-field id="password" label="Password" name="password" prepend-icon="mdi-lock" type="password" />
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-spacer />
            <v-btn color="primary">Login</v-btn>
            <v-btn color="primary" @click="signup">SignUP</v-btn>
            <v-btn color="primary" @click="backpage">Cancel</v-btn>
            <v-spacer />
          </v-card-actions>
          <div>
            <div>
              <hr />
              <div style="text-align: center;">SNS Login</div>
              <v-card-actions>
                <v-spacer />
                <v-btn color="yellow" @click="kakaologin()">Kakao</v-btn>
                <v-btn color="success" @click="naverlogin()">Naver</v-btn>
                <v-spacer />
              </v-card-actions>
            </div>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "Login",

  data: () => ({
    name: "",
    email: "",
    emailRules: [(v) => !!v || "E-mail is required", (v) => /.+@.+\..+/.test(v) || "E-mail must be valid"],
  }),
  methods: {
    backpage() {
      this.$router.go(-1);
    },
    signup() {
      this.$router.push("/signup");
    },
    kakaologin() {},
    naverlogin() {},
  },
};
</script>

<style></style>
