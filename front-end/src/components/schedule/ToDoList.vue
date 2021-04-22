@@ -0,0 +1,354 @@
<template>
  <v-flex xs12 md5 sm12>
    <v-text-field
      label="Add To Do..."
      solo
      dense
      append-icon="mdi-calendar-check-outline "
      v-model="addToDoItem"
      @keyup.enter="addTodo"
    >
    </v-text-field>
    <v-card margin-top="150px">
      <v-dialog v-model="toScheduleModal" dark persistent max-width="450px">
        <WriteSchedule @cancleButtonEvent="toScheduleModal = false" />
      </v-dialog>
      <v-progress-linear
        class="my-0"
        color="success"
        v-model="progressPercentage"
      />
      <v-card-actions class="px-3" v-show="items.length">
        <span class="success--text" style="font-size: small">
          {{ remaining }} {{ remaining | pluralize("item") }} left
        </span>
        <v-spacer></v-spacer>
        <v-btn-toggle
          style="font-size: x-small"
          class="elevation-0"
          mandatory
          color="white"
          v-show="items.length"
        >
          <v-btn @click="changeList(1)" class="mx-0" color="success--text" small
            >all
          </v-btn>
          <v-btn @click="changeList(2)" class="mx-0" color="success--text" small
            >active
          </v-btn>
          <v-btn @click="changeList(3)" class="mx-0" color="success--text" small
            >complete
          </v-btn>
        </v-btn-toggle>
      </v-card-actions>
      <v-list
        id="todoContainer"
        style="padding-top: 0px; overflow-y: hidden"
        height="310"
        class="overflow-y-auto"
      >
        <template v-for="(item, index) in list">
          <v-list-item two-line :key="item.index">
            <v-list-item-content>
              <v-row class="pointerEffect" @click="isCompleteTodo(item)">
                <v-checkbox
                  style="margin: 5px 0 0 20px"
                  v-model="item.is_completed"
                  @click="isCompleteTodo(item)"
                  color="success"
                  hide-details
                ></v-checkbox>

                <v-list-item-title
                  v-if="item.is_completed"
                  class="success--text"
                  style="
                    margin: 12px 0 10px 10px;
                    flex: 0;
                    overflow: inherit;
                    text-decoration: line-through;
                  "
                  v-text="item.title"
                >
                </v-list-item-title>
                <v-list-item-title
                  v-else
                  style="margin: 12px 0 10px 10px; flex: 0; overflow: inherit"
                  v-text="item.title"
                >
                </v-list-item-title>
              </v-row>
            </v-list-item-content>
            <v-list-item-action>
              <v-row style="margin-bottom: 5px; margin-right: -1px">
                <v-btn
                  icon
                  style="margin-top: 10px"
                  @click="
                    toScheduleModal = true;
                    changeScheduleId(item.id);
                  "
                  ><v-icon> mdi-calendar </v-icon></v-btn
                >
                <v-btn
                  icon
                  style="margin-top: 9.5px"
                  @click="deleteTodo(item.id)"
                  ><v-icon color="red"> mdi-delete </v-icon></v-btn
                >
              </v-row>
            </v-list-item-action>
          </v-list-item>

          <v-divider
            v-if="index + 1 < items.length"
            :key="index"
            style="margin-top: 0px; margin-bottom: 0px"
          ></v-divider>
        </template>
      </v-list>
    </v-card>
    <v-btn
      @click="clearCompleted"
      block
      class="mt-3"
      color="success"
      depressed
      rounded
      v-show="items.length > remaining"
    >
      Clear completed
    </v-btn>
  </v-flex>
</template>

