<template>
  <div>
    <ul class="list">
      <li v-for="(item, index) in data" :key="item.todo">
        <span class="glyphicon glyphicon-asterisk"></span>
        {{item.todo}}
        <span class="todoBtn label label-primary"
          v-if="selectedTab === 'todo'"
          v-on:click="finishBtnClick(index)">
          <span class="glyphicon glyphicon-ok"></span>
          finish
        </span>
        <span class="todoBtn label label-danger"
          v-if="selectedTab === 'finish'"
          v-on:click="resetBtnClick(index)">reset</span>
      </li>
    </ul>
  </div>
 
 
</template>
 
<script>
import "@/assets/css/style.css"
export default {
  props: ['data', 'selectedTab'],
  methods: {
    finishBtnClick(item) {
      this.$emit('@finish', item)
    },
    resetBtnClick(item) {
      this.$emit('@reset', item)
    }
  }
}
</script>

