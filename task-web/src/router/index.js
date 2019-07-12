import Vue from 'vue';
import Router from 'vue-router';
import layouts from '@/components/layouts';
import todo from '@/components/todo';
import login from '@/components/login';
import store from '@/vuex/store';

Vue.use(Router);


const routes = [
 {
    path: '/',
    name: 'Task',
    component: layouts,
    meta: {
      requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录的
    },
    children: [
      {
        path: '/schedule/:id',
        name: 'detail',
        component: todo
      }
    ]
  },
  {
    path: '/login', name: 'login', component: login
  }
];

// 页面刷新时，重新赋值token
if (window.localStorage.getItem('token')) {
  store.commit('LOGIN', window.localStorage.getItem('token'))
}

const router = new Router({
  routes
});


router.beforeEach((to, from, next) => {
  if (to.matched.some(res => res.meta.requireAuth)) {
  if (store.state.token) {
    next();
  }
  else {
    next({
      path: '/login'
    })
  }
}
else {
  next();
}
});


export default router;
