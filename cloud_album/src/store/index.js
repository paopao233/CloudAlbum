import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from "vuex-persist";

const vuexLocal = new VuexPersistence({
  storage: window.localStorage
})

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userKey: '',
    images: '',
    userName: ''
  },
  mutations: {
    setUserKey(state, userKey) {
      state.userKey = userKey
    },
    setImages(state, images) {
      state.images = images
    },
    setUserName(state, userName) {
      state.userName = userName
    }
  },
  actions: {
  },
  modules: {
  },
  plugins: [vuexLocal.plugin]
})
