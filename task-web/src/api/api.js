import axios from 'axios';
import store from '@/vuex/store';


axios.defaults.timeout = 10000
axios.defaults.baseURL = process.env.BASE_API
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8'


axios.interceptors.request.use(config => {
  var token = store.state.token;
  config.headers['token'] = token;
  return config
},function(error){
  return Promise.reject(error)
})
/*
// http response 拦截器
axios.interceptors.response.use(
  response => {
  return response
},
  error => {
  if (error.response) {
    switch (error.response.status) {
      case 401:
        // 401 清除token信息并跳转到登录页面
        store.commit('LOGOUT')
        // 只有在当前路由不是登录页面才跳转
        router.currentRoute.path !== 'login'
    }
  }
  return Promise.reject(error.response.data)
});
*/

axios.interceptors.response.use(function (response) {
  // token 已过期，重定向到登录页面
  if (response.data.code == 401){
    localStorage.clear()
    router.replace({
      path: '/login'
      //query: {redirect: router.currentRoute.fullPath}
    })
  }
  return response
}, function (error) {
  // Do something with response error
  return Promise.reject(error)
})


export const ajaxLogin = params => {
  return axios.post(`/login`, params).then(res => res.data);
};

export const register = params => {
  return axios.post(`/user/register`, params).then(res => res.data);
};

export const getTodo = params => {
  return axios.get(`/task/schedule`, {
    params: params
  });
};

export const addDetail = params => {
  return axios.post(`/task/schedule/detail/add`, params).then(res => res.data);
};

export const editSchedule = params => {
  return axios.put(`/task/schedule/edit`, params).then(res => res.data);
};
export const editScheduleDetail = params => {
  return axios.put(`/task/schedule/detail/edit`, params).then(res => res.data);
};

export const addSchedule = params => {
  return axios.post(`/task/schedule/add`, params).then(res => res.data);
};

export function schedule() {
  return axios.get("/task/schedule");
}

export function detail(params) {
  return axios.get("/task/schedule/detail", {params: params});
}

// export const editUser = params => { return axios.get(`${base}/user/edit`, { params: params }); };

// export const addUser = params => { return axios.get(`${base}/user/add`, { params: params }); };

export default axios;

