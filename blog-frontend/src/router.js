import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: () => import('./views/Index.vue'),
      meta: {
        title: '首页'
      }
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('./views/Login.vue'),
      meta: {
        title: '登入'
      }
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('./views/Register.vue'),
      meta: {
        title: '注册'
      }
    },
    {
      path: '/contentDetail',
      name: 'contentDetail',
      component: () => import('./views/ContentDetail.vue'),
      meta: {
        title: '文章'
      }
    },
    {
      path: '/home',
      name: 'home',
      component: () => import('./views/Home.vue'),
      meta: {
        title: '博客管理',
        requireAuth:true
      }
    },
    {
      path: '/write',
      name: 'write',
      component: () => import('./views/user/Write.vue'),
      meta: {
        title: '写博客',
        requireAuth:true
      }
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('./views/user/Profile.vue'),
      meta: {
        title: '修改密码',
        requireAuth:true
      }
    },
  ]
})
