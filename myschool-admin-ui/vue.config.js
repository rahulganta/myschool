module.exports = {
    devServer: {
        proxy: 'http://localhost:9095' /*https://gantaedu.herokuapp.com/*/
    },
    css: {
        loaderOptions: {
            sass: {
                prependData: '@import "@/assets/css/variables.scss";'
            }
        }
    }
};