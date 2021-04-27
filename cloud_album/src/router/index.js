import Vue from 'vue'
import VueRouter from 'vue-router'
import PicturePage from "@/views/picturePage/PicturePage";
import Moment from "@/views/moment/Moment";
import Login from "@/views/login/Login";
import Register from "@/views/login/components/Register";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login,
    meta: {
      title: '云端智能相册',
      type: ''
    }
  },
  {
    path: '/picture/:classifyKey',
    component: PicturePage,
    meta: {
      title: '照片',
      type: 'checkLogin'
    }
  },
  {
    path: '/picture',
    component: PicturePage,
    meta: {
      title: '照片',
      type: 'checkLogin'
    }
  },
  {
    path: '/moment',
    component: Moment,
    meta: {
      title: '精彩时刻',
      type: 'checkLogin'
    }
  }
]

const router = new VueRouter({
  routes,
  mode: 'history'
})

export default router
