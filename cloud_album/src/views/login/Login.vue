<template>
  <div class="body">
    <v-card class="loginBox" width="35vw" height="45vh" elevation="3">

      <v-card-title>
        {{ title }}
      </v-card-title>

      <v-card-text>
        <v-text-field label="账号" outlined dense v-model="account" :rules="check(this.account, 'account')"></v-text-field>
        <v-text-field label="密码" outlined dense type="password" v-model="password" :rules="check(this.password, 'password')"></v-text-field>
      </v-card-text>

      <v-card-actions>
        <v-row>
          <v-spacer></v-spacer>
          <v-btn color="#12B7F5" style="color: #FFFFFF" @click="login(account, password)">
            登录
          </v-btn>
          <v-spacer></v-spacer>
        </v-row>
      </v-card-actions>

      <div style="margin: 20px 0; text-align: center">
        <v-dialog v-model="isRegister" width="400px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn text color="#FB7299" v-on="on" v-bind="attrs">注册账号</v-btn>
          </template>
          <register></register>
        </v-dialog>
        <v-btn text color="#FB7299" @click="login('123', '123')">游客登录</v-btn>
      </div>
    </v-card>

    <v-snackbar bottom v-model="snackbar" color="#FB7299" timeout="1000">
      {{ snackBarTip }}
    </v-snackbar>
  </div>
</template>

<script>
import {network} from "@/network/request";
import Register from "@/views/login/components/Register";

  export default {
    name: "Login",
    components: {Register},
    data: () => ({
      title: '云端智能相册',
      account: '',
      password: '',
      accountIsNull: ['请输入账号'],
      passwordIsNull: ['请输入密码'],
      isRegister: false,
      snackbar: false,
      snackBarTip: ''
    }),

    methods: {
      check(input, type) {
        if (input === '') {
          return type === 'account' ? this.accountIsNull : this.passwordIsNull
        }
      },

      login(account, password) {
        if (account === '' || password === '') {
          this.check(account, 'account')
          this.check(password, 'password')
          return
        }

        if (account === '123') {
          this.snackBarTip = '即将使用游客账号登录,与其他游客共享账号'
          this.snackbar = true
        }

        let userInfo = this.$qs.stringify({
          account,
          password
        })

        network({
          url: '/login',
          method: 'post',
          data: userInfo
        }).then(res => {
          if (res !== -1) {
            this.$store.commit("setUserKey", res)
            this.$store.commit('setUserName', account)
            window.sessionStorage.setItem("account", account);
            this.$router.push('/picture')
          } else {
            this.snackBarTip = '账号或密码错误'
            this.snackbar = true
          }
        })
      }
    }
  }
</script>

<style scoped>
  .body {
    background-color: #444444;
    width: 100vw;
    height: 100vh;
  }

  .loginBox {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }
</style>