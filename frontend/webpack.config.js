const os =require('os')
const path = require('path')
const VueLoaderPlugin = require('vue-loader/lib/plugin')
const CssMinimizerPlugin = require('css-minimizer-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin')

module.exports = {
  mode: 'production',
  entry: {
    app: path.resolve('./src', 'client.js'),
    server: path.resolve('./src', 'server.js')
  },
  output: {
    filename: '[name].js',
    path: path.join(__dirname, '../backend/src/main/resources/static/js')
  },
  resolve: {
    alias: {
      vue: 'vue/dist/vue.js'
    },
    extensions: [".ts", ".tsx", ".js", ".json"],
    fallback : {
      fs: false,
      child_process: false,
      net: false,
      tls: false,
      dns: false,
    }
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        loader: 'babel-loader',
        options:  {
          presets: [
            ['@babel/env',
              {
                "targets": {
                  "ie": 11
                },
              }
            ]
          ]
        }
      },
      {
        test: /\.vue$/,
        loader: 'vue-loader',

      },
      {
        test: /\.css$/,
        use: [
          MiniCssExtractPlugin.loader,
          'css-loader',
        ],
      },
    ]
  },
  optimization: {
    minimize : true,
    minimizer: [
      new CssMinimizerPlugin(),'...' // '...' 기본값
    ],
  },
  plugins: [
    new VueLoaderPlugin(),
    new MiniCssExtractPlugin({
      filename: '../css/app.css'
    })
  ],
}
