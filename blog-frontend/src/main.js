import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/common.css';

import 'normalize.css'

// import ElementUI, { TabPane } from 'element-ui';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

import Axios from 'axios'

const axiosInstance = Axios.create({
  // baseURL: '/api',
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded'
  },
  transformRequest: [function (data) {
    let ret = ''
    for (let it in data) {
      ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
    }
    return ret
  }]
})
// Add a request interceptor
// 异步请求前在header里加入token
axiosInstance.interceptors.request.use(function (config) {
  // Do something before request is sent
  // let token = localStorage.getItem('token')
  // if(token !== undefined) {
  //     config.headers.Authorization = 'Bearer '+token
  // }
  // console.log('config.url:' + config.url)
  // console.log(config.url.indexOf("/content/"))
  
  if (config.url === '/login' || config.url === '/register') {
    console.log('111')
  } else {
    config.headers.Authorization = localStorage.getItem('Authorization')
  }
  return config;
}, function (error) {
  // Do something with request error
  return Promise.reject(error);
});
// Add a response interceptor
axiosInstance.interceptors.response.use(function (response) {
  // Do something with response data
  return response.data;
}, function (error) {
  // Do something with response error
  if (error.response) {
    switch (error.response.status) {
      case 400:
        ElementUI.Message({
          message: error.response.data.msg,
          type: 'error'
        })
        break
      case 401:
        // 返回 401 清除 token 信息并跳转到登录页面
        ElementUI.Message({
          message: '请登入',
          type: 'error'
        })
        store.commit('logout')
        localStorage.removeItem('Authorization')
        router.replace({
          path: '/login',
          query: { redirect: router.currentRoute.fullPath }
        })
        break
      case 403:
        ElementUI.Message({
          message: '你无权进行此操作',
          type: 'error'
        })
        break
      case 404:
        ElementUI.Message({
          message: '无法找到请求页面',
          type: 'error'
        })
        break
      case 500:
        ElementUI.Message({
          message: '服务器出错',
          type: 'error'
        })
        break
    }
    return Promise.reject(error.response.data);
  } else {
    return Promise.reject(error);
  }
});
Vue.prototype.$axios = axiosInstance

router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  console.log(to.path)
  if (to.meta.title) {
    document.title = to.meta.title
  }
  if (to.meta.requireAuth) {
    let token = localStorage.getItem('Authorization')
    console.log('main 路由守卫')
    console.log(token)
    if (token === null || token === '' || token === undefined) {
      next('/login')
    } else {
      next()
    }
  } else {
    next()
  }
})

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
