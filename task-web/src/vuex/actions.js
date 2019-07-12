import {
  schedule
} from '../api/api'; // 引入api接口函数 schedule，请求最新的schedule列表数据

export const getScheduleList = ({ // 定义一个名字为 getScheduleList 的事件
  commit
}) => {
  return new Promise((resolve) => {
    /**
     *调用 getScheduleList 这个函数的时候
     会调用getTodoList这个ajax请求函数，
     函数返回值后，在调用store.js里面的EDITTODE方法，并且把值传给它。
     */
    schedule().then((res) => {
      commit('EDITTODE', res.data.data);
      resolve();
    });
  });
};

export const updateMenu = ({ // 定义一个名字为updateMenu的事件
  commit
}) => {
  commit('MENUOPEN'); // 调用store.js里面的MENUOPEN方法
};
