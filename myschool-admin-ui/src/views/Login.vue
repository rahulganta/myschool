<template>
  <div class="login">
    <div class="d-flex justify-content-center">
      <form name="form" class="loginform mt-5" action="#" @submit.prevent="login">

        <h4 class="d-flex justify-content-center mt-1 mb-4">{{$t('hdr.memberlogin')}}</h4>
        <div v-if="error">
          <div class="alert alert-danger">Invalid Username or Password</div>
        </div>

        <div class="form-group">
          <input type="text" class="form-control" id="username" :placeholder="$t('plch.username')"  v-model="username" autofocus="autofocus">
        </div>
        <div class="form-group">
          <input type="password" class="form-control" id="password" :placeholder="$t('plch.password')" v-model="password" autofocus="autofocus">
        </div>
        <button type="submit" class="btn mi-primarybtn w-100 mb-3">{{$t('btn.login')}}</button>
      </form>
    </div>

  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      error: false,
      errorMsg: '',
      username: '',
      password: ''
    }
  },
  methods: {
    login() {
      var vm = this;
      this.$store.dispatch('getJwtToken', {username: this.username, password: this.password})
          .then(response => {
            vm.error = false;
            this.$router.push({ name: 'Home' })
          })
          .catch(error => {
            vm.error = true;
            vm.errorMsg = error.response.data;
          })
    }
  }
}
</script>

<style lang="scss">
.loginform {
  width: 380px;
  padding: 15px;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.16), 0 4px 10px 0 rgba(0,0,0,0.12) !important;
}

input::placeholder {
  font-size: 12px;
}
</style>