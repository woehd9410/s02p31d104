<template>
  <ul class="tabs">
    <li v-for="tab in tabs" :key="tab" v-bind:class="{active: tab === selectedTab}"
      v-on:click="onClickTabs(tab)">
      <!-- v-bind:class => 선택한 탭에 active class를 지정 -->
      {{tab}}
    </li>
  </ul>
</template>
 
<script>
import "@/assets/css/style.css"
export default {
  props: ['tabs', 'selectedTab'], //부모(App.vue)로부터 받아옴 => tabs(todo, finish), selectedTab : 현재 선택한 탭
  methods: {
    onClickTabs(tab) {
      //$emit => 부모(App.vue)에게 @change 이름으로 이벤트 전달
      //tab(선택한 탭) 인자로 같이 전달
      this.$emit('@change', tab)
    }
  }
}
</script>

