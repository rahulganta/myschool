<template>
  <form>
    <modal class="mi-custom-modal addadmin-modal">
      <div slot="header" class="mi-custom-header">
        <div class="row no-gutters">
          <h4 class="col-10 title" v-if="action=='update'">Update User Of<br/><small>{{school.name}}</small></h4>
          <h4 class="col-10 title" v-else>Add User To<br/><small>{{school.name}}</small></h4>
          <div class="col-2 text-right">
            <a class="btn mi-linkbtn" @click="close"><i class="fas fa-times"/></a>
          </div>
        </div>
      </div>
      <template slot="body">
        <ErrorMessage :error-message="errorMsg"></ErrorMessage>
        <ErrorMessage :error-message="$t(uiErrorMsg)"></ErrorMessage>

        <div class="form-group">
          <label for="username">Username *  <small id="usernamehelp" class="form-text text-muted">User login id</small></label>
          <input id="username" type="text" class="form-control" aria-describedby="usernamehelp" v-model.trim="admin.username">
          <!--<small id="schoolNameHelp" class="form-text text-muted">School Name should be unique</small>-->
        </div>

        <div class="form-group">
          <label for="password">Password *</label>
          <div class="input-group input-group-md">
            <input :type="showPassword?'text': 'password'" class="form-control" id="password" :placeholder="$t('plch.password')" v-model.trim="admin.password" autofocus="autofocus">
            <div class="input-group-append">
              <span class="input-group-text" id="basic-addon1" @click="toggleShowPassword"><i class="fas" :class="{ 'fa-eye-slash': showPassword, 'fa-eye': !showPassword }"></i></span>
            </div>
          </div>
        </div>

        <div class="form-group">
          <label for="confirmpassword">Confirm Password *</label>
          <input id="confirmpassword" onpaste="return false;" ondrop="return false;" autocomplete="off" type="password" class="form-control" v-model.trim="confirmPassword" >
        </div>

        <div class="form-group">
          <label for="firstname">First Name</label>
          <input id="firstname" type="text" class="form-control"v-model.trim="admin.firstName">
        </div>

        <div class="form-group">
          <label for="lastname">Last Name</label>
          <input id="lastname" type="text" class="form-control" v-model.trim="admin.lastName">
        </div>

        <div class="form-group">
          <label for="email">Email</label>
          <input id="email" type="email" class="form-control" v-model.trim="admin.email">
        </div>

        <div class="form-group">
          <label>Roles</label>
          <div class="form-check mt-2">
            <input class="form-check-input" id="student" type="radio" value="ROLE_STUDENT" v-model="admin.roles">
            <label class="form-check-label" for="student">
              STUDENT
            </label>
          </div>
          <div class="form-check mt-2">
            <input class="form-check-input" id="teacher" type="radio" value="ROLE_TEACHER" v-model="admin.roles">
            <label class="form-check-label" for="teacher">
              TEACHER
            </label>
          </div>
          <div class="form-check mt-2">
            <input class="form-check-input" id="schooladmin" type="radio" value="ROLE_SCHOOLADMIN" v-model="admin.roles">
            <label class="form-check-label" for="schooladmin">
              SCHOOL ADMIN
            </label>
          </div>
          <div class="form-check mt-2">
            <input class="form-check-input" id="franchiseadmin" type="radio" value="ROLE_FRANCHISEADMIN" v-model="admin.roles">
            <label class="form-check-label" for="franchiseadmin">
              FRANCHISE ADMIN
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
        <button type="button" class="btn mi-primarybtn" id="add-button" @click="addAdmin"><span v-if="action=='update'">Update</span><span v-else>Add</span> User</button>
      </div>
    </modal>
  </form>
</template>

<script>
const API_URL = "/api/myschool/";
import Modal from '@/components/Modal';
import ErrorMessage from "@/components/ErrorMessage";
export default {
  name: "AddAdmin",
  components: {
    Modal,
    ErrorMessage
  },
  props: {
    admin: {
      type: Object,
      required: true
    },
    school: {
      type: Object,
      required: false
    },
    action: {
      type: String,
      required: false
    }
  },
  data () {
    return {
      error: false,
      errorMsg: '',
      uiErrorMsg:'',
      confirmPassword: '',
      showPassword: false,
    }
  },
  mounted () {
  },
  methods: {
    addAdmin () {
      let vm = this;
      if(vm.action == 'update') {
        this.axios.put(API_URL+ "updateuser", vm.admin, this.restCallHeaders()).then(
            response => {
              /*$('.toast').toast('show');*/
              let res = response.data;
              //Clear the form data
              /*event.target.reset();*/
              /*vm.contact = JSON.parse(JSON.stringify(vm.initContact));*/
              vm.error = false;
              this.$emit("close");
              this.$emit("addadmin")
            },
            error => {
              vm.error = true;
              vm.errorMsg = error.response.data.message;
            });
      } else {
        if(vm.admin.password != vm.confirmPassword) {
          vm.error = true;
          vm.uiErrorMsg = "error.passwordnotmatching";
          return;
        }
        this.axios.post(API_URL+ "addadmin", vm.admin, this.restCallHeaders()).then(
            response => {
              /*$('.toast').toast('show');*/
              let res = response.data;
              //Clear the form data
              /*event.target.reset();*/
              /*vm.contact = JSON.parse(JSON.stringify(vm.initContact));*/
              vm.error = false;
              this.$emit("close");
              this.$emit("addadmin")
            },
            error => {
              vm.error = true;
              vm.errorMsg = error.response.data.message;
            });
      }

    },
    close() {
      this.$emit("close")
    },
    toggleShowPassword() {
      this.showPassword ? this.showPassword = false : this.showPassword = true;
    },
  }
}
</script>

<style lang="scss">
.addadmin-modal .modal-container {
  max-width: 500px;
}
</style>