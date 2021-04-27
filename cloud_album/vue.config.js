module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    proxy: {
      '/api': {
        target: 'http://49.234.149.121:8080/',
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      },
      '/localhost': {
        target: 'http://localhost:8081/',
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          '^/localhost': ''
        }
      }
    }
  }
}
