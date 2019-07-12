<template>
  <transition name="slide-fade">
    <div class="list-item  editingClass editing " :class="{checked: item.status === 0 }" v-show="item.isDelete === 0">
      <label class="checkbox">
        <input type="checkbox" v-model="item.checked" name="checked" @change="changeStatus" v-if="!locked">
        <span class="checkbox-custom"></span>
      </label>
      <input type="text" v-model="item.title" placeholder='写点什么。。。'  :disabled="locked || item.status === 0" @keyup.enter="onChange">
      <a class="delete-item" v-if="item.status === 0 && !locked" @click="item.isDelete = 1;deleteItem()">
        <span class="icon-trash"></span>
      </a>
    </div>
  </transition>
</template>
<script>
// item 是todo的子组件,他接受一个对象item,来进行处理
import { editScheduleDetail } from '../api/api';

export default {
  data(){
    return{
      scheduleId: 0,
      count: -1
    }
  },
  props: {
    item: {
      type: Object
    },
    'index': {

    },
    'id': {

    },
    'init': {

    },
    'locked': {
    }
  },
  methods: {
    // 用户无论删除,修改，锁定都可以利用这个方法。
    onChange() {
      editScheduleDetail({
        id: this.id, "title": this.item.title
      }).then(data => {
        this.init();
        this.$store.dispatch('getTodo');
      });
    },
    changeStatus() {
      if (this.item.status === 0) {
        this.item.status = 1;
        this.count = 1;
      }else {
        this.item.status = 0;
        this.count = -1;
      }
      this.item.scheduleId = this.$route.params.id
      console.log( "scheduleId" + this.item.scheduleId)
      editScheduleDetail({
        id: this.id, status: this.item.status, count: this.count, "scheduleId": this.item.scheduleId
      }).then(data => {
        this.init();
        this.$store.dispatch('getScheduleList');
      });
    },
    deleteItem() {
      editScheduleDetail({
        id: this.id, isDelete: this.item.isDelete
      }).then(data => {
        this.init();
      this.$store.dispatch('getScheduleList');
    });
    }
  }
};
</script>
<style lang="less">
@import '../common/style/list-items.less';
.slide-fade-enter-active {
  transition: all .3s ease;
}
.slide-fade-leave-active {
  transition: all .3s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slide-fade-enter, .slide-fade-leave-active {
  transform: translateX(10px);
  opacity: 0;
}
</style>
