const path = require('path');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');


var project = {
        build: {
            directory: 'target/classes',
            outputDirectory: './target/classes/public',
            sourceDirectory: './src/main/resources/public'
        }
};



module.exports = {
    mode: 'production',
    entry: project.build.sourceDirectory + '/js/widget.js',
    output: {
        filename: 'widget.js',
        path: path.resolve(__dirname, project.build.outputDirectory + '/js')
    },
    module: {
        rules: [
            {
                test: /\.(html)$/,
                use: [
                    {
                        loader: 'html-loader',
                        options: {
                            minimize: true
                        }
                    }
                ]
            },
            {
                test: /\.less$/,
                use: [ 'style-loader', 'css-loader', 'less-loader' ]
            },
            {
                test: /\.(png|svg|jpg|gif)$/,
                use: [ 'url-loader' ]
            }
        ]
    },
    optimization: {
        minimizer: [
            new UglifyJsPlugin({
                extractComments: true
            })
        ]
    }
};
