<template>
  <v-content>
    <v-container>
      <v-data-table
        :headers="headers"
        :items="desserts"
        :page.sync="page"
        :items-per-page="itemsPerPage"
        hide-default-footer
        class="elevation-1 mt-5"
        @page-count="pageCount = $event"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar flat color="white">
            <v-toolbar-title>My Diary</v-toolbar-title>
            <v-divider class="mx-4" inset vertical></v-divider>
            <v-spacer></v-spacer>

            <v-text-field v-model="search" append-icon="mdi-magnify" label="Search" single-line hide-details></v-text-field>

            <v-dialog v-model="dialog" max-width="500px">
              <v-card>
                <v-card-title>
                  <span class="headline">수정하기</span>
                </v-card-title>

                <v-card-text>
                  <writeBoard></writeBoard>
                </v-card-text>
              </v-card>
            </v-dialog>

            <v-dialog v-model="updateDialog" max- width="500">
              <v-card>
                <boardDetail></boardDetail>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>

        <template v-slot:item.actions="{ item }">
          <v-icon class="mr-5" @click="editItem(item)">
            mdi-pencil
          </v-icon>

          <v-icon class="mr-5" @click="deleteItem(item)" color="red">
            mdi-delete
          </v-icon>

          <v-icon @click="showDetail(item)">
            {{ icon_detail }}
          </v-icon>
        </template>
      </v-data-table>

      <div class="text-center pt-2">
        <v-pagination v-model="page" :length="pageCount" class="mt-5"></v-pagination>
      </div>
    </v-container>
  </v-content>
</template>

<script>
import writeBoard from "@/components/board/WriteBoard.vue";
import boardDetail from "@/components/board/BoardDetail.vue";
import { EventBus } from "../../plugins/eventBus.js";
import { mdiStickerPlusOutline } from "@mdi/js";

export default {
  components: {
    writeBoard,
    boardDetail,
  },

  data: () => ({
    icon_detail: mdiStickerPlusOutline,

    page: 1,
    itemsPerPage: 7,
    pageCount: 0,

    dialog: false,
    updateDialog: false,

    headers: [
      {
        text: "번호",
        align: "start",
        value: "number",
      },
      {
        text: "제목",
        sortable: false,
        value: "title",
      },
      {
        text: "날짜",
        value: "date",
      },
      { text: "Actions", value: "actions", sortable: false },
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {
      name: "",
      calories: 0,
      fat: 0,
      carbs: 0,
      protein: 0,
    },
    defaultItem: {
      name: "",
      calories: 0,
      fat: 0,
      carbs: 0,
      protein: 0,
    },

    search: "",
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "New Memo" : "Edit Memo";
    },
  },

  watch: {
    dialog(val) {
      val || this.close();
    },
  },

  created() {
    this.initialize();
    EventBus.$on("use-eventbus", (dialog) => {
      this.dialog = dialog;
    });

    EventBus.$on("use-eventbus", (updateDialog) => {
      this.updateDialog = updateDialog;
    });
  },

  methods: {
    initialize() {
      this.desserts = [
        {
          number: 1,
          title: "첫번째 글입니다.",
          date: "2020년 5월 5일",
        },
        {
          number: 2,
          title: "두번째 글입니다.",
          date: "2020년 5월 6일",
        },
        {
          number: 3,
          title: "세번째 글입니다.",
          date: "2020년 5월 7일",
        },
        {
          number: 4,
          title: "네번째 글입니다.",
          date: "2020년 5월 8일",
        },
        {
          number: 5,
          title: "다섯번째 글입니다.",
          date: "2020년 5월 9일",
        },
        {
          number: 6,
          title: "여섯번째 글입니다.",
          date: "2020년 5월 10일",
        },
        {
          number: 7,
          title: "일곱번째 글입니다.",
          date: "2020년 5월 11일",
        },
        {
          number: 8,
          title: "여덟번째 글입니다.",
          date: "2020년 5월 12일",
        },
        {
          number: 9,
          title: "아홉번째 글입니다.",
          date: "2020년 5월 13일",
        },
        {
          number: 10,
          title: "열번째 글입니다.",
          date: "2020년 5월 14일",
        },
      ];
    },

    editItem(item) {
      this.editedIndex = this.desserts.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    showDetail(item) {
      this.editedIndex = this.desserts.indexOf(item);

      this.updateDialog = true;
    },

    deleteItem(item) {
      const index = this.desserts.indexOf(item);
      confirm("삭제 하시겠습니다?") && this.desserts.splice(index, 1);
    },

    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.desserts[this.editedIndex], this.editedItem);
      } else {
        this.desserts.push(this.editedItem);
      }
      this.close();
    },
  },
};
</script>
