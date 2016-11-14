var packageJSON = require('./package.json');
var path = require('path');
var webpack = require('webpack');

const PATHS = {
  build: path.join(__dirname, 'resources', 'public')
};

module.exports = {
  entry: './resources/src/index.js',
  contentBase: PATHS.build,
  output: {
    path: PATHS.build,
    filename: path.join(PATHS.build + 'app-bundle.js')
  },
  devServer: {
    proxy: {
      '/api': {
        target: 'http://0.0.0.0:4567',
        secure: false,
        pathRewrite: {'^/api' : ''}
      }
    }
  }
};
