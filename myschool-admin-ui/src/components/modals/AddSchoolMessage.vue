<template type="text/x-template">
  <form>
    <modal class="mi-custom-modal addschool-modal">
      <div slot="header" class="mi-custom-header">
        <div class="row no-gutters">
          <h4 class="col-10 title"><span v-if="action=='update'">Update</span><span v-else>Add</span> Announcement</h4>
          <div class="col-2 text-right">
            <a class="btn mi-linkbtn" @click="close"><i class="fas fa-times"/></a>
          </div>
        </div>
      </div>
      <template slot="body">
        <ErrorMessage :error-message="errorMsg"></ErrorMessage>

        <div class="form-group">
          <label for="subject">Message Subject*</label>
          <input id="subject" type="text" class="form-control" aria-describedby="schoolNameHelp"  v-model="message.subject">
        </div>

        <div class="form-group">
          <label for="schoolName">Message*</label>
          <textarea id="schoolName" rows="10" maxlength="10000" class="form-control" :placeholder="$t('plch.announcement')" v-model="message.message"></textarea>
        </div>

        <div class="form-group">
          <label for="priority">Priority</label>
          <select class="form-control" id="priority" v-model="message.priority">
            <option value="important">Important</option>
            <option value="general">General</option>
          </select>
        </div>

      </template>
      <div slot="footer">
        <button type="button" class="btn mi-linkbtn mx-3" @click="close" id="cancel-button">Cancel</button>
        <button type="button" class="btn mi-primarybtn" id="add-button" @click="addMessage"><span v-if="action=='update'">Update</span><span v-else>Add</span> Announcement</button>
      </div>
    </modal>
  </form>
</template>

<script>
import Modal from '@/components/Modal';
import ErrorMessage from "@/components/ErrorMessage";

export default {
  name: "AddSchoolMessage",
  components: {
    ErrorMessage,
    Modal
  },
  props: {
    message: {
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
  mounted() {
  },
  methods: {
    addMessage() {
      let vm = this;
      //Update Course
      //Add Course
      this.axios.post(this.$constants().BASE_URL + "messages/addschoolmessage", vm.message, this.restCallHeaders()).then(
          response => {
            /*$('.toast').toast('show');*/
            let res = response.data;
            //Clear the form data
            /*event.target.reset();*/
            /*vm.contact = JSON.parse(JSON.stringify(vm.initContact));*/
            vm.errorMsg = '';
            //Toast
            if(vm.action == "update") {
              this.$toast.success("School announcement updated successfully!!");
            } else {
              this.$toast.success("School announcement added successfully!!");
            }
            this.$emit("close")
            this.$emit("addMessage")
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

<style scoped>

</style>