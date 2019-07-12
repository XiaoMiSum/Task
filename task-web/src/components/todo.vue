<template>
  <div class="page lists-show" v-show="!this.isDelete">
    <!-- 头部模块 -->
    <nav>
      <!-- 当用户浏览车窗口尺寸小于40em时候，显示手机端的菜单图标 -->
      <div class="form list-edit-form" v-show="isUpdate">
        <!-- 当用户点击标题进入修改状态，就显示当前内容可以修改 -->
        <input type="text" v-model="scheduleInfo.title" @keyup.enter="updateTitle" :disabled="isLocked">
        <div class="nav-group right">
          <a class="nav-item" @click="isUpdate = false">
            <span class="icon-close">
            </span>
          </a>
        </div>
      </div>
      <div class="nav-group" @click="$store.dispatch('updateMenu')" v-show="!isUpdate">
        <!-- 在菜单的图标下面添加updateMenu时间，他可以直接调用vuex actions.js里面的updateMenu方法 -->
        <a class="nav-item">
          <span class="icon-list-unordered">
          </span>
        </a>
      </div>
      <!-- 显示标题和数字模块 -->
      <h1 class="title-page" v-show="!isUpdate" @click="isUpdate = true">
        <span class="title-wrapper">{{scheduleInfo.title}}</span>
        <!-- title:标题 绑定标题 -->
        <span class="count-list">{{scheduleInfo.count || 0}}</span>
        <!-- count:数量 绑定代办单项数量-->
      </h1>
      <!-- 右边显示删除图标和锁定图标的模块 -->
      <div class="nav-group right" v-show="!isUpdate">
        <div class="options-web">
          <a class=" nav-item" @click="onLock">
            <!-- icon-lock锁定的图标
                                                    icon-unlock：非锁定的图标
                                                    -->
            <span class="icon-lock" v-if="isLocked"></span>
            <span class="icon-unlock" v-else>
            </span>
          </a>
          <a class="nav-item">
            <span class="icon-trash" @click="onDelete">
            </span>
          </a>
        </div>
      </div>
      <!-- 用户新增代办事项的input模块 -->
      <div class=" form todo-new input-symbol">
        <!-- 绑定disabled值，当todo.locked为绑定的时候，禁止input输入,双向绑定text,和监听input的回车事件@keyup.enter -->
        <input type="text" v-model="text" placeholder='请输入' @keyup.enter="onAdd" :disabled="isLocked" />
        <span class="icon-add"></span>
      </div>
    </nav>
    <!-- 列表主体模块 -->
    <div class="content-scrollable list-items">
      <div v-for="(item,index) in items">
        <item :item="item" :index="index" :id="item.id" :init="init" :locked="isLocked"></item>
      </div>
    </div>
  </div>
</template>
<script>

import item from './item';
import { addDetail, detail, editSchedule } from '../api/api';
export default {
  data() {
    return {
      id: 0,
      scheduleInfo: {},
      items: [  // 代办单项列表
      ],
      text: '', // 用户输入单项项绑定的输入
      isDelete: false, // 是否删除
      isLocked: false, // 是否锁定
      isUpdate: false // 新增修改状态
    };
  },
  components: {
    item
  },
  watch: {
    '$route.params.id'() {
      // 监听$route.params.id的变化，如果这个id即代表用户点击了其他的待办项需要重新请求数据。
      this.init();
    }
  },
  created() {
    // created生命周期，在实例已经创建完成，页面还没渲染时调用init方法。
    this.init();
  },
  methods: {
    init() {
      this.id = this.$route.params.id;
      detail({ "scheduleId": this.id }).then(res => {
        this.items = res.data.data.detail;
        this.scheduleInfo = res.data.data;
        this.isLocked = this.scheduleInfo.locked === 1;
        this.isDelete = this.scheduleInfo.isDelete === 1;
      });
    },
    onAdd() {
      addDetail({ "scheduleId": this.id, title: this.text, count: 1 }).then(res => {
        this.text = '';
        this.init();
        this.$store.dispatch('getScheduleList');
      });
    },
    updateTodo() {
      let _this = this;
      editSchedule({
        id: _this.id, "title": _this.scheduleInfo.title, "locked": _this.scheduleInfo.locked , "isDelete": _this.scheduleInfo.isDelete
      }).then(data => {
        _this.$store.dispatch('getScheduleList');
      });
    },
    updateTitle() {
      this.updateTodo();
      this.isUpdate = false;
    },
    onDelete() {
      this.isDelete = true;
      this.scheduleInfo.isDelete = 1;
      this.updateTodo();
    },
    onLock() {
      this.isLocked = !this.isLocked;
      this.scheduleInfo.locked = 0;
      if (this.isLocked){
        this.scheduleInfo.locked = 1;
      }
      this.updateTodo();
    }
  }
};
</script>

<style lang = "less">
@import '../common/style/nav.less';
@import '../common/style/form.less';
@import '../common/style/todo.less';
</style>
