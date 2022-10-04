module.exports = {
  outputDir:"../resources/static",
  indexPath:"./index.html",
  devServer:{
    proxy:{
      '/api':{
        target:"http://localhost:8888",
        charngeOrigin:true
      }
    }
  }
}
