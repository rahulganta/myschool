<template>
  <div class="mycourses">
    <h3 class="mi-page-title">{{$t('hdr.mycourses')}}</h3>
    <div class="row row-cols-sm-1 row-cols-md-2 row-cols-lg-3">
      <div class="col-sm-12 mb-4" v-for="(course, index) in courses">
        <div class="card mi-card h-100"> <!--v-bind:style="{backgroundColor:'#F9AA33',color: '#FFF' }"-->
          <div class="card-body">
            <div class="row">
              <h5 class="col-10" v-bind:style="{color: colors[index] }" @click="navToCourse(course)">{{course.name}} <small>{{course.grade}}</small></h5>
              <div class="col-2 text-right mi-menu dropdown">
                <a class="btn mi-linkbtn" href="#" role="button" id="dropdownMenuButton" name="dropdown" data-toggle="dropdown" aria-haspopup="true"
                   aria-expanded="false"> <i class="fas fa-ellipsis-v"></i>
                </a>
                <div class="dropdown-menu dropdown-menu-right mi-dropdown-menu " aria-labelledby="dropdownMenuButton">
                  <button class="dropdown-item" name="edit" @click="navToCourse(course)"><i class="fas fa-info-circle pr-1"/> {{$t('btn.viewcourse')}}</button>
                  <button class="dropdown-item" name="copy" @click="showModal('courseModal', 'update', course)"><i class="fas fa-pen pr-1"/> {{$t('btn.editcourse')}}</button>
                  <hr>
                  <button class="dropdown-item" name="delete"><i class="fas fa-ban pr-1"/> {{$t('btn.inactivatecourse')}}</button>
                </div>
              </div>
            </div>
            <div class="card-text">{{course.description}}</div>
            <span>{{$t('label.instructor')}}: {{course.instructor}}</span>
          </div>
        </div>
      </div>


      <div class="col mb-4" @click="showModal('courseModal', 'add')">
        <div class="card mi-card h-100" > <!--v-bind:style="{backgroundColor:'#37966F',color: '#FFF' }"-->
          <div class="card-body text-center mi-text-primary">
            <h5 class="card-title">{{$t('btn.add')}}</h5>
            <h5 class="card-title"><i class="fas fa-plus" /> </h5>
            <h5 class="card-text">{{$t('btn.course')}}</h5>
            <!--<h5 class="card-text"><i class="fas fa-plus" /> Add Course</h5>-->
          </div>
        </div>
      </div>
    </div>

    <AddCourse v-if="showAddCourseModal" @close="close" :course="course" :action="action" @addCourse="addCourse"></AddCourse>
  </div>
</template>

<script>
const API_URL = "/api/myschool/";
import AddCourse from "@/components/modals/AddCourse";
export default {
  name: "Courses",
  components: {
    AddCourse
  },
  data() {
    return {
      errorMsg: '',
      action: 'add',
      showAddCourseModal: false,
      school: this.$store.state.user.school,
      courses:[],
      course: {
        name: '',
        description: '',
        grade: '',
        instructor: '',
        courseSchoolId: 0
      },
      colors: this.$store.state.colors
    }
  },
  created() {
    this.getCourses();
  },
  methods: {
    getCourses() {
      let vm = this;
      /*let schoolId = this.$route.params.id;*/
      let school = this.$store.state.school;
      if(school) {
        vm.school = school;
      }
      this.axios.get(API_URL+ "schoolcourses/"+vm.school.id, {'headers': {'Authorization': 'Bearer ' + this.$store.state.user.token}}).then(
          response => {
            vm.courses = response.data;
          },
          error => {
            this.errorMsg = error.response.message;
          });
    },
    navToCourse(course) {
      this.$store.commit('saveCourse', {course: course});
      this.$router.push('/course/'+course.id);
    },
    addCourse() {
      this.getCourses();
    },
    showModal(modal, action, data) {
      if(modal === 'courseModal') {
        this.action = action;
        if(action === 'update') {
          this.course = data;
        } else {
          this.course = {};
          this.course.courseSchoolId = this.school.id;
        }

        this.showAddCourseModal = true;
      }
    },
    close() {
      this.showAddCourseModal = false;
    }
  }

}
</script>

<style scoped>

</style>