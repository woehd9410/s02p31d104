<template>
  <form v-on:enter.prevent="addTodo">
    <input type="text" v-model="inputValue" />
    <button class="btn-reset" v-on:click.prevent="addTodo"></button>
    <!-- v-on:click => click 이벤트 리슨 (.prevent 속성을 추가하면 이벤트 전파를 중단함) -->
  </form>
</template>
 
<script>
import "@/assets/css/style.css"
export default {
  props: ['value'], //부모(App.vue)로부터 받아옴
  data() {
    return {
      inputValue: this.value
    }
  },
  methods: {
    addTodo() {
      //$emit => 부모(App.vue)에게 @submit이라는 이름으로 이벤트 전달
      //this.inputValue 인자로 같이 전달
      this.$emit('@submit', this.inputValue)
 
      //데이터 추가 후 form을 비우기 위해 inputValue값을 비워줌
      this.inputValue = ''
    }
  }
}
</script>

