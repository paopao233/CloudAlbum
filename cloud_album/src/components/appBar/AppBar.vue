<template>
  <v-app-bar app color="#FFFFFF" elevation="1" clipped-left>
    <v-row>
      <v-col cols="2">{{ title }}</v-col>

      <v-col>
        <tabs-navigation></tabs-navigation>
      </v-col>

      <v-spacer></v-spacer>

      <v-col cols="1">
        <v-menu open-on-hover bottom>
          <template v-slot:activator="{ on, attrs }">
            <div v-bind="attrs" v-on="on">
              {{ $store.state.userName }}
            </div>
          </template>

          <v-card>
            <v-card-text>
              <v-list>
                <v-list-item>
                  <v-btn text color="#FB7299" width="100%" @click="logout">退出登录</v-btn>
                </v-list-item>
              </v-list>
            </v-card-text>
          </v-card>
        </v-menu>
      </v-col>
    </v-row>
  </v-app-bar>
</template>

<script>
  import TabsNavigation from "@/components/appBar/components/tabsNavigation/TabsNavigation";
  export default {
    name: "AppBar",
    components: {TabsNavigation},
    data: () => ({
      title: '云端智能相册'
    }),
    methods: {
      logout() {
        this.$store.commit("setUserKey", '')
        this.$store.commit('setUserName', '')
        this.$store.commit('setImages', '')
        window.sessionStorage.removeItem('account')
        this.$router.push('/login')
      }
    }
  }
</script>

<style scoped>

</style>