export default {
    data: [
      {todo: '할 일 0', state: true},
      {todo: '할 일 1', state: true},
      {todo: '할 일 2', state: false},
      {todo: '할 일 3', state: true},
      {todo: '할 일 4', state: false}
    ],
   
    list(tab) {
      return new Promise(res => {
        if(tab === 'todo') res(this.data.filter(item => item.state === true))
        if(tab === 'finish') res(this.data.filter(item => item.state === false))
      })
    },
   
    add(todo = '') {
      todo = todo.trim()
      if (!todo) return
   
      const state = true
      this.data.push({todo, state})
    },
   
    finish(index) {
      this.data.filter(item => item.state === true)[index].state = false
    },
   
    reset(index) {
      this.data.filter(item => item.state === false)[index].state = true
    },
   
    remove(todo) {
      this.data = this.data.filter(item => item.todo !== todo)
    }
  }
  
  