<script>
import axiosScript from "@/api/axiosScript.js";
import WriteSchedule from "@/components/schedule/WriteSchedule.vue";
export default {
  props: {
    items: {
      type: Array,
      require: true,
    },
  },
  components: {
    WriteSchedule,
  },
  created() {
    this.updateNum = 1;
  },
  updated() {
    if (this.updateNum == 1) {
      this.changeList(1);
      this.updateNum++;
    }
    var elem = this.$el.querySelector("#todoContainer");
    elem.scrollTop = elem.scrollHeight;
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
    remaining() {
      var completeNum = 0;
      for (var i = 0; i < this.items.length; i++) {
        if (this.items[i].is_completed == 0) completeNum++;
      }
      return completeNum;
    },
    progressPercentage() {
      const len = this.items.length;
      return ((len - this.remaining) * 100) / len;
    },
  },
  data() {
    return {
      addToDoItem: "",
      list: this.items,
      toScheduleModal: false,
      scheduleId: 0,
      schedulemodal: false,
      typeName: "",
      btnNum: 0,
      updateNum: 0,
    };
  },
  methods: {
    changeScheduleId(id) {
      this.scheduleId = id;
    },
    todoToSchedule() {
      this.toScheduleModal = false;
      let start = this.startDate + " " + this.startTime + ":00";
      let end = this.endDate + " " + this.endTime + ":00";
      let type;
      if (this.typeName === "private") type = 0;
      else type = 1;

      let data = {
        id: this.scheduleId,
        content: this.content,
        title: this.addToDoItem,
        start_time: start,
        end_time: end,
        address: this.address,
        public_type: type,
        schedule_type: 0,
        user_id: this.userInfo.id,
      };

      axiosScript.todoToSchedule(
        data,
        () => {
          this.schedulemodal = false;
          this.$emit("deleteEvent", data.id);
          this.addToDoItem = null;
          this.content = null;
          this.startTime = null;
          this.endTime = null;
          this.startDate = null;
          this.endDate = null;
          this.address = null;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    addTodo() {
      let type = 0;
      let userId = this.userInfo.id;
      let data = {
        title: this.addToDoItem,
        public_type: type,
        user_id: userId,
      };

      axiosScript.addToDo(
        data,
        (res) => {
          this.$emit("addEvent", res.data);
          this.changeList(this.btnNum);
          // this.scrollToEnd();
        },
        (error) => {
          console.log(error);
        }
      );
      this.addToDoItem = null;
    },
    deleteTodo(id) {
      axiosScript.deleteToDo(
        id,
        () => {
          this.$emit("deleteEvent", id);
          this.changeList(this.btnNum);
        },
        (error) => console.log(error)
      );
    },
    clearCompleted() {
      axiosScript.deleteCompleteToDo(
        (res) => {
          console.log(res);
          this.$emit("deleteCompleteTodoEvent");
          this.changeList(this.btnNum);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    isCompleteTodo(item) {
      let isCompleted = 0;
      if (item.is_completed == 0) isCompleted = 1;
      else isCompleted = 0;
      let data = {
        id: item.id,
        isCompleted: isCompleted,
      };
      axiosScript.updateToDoState(
        data,
        () => {
          this.$emit("updateEvent", data);
          this.changeList(this.btnNum);
        },
        (error) => console.log(error)
      );
    },
    changeList(num) {
      if (num == 1) {
        this.btnNum = 1;
        this.list = this.items;
      } else if (num == 2) {
        this.btnNum = 2;
        let tmp = [];
        for (var i = 0; i < this.items.length; i++) {
          if (this.items[i].is_completed == 0) tmp.push(this.items[i]);
        }
        this.list = tmp;
      } else if (num == 3) {
        this.btnNum = 3;
        let tmp = [];
        for (var j = 0; j < this.items.length; j++) {
          if (this.items[j].is_completed == 1) tmp.push(this.items[j]);
        }
        this.list = tmp;
      }
    },
  },
  filters: {
    pluralize: (n, w) => (n === 1 ? w : w + "s"),
    capitalize: (s) => s.charAt(0).toUpperCase() + s.slice(1),
  },
};
</script>

<style>
.v-icon.v-icon.v-icon--link {
  width: 0px;
  height: 0px;
}
.pointerEffect {
  cursor: pointer;
}
</style>
No newline at end of file
