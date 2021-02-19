import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from "axios";
import moment from "moment";

import i18n from './../public/static/locale/i18n.config';
import {loadLanguageAsync} from './../public/static/locale/i18n.config';

//Boostrap
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

//Fontawesome
import "@fortawesome/fontawesome-free/css/all.css"

//Custom CSS
import "./assets/css/custome.scss";
import "./assets/css/theme.scss";

import Constants from "@/utils/const";
Vue.use(Constants);

import ToastsPlugin from "@/utils/toastsPlugin";
Vue.use(ToastsPlugin);

Vue.config.productionTip = false
Vue.prototype.axios = axios;

Vue.filter('formatDateTime', function(value) {
  if (value) {
    return moment(String(value)).format('MMM Do YYYY hh:mm:ss a')
  }
});

Vue.filter('formatBytes', function (bytes, decimals = 2) {
  const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
  const k = 1024;
  const dm = decimals < 0 ? 0 : decimals;
  if (bytes === 0) return '0 Bytes';
  const i = Math.floor(Math.log(bytes) / Math.log(k));
  return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
})

new Vue({
  router,
  store,
  axios,
  i18n,
  data: {},
  mounted: function () {
    loadLanguageAsync(this.$i18n.locale);
  },
  render: h => h(App)
}).$mount('#app')
