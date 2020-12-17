<template>
  <div class="messages">
    <h2 class="mi-page-title">Messages</h2>
    <div class="row row-cols-lg-3 row-cols-md-1 row-cols-sm-1">
      <div class="col-sm-12 mb-4">
        <div class="card mi-card">
          <div class="card-body">
            <h5 class="card-title">Course Messages</h5>
            <ul class="list-unstyled">
              <li class="media mb-3" v-for="(courseMessage, index) in courseMessages">
                <!--<i class="far fa-circle fa-2x mr-2"/>-->
                <div class="numberCircle mr-3" style="border-color: #37966f" ><span style="color: #37966f">{{ index+1 }}</span></div>
                <div class="media-body">
                  <strong class="mt-0 mb-1">{{courseMessage.subject}}</strong>
                  <div>{{courseMessage.message}}</div>
                  <p class="text-muted">Posted: {{ courseMessage.createdTimeStamp | formatDateTime }}</p>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="col-sm-12 mb-4">
        <div class="card mi-card">
          <div class="card-body">
            <h5 class="card-title">Personal Messages</h5>
            <ul class="list-unstyled">
              <li class="media mb-3" v-for="(courseMessage, index) in courseMessages">
                <!--<i class="far fa-circle fa-2x mr-2"/>-->
                <div class="numberCircle mr-3" style="border-color: #37966f" ><span style="color: #37966f">{{ index+1 }}</span></div>
                <div class="media-body">
                  <strong class="mt-0 mb-1">{{courseMessage.subject}}</strong>
                  <div>{{courseMessage.message}}</div>
                  <p class="text-muted">Posted: {{ courseMessage.createdTimeStamp | formatDateTime }}</p>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="col-sm-12 mb-4">
        <div class="card mi-card">
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
    </div>

  </div>
</template>

<script>
export default {
  name: "Messages",
  components: {

  },
  data() {
    return {
      errorMsg: '',
      school: this.$store.state.user.school,
      schoolMessages:[],
      courseMessages:[],
      userMessages:[],
    }
  },
  created() {
    this.getAllCoursesMessages();
    this.getSchoolMessages();
  },
  methods: {
    getSchoolMessages() {
      let vm = this;
      let school = this.$store.state.school;
      if(school) {
        vm.school = school;
      }
      this.axios.get(this.$constants().BASE_URL + "messages/allschoolmessages/"+vm.school.id, this.restCallHeaders()).then(
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
  }
}
</script>

<style scoped>

</style>