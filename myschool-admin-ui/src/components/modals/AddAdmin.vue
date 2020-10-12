<template>
  <form>
    <modal class="mi-custom-modal addadmin-modal">
      <div slot="header" class="mi-custom-header">
        <div class="row no-gutters">
          <h3 class="col-10 title">Add Admin</h3>
          <div class="col-2 text-right">
            <a class="btn mi-linkbtn" @click="close"><i class="fas fa-times"/></a>
          </div>
        </div>
      </div>
      <template slot="body">
        <div class="form-group">
          <label for="username">Username *  <small id="usernamehelp" class="form-text text-muted">Admin User login id</small></label>
          <input id="username" type="text" class="form-control" aria-describedby="usernamehelp" v-model="admin.username">
          <!--<small id="schoolNameHelp" class="form-text text-muted">School Name should be unique</small>-->
        </div>

        <div class="form-group">
          <label for="password">Password *</label>
          <input id="password" type="password" class="form-control" v-model="admin.password">
        </div>
        <div class="form-group">
          <label for="confirmpassword">Confirm Password *</label>
          <input id="confirmpassword" type="password" class="form-control">
        </div>

        <div class="form-group">
          <label for="firstname">First Name</label>
          <input id="firstname" type="text" class="form-control"v-model="admin.firstName">
        </div>

        <div class="form-group">
          <label for="lastname">Last Name</label>
          <input id="lastname" type="text" class="form-control" v-model="admin.lastName">
        </div>

        <div class="form-group">
          <label for="email">Email</label>
          <input id="email" type="email" class="form-control" v-model="admin.email">
        </div>

        <div class="form-group">
          <label>Roles</label>
          <div class="form-check">
            <input class="form-check-input" id="schooladmin" type="checkbox" value="ROLE_SCHOOLADMIN" v-model="admin.roles">
            <label class="form-check-label" for="schooladmin">
              School Admin
            </label>
          </div>
          <div class="form-check mt-2">
            <input class="form-check-input" id="franchiseadmin" type="checkbox" value="ROLE_FRANCHISEADMIN">
            <label class="form-check-label" for="franchiseadmin">
              Franchise Admin
            </label>
          </div>

          <!--<input id="roles" type="checkbox" class="form-control" v-model="admin.correspondent">-->
        </div>

        <!--<div class="form-group">
          <label for="status">Status</label>
          <select id="status" class="form-control" v-model="school.status">
            <option value="Active">Active</option>
            <option value="Inactive">Inactive</option>
          </select>
        </div>-->

      </template>
      <div slot="footer">
        <button type="button" class="btn mi-linkbtn mx-3" @click="close" id="cancel-button">Cancel</button>
        <button type="button" class="btn mi-primarybtn" id="add-button" @click="addAdmin">Add ADMIN</button>
      </div>
    </modal>
  </form>
</template>

<script>
const API_URL = "/api/myschool/";
import Modal from '@/components/Modal';

export default {
  name: "AddAdmin",
  components: {
    Modal
  },
  props: {
    admin: {
      type: Object,
      required: false
    }
  },
  data () {
    return {
      error: false,
      errorMsg: '',
    }
  },
  mounted () {
  },
  methods: {
    addAdmin () {
      let vm = this;
      this.axios.post(API_URL+ "addadmin", vm.admin).then(
          response => {
            /*$('.toast').toast('show');*/
            let res = response.data;
            //Clear the form data
            /*event.target.reset();*/
            /*vm.contact = JSON.parse(JSON.stringify(vm.initContact));*/
            vm.error = false;
            this.$emit("close")
          },
          error => {
            vm.error = true;
            vm.errorMsg = error.response.error +": " + error.message;
          });
    },
    close() {
      this.$emit("close")
    },
  }
}
</script>

<style lang="scss">
.addadmin-modal .modal-container {
  max-width: 500px;
}
</style>