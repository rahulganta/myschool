import Vue from 'vue'
import VueRouter from 'vue-router'
import store from './../store';

import Home from '../views/Home.vue';
import Login from "../views/Login";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    meta: { requiresAuth: true }
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue'),
    meta: { requiresAuth: true }
  },
  { path: '/login',
    name: "Login",
    component: Login
  },
  {
    path: '/test',
    name: 'Test',
    component: () => import('../views/Test'),
    meta: { requiresAuth: true, authorize: ['ROLE_SUPERADMIN', 'TEST'] }
  },
  {
    path: '/error',
    name: 'Error',
    component: () => import('../views/Error'),
    meta: { }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  /*const loggedInLoc = localStorage.getItem('user');*/
  const loggedIn = store.state.user;
  const authorize_roles = to.meta.authorize
  let autherize = false;

  if (authorize_roles === undefined) {
    autherize = true;
  } else if(loggedIn) {
    authorize_roles.forEach(function (role) {
        loggedIn.roles.forEach(function (userRole) {
          if (role == userRole) {
            autherize = true;
          }
        });
    });
  }

  // try to access a restricted page + not logged in
  if (!loggedIn && to.matched.some(record => record.meta.requiresAuth)) {
    store.commit("setGlobalError", "Login required before performing this action");
    return next('/login');
  } else if(loggedIn && !autherize) {
    store.commit("setGlobalError", "You dont have permissions to access it");
    return next('/error');
  }

  next();

});

export default router
