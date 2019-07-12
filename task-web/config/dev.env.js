var merge = require('webpack-merge')
var prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  //BASE_API: '"http://localhost:80/api"',
  //APP_ORIGIN: '"http://localhost:80/api"'
  BASE_API: '"http://migoo.xyz/api"',
  APP_ORIGIN: '"http://migoo.xyz/api"'
})
