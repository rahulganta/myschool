<template>
<div class="school">
  <h3 class="mi-page-title text-uppercase">{{ school.name}}</h3>
  <div class="card mi-card mb-4 pb-0 h-100">
    <div class="card-body pb-0">
      <h5 class="card-title">School Info</h5>
      <div class="card-text row">
        <div class="col-lg-4 col-md-6">
          <p><strong>Name: </strong>{{school.name}}</p>
          <p><strong>Display Name: </strong>{{school.displayName}}</p>
          <p><strong>Franchise Name: </strong>{{school.franchiseName}}</p>
        </div>
        <div class="col-lg-4 col-md-6">
          <p><strong>Correspondant: </strong>{{school.correspondent}}</p>
          <p><strong>Status: </strong>{{school.status}}</p>
        </div>
      </div>
    </div>
    <div class="card-footer mi-card-footer">
      <button id="editschool1" class="btn mi-linkbtn" @click="showModal('schoolModal')" :aria-expanded="showAddSchoolModal ? 'true':'false'">
        <i class="fas fa-pen"/> EDIT SCHOOL</button>
      <button id="addadmin1" class="btn mi-linkbtn" @click="showModal('adminModal')" :aria-expanded="showAddAdminModal ? 'true':'false'">
        <i class="fas fa-plus"/> ADD USER</button>
    </div>
  </div>

  <div class="row row-cols-lg-3 row-cols-md-1 row-cols-sm-1">
    <div class="col-sm-12 mb-4">
      <div class="card mi-card h-100">
        <div class="card-body">
          <h5 class="card-title">My Messages</h5>
          <ul class="list-unstyled">
            <li class="media mb-3" v-for="(userMessage, index) in userMessages">
              <!--<i class="far fa-circle fa-2x mr-2"/>-->
              <div class="numberCircle mr-3" style="border-color: #37966f" ><span style="color: #37966f">{{ index+1 }}</span></div>
              <div class="media-body">
                <strong class="mt-0 mb-1">{{userMessage.subject}}</strong>
                <div>{{userMessage.message}}</div>
                <p class="text-muted">Posted: {{ userMessage.createdTimeStamp | formatDateTime }}</p>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="col-sm-12 mb-4">
      <div class="card mi-card h-100">
        <div class="card-body">
          <h5 class="card-title">School Announcements</h5>
          <ul class="list-unstyled">
            <li class="media mb-3" v-for="(schoolMessage, index) in schoolMessages">
              <!--<i class="far fa-circle fa-2x mr-2"/>-->
              <div class="numberCircle mr-3" style="border-color: #F9AA33" ><span style="color: #F9AA33">{{ index+1 }}</span></div>
              <div class="media-body">
                <strong class="mt-0 mb-1">{{schoolMessage.subject}}</strong>
                <div>{{schoolMessage.message}}</div>
                <p class="text-muted">Posted: {{ schoolMessage.createdTimeStamp | formatDateTime }}</p>
              </div>
            </li>
          </ul>
        </div>
        <div class="card-footer mi-card-footer">
          <button id="addschoolmessage" class="btn mi-linkbtn" @click="showModal('schoolMessageModal')" :aria-expanded="showAddSchoolMessage ? 'true':'false'">
            <i class="fas fa-plus"/> ADD SCHOOL ANNOUNCEMENT</button>
        </div>
      </div>
    </div>

    <div class="col-sm-12 mb-4">
      <div class="card mi-card pb-0 h-100">
        <div class="card-body pb-0">
          <h5 class="card-title">School Info</h5>
          <div class="card-text row">
            <div class="col-lg-6 col-md-6">
              <p><strong>Name: </strong>{{school.name}}</p>
              <p><strong>Display Name: </strong>{{school.displayName}}</p>
              <p><strong>Franchise Name: </strong>{{school.franchiseName}}</p>
            </div>
            <div class="col-lg-6 col-md-6">
              <p><strong>Correspondant: </strong>{{school.correspondent}}</p>
              <p><strong>Status: </strong>{{school.status}}</p>
            </div>
          </div>
        </div>
        <div class="card-footer mi-card-footer">
          <button id="editschool" class="btn mi-linkbtn" @click="showModal('schoolModal')" :aria-expanded="showAddSchoolModal ? 'true':'false'">
            <i class="fas fa-pen"/> EDIT SCHOOL</button>
          <button id="addadmin" class="btn mi-linkbtn" @click="showModal('adminModal')" :aria-expanded="showAddAdminModal ? 'true':'false'">
            <i class="fas fa-plus"/> ADD USER</button>
        </div>
      </div>
    </div>
  </div>

  <h3>My courses</h3>
  <div class="row row-cols-sm-1 row-cols-md-2 row-cols-lg-3">
    <div class="col-sm-12 mb-4" v-for="(course, index) in courses">
      <div class="card mi-card h-100"> <!--v-bind:style="{backgroundColor:'#F9AA33',color: '#FFF' }"-->
        <div class="card-body">
          <div class="row">
            <h5 class="col-10" v-bind:style="{color: colors[index] }" @click="navToCourse(course)" style="cursor: pointer">{{course.name}} <small>{{course.grade}}</small></h5>
            <div class="col-2 text-right mi-menu dropdown">
              <a class="btn mi-linkbtn" href="#" role="button" id="dropdownMenuButton" name="dropdown" data-toggle="dropdown" aria-haspopup="true"
                 aria-expanded="false"> <i class="fas fa-ellipsis-v"></i>
              </a>
              <div class="dropdown-menu dropdown-menu-right mi-dropdown-menu " aria-labelledby="dropdownMenuButton">
                <button class="dropdown-item" name="copy" @click="showModal('courseModal', 'update', course)"><i class="fas fa-pen pr-1"/> Edit Course</button>
                <button class="dropdown-item" name="edit" @click="navToCourse(course)"><i class="fas fa-info-circle pr-1"/> View Course</button>
                <hr>
                <button class="dropdown-item" name="delete"><i class="fas fa-ban pr-1"/> Inactivate Course</button>
              </div>
            </div>
          </div>
          <div class="card-text">{{course.description}}</div>
          <span>Instructor: {{course.instructor}}</span>
        </div>
      </div>
    </div>


    <div class="col mb-4" @click="showModal('courseModal', 'add')">
      <div class="card mi-card h-100" > <!--v-bind:style="{backgroundColor:'#37966F',color: '#FFF' }"-->
        <div class="card-body text-center mi-text-primary">
          <h5 class="card-title">Add</h5>
          <h5 class="card-title"><i class="fas fa-plus" /> </h5>
          <h5 class="card-text">Course</h5>
          <!--<h5 class="card-text"><i class="fas fa-plus" /> Add Course</h5>-->
        </div>
      </div>
    </div>

  </div>

  <!--Modals-->
  <AddSchool v-if="showAddSchoolModal" @close="close" :school="school" :action="'update'" @addschool="addSchool"></AddSchool>
  <AddAdmin v-if="showAddAdminModal" @close="close" :school="school" :admin="admin" @addadmin="addAdmin"></AddAdmin>
  <AddCourse v-if="showAddCourseModal" @close="close" :course="course" :action="action" @addCourse="addCourse"></AddCourse>
  <AddSchoolMessage v-if="showAddSchoolMessage" @close="close" :message="schoolMessage" :action="action" @addMessage="addSchoolMessage"></AddSchoolMessage>

  <!--Toast-->
  <Toasts></Toasts>
