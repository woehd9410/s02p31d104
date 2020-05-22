<template>
  <div>
    <v-dialog v-model="favoriteDialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn block tile color="grey darken-1" class="mb-5" dark v-on="on"
          ><v-icon>mdi-account-arrow-left-outline </v-icon>&nbsp;Add
          Favorite</v-btn
        >
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Add Favorite (Person/Department) </span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="User Req Key (ID#Name)"
                  required
                ></v-text-field>
                <v-radio-group v-model="addType" :mandatory="false" row>
                  <v-radio label="Person" value="Person"></v-radio>
                  <v-radio label="Department" value="Department"></v-radio>
                </v-radio-group>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="addFavorite">Add</v-btn>
          <v-btn color="blue darken-1" text @click="favoriteDialog = false"
            >Close</v-btn
          >
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
          <v-list-item v-for="item in showItems" :key="item.title">
            <v-list-item-avatar>
              <v-img :src="item.avatar"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title v-text="item.title"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-card>
  </div>
</template>

<script>
export default {
  mounted() {
    this.showItems = this.items;
  },
  methods: {
    addFavorite() {
      console.log(`show to add favorite modal`);
      this.favoriteDialog = false;
    },
    searching() {
      console.log(`search input ${this.searchItem}`);
      this.showItems = [];
      if (this.searchItem == "") {
        this.showItems = this.items;
        return;
      }

      for (let item of this.items) {
        if (item.title.search(this.searchItem) > 0) {
          this.showItems.push(item);
          console.log(`add favoriteList ${item.title}`);
        }
      }
      this.notFound = this.showItems.length == 0 ? true : false;
      this.searchItem = "";
      setTimeout(() => {
        this.notFound = false;
      }, 500);
    },
  },
  data() {
    return {
      favoriteDialog: false,
      addType: null,
      selectedPerson: null,
      searchItem: null,
      notFound: false,
      showItems: [],
      items: [
        {
          icon: true,
          title: "Jason Oner",
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
        },
        {
          title: "Travis Howard",
          avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg",
        },
        {
          title: "Ali Connors",
          avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg",
        },
        {
          title: "Cindy Baker",
          avatar: "https://cdn.vuetifyjs.com/images/lists/4.jpg",
        },
      ],
    };
  },
};
</script>

<style></style>
