<template>
  <v-content>
    <v-container>
      <v-data-table
        :headers="headers"
        :items="boardArray"
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

            <!-- 수정하기 모달 -->
            <v-dialog v-if="updateInfo != null" v-model="dialog" max-width="500px">
              <v-card>
                <v-card-title>
                  <span class="headline">수정하기</span>
                </v-card-title>

                <v-card-text>
                  <writeBoard :updateInfo="updateInfo"></writeBoard>
                </v-card-text>
              </v-card>
            </v-dialog>

            <!-- 상세보기 모달 -->
            <v-dialog v-if="board != null" v-model="updateDialog" max- width="500">
              <v-card>
                <v-card-title>
                  <span class="headline">상세보기</span>
                </v-card-title>

                <v-card-text>
                  <writeBoard :updateInfo="updateInfo"></writeBoard>
                </v-card-text>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>

        <template v-slot:item.actions="{ item }">
          <v-icon class="mr-10" @click="showDetail(item)">
            <!-- {{ icon_detail }} -->

            mdi-sticker-plus-outline
          </v-icon>

          <v-icon class="mr-10" @click="editItem(item)">
            mdi-pencil
          </v-icon>

          <v-icon @click="deleteItem(item)" color="red">
            mdi-delete
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
// import boardDetail from "@/components/board/BoardDetail.vue";
import { EventBus } from "../../plugins/eventBus.js";
import { mdiStickerPlusOutline } from "@mdi/js";
import BoardApi from "../../api/v1/boardAxiosScript.js";

export default {
  components: {
    writeBoard,
    // boardDetail,
  },

  data: () => ({
    board: null,
    flag: null,
    updateInfo: null,

    boardArray: [],

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
        value: "id",
      },
      {
        text: "제목",
        sortable: false,
        value: "title",
      },
      {
        text: "날짜",
        value: "updated_time",
      },
      { text: "Actions", value: "actions", sortable: false },
    ],
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

    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },

  mounted() {
    // alert("여기가 진짜야!!");

    console.log("현재 내 이메일 : " + this.userInfo.email);

    BoardApi.searchBoardByEmail(
      this.userInfo.email,
      (res) => {
        console.log("게시판 불러오기 성공");

        this.boardArray = res.data;
      },
      (error) => {
        console.log("게시판 불러오기 실패");
        console.log(error);
      }
    );
  },

  watch: {
    dialog(val) {
      val || this.close();
    },
  },

  created() {
    EventBus.$on("use-eventbus", (dialog) => {
      this.dialog = dialog;

      BoardApi.searchBoardByEmail(
        this.userInfo.email,
        (res) => {
          console.log("게시판 불러오기 성공");

          this.boardArray = res.data;
        },
        (error) => {
          console.log("게시판 불러오기 실패");
          console.log(error);
        }
      );
    });

    EventBus.$on("use-eventbus", (updateDialog) => {
      this.updateDialog = updateDialog;
    });
  },

  methods: {
    editItem(item) {
      this.editedIndex = this.boardArray.indexOf(item);
      this.editedItem = Object.assign({}, item);
      console.log("수정할 게시글 index :: " + this.editedIndex);

      // 0이면 글쓰기, 1이면 수정하기, 상세보기
      this.flag = 1;
      this.board = this.boardArray[this.editedIndex];

      this.updateInfo = {
        flag: this.flag,
        board: this.board,
      };

      console.log(this.updateInfo);

      this.dialog = true;
    },

    showDetail(item) {
      this.editedIndex = this.boardArray.indexOf(item);
      // this.board = this.boardArray[this.editedIndex];
      console.log("index:::" + this.editedIndex);
      console.log("boardArray에서 가져온 board ::: " + this.boardArray[this.editedIndex].title);
      console.log(this.board);

      // 0이면 글쓰기, 1이면 수정하기, 2이면 상세보기
      this.flag = 2;
      this.board = this.boardArray[this.editedIndex];

      this.updateInfo = {
        flag: this.flag,
        board: this.board,
      };

      // EventBus.$emit("eventbus", board);
      // console.log("이벤트 버스 BoadDetail로 보내기 완료");
      this.updateDialog = true;
    },

    deleteItem(item) {
      const index = this.boardArray.indexOf(item);
      console.log("삭제 : " + index);
      if (confirm("삭제 하시겠습니까?")) {
        BoardApi.deleteBoard(
          this.boardArray[index].id,
          (res) => {
            this.boardArray.splice(index, 1);
            console.log(index + "번째 게시물 삭제 성공");
            console.log(res);
          },
          (error) => {
            console.log("게시판 삭제 실패");
            console.log(error);
          }
        );
      }
      // confirm("삭제 하시겠습니다?") && this.desserts.splice(index, 1);
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
