<template>
  <v-row justify="center">
    <v-col>
      <v-dialog persistent max-width="600px" v-model="dialog">
        <template v-slot:activator="{ on }">
          <v-btn block tile color="grey darken-1" dark v-on="on" @click.stop="dialog = true"><v-icon>mdi-lead-pencil </v-icon>Write Board</v-btn>
        </template>
        <v-card>
          <v-card-title>
            <span class="headline">글쓰기</span>
          </v-card-title>

          <writeBoard></writeBoard>
        </v-card>
      </v-dialog>
    </v-col>
  </v-row>
</template>

<script>
import writeBoard from "@/components/board/WriteBoard.vue";
import { EventBus } from "../../plugins/eventBus.js";

export default {
  components: {
    writeBoard,
  },

  data: () => ({
    editedItem: {
      name: "",
      calories: 0,
      fat: 0,
      carbs: 0,
      protein: 0,
    },

    dialog: false,
  }),

  mounted() {
    EventBus.$on("use-eventbus", (dialog) => {
      this.dialog = dialog;
    });
  },
  computed: {},

  methods: {},
};
</script>

<style></style>
