<template>
  <v-card dark tile width="300px">
    <v-card-title>照片</v-card-title>
    <v-card-text>
      <v-row>
        <v-card-subtitle>分类</v-card-subtitle>
        <span style="color: #FFFFFF;padding: 16px;">{{ imageInfo.classify }}</span>
      </v-row>
      <v-row>
        <v-card-subtitle>文件名</v-card-subtitle>
        <span style="margin: 16px; color: #FFFFFF">{{ imageInfo.name }}</span>
      </v-row>
      <v-row>
        <v-card-subtitle>时间</v-card-subtitle>
        <span style="margin: 16px; color: #FFFFFF">{{ date }}</span>
      </v-row>
      <v-row>
        <v-card-subtitle>大小</v-card-subtitle>
        <span style="margin: 16px; color: #FFFFFF">{{ sizes }}</span>
      </v-row>
    </v-card-text>
  </v-card>
</template>

<script>
  import * as dayjs from "dayjs";

  export default {
    name: "ImageInfo",
    props: {
      imageInfo: '',
      size: ''
    },
    computed: {
      date() {
        return dayjs(this.imageInfo.date).format('YYYY-MM-DD HH:mm:ss')
      },
      sizes() {
        return this.size + ',' + ((this.imageInfo.size/1024)/1024).toFixed(2) + 'M'
      }
    }
  }
</script>

<style scoped>

</style>