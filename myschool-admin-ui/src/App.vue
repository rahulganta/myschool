<template>
  <div id="app" :class="theme">
    <div id="appheader" class="sticky-top mi-card" v-if="currentLoggedInUser" style="min-height: 0px">
      <nav class="navbar navbar-expand-lg navbar-light">
        <a class="navbar-brand" href="/">{{$t('appname')}}<!--<img src="./assets/img/logo.png" style="width:96px;height:40px;" alt="myschool logo"  class="img-fluid"/>--></a>
        <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
          <span class="sr-only">Toggle navigation</span>
          <i id="menuIcon" class="fas fa-bars"></i>
          <i id="closeIcon" class="fas fa-times"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
          <ul class="navbar-nav">
            <li class="nav-item active">
              <router-link class="nav-link" data-toggle="collapse" data-target=".navbar-collapse.show" to="/"><i class="fas fa-home fa-2x d-inline d-sm-none mr-2" /> {{$t('nav.home')}}<span class="sr-only">(current)</span></router-link>
            </li>
            <!--<li class="nav-item">
              <router-link class="nav-link" data-toggle="collapse" data-target=".navbar-collapse.show" to="/about"><i class="fas fa-address-card fa-2x d-inline d-sm-none" /> {{$t('nav.aboutus')}}</router-link>
            </li>-->
            <li class="nav-item">
              <router-link class="nav-link" data-toggle="collapse" data-target=".navbar-collapse.show" to="/mycourses"><i class="fas fa-address-card fa-2x d-inline d-sm-none mr-2" />{{$t('nav.mycourses')}}</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" data-toggle="collapse" data-target=".navbar-collapse.show" to="/messages"><i class="fas fa-address-card fa-2x d-inline d-sm-none mr-2" />{{$t('nav.messages')}}</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" data-toggle="collapse" data-target=".navbar-collapse.show" to="/staff"><i class="fas fa-address-card fa-2x d-inline d-sm-none mr-2" />{{$t('nav.staff')}}</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" data-toggle="collapse" data-target=".navbar-collapse.show" to="/students"><i class="fas fa-address-card fa-2x d-inline d-sm-none mr-2" />{{$t('nav.students')}}</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" data-toggle="collapse" data-target=".navbar-collapse.show" to="/attendance"><i class="fas fa-address-card fa-2x d-inline d-sm-none mr-2" />{{$t('nav.attendance')}}</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" data-toggle="collapse" data-target=".navbar-collapse.show" to="/reports"><i class="fas fa-address-card fa-2x d-inline d-sm-none mr-2" />{{$t('nav.reports')}}</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" data-toggle="collapse" data-target=".navbar-collapse.show" to="/schedule"><i class="fas fa-address-card fa-2x d-inline d-sm-none mr-2" />{{$t('nav.schedule')}}</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" data-toggle="collapse" data-target=".navbar-collapse.show" to="/test"><i class="fas fa-futbol fa-2x d-inline d-sm-none mr-2" />{{$t('nav.test')}}</router-link>
            </li>
          </ul>

          <!--Right Links -->
          <!--<form class="right-nav ml-auto">
            <select class="form-control" id="theme" v-model="theme">
              <option value="defaulttheme">{{$t('option.defaulttheme')}}</option>
              <option value="darktheme">{{$t('option.darktheme')}}</option>
            </select>
          </form>-->

          <ul class="right-nav ml-auto navbar-nav">
            <li class="nav-item" v-if="!currentLoggedInUser">
              <router-link class="nav-link" data-toggle="collapse" data-target=".navbar-collapse.show" to="/login"><i class="fas fa-sign-in-alt d-inline d-sm-none mr-2"/> {{$t('btn.login')}}</router-link>
            </li>
            <li class="nav-item" v-else>
              <div class="userprofilemenu dropdown">
                <a class="nav-link btn dropdown-toggle d-flex align-items-center" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <i class="fas fa-user-circle fa-2x"></i><span class="d-inline d-sm-none ml-2" style="font-weight: 700;">{{$t('btn.userprofile')}}</span>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">
                  <!--Display Logedin User Name-->
                  <div class="dropdown-item"><!--{{ $t('label.username') }}:--> <strong>{{currentLoggedInUser.userName}}</strong></div>
                  <div class="dropdown-divider"></div>
                  <form class="mt-1">
                    <select class="form-control" id="theme1" v-model="theme">
                      <option value="defaulttheme">{{$t('option.defaulttheme')}}</option>
                      <option value="darktheme">{{$t('option.darktheme')}}</option>
                    </select>
                  </form>

                  <form class="mt-1">
                    <select class="form-control" id="locale" v-model="locale">
                      <option value="en">{{$t('localeoption.english')}}</option>
                      <option value="te-IN">{{$t('localeoption.telugu')}}</option>
                      <option value="hi-IN">{{$t('localeoption.hindi')}}</option>
                      <option value="es">{{$t('localeoption.spanish')}}</option>
                    </select>
                  </form>

                  <router-link class="dropdown-item" data-toggle="collapse" data-target=".navbar-collapse.show" to="/settings"><i class="fas fa-cog" /> {{$t('btn.settings')}}</router-link>
                  <a class="dropdown-item" data-toggle="collapse" data-target=".navbar-collapse.show" @click="logOut"><i class="fas fa-sign-out-alt"/> {{$t('btn.signout')}}</a>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </nav>
    </div>

    <div id="contentbody" class="container-fluid pt-2">
      <router-view />
    </div>

    <footer class="footer">
      <div class="row footercontent pt-2">
        <div class="col-sm-2"></div>
        <div class="col-sm-8 text-center"> <span class="text-uppercase mi-copyright">{{$t('copyright')}} <!--Copyright © 2020 myschool. All Rights Reserved.--></span></div>
        <!--<div class="col-sm-2">
          <select class="form-control" id="locale" v-model="locale">
            <option value="en">{{$t('localeoption.english')}}</option>
            <option value="te-IN">{{$t('localeoption.telugu')}}</option>
            <option value="es">{{$t('localeoption.spanish')}}</option>
          </select>
        </div>-->
      </div>
    </footer>
  </div>
