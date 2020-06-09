<template>
  <v-navigation-drawer
    :color="themeColor"
    v-model="$store.state.ui.drawer"
    app
    clipped
  >
    <v-card class="mx-auto" width="300">
      <v-list>
        <v-list-item @click="moveRoute()">
          <v-list-item-title>Alarms</v-list-item-title>
        </v-list-item>

        <v-list-item @click="moveRoute('schedule')">
          <v-list-item-icon
            ><v-icon>mdi-calendar-arrow-right </v-icon></v-list-item-icon
          >
          <v-list-item-title>Schedule</v-list-item-title>
        </v-list-item>

        <v-list-group
          prepend-icon="mdi-account-multiple-plus-outline"
          no-action
        >
          <template v-slot:activator>
            <v-list-item-title>Favorite</v-list-item-title>
          </template>
          <v-list-item @click="moveRoute('favorite')">
            <v-list-item-title>person</v-list-item-title>
            <v-list-item-action>
              <v-icon>mdi-account-outline</v-icon>
            </v-list-item-action>
          </v-list-item>
          <v-list-item @click="moveRoute('favorite-0')">
            <v-list-item-title>group</v-list-item-title>
            <v-list-item-action>
              <v-icon>mdi-account-group-outline </v-icon>
            </v-list-item-action>
          </v-list-item>
          <v-list-item @click="moveRoute('favorite-1')">
            <v-list-item-title>department</v-list-item-title>
            <v-list-item-action>
              <v-icon>mdi-account-tie</v-icon>
            </v-list-item-action>
          </v-list-item>
        </v-list-group>
      </v-list>
    </v-card>
  </v-navigation-drawer>
</template>

<script>
export default {
  methods: {
    moveRoute(type='') {
      console.log(`go route /alarm:${type}`);
    },
  },
  data: () => ({}),
  computed: {
    themeColor() {
      return this.$store.getters.getThemeColor;
    },
  },
};
</script>

<style></style>
