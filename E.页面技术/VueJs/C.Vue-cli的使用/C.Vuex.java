1.vuex 介绍:
2.使用步骤:






---------------------------------------------------------------------------------------------------------------

1.vuex 介绍:
   Vuex 是1个专为 Vue.js 应用程序开发的状态管理模式。它采用集中式存储管理应用的所有组件的状态，
并以相应的规则保证状态以1种可预测的方式发生变化。

  为 Vue.js 开发的状态管理模式
  组件状态集中管理
  组件状态改变遵循统1的规则

有点类似维护全局属性，像　java 的配置文件。



---------------------------------------------------

2.使用步骤:


2.1 编辑 store.js

import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    count: 0
  },
  mutations: {
    increase () {
      this.state.count++
    }
  },
  actions: {

  }
})

----------------------------
2.2:编辑页面

<template>
  <div>
    <div>Hello Info</div>
    <button type="button" @click="add">添加</button>
    <p>{{msg}}</p>
  </div>
</template>

<script>
import store from '@/store.js'
export default {
  name: 'Info',
  store,
  data () {
    return {
      msg: store.state.count
    }
  },
  methods: {
    add () {
      store.commit('increase')
    }
  }
}
</script>

<style scoped>

</style>
