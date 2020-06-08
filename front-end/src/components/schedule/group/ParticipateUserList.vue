<template>
  <v-simple-table height="300">
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">Name</th>
          <th class="text-left">Color</th>
          <th class="text-left">On</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in participateUserList" :key="item.name">
          <td>{{ item }}</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
export default {
  props: ["participateUserList"],
};
</script>

<style></style>
