<template>
  <!-- npm install vuelidate 
       npm install vue
    -->
  <div>
    <v-layout row my-10 class="justify-center mt-10">
      <v-flex lg3 md3 sm3 xs8>
        <v-tooltip top>
          <template v-slot:activator="{ on }">
            <v-icon class="mb-5" v-on="on" @click="back()">{{ icon_back }}</v-icon>
          </template>
          <span>다시 선택하기</span>
        </v-tooltip>

        <v-carousel v-model="model2" height="300">
          <v-carousel-item v-for="(color, i) in colors" :key="color">
            <v-sheet :color="color" height="100%" tile>
              <v-row class="fill-height" align="center" justify="center">
                <div class="display-3">Slide {{ i + 1 }}</div>
              </v-row>
            </v-sheet>
          </v-carousel-item>
        </v-carousel>
      </v-flex>
    </v-layout>

    <v-layout row my-10 class="justify-center mt-10">
      <v-flex lg4 md4 sm6 xs8>
        <form>
          아이디
          <div class="row">
            <v-textarea
              v-model="name"
              :clearable="clearable"
              :auto-grow="autoGrow"
              :outlined="outlined"
              :placeholder="placeholder_name"
              :rounded="rounded"
              :rows="rows"
              :error-messages="nameErrors"
              required
            ></v-textarea>
          </div>
          이메일
          <div class="row">
            <v-textarea
              v-model="email"
              :auto-grow="autoGrow"
              :outlined="outlined"
              :placeholder="placeholder_email"
              :rounded="rounded"
              :rows="rows"
              :clearable="clearable"
              :error-messages="emailErrors"
              required
            ></v-textarea>
            <v-btn class="ml-2 mt-1" large @click="emailCheck()">중복확인</v-btn>
          </div>

          비밀번호
          <div class="row">
            <v-textarea
              v-model="password"
              :auto-grow="autoGrow"
              :outlined="outlined"
              :placeholder="placeholder_password"
              :rounded="rounded"
              :rows="rows"
              :clearable="clearable"
              :error-messages="passwordErrors"
              required
            ></v-textarea>
          </div>

          비밀번호 확인
          <div class="row">
            <v-textarea
              v-model="passwordCheck"
              :auto-grow="autoGrow"
              :outlined="outlined"
              :placeholder="placeholder_passwordCheck"
              :rounded="rounded"
              :rows="rows"
              :clearable="clearable"
              :error-messages="passwordCheckErrors"
              required
            ></v-textarea>
          </div>

          휴대폰 번호
          <div class="row">
            <v-textarea
              v-model="number"
              :auto-grow="autoGrow"
              :outlined="outlined"
              :placeholder="placeholder_number"
              :rounded="rounded"
              :rows="rows"
              :clearable="clearable"
              :error-messages="numberErrors"
              required
            ></v-textarea>
          </div>

          생년월일
          <div class="row">
            <v-menu v-model="menu2" :close-on-content-click="false" :nudge-right="40" transition="scale-transition" offset-y min-width="290px">
              <template v-slot:activator="{ on }">
                <v-textarea
                  v-model="date"
                  :auto-grow="autoGrow"
                  :outlined="outlined"
                  :placeholder="placeholder_birthday"
                  :rounded="rounded"
                  :rows="rows"
                  readonly
                  v-on="on"
                  :error-messages="birthdayErrors"
                  required
                ></v-textarea>
              </template>
              <v-date-picker v-model="date" @input="menu2 = false"></v-date-picker>
            </v-menu>
          </div>

          <div class="row">
            <v-checkbox v-model="checkbox" :error-messages="checkboxErrors" label="회원가입을 동의합니다." required color="black"></v-checkbox>
          </div>

          <v-btn class="mr-4" @click="join()">가입하기</v-btn>
        </form>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
import { validationMixin } from "vuelidate";
import { required, maxLength, email } from "vuelidate/lib/validators";
import { mdiArrowLeft } from "@mdi/js";
import { EventBus } from "../api/eventBus.js";
export default {
  mixins: [validationMixin],

  validations: {
    name: { required, maxLength: maxLength(10) },
    email: { required, email },
    password: { required },
    passwordCheck: { required },
    number: { required },
    birthday: { required },
    checkbox: {
      checked(val) {
        return val;
      },
    },
  },

  data: () => ({
    name: "",
    email: "",
    password: "",
    passwordCheck: "",
    number: "",
    checkbox: false,

    date: "",
    menu2: false,

    autoGrow: true,
    label: "",
    model: "",
    outlined: true,

    placeholder_name: "이름 입력",
    placeholder_email: "이메일 입력",
    placeholder_password: "비밀번호 입력",
    placeholder_passwordCheck: "비밀번호 확인",
    placeholder_number: "휴대폰 번호(숫자만 입력)",
    placeholder_birthday: "생년월일 입력",
    rounded: true,
    rows: 1,
    clearable: true,

    colors: ["primary", "secondary", "yellow darken-2", "red", "orange"],
    model2: 0,

    icon_back: mdiArrowLeft,
  }),

  computed: {
    checkboxErrors() {
      const errors = [];
      if (!this.$v.checkbox.$dirty) return errors;
      !this.$v.checkbox.checked && errors.push("동의하기를 눌러주세요!");
      return errors;
    },

    emailErrors() {
      const errors = [];
      if (!this.$v.email.$dirty) return errors;
      !this.$v.email.email && errors.push("이메일 형식을 확인해주세요.");
      !this.$v.email.required && errors.push("이메일을 입력해주세요!");
      return errors;
    },

    nameErrors() {
      const errors = [];
      if (!this.$v.name.$dirty) return errors;
      // !this.$v.name.name && errors.push("이메일 형식을 확인해주세요.");
      !this.$v.name.required && errors.push("이름을 입력해주세요!");
      return errors;
    },

    passwordErrors() {
      const errors = [];
      if (!this.$v.password.$dirty) return errors;
      // !this.$v.name.name && errors.push("이메일 형식을 확인해주세요.");
      !this.$v.password.required && errors.push("비밀번호를 입력해 주세요!");
      return errors;
    },

    passwordCheckErrors() {
      const errors = [];
      if (!this.$v.passwordCheck.$dirty) return errors;
      // !this.$v.name.name && errors.push("이메일 형식을 확인해주세요.");
      !this.$v.passwordCheck.required && errors.push("비밀번호 확인을 입력해 주세요!");
      return errors;
    },

    numberErrors() {
      const errors = [];
      if (!this.$v.number.$dirty) return errors;
      // !this.$v.name.name && errors.push("이메일 형식을 확인해주세요.");
      !this.$v.number.required && errors.push("휴대폰 번호를 입력해 주세요!");
      return errors;
    },

    birthdayErrors() {
      const errors = [];
      if (!this.$v.birthday.$dirty) return errors;
      // !this.$v.name.name && errors.push("이메일 형식을 확인해주세요.");
      !this.$v.birthday.required && errors.push("생일을 선택해 주세요!");
      return errors;
    },
  },

  methods: {
    join() {
      // this.$v.$touch();
      console.log("회원가입");
      alert("회원가입 하기!!");
    },

    emailCheck() {
      console.log("이메일 중복 검사");
      alert("이메일 중복 검사");
    },

    back() {
      console.log("개인 vs 부서 다시 고르기");
      let flag = 1;
      EventBus.$emit("use-eventbus", flag);
      console.log("이벤트 버스 완료");
    },
  },
};
</script>
