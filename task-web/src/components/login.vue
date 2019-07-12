<template>
  <div>
    <form  >
      <div class="form input-symbol">
        <input type="text" v-model="userName" placeholder='用户名'/>
        <span class="icon-email"></span>
      </div>
      <div class="form input-symbol">
        <input type="password" v-model="password" placeholder='密码'/>
        <span class="icon-lock"></span>
      </div>
    </form>
      <a class="button button-primary button-rounded button-small login"  @click="doLogin">
        登录
      </a>
    <a class="button button-primary button-rounded button-small login"  @click="doSignUp">
      注册
    </a>
  </div>
</template>

<script type="application/ecmascript">
  import {ajaxLogin, register} from '../api/api';
  export default {
    name: 'login',
    data () {
      return {
        userName: '',
        password: ''
      }
    },
    methods: {
      doLogin(){
        console.log(this.md5(this.userName + this.password))
        ajaxLogin({userName: this.userName, password: this.password, sign: this.md5(this.userName + this.password)}).then(res => {
          if(res.status === 200){
            var token = res.data.token;
            this.$store.commit('LOGIN', token)
            this.$router.push({
              path: "/"
            })
          }
        })
      },
      doSignUp(){
        register({userName: this.userName, password: this.password}).then(res => {
          if(res.data.status === 200){
            this.doLogin();
          }
        })
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang='less'>
  @import '../common/style/button.less';
</style>