</template>
<script>

import {loadLanguageAsync} from './../public/static/locale/i18n.config';

export default {
  name: "app",
  components: {},
  data: function () {
    return {
      theme:  "defaulttheme",
      locale: 'en'
    }
  },
  created() {
    var vm = this;
    var currentTheme = localStorage.getItem('theme');

    if(currentTheme === null) {
      this.theme = "defaulttheme";
    } else {
      this.theme = currentTheme;
    }

    var currentLocale = localStorage.getItem('locale');
    if(currentLocale === null) {
      this.locale = 'en';
    } else {
      this.locale = currentLocale;
      //Setting locale for moment JS
      this.moment.locale(currentLocale);
    }

    this.axios.interceptors.response.use(function (response) {
      return response;
    }, function (error) {
      if(error.response.status == 401 && (!error.config.url.includes('/login'))) {
        vm.$store.dispatch('logout');
        vm.$router.push('/login');
      } else {
        return Promise.reject(error);
      }
    });

  },
  computed: {
    currentLoggedInUser() {
      var user = this.$store.state.user;
      return user;
    },
  },
  watch: {
    theme(theme, oldTheme) {
      localStorage.setItem("theme", theme);
    },
    locale(locale, oldLocale) {
      loadLanguageAsync(locale);
      localStorage.setItem("locale", locale);
      //Setting locale for moment JS
      this.moment.locale(locale);
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('logout');
      this.$router.push('/login');
    }
  },

};
</script>
<style lang="scss">
#appheader.sticky-top {
  top: -1px;
  background-color: #FFF;
}

#appheader .navbar-brand {
  color: $textColor;
}

#appheader .navbar-toggler {
  color: $textColor;
  border: none;
}

/* hide close when burger shown */
.navbar-toggler.collapsed #closeIcon {
  display: none;
}

.navbar-toggler:not(.collapsed) #menuIcon {
  display: none;
}

#appheader .nav-item .nav-link {
  padding: 0.5rem 1.5rem;
  color: $textColor;
  font-weight: 700;
  font-style: normal;
  margin-right: 0.5rem;
}

#appheader .nav-item .router-link-exact-active {
  border-bottom: $textColor solid 1px;
}

/* dont display default dropdown arrow beside user icon */
.userprofilemenu .dropdown-toggle::after {
  display: none;
}

</style>
