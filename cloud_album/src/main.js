import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import qs from 'qs'

Vue.config.productionTip = false
Vue.prototype.$qs = qs

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')


router.beforeEach((to, from, next) => {
  document.title = to.meta.title

  const type = to.meta.type

  if (type === 'checkLogin') {
    if (window.localStorage.getItem('account')) {
      next()
    } else {
      next('/login')
    }
  } else {
    next()
  }
})