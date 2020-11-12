<template type="text/x-template">
  <form>
    <modal class="mi-custom-modal addschool-modal">
      <div slot="header" class="mi-custom-header">
        <div class="row no-gutters">
          <h4 class="col-10 title"><span v-if="action=='update'">Update</span><span v-else>Add</span> Course</h4>
          <div class="col-2 text-right">
            <a class="btn mi-linkbtn" @click="close"><i class="fas fa-times"/></a>
          </div>
        </div>
      </div>
      <template slot="body">
        <div class="form-group">
          <label for="schoolName">Course Name *</label>
          <input id="schoolName" type="text" class="form-control" aria-describedby="schoolNameHelp"  v-model="course.name">
        </div>

        <div class="form-group">
          <label for="schoolDName">Course Description</label>
          <input id="schoolDName" type="text" class="form-control" v-model="course.description">
        </div>

        <div class="form-group">
          <label for="franchiseName">Course Grade</label>
          <input id="franchiseName" type="text" class="form-control"v-model="course.grade">
        </div>

        <div class="form-group">
          <label for="instructor">Instructor</label>
          <select class="form-control" id="instructor" v-model="course.instructor">
            <option value="">Select</option>
            <option v-for="(teacher, index) in teachers" :value="teacher.username" selected>{{teacher.firstName}} {{teacher.lastName}}</option>
          </select>
        </div>

      </template>
      <div slot="footer">
        <button type="button" class="btn mi-linkbtn mx-3" @click="close" id="cancel-button">Cancel</button>
        <button type="button" class="btn mi-primarybtn" id="add-button" @click="addCourse"><span v-if="action=='update'">Update</span><span v-else>Add</span> Course</button>
      </div>
    </modal>
  </form>
</template>

<script>
const API_URL = "/api/myschool/";
import Modal from '@/components/Modal';

export default {
  name: "AddCourse",
  components: {
    Modal
  },
  props: {
    course: {
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
      teachers: [],
    }
  },
  mounted () {
    this.getTeachers();
  },
  methods: {
    addCourse() {
      let vm = this;
      //Update Course
      //Add Course
      this.axios.post(API_URL+ "addcourse", vm.course).then(
          response => {
            /*$('.toast').toast('show');*/
            let res = response.data;
            //Clear the form data
            /*event.target.reset();*/
            /*vm.contact = JSON.parse(JSON.stringify(vm.initContact));*/
            vm.errorMsg = '';
            this.$emit("close")
            this.$emit("addCourse")
          },
          error => {
            vm.errorMsg = error.response.error +": " + error.message;
          });
    },
    getTeachers() {
      let vm = this;
      let schoolId = this.course.courseSchoolId;
      this.axios.get(API_URL+ "teachers/"+schoolId, {'headers': {'Authorization': 'Bearer ' + this.$store.state.user.token}}).then(
          response => {
            vm.teachers = response.data;
            vm.errorMsg = '';
          },
          error => {
            vm.errorMsg = error.response.error +": " + error.message;
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