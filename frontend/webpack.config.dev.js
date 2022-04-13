const os =require('os')
const path = require('path')
const VueLoaderPlugin = require('vue-loader/lib/plugin')
const CssMinimizerPlugin = require('css-minimizer-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    mode: 'development',
    devServer: {
        contentBase : path.resolve(__dirname, './dist'),
        inline: true,
        hot : true,
        host : "localhost",
        port : 5000
    },

    entry: {
        app: path.resolve('./src', 'client.js'),
        // server: path.resolve('./src', 'server.js')
    },
    output: {
        filename: '[name].js',
        path: path.join(__dirname, './dist/js')
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
            filename: './css/app.css'
        }),
        new HtmlWebpackPlugin({
            template: '!!html-loader!./public/index.html'
        }),
    ],
}