</div>
</template>

<script>
import AddSchool from "@/components/modals/AddSchool";
import AddAdmin from "@/components/modals/AddAdmin";
import AddCourse from "@/components/modals/AddCourse";
import AddSchoolMessage from "@/components/modals/AddSchoolMessage";

export default {
  name: "School",
  components: {
    AddSchool,
    AddAdmin,
    AddCourse,
    AddSchoolMessage
  },
  data() {
    return {
      errorMsg: '',
      action: 'add',
      showAddSchoolModal: false,
      showAddAdminModal: false,
      showAddCourseModal: false,
      showAddSchoolMessage: false,
      school: {},
      admin: {
        username: '',
        password: '',
        firstName: '',
        lastName: '',
        email: '',
        roles: 'ROLE_SCHOOLADMIN',
        schoolId: 0
      },
      course: {
        name: '',
        description: '',
        grade: '',
        instructor: '',
        courseSchoolId: 0
      },
      schoolMessage: {
        subject: '',
        message: '',
        priority: 'important',
        schoolId: 0
      },
      schoolMessages:[],
      courseMessages:[],
      userMessages:[],
      courses:[],
      colors: this.$store.state.colors,
    }
  },
  created() {
    this.school = this.$store.state.school
    if(!this.school) {
      this.getSchool();
    }
    this.getUserMessages();
    this.getAllCoursesMessages();
    this.getSchoolMessages();
    this.getCourses();
  },
  methods: {
    getSchool() {
      let vm = this;
      let schoolId = this.$route.params.id;
      this.axios.get(this.$constants().BASE_URL + "school/"+schoolId, this.restCallHeaders()).then(
          response => {
            vm.school = response.data;
            vm.$store.commit('saveSchool', {school: vm.school});
          },
          error => {
            this.errorMsg = error.response.message;
          });
    },
    getSchoolMessages() {
      let vm = this;
      let schoolId = this.$route.params.id;
      this.axios.get(this.$constants().BASE_URL + "messages/allschoolmessages/"+schoolId, this.restCallHeaders()).then(
          response => {
            vm.schoolMessages = response.data;
          },
          error => {
            this.errorMsg = error.response.message;
          });
    },
    getAllCoursesMessages() {
      let vm = this;
      let schoolId = this.$route.params.id;
      this.axios.get(this.$constants().BASE_URL + "messages/allcoursemessages/", this.restCallHeaders()).then(
          response => {
            vm.courseMessages = response.data;
          },
          error => {
            this.errorMsg = error.response.message;
          });
    },
    getUserMessages() {
      let vm = this;
      this.axios.get(this.$constants().BASE_URL + "messages/usermessages?username="+vm.$store.state.user.userName, this.restCallHeaders()).then(
          response => {
            vm.userMessages = response.data;
          },
          error => {
            this.errorMsg = error.response.message;
          });
    },
    getCourses() {
      let vm = this;
      let schoolId = this.$route.params.id;
      this.axios.get(this.$constants().BASE_URL + "schoolcourses/"+schoolId, this.restCallHeaders()).then(
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
    showModal(modal, action, data) {
      if(modal === 'adminModal') {
        this.admin.schoolId = this.school.id;
        this.showAddAdminModal = true;
      }

      if(modal === 'schoolModal') {
        this.showAddSchoolModal = true;
      }

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

      if(modal === 'schoolMessageModal') {
        if(action === 'update') {
          this.action = action;
          this.schoolMessage = data;
        } else {
          this.action = 'add';
          this.schoolMessage = {}
          this.schoolMessage.priority = 'important',
          this.schoolMessage.schoolId = this.school.id;
        }
        this.showAddSchoolMessage = true;
      }
    },
    addAdmin() {

    },
    addschool() {

    },
    addCourse() {
      this.getCourses();
    },
    addSchoolMessage() {
      this.getSchoolMessages();
    },
    close() {
      this.showAddSchoolModal = false;
      this.showAddAdminModal = false;
      this.showAddCourseModal = false;
      this.showAddSchoolMessage = false;
    }
  }
}
</script>

<style lang="scss">

</style>