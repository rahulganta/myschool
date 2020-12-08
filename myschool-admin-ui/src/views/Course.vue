<template>
  <div class="course">
    <div class="row">
      <div class="col-lg-3 col-sm-12 justify-content-center"><h4>{{course.name}} <small>{{course.grade}}</small></h4></div>
      <div class="col-lg-6 col-sm-6">
        <ul class="nav nav-pills mb-3 justify-content-center" id="pills-tab" role="tablist">
          <li class="nav-item mr-2" role="presentation">
            <a class="mi-linkbtn active" id="pills-home-tab" data-toggle="pill" href="#pills-messages" role="tab" aria-controls="pills-messages" aria-selected="true">Messages</a>
          </li>
          <li class="nav-item mr-2" role="presentation">
            <a class="mi-linkbtn" id="pills-profile-tab" data-toggle="pill" href="#pills-classwork" role="tab" aria-controls="pills-classwork" aria-selected="false">Classwork</a>
          </li>
          <li class="nav-item" role="presentation">
            <a class="mi-linkbtn" id="pills-contact-tab" data-toggle="pill" href="#pills-students" role="tab" aria-controls="pills-students" aria-selected="false">Students</a>
          </li>
        </ul>
      </div>
    </div>

    <div class="tab-content" id="pills-tabContent">
      <div class="tab-pane fade show active" id="pills-messages" role="tabpanel" aria-labelledby="pills-messages-tab">
        <div class="card mi-card h-100">
          <div class="card-body">
            <h5 class="card-title">Course Messages</h5>
            <ul class="list-unstyled">
              <li class="media mb-3" v-for="(courseMessage, index) in courseMessages">
                <!--<i class="far fa-circle fa-2x mr-2"/>-->
                <div class="numberCircle mr-3" style="border-color: #37966f" ><span style="color: #37966f">{{ index+1 }}</span></div>
                <div class="media-body">
                  <strong class="mt-0 mb-1">{{courseMessage.subject}}</strong>
                  <div>{{courseMessage.message}}</div>
                  <p class="text-muted">Posted on: {{ courseMessage.createdTimeStamp | formatDateTime }}</p>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="tab-pane fade" id="pills-classwork" role="tabpanel" aria-labelledby="pills-classwork-tab">
        <div class="card mi-card h-100">
          <div class="card-body">
            <h5 class="card-title">Class Work</h5>
            <ul class="list-unstyled">
              <li class="media mb-3" v-for="(courseMessage, index) in courseMessages">
                <!--<i class="far fa-circle fa-2x mr-2"/>-->
                <div class="numberCircle mr-3" style="border-color: #37966f" ><span style="color: #37966f">{{ index+1 }}</span></div>
                <div class="media-body">
                  <strong class="mt-0 mb-1">{{courseMessage.subject}}</strong>
                  <div>{{courseMessage.message}}</div>
                  <p class="text-muted">Posted on: {{ courseMessage.createdTimeStamp | formatDateTime }}</p>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="tab-pane fade" id="pills-students" role="tabpanel" aria-labelledby="pills-students-tab">
        <div class="card mi-card h-100">
          <div class="card-body">
            <h5 class="card-title">Students</h5>
            <ul class="list-unstyled">
              <li class="media mb-3" v-for="(courseMessage, index) in courseMessages">
                <!--<i class="far fa-circle fa-2x mr-2"/>-->
                <div class="numberCircle mr-3" style="border-color: #37966f" ><span style="color: #37966f">{{ index+1 }}</span></div>
                <div class="media-body">
                  <strong class="mt-0 mb-1">{{courseMessage.subject}}</strong>
                  <div>{{courseMessage.message}}</div>
                  <p class="text-muted">Posted on: {{ courseMessage.createdTimeStamp | formatDateTime }}</p>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
const API_URL = "/api/myschool/";
export default {
  name: "Course",
  components: {

  },
  data() {
    return {
      errorMsg: '',
      course: {},
      courseMessages:[],
    }
  },
  created() {
    this.course = this.$store.state.course;
    if(Object.keys(this.course).length === 0) {
      this.getCourse();
    }
    this.getCourseMessages();
  },
  methods: {
    getCourse() {
      let vm = this;
      let courseId = this.$route.params.id;
      this.axios.get(API_URL+ "course/"+courseId, this.restCallHeaders()).then(
          response => {
            vm.course = response.data;
            vm.$store.commit('saveCourse', {course: vm.course});
          },
          error => {
            this.errorMsg = error.response.message;
          });
    },
    getCourseMessages() {
      let vm = this;
      let courseId = this.$route.params.id;
      this.axios.get(API_URL+ "messages/allcoursemessages/"+courseId, this.restCallHeaders()).then(
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
  a.mi-linkbtn.active {

  }
</style>