import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    Authorization: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : ''
  },
  mutations: {
    changeLogin(state,user){
      state.Authorization = user.Authorization
      console.log('我是store里面的changeLogin方法')
      console.log(user)
      localStorage.setItem('Authorization',user.Authorization)
    }
  },
  actions: {

  }
})