<template>
  <div>
    <v-card color="#385F73" dark>
      <v-card-title class="headline">
        <v-icon class="mr-1">mdi-magnify</v-icon>Find Password</v-card-title
      >
      <div v-if="!showChangePasswordForm">
        <v-col>
          <v-text-field
            label="Email"
            placeholder="가입하신 이메일을 입력하세요."
            v-model="input.email"
            outlined
            @keydown.enter="sendAuthEmail"
            append-icon="mdi-magnify"
          ></v-text-field>
          <v-progress-linear
            v-if="loadingSendEmail"
            indeterminate
            color="teal"
          ></v-progress-linear>
        </v-col>
        <v-col v-if="showAuthCodeField" class="authCode">
          <v-text-field
            label="Code"
            outlined
            v-model="input.code"
            :color="inspecPassEmail ? 'blue' : 'red'"
          ></v-text-field>
        </v-col>
      </div>
      <div v-else>
        <v-col>
          <v-text-field
            label="Password"
            v-model="input.password"
            outlined
            type="password"
          ></v-text-field>
        </v-col>
        <v-col v-if="showAuthCodeField" class="authCode">
          <v-text-field
            label="Check Password"
            outlined
            type="password"
            v-model="input.inspecPassword"
            :color="inspecPassPassword ? 'blue' : 'red'"
          ></v-text-field>
        </v-col>
      </div>

      <v-card-actions>
        <v-spacer />
        <v-btn
          v-if="!showChangePasswordForm"
          class="primary"
          :disabled="!inspecPassEmail"
          @click="nextFormClick"
          text
          >Next Page</v-btn
        >
        <v-btn
          v-else
          class="primary"
          :disabled="!inspecPassPassword"
          @click="changePassword"
          text
          >Change password</v-btn
        >
        <v-btn class="error" @click="finishEvent" text>Cancle</v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import axiosScript from "@/api/axiosScript.js";
export default {
  data() {
    return {
      input: {
        email: "",
        code: "",
        password: "",
        inspecPassword: "",
      },
      loadingSendEmail: false,
      showAuthCodeField: false,
      resInspecCode: "",
      inspecPassEmail: false,

      //change pass data
      showChangePasswordForm: false,
      inspecPassPassword: false,
    };
  },
  watch: {
    "input.code"(newValue) {
      console.log(newValue);
      console.log(this.resInspecCode)
      this.inspecPassEmail = this.resInspecCode === newValue;
    },
    "input.inspecPassword"(newValue) {
      console.log(newValue);
      this.inspecPassPassword = this.input.password === newValue;
    },
  },
  methods: {
    nextFormClick() {
      console.log("FindPassword nextFormclick method");
      this.showChangePasswordForm = true;
    },
    sendAuthEmail() {
      console.log("FindPassword sendAuthEmail method");
      axiosScript.emailAuth(
        this.input.email,
        (res) => {
          console.log(res)
          this.resInspecCode = res.data;
        },
        (err) =>{console.log(err)},
      )
      this.loadingSendEmail = true;
      this.showAuthCodeField = true;
      this.loadingSendEmail = false;
    },
    changePassword() {
      console.log("FindPassword changePassword method");
      this.finishEvent();
    },
    finishEvent() {
      console.log("FindPassword finishEvent method");
      let data ={
        email: this.input.email,
        password: this.input.password
      }
      axiosScript.updateUserPassword(
        data,
        (res) => {console.log(res)},
        (err) => {console.log(err)}
      )
      this.inspecPassEmail = false;
      this.inspecPassPassword = false;
      this.showAuthCodeField = false;
      this.showChangePasswordForm = false;
      this.input = {
        email: "",
        code: "",
        password: "",
        inspecPassword: "",
      };

      
      this.$emit("finishEvent");
    },
  },
};
</script>

<style>
.authCode {
  animation-name: example;
  animation-duration: 2s;
}

@keyframes example {
  0% {
    opacity: 0;
    top: -80px;
  }
  100% {
    opacity: 1;
    top: 0px;
  }
}
</style>
