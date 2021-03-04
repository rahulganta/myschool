<template type="text/x-template">
  <form>
    <modal class="mi-custom-modal addstudenttocourse-modal">
      <div slot="header" class="mi-custom-header">
        <div class="row no-gutters">
          <h4 class="col-10 title"><span v-if="action=='update'">Update</span><span v-else>Add</span> student to course</h4>
          <div class="col-2 text-right">
            <a class="btn mi-linkbtn" @click="close"><i class="fas fa-times"/></a>
          </div>
        </div>
      </div>
      <template slot="body">
        <!--<div class="form-group">
          <label for="schoolName">Student *</label>
          <input id="schoolName" type="text" class="form-control" aria-describedby="schoolNameHelp" v-model="studentId">
        </div>-->

        <div class="form-group">
          <div class="input-group input-group-md">
            <div class="input-group-prepend">
              <span class="input-group-text" id="basic-addon1"><i class="fas fa-search"></i></span>
            </div>
            <input type="text" name="search" class="form-control" aria-label="student search" aria-describedby="basic-addon1" placeholder="search for student" v-model.trim="searchString" @input="studentSearch"/>
          </div>
        </div>

        <!--if wanted to show already selected ones-->
        <!--<div class="form-group" v-for="(student, index) in selectedStudents">
          <label class="form-checkbox sr-only">Select row checkbox</label>
          <input type="checkbox" class="mi-tablecheckbox" :value="student" v-model="selectedStudents" >
          <label class="ml-3">{{student.firstName}} {{student.lastName}} (<span class="text-muted">{{student.username}}</span>)</label>
        </div>-->

        <div class="form-group" v-for="(student, index) in studentList">
          <label class="form-checkbox sr-only">Select row checkbox</label>
          <input type="checkbox" class="mi-tablecheckbox" :value="student" v-model="selectedStudents" >
          <label class="ml-3">{{student.firstName}} {{student.lastName}} (<span class="text-muted">{{student.username}}</span>)</label>
        </div>

      </template>
      <div slot="footer">
        <button type="button" class="btn mi-linkbtn mx-3" @click="close" id="cancel-button">Cancel</button>
        <button type="button" class="btn mi-primarybtn" id="add-button" @click="addStudents"><span v-if="action=='update'">Update</span><span v-else>Add</span> Students</button>
      </div>
    </modal>
  </form>
</template>

<script>
import Modal from '@/components/Modal';
export default {
  name: "AddStudentToCourse",
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
  data() {
    return {
      errorMsg: '',
      searchString: '',
      studentId: '',
      originalStuList: '',
      studentList: [],
      searchKeys: ['firstName','lastName', 'username'],
      selectedStudents: [],
      courseRegistration: {
        student: {

        },
        regCourse: this.course
      },
      courseRegistrationList: []
    }
  },
  mounted() {

  },
  methods: {
    studentSearch() {
      let vm = this;
      if(this.searchString.length < 3) {
        this.studentList = [];
      } else if(this.searchString.length == 3) {
        //get students based on searchString
        this.getStudents();
      } else {
        this.studentList = JSON.parse(this.originalStuList);
        this.studentList = this.studentList.filter(function(row) {
          return Object.keys(row).some(function(key) {
            //Search only in search keys
            if(vm.searchKeys.includes(key)) {
              return (String(row[key]).toLowerCase().indexOf(vm.searchString) > -1);
            }
          });
        });

      }
    },
    getStudents() {
      let vm = this;
      this.axios.get(this.$constants().BASE_URL + "searchstudents/"+vm.course.courseSchoolId+"?q="+vm.searchString, this.restCallHeaders()).then(
          response => {
            vm.studentList = response.data;
            vm.originalStuList = JSON.stringify(vm.studentList);
            vm.errorMsg = '';
          },
          error => {
            vm.errorMsg = error.response.error +": " + error.message;
          });
    },
    addStudents() {
      let vm = this;
      this.selectedStudents.forEach(function(student) {
        let courseRegistrationPK = new Object({studentId:'', courseId: ''});
        let courseRegistration = new Object();

        courseRegistration.courseRegistrationPK = courseRegistrationPK;
        courseRegistration.student = student;
        courseRegistration.regCourse = vm.course;
        vm.courseRegistrationList.push(courseRegistration);
      });

      this.axios.post(this.$constants().BASE_URL + "addcourseregistrations/", vm.courseRegistrationList, this.restCallHeaders()).then(
          response => {
            let res = response.data;
            vm.errorMsg = '';
            this.$emit("close")
            this.$emit("AddStudentToCourse")
          },
          error => {
            vm.courseRegistrationList = [];
            vm.errorMsg = error.response.error +": " + error.message;
          });

    },
    close() {
      this.selectedStudents = [];
      this.$emit("close")
    },
  }
}
</script>

<style lang="scss">
.addstudenttocourse-modal .modal-container {
  max-width: 500px;
}
</style>