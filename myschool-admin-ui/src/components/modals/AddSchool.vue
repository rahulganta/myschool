<template type="text/x-template">
  <form>
    <modal class="mi-custom-modal addschool-modal">
      <div slot="header" class="mi-custom-header">
        <div class="row">
          <h3 class="col-10 title">Add School</h3>
          <div class="col-2 text-right">
            <a class="btn mi-linkbtn" @click="close"><i class="fas fa-times"/></a>
          </div>
        </div>
      </div>
      <template slot="body">
        <div class="form-group">
          <label for="schoolName">School Name *  <small id="schoolNameHelp" class="form-text text-muted">School Name should be unique</small></label>
          <input id="schoolName" type="text" class="form-control" aria-describedby="schoolNameHelp"  v-model="school.name">
          <!--<small id="schoolNameHelp" class="form-text text-muted">School Name should be unique</small>-->
        </div>

        <div class="form-group">
          <label for="schoolDName">School Display Name *</label>
          <input id="schoolDName" type="text" class="form-control" v-model="school.displayName">
        </div>

        <div class="form-group">
          <label for="franchiseName">Franchise Name</label>
          <input id="franchiseName" type="text" class="form-control"v-model="school.franchiseName">
        </div>

        <div class="form-group">
          <label for="correspondant">Correspondant Name</label>
          <input id="correspondant" type="text" class="form-control" v-model="school.correspondent">
        </div>

        <!--<div class="form-group">
          <label for="status">Status</label>
          <select id="status" class="form-control" v-model="school.status">
            <option value="Active">Active</option>
            <option value="Inactive">Inactive</option>
          </select>
        </div>-->

        <!--<div class="form-group">
          <label>School Display Name *</label>
          <select id="display-select" class="form-control">
            <option disabled selected value>Select School</option>
            <option v-for="(n, index) in 10" :key="index" :value="n">{{n}}</option>
          </select>
        </div>-->
      </template>
      <div slot="footer">
        <button type="button" class="btn mi-linkbtn mx-3" @click="close" id="cancel-button">Cancel</button>
        <button type="button" class="btn mi-primarybtn" id="add-button" @click="addSchool">Add School</button>
      </div>
    </modal>
  </form>
</template>

<script>
const API_URL = "/api/myschool/";
import Modal from '@/components/Modal';
  export default {
    name: "AddSchool",
    components: {
      Modal
    },
    props: {
      school: {
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
      addSchool () {
        /*this.$emit("addschool", this.school)*/
        let vm = this;
        this.axios.post(API_URL+ "addschool", vm.school).then(
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
.addschool-modal .modal-container {
  max-width: 500px;
}
.iconBtn {
  cursor: pointer;
  padding: 10px;

  &:hover {
    background-color: #eff1f1;
  }
}
</style>