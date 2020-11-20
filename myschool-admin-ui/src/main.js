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
import "./assets/css/theme.scss";

Vue.config.productionTip = false
Vue.prototype.axios = axios;

Vue.filter('formatDateTime', function(value) {
  if (value) {
    return moment(String(value)).format('MMM Do YYYY hh:mm:ss a')
  }
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
