<template>
  <div>
    <v-dialog v-model="favoriteDialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn block tile color="grey darken-1" class="mb-5" dark v-on="on"
          ><v-icon>mdi-account-arrow-left-outline </v-icon>&nbsp;Add
          Favorite</v-btn
        >
      </template>
      <v-card dark>
        <div class="d-flex flex-no-wrap justify-space-between">
          <div>
            <v-card-title class="headline">+ Favorite</v-card-title>

            <v-card-subtitle class="mt-1 headline">
              <p class="mb-3">
                <strong>NAME</strong> :
                <span class="primary">{{
                  fUserInfo == null ? "searching" : fUserInfo.name
                }}</span>
              </p>
              <v-text-field
                label="친구 추천 코드"
                placeholder="NAME # CODE"
                :hint="loading"
                outlined
                v-model="addCode"
                @keydown.enter.prevent
              ></v-text-field>
            </v-card-subtitle>
          </div>

          <v-avatar v-if="fUserInfo != null" class="ma-3" size="125" tile>
            <v-img :src="fUserInfo.url"></v-img>
          </v-avatar>
        </div>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green" text @click="addFavorite">Request</v-btn>
          <v-btn color="red" text @click="favoriteDialog = false">Cancle</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-card max-width="500" class="mx-auto">
      <v-text-field
        solo
        label="List"
        append-icon="mdi-magnify"
        v-model="searchItem"
        @keyup.enter="searching"
        :class="{ vibro: notFound }"
      ></v-text-field>
      <v-list shaped>
        <v-list-item-group v-model="selectedPerson" color="primary">
          <v-list-item
            v-for="item in showItems"
            :key="item.id"
            @click="showFavoriteProfile(item.id)"
          >
            <v-list-item-avatar>
              <v-img :src="item.url"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title v-text="item.name"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-card>
  </div>
</template>

<script>
import axiosScript from "@/api/axiosScript.js";
export default {
  mounted() {
    this.$store.commit("taskCntUp");
    axiosScript.searchFavoriteByToId(
      this.userInfo.id,
      (res) => {
        this.items = res.data;
        this.showItems = this.items;
      },
      (err) => console.log(err),
      () => {
        this.$store.commit("taskCntDown");
      }
    );
  },
  watch: {
    addCode() {
      this.loading = "loading ...";
      let curText = this.addCode;
      setTimeout(() => {
        this.loading = curText;
        console.log(`FavoriteList watch addCode : ${this.addCode}`);
        console.log(`FavoriteList watch curText : ${curText}`);
        if (this.addCode == curText && curText.split("#").length == 2) {
          console.log(
            `FavoriteList watch eq this.addCode and curText then searchUserByOptions axios res fUserinfo`
          );
          this.findUserByAddCode(curText);
        }
      }, 2000);
    },
  },
  methods: {
    findUserByAddCode(code) {
      let name = code.split("#")[0];
      let id = Number(code.split("#")[1]);
      axiosScript.searchUserByOptions(
        id,
        (res) => {
          if (name.toUpperCase() == res.data[0].name.toUpperCase()) {
            this.fUserInfo = res.data[0];
          }
        },
        (err) => console.log(err)
      );
    },
    showFavoriteProfile(id) {
      console.log(`show favorite id : ${id}`);
      this.$router.push({ name: `Profile`, params: { id: id } });
    },
    addFavorite() {
      console.log(`show to add favorite modal`);
      this.$store.commit("snackbar", {
        text: "서비스 준비중입니다..",
        color: "warning",
      });
    },
    searching() {
      console.log(
        `FavoriteList searching searchItem before : ${this.searchItem}`
      );
      this.showItems = [];
      if (this.searchItem == "" || this.searchItem == null) {
        this.showItems = this.items;
        return;
      }
      for (let item of this.items) {
        if (
          item.name.toUpperCase().indexOf(this.searchItem.toUpperCase()) >= 0
        ) {
          this.showItems.push(item);
          console.log(`add favoriteList ${item.title}`);
        }
      }
      this.notFound = this.showItems.length == 0 ? true : false;
      this.searchItem = "";
      console.log(
        `FavoriteList searching searchItem after : ${this.searchItem}`
      );
      setTimeout(() => {
        this.notFound = false;
      }, 500);
    },
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },
  data() {
    return {
      loading: "loading ...",
      fUserInfo: null,
      addCode: "",
      favoriteDialog: false,
      addType: null,
      selectedPerson: null,
      searchItem: null,
      notFound: false,
      showItems: [],
    };
  },
};
</script>

<style></style>
