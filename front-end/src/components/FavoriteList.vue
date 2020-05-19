<template>
  <div>
    <v-btn
      block
      tile
      color="grey darken-1"
      class="mb-5"
      dark
      @click="addFavorite"
      ><v-icon>mdi-account-arrow-left-outline </v-icon>&nbsp;Add Favorite</v-btn
    >
    <v-card max-width="500" class="mx-auto">
      <v-toolbar color="grey darken-1" dark>
        <v-toolbar-title>List</v-toolbar-title>
        <v-spacer></v-spacer>

        <v-btn icon>
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
      </v-toolbar>
      <v-list>
        <v-list-item v-for="item in items" :key="item.title">
          <v-list-item-avatar>
            <v-img :src="item.avatar"></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title v-text="item.title"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-card>
  </div>
</template>

<script>
export default {
  methods: {
    addFavorite() {
      console.log(`show to add favorite modal`);
    },
  },
  data() {
    return {
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
