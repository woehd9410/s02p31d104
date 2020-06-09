<template>
  <v-list-item>
    <template v-slot:default="{ active }">
      <v-list-item-action>
        <v-checkbox :input-value="active"></v-checkbox>
      </v-list-item-action>

      <v-list-item-content>
        <v-list-item-title>{{ title }}</v-list-item-title>
        <v-list-item-subtitle>{{ pushDate }} </v-list-item-subtitle>
        {{ content }}
      </v-list-item-content>
    </template>

  </v-list-item>
</template>

<script>
export default {
  props: {
    id: {
      type: Number,
    },
    type: {
      type: Number,
    },
    pushDate: {
      type: String,
    },
    title: {
      type: String,
    },
    content: {
      type: String,
    },
    isRead: {
      type: Number,
    },
    isAccept: {
      type: Number,
    },
    toUserId: {
      type: Number,
    },
    fromUserId: {
      type: Number,
    },
  },
};
</script>

<style>
.alarm-item-right {
  float: right;
}
</style>
