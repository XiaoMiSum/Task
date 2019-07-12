<template>
  <!--绑定点击事件goList),并且判断当todoId 时候 item.id时,文字高亮度-->
  <div class="list-todos">
    <!-- 绑定class，当items循环中的id等于我们设置的选中todoId时候,就会加上active这个calss,这样样式就会发生变化。-->
    <a @click="getScheduleDetail(item.id)" class="list-todo list activeListClass" :class="{'active': item.id === scheduleId}" v-for="(item,index) in scheduleList" :key="index">
      <!-- 把以前的item换成todoList -->
      <span class="icon-lock" v-if="item.locked"></span>
      <span class="count-list" v-if="item.count > 0">{{item.count}}</span>
      {{item.title}}
    </a>
    <a class=" link-list-new" @click="addTodoList">
      <span class="icon-plus">
      </span>
      新增
    </a>
  </div>
</template>
<script>
import { addSchedule } from '../api/api';// 引入我们 封装好的两个接口函数。
export default {
  data() {
    return {
      scheduleId: '', // 默认选中id,
      todoNum: 0 // 新增一个状态来判断代办事项的数据
    };
  },
  watch: {
    'scheduleId'(id) {
      this.$router.push({ name: 'detail', params: { id: id } });
    }
  },
  computed: {
    scheduleList() {
      /* get schedule list  */
      const number = this.$store.getters.getScheduleList.length;
      if (this.$store.getters.getScheduleList.length < this.todoNum) {
        this.getScheduleDetail(this.$store.getters.getScheduleList[0].id);
      }
      this.todoNum = number;
      return this.$store.getters.getScheduleList; // 返回vuex getters.js 定义的 getScheduleList 数据
    }
  },
  created() { // 调用vuex actions.js 里面的 getScheduleList 函数
    this.$store.dispatch('getScheduleList').then(() => {
      this.$nextTick(() => {
        this.getScheduleDetail(this.scheduleList[0].id);
      });
    });
  },
  methods: {
    getScheduleDetail(id) { // 点击菜单时候,替换选中id
      this.scheduleId = id;
    },
    addTodoList() { // 点击新增按钮时候
      addSchedule({"title": "new schedule"}).then(data => {
        // 调用vuex actions.js 里面的 getTodo函数
        this.$store.dispatch('getScheduleList').then(() => {
          this.$nextTick(() => {
            setTimeout(() => {
              this.getScheduleDetail(this.scheduleList[this.scheduleList.length - 1].id);
            }, 100);
          });
        });
      });
    }
  }
};
</script>

<style lang="less">
@import '../common/style/menu.less';
</style>

