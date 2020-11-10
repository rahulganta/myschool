import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: JSON.parse(localStorage.getItem('user')) || null,
    globalError: '',
    school: '',
    colors: ['#F9AA33','#2F3F3F', '#37966F','#FF0266','#AD634F','#5D1049', '#7D9FB7', '#957199', '#94A7A7', '#957199']
  },
  getters: {
    loggedIn(state) {
      return state.user !== null
    },
  },
  mutations: {
    setGlobalError (state, error) {
      state.globalError = error
    },
    getJwtToken(state, user) {
      state.user = user;
    },
    loginFailure(state) {
      state.user = null;
    },
    logout(state) {
      state.user = null;
    },
    saveSchool(state, payload) {
      state.school = payload.school;
    }
  },
  actions: {
    getJwtToken(context, credentials) {
      return new Promise((resolve, reject) => {

        axios.post('/api/myschool/login', credentials)
            .then(response => {
              let user = response.data;
              if (response.data.token) {
                localStorage.setItem('user', JSON.stringify(user));
              }
              context.commit('getJwtToken', user);
              resolve(response);
            })
            .catch(error => {
              console.log(error);
              reject(error);
            })
      })
    },
    logout(context) {
      axios.defaults.headers.common['Authorization'] = 'Bearer ' + context.state.user.token;

      if (context.getters.loggedIn) {
        localStorage.removeItem('user');
        context.commit('logout');
        return new Promise((resolve, reject) => {
          axios.post('/api/myschool/logout').then( response => {
            resolve(response);
          })
              .catch(error => {
                reject(error);
              })
        })
      }
    }
  },
  modules: {

  }
})
