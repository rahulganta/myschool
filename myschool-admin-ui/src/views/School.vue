<template>
<div class="school">
  <h3 class="mi-page-title text-uppercase">{{ school.name}}</h3>
  <div class="row row-cols-lg-3 row-cols-md-1 row-cols-sm-1">
    <div class="col-lg-8 mb-4">
      <div class="card mi-card h-100">
        <div class="card-body">
          <h5 class="card-title">Messages</h5>
          <!--<div v-for="(schoolMessage, index) in schoolMessages">
            <strong class="card-text">{{schoolMessage.subject}}</strong>
            <div>{{schoolMessage.message}}</div>
            <span class="text-muted">{{schoolMessage.createdTimeStamp}}</span>
          </div>-->
          <ul class="list-unstyled">
            <li class="media mb-3" v-for="(schoolMessage, index) in schoolMessages">
              <!--<i class="far fa-circle fa-2x mr-2"/>-->
              <div class="numberCircle mr-3" style="border-color: #F9AA33" ><span style="color: #F9AA33">{{ index+1 }}</span></div>
              <div class="media-body">
                <strong class="mt-0 mb-1">{{schoolMessage.subject}}</strong>
                <div>{{schoolMessage.message}}</div>
                <span class="text-muted">{{schoolMessage.createdTimeStamp}}</span>
              </div>
            </li>
          </ul>

        </div>
      </div>
    </div>

    <div class="col mb-4">
      <div class="card mi-card h-100">
        <div class="card-body">
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
            <!--<div class="col-lg-3 col-md-6">
              <p><strong>Address: </strong>{{school.line1}}, {{school.line2}}</p>
              <p>{{school.city}}, {{school.state}}-{{school.zipcode}}, {{school.country}}</p>
            </div>-->
          </div>
          <div class="card-footer mi-card-footer">
            <button id="editschool" class="btn mi-linkbtn" @click="showModal('schoolModal')" :aria-expanded="showAddSchoolModal ? 'true':'false'">
              <i class="fas fa-pen"/> EDIT SCHOOL</button>
            <button id="addadmin" class="btn mi-linkbtn" @click="showModal('adminModal')" :aria-expanded="showAddAdminModal ? 'true':'false'">
              <i class="fas fa-plus"/> ADD ADMIN</button>
          </div>
        </div>
      </div>
    </div>
  </div>

  <h3>My courses</h3>
  <div class="row row-cols-lg-3 row-cols-md-2 row-cols-sm-1">
    <div class="col mb-4" v-for="(course, index) in courses">
      <div class="card mi-card h-100" v-bind:style="{backgroundColor:colors[index], color: '#FFF' }"> <!--v-bind:style="{backgroundColor:'#F9AA33',color: '#FFF' }"-->
        <!--<img src="..." class="card-img-top" alt="...">-->
        <div class="card-body">
          <h5 class="card-title">{{course.name}}</h5>
          <div class="card-text">{{course.description}}</div>
          <span>Instructor: {{course.instructor}}</span>
        </div>
      </div>
    </div>

    <div class="col mb-4">
      <div class="card mi-card h-100" > <!--v-bind:style="{backgroundColor:'#37966F',color: '#FFF' }"-->
        <div class="card-body">
          <h5 class="card-title" v-bind:style="{color: '#37966F' }">Chemistry</h5>
          <p class="card-text">This is a short card.</p>
        </div>
      </div>
    </div>
    <div class="col mb-4">
      <div class="card mi-card h-100"> <!--v-bind:style="{backgroundColor:'#AD634F /*#FF0266*/', color: '#FFF' }"-->
        <div class="card-body">
          <h5 class="card-title" v-bind:style="{color: '#FF0266' }">Maths</h5>
          <p class="card-text">This is a short card.</p>
        </div>
      </div>
    </div>
    <div class="col mb-4">
      <div class="card mi-card h-100" v-bind:style="{backgroundColor:'#5D1049', color: '#FFF' }">
        <div class="card-body">
          <h5 class="card-title">Physics</h5>
          <p class="card-text">This is a short card.</p>
        </div>
      </div>
    </div>
    <div class="col mb-4">
      <div class="card mi-card h-100" v-bind:style="{backgroundColor:'#2F3F3F', color: '#FFF' }">
        <div class="card-body">
          <h5 class="card-title">Card title</h5>
          <p class="card-text">This is a short card.</p>
        </div>
      </div>
    </div>
    <div class="col mb-4">
      <div class="card mi-card h-100" v-bind:style="{backgroundColor:'#7D9FB7 /*#94A7A7*/ /*#957199*/' , color: '#FFF' }">
        <div class="card-body">
          <h5 class="card-title">Card title</h5>
          <p class="card-text">This is a short card.</p>
        </div>
      </div>
    </div>
  </div>

  <!--Modals-->
  <AddSchool v-if="showAddSchoolModal" @close="close" :school="school" :action="'update'" @addschool="addSchool"></AddSchool>
  <AddAdmin v-if="showAddAdminModal" @close="close" :school="school" :admin="admin" @addadmin="addAdmin"></AddAdmin>
</div>
</template>

<script>
const API_URL = "/api/myschool/";
import AddSchool from "@/components/modals/AddSchool";
import AddAdmin from "@/components/modals/AddAdmin";

export default {
  name: "School",
  components: {
    AddSchool,
    AddAdmin
  },
  data() {
    return {
      error: false,
      errorMsg: '',
      showAddSchoolModal: false,
      showAddAdminModal: false,
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
      schoolMessages:[],
      courses:[],
      colors: this.$store.state.colors
    }
  },
  created() {
    this.school = this.$store.state.school
    if(!this.school) {
      this.getSchool();
    }
    this.getSchoolMessages();
    this.getCourses();
  },
  methods: {
    getSchool() {
      let vm = this;
      let schoolId = this.$route.params.id;
      this.axios.get(API_URL+ "school/"+schoolId, {'headers': {'Authorization': 'Bearer ' + this.$store.state.user.token}}).then(
          response => {
            vm.school = response.data;
            vm.$store.commit('saveSchool', {school: vm.school});
          },
          error => {
            this.error = true;
            this.errorMsg = error.response.message;
          });
    },
    getSchoolMessages() {
      let vm = this;
      let schoolId = this.$route.params.id;
      this.axios.get(API_URL+ "messages/allschoolmessages/"+schoolId, {'headers': {'Authorization': 'Bearer ' + this.$store.state.user.token}}).then(
          response => {
            vm.schoolMessages = response.data;
          },
          error => {
            this.error = true;
            this.errorMsg = error.response.message;
          });
    },
    getCourses() {
      let vm = this;
      let schoolId = this.$route.params.id;
      this.axios.get(API_URL+ "schoolcourses/"+schoolId, {'headers': {'Authorization': 'Bearer ' + this.$store.state.user.token}}).then(
          response => {
            vm.courses = response.data;
          },
          error => {
            this.error = true;
            this.errorMsg = error.response.message;
          });
    },
    showModal(modal) {
      if(modal === 'adminModal') {
        this.admin.schoolId = this.school.id;
        this.showAddAdminModal = true;
      }
      if(modal === 'schoolModal') {
        this.showAddSchoolModal = true;
      }
    },
    addAdmin() {

    },
    addschool() {

    },
    close() {
      this.showAddSchoolModal = false;
      this.showAddAdminModal = false;
    }
  }
}
</script>

<style scoped>

</style>