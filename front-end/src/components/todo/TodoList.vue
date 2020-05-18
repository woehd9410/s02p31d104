<template>
  <div>
      <header>
          <h2>Todo</h2>
      </header>

      <div calss="container">
          <add-form v-bind:value="query" v-on:@submit="onInputTodo"></add-form>

          <tab v-bind:tabs="tabs" v-bind:selected-tab="selectedTab" v-on:@change="onClickTab"></tab>

      </div>

      <div>
          <list v-bind:selected-tab="selectedTab" v-bind:data="todoList"
           v-on:@finish="onClickFinish"
           v-on:@reset="onClickReset"></list>
      </div>
  </div>
</template>

<script>
//FormComponent 불러옴
import FormComponent from '@/components/todo/FormComponent.vue'
//TabComponent 불러옴
import TabComponent from '@/components/todo/TabComponent.vue'
//ListComponent 불러옴
import ListComponent from '@/components/todo/ListComponent.vue'

import TodoModel from '@/router/todo/TodoModel.js'
import "@/assets/css/style.css"
export default {
 data () {
    return {
      query: '',
      tabs: ['todo', 'finish'],
      selectedTab: '',
      todoList: []
    }
  },
  created() { //vue 인스턴스가 생성된 후에 실행됨
    this.selectedTab = this.tabs[0] //todo 탭 선택
    this.search() //todo list 출력
  },
  components: { //사용할 컴포넌트 등록
    'add-form': FormComponent,
    'tab': TabComponent,
    'list': ListComponent
  },
  methods: {
    search() { //list 검색
      TodoModel.list(this.selectedTab).then(data => {
        this.todoList = data
      })
    },
    onClickTab(tab) { //tab 선택
      this.selectedTab = tab
      this.search()
    },
    onClickFinish(item) { //todo 완료
      TodoModel.finish(item)
      this.search()
    },
    onClickReset(item) { //완료된 todo 리셋
      TodoModel.reset(item)
      this.search()
    },
    onInputTodo(query) { //todo 입력
      TodoModel.add(query)
      this.selectedTab = this.tabs[0]
      this.search()
    }
  }
}
</script>

<style>

</style>