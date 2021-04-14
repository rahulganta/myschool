<template>
  <div class="student">
    <h4 class="mi-page-title"><a class="btn mi-linkbtn" @click="$router.go(-1)"><i class="far fa-arrow-alt-circle-left fa-2x"></i></a> {{$route.params.username}} <small>{{$route.params.courseId}}</small></h4>

    <div class="card mi-card mb-4 pb-0 h-100">
      <div class="card-body">
        <div class="row">
          <div class="col-lg-6 col-sm-12 col-xs-12">
            <h5 class="card-title">User Messages</h5>
          </div>
          <div class="col-lg-6 col-sm-12 col-xs-12 text-right">
            <button id="addusermessage" class="btn mi-linkbtn" @click="showModal('userMessageModal', 'add', initialUserMessage)" :aria-expanded="showAddUserMessageModal ? 'true':'false'">
              <i class="fas fa-plus"/> ADD MESSAGE</button>
          </div>
        </div>
        <ul class="list-unstyled">
          <li class="media mb-3" v-for="(userMessage, index) in userMessages">
            <!--<i class="far fa-circle fa-2x mr-2"/>-->
            <div class="numberCircle mr-3" style="border-color: #37966f" ><span style="color: #37966f">{{ index+1 }}</span></div>
            <div class="media-body">
              <strong class="mt-0 mb-1">{{userMessage.subject}}</strong>
              <div>{{userMessage.message}}</div>
              <p class="text-muted">Posted By: {{userMessage.postedBy}}</p>
              <p class="text-muted">Posted: {{ userMessage.createdTimeStamp | formatDateTime }}</p>
            </div>
          </li>
        </ul>
      </div>
      <div class="card-footer mi-card-footer">
        <!--<button id="addschoolmessage" class="btn mi-linkbtn" @click="showModal('schoolMessageModal')" :aria-expanded="showAddSchoolMessage ? 'true':'false'">
          <i class="fas fa-plus"/> ADD SCHOOL ANNOUNCEMENT</button>-->
      </div>
    </div>

    <!--Modal Components-->
    <AddUserMessage v-if="showAddUserMessageModal" @close="close" :message="userMessage" :action="action" @addMessage="addUserMessage"></AddUserMessage>

  </div>
</template>

<script>
import AddUserMessage from "@/components/modals/AddUserMessage";

export default {
  name: "Student",
  components: {
    AddUserMessage
  },
  data() {
    return {
      username: this.$route.params.username,
      courseId: this.$route.params.courseId,
      action: 'add',
      showAddUserMessageModal: false,
      userMessages:[],
      userMessage: {},
      initialUserMessage: {
        subject: '',
        message: '',
        priority: 'important',
        postedTo: this.$route.params.username,
      },
    }
  },
  created() {
    this.getUserMessages();
  },
  methods: {
    getUserMessages() {
      let vm = this;
      this.axios.get(this.$constants().BASE_URL + "messages/usermessages?username="+vm.username, this.restCallHeaders()).then(
          response => {
            vm.userMessages = response.data;
          },
          error => {
            this.errorMsg = error.response.message;
          });
    },
    addUserMessage() {
      this.getUserMessages();
    },
    showModal(modalName, action, data) {
      let vm = this;
      this.action = action;
      this.userMessage = data;
      if(modalName == 'userMessageModal') {
        if(action === 'update') {
          this.action = action;
          this.schoolMessage = data;
        } else {
          this.action = 'add';
        }
        this.showAddUserMessageModal = true;
      }

    },
    close() {
      this.showAddUserMessageModal = false;
    }

  }
}
</script>

<style scoped>

</style>