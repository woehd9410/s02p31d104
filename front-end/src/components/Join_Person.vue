<template>
  <!-- npm install vuelidate 
    -->
  <div>
    <v-layout row my-10 class="justify-center">
      <v-flex lg3 md3 sm3 xs8>
        <v-carousel v-model="model2" height="300">
          <v-carousel-item v-for="(color, i) in colors" :key="i">
            <v-sheet height="100%" tile>
              <v-img :src="color.url" height="100%" width="100%"></v-img>
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
          이름
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
              @input="$v.name.$touch()"
              @blur="$v.name.$touch()"
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
              @input="$v.email.$touch()"
              @blur="$v.email.$touch()"
            ></v-textarea>
            <v-btn
              class="ml-2 mt-1"
              :class="{
                primary: duplicateCheckEmail,
                vibro: duplicateCheckEmailUX,
              }"
              large
              @click="emailCheck()"
              >{{ duplicateCheckEmail ? "완료" : "중복확인" }}</v-btn
            >
          </div>

          비밀번호
          <div class="row">
            <v-text-field
              v-model="password"
              type="password"
              :auto-grow="autoGrow"
              :outlined="outlined"
              :placeholder="placeholder_password"
              :rounded="rounded"
              :rows="rows"
              :clearable="clearable"
              :error-messages="passwordErrors"
              required
              @input="$v.password.$touch()"
              @blur="$v.password.$touch()"
            ></v-text-field>
          </div>

          비밀번호 확인
          <div class="row">
            <v-text-field
              type="password"
              v-model="passwordCheck"
              :auto-grow="autoGrow"
              :outlined="outlined"
              :placeholder="placeholder_passwordCheck"
              :rounded="rounded"
              :rows="rows"
              :clearable="clearable"
              :error-messages="passwordCheckErrors"
              required
              @input="$v.passwordCheck.$touch()"
              @blur="$v.passwordCheck.$touch()"
            ></v-text-field>
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
              @input="$v.number.$touch()"
              @blur="$v.number.$touch()"
            ></v-textarea>
          </div>

          생년월일
          <div class="row">
            <v-menu
              v-model="menu2"
              :close-on-content-click="false"
              :nudge-right="40"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
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
                ></v-textarea>
              </template>
              <v-date-picker
                v-model="date"
                @input="menu2 = false"
              ></v-date-picker>
            </v-menu>
          </div>

          <div class="row">
            <v-checkbox
              v-model="checkbox"
              :error-messages="checkboxErrors"
              label="회원가입을 동의합니다."
              required
              color="black"
            ></v-checkbox>
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
import { EventBus } from "../plugins/eventBus.js";
import axiosScript from "@/api/axiosScript.js";
export default {
  mixins: [validationMixin],

  validations: {
    name: { required, maxLength: maxLength(10) },
    email: { required, email },
    password: { required },
    passwordCheck: { required },
    number: { required },
    // birthday: { required },
    checkbox: {
      checked(val) {
        return val;
      },
    },
  },

  data: () => ({
    duplicateCheckEmailUX: false,
    duplicateCheckEmail: false,
    name: "",
    email: "",
    password: "",
    phoneNumber: "",
    date: "",
    passwordCheck: "",
    number: "",
    checkbox: false,

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

    colors: [],
    model2: 0,
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
      !this.$v.passwordCheck.required &&
        errors.push("비밀번호 확인을 입력해 주세요!");
      return errors;
    },

    numberErrors() {
      const errors = [];
      if (!this.$v.number.$dirty) return errors;
      // !this.$v.name.name && errors.push("이메일 형식을 확인해주세요.");
      !this.$v.number.required && errors.push("휴대폰 번호를 입력해 주세요!");
      return errors;
    },

    // birthdayErrors() {
    //   const errors = [];
    //   if (!this.$v.birthday.$dirty) return errors;
    //   // !this.$v.name.name && errors.push("이메일 형식을 확인해주세요.");
    //   !this.$v.birthday.required && errors.push("생일을 선택해 주세요!");
    //   return errors;
    // },
  },
  mounted() {
    this.getImgAll();
  },
  methods: {
    getImgAll() {
      axiosScript.searchImgAll(
        (res) => {
          console.log(res.data);
          this.colors = res.data;
        },
        (err) => console.log(err)
      );
    },

    join() {
      console.log(this.$v.$touch());
      // if (typeof this.$v.$touch() === "undefined") return;

      this.$v.$touch();

      if (
        this.nameErrors == "" &&
        this.emailErrors == "" &&
        this.passwordErrors == "" &&
        this.passwordCheckErrors == "" &&
        this.numberErrors == "" &&
        this.checkboxErrors == "" &&
        this.duplicateCheckEmail
      ) {
        let userInfo = {
          name: this.name,
          email: this.email,
          password: this.password,
          phone_number: this.number,
          birthday: this.date,
          img: this.colors[this.model2].url,
          type: "Person",
        };
        this.$emit("joinSuccess", userInfo);
      }
    },

    emailCheck() {
      console.log("이메일 중복 검사");
      var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      if (!regExp.test(this.email)) {
        this.duplicateCheckEmailUX = true;
        setTimeout(() => {
          this.duplicateCheckEmailUX = false;
        }, 500);
        return;
      }

      axiosScript.searchUserByOptions(
        this.email,
        (res) => {
          if (res.data.length == 0) this.duplicateCheckEmail = true;
          else {
            this.duplicateCheckEmailUX = true;
            this.duplicateCheckEmail = false;
            setTimeout(() => {
              this.duplicateCheckEmailUX = false;
            }, 500);
          }
        },
        (err) => console.log(err)
      );
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
