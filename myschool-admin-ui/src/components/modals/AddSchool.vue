<template type="text/x-template">
  <div>
  <form>
    <modal class="mi-custom-modal addschool-modal">
      <div slot="header" class="mi-custom-header">
        <div class="row no-gutters">
          <h4 class="col-10 title"><span v-if="action=='update'">Update</span><span v-else>Add</span> School</h4>
          <div class="col-2 text-right">
            <a class="btn mi-linkbtn" @click="close"><i class="fas fa-times"/></a>
          </div>
        </div>
      </div>
      <template slot="body">
        <ErrorMessage :error-message="errorMsg"></ErrorMessage>

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
        <button type="button" class="btn mi-primarybtn" id="add-button" @click="addSchool"><span v-if="action=='update'">Update</span><span v-else>Add</span> School</button>
      </div>
    </modal>
  </form>
  <Toasts></Toasts>
  </div>
</template>

<script>
import Modal from '@/components/Modal';
import ErrorMessage from "@/components/ErrorMessage";

  export default {
    name: "AddSchool",
    components: {
      Modal,
      ErrorMessage
    },
    props: {
      school: {
        type: Object,
        required: true
      },
      action: {
        type: String,
        required: false
      }

    },
    data () {
      return {
        errorMsg: '',
      }
    },
    mounted () {
    },
    methods: {
      addSchool () {
        /*this.$emit("addschool", this.school)*/
        let vm = this;

        //Update School
        //Add School
        this.axios.post(this.$constants().BASE_URL + "addschool", vm.school, this.restCallHeaders()).then(
            response => {
              /*$('.toast').toast('show');*/
              let res = response.data;
              //Clear the form data
              /*event.target.reset();*/
              /*vm.contact = JSON.parse(JSON.stringify(vm.initContact));*/
              vm.errorMsg = '';

              if(vm.action == "update") {
                this.$toast.success("School updated successfully!!");
              } else {
                this.$toast.success("School added successfully!!");
              }

              this.$emit("close");
              this.$emit("addschool");
            },
            error => {
              vm.errorMsg = error.response.data.message;
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
</style>