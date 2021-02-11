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
            <div class="row">
              <div class="col-lg-6 col-sm-12 col-xs-12">
                <h5 class="card-title">Course Messages</h5>
              </div>
              <div class="col-lg-6 col-sm-12 col-xs-12 text-right">
                <button id="addcoursemessage" class="btn mi-linkbtn" @click="showModal('courseMessageModal', 'add', initialCourseMessage)" :aria-expanded="showAddCourseMessageModal ? 'true':'false'">
                  <i class="fas fa-plus"/> ADD CLASS ANNOUNCEMENT</button>
              </div>
            </div>
            <ul class="list-unstyled">
              <li class="media mb-3" v-for="(courseMessage, index) in courseMessages">
                <!--<i class="far fa-circle fa-2x mr-2"/>-->
                <div class="numberCircle mr-3" style="border-color: #37966f" ><span style="color: #37966f">{{ index+1 }}</span></div>
                <div class="media-body">
                  <strong class="mt-0 mb-1">{{courseMessage.subject}}</strong>
                  <div>{{courseMessage.message}}</div>
                  <p class="text-muted">Posted on: {{ courseMessage.createdTimeStamp | formatDateTime }}</p>
                </div>
                <div class="mi-menu dropdown">
                  <a class="btn mi-linkbtn" href="#" role="button" id="dropdownMenuButton" name="dropdown" data-toggle="dropdown" aria-haspopup="true"
                     aria-expanded="false"> <i class="fas fa-ellipsis-v"></i>
                  </a>
                  <div class="dropdown-menu dropdown-menu-right mi-dropdown-menu " aria-labelledby="dropdownMenuButton">
                    <button class="dropdown-item" name="edit" @click="showModal('courseMessageModal', 'update', courseMessage)" :aria-expanded="showAddCourseMessageModal ? 'true':'false'"><i class="fas fa-pen pr-1"/> Edit</button>
                    <hr>
                    <button class="dropdown-item" name="delete"><i class="fas fa-ban pr-1"/> Delete</button>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="tab-pane fade" id="pills-classwork" role="tabpanel" aria-labelledby="pills-classwork-tab">
        <div class="card mi-card h-100">
          <div class="card-body">
            <div class="row">
              <div class="col-lg-6 col-sm-12 col-xs-12">
                <h5 class="card-title">Class Work</h5>
              </div>
              <div class="col-lg-6 col-sm-12 col-xs-12 text-right">
                <button id="createclasswork" class="btn mi-linkbtn" @click="showModal('courseWorkModal', 'add', initialCourseWork)" :aria-expanded="showAddCourseWorkModal ? 'true':'false'">
                  <i class="fas fa-plus"/> CREATE CLASS WORK</button>
              </div>
            </div>
            <div v-for="classwork in courseWorkList">
              <div class="card mi-card">
                <h5 class="card-title">{{classwork.title}}<br/><small class="text-muted">{{classwork.createdTimeStamp}}</small></h5>
                <div class="mb-3">{{classwork.description}}</div>
                <div class="btn mi-linkbtn" @click="downloadFile(classwork)">DownLoad</div>
                <iframe width="320" height="245" v-if="classwork.videoLink" :src="classwork.videoLink"></iframe>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="tab-pane fade" id="pills-students" role="tabpanel" aria-labelledby="pills-students-tab">
        <div class="card mi-card h-100">
          <div class="card-body">
            <h5 class="card-title">Students</h5>
            <MiTable :columns-headers="studentTableColumnsHeaders" :data-list="studentList" :row-actions="studentTableRowActions" :table-actions="studentTableActions" :show-row-check-box="true"
                     @rowAaction="tableRowAction" @tableAction="tableAction"></MiTable>
          </div>
        </div>
      </div>

    </div>

    <!--Modal Components-->
    <AddCourseMessage v-if="showAddCourseMessageModal" @close="close" :message="courseMessage" :action="action" @addMessage="addCourseMessage"></AddCourseMessage>
    <AddClassWork v-if="showAddCourseWorkModal" @close="close" :course-work="courseWork" :action="action" @AddCourseWork="addCourseWork"></AddClassWork>
    <!--Toast-->
    <Toasts></Toasts>
  </div>
</template>

<script>
import AddCourseMessage from "@/components/modals/AddCourseMessage";
import MiTable from "@/components/MiTable";
import AddClassWork from "@/components/modals/AddClassWork";

export default {
  name: "Course",
  components: {
    AddCourseMessage,
    MiTable,
    AddClassWork
  },
  data() {
    return {
      errorMsg: '',
      action: 'add',
      showAddCourseMessageModal: false,
      showAddCourseWorkModal: false,
      school: this.$store.state.user.school,
      course: {},
      courseMessages:[],
      initialCourseMessage: {
        subject: '',
        message: '',
        priority: 'important',
        courseId: 0
      },
      courseMessage: {},
      initialCourseWork: {
        type: '',
        title: '',
        description: '',
        topic: '',
        videoLink: '',
        courseId: 0
      },
      courseWork: {},
      courseWorkList: [
        {title: 'Material 1',
        description: 'This is the material for first chapter',
        topic: 'Algorithms',
        videoLink: 'https://www.youtube.com/embed/E2vCWDLEkoo',
        file: '',
        createdTimeStamp: 'Jan 21st 2021 05:14:13 pm'
        },
        {title: 'Material 2',
          description: 'This is the material for first chapter',
          topic: 'Algorithms',
          videoLink: 'https://youtube.com/embed/rL8X2mlNHPM',
          file: ''
        },
        {title: 'Assignment',
          description: 'This is the material for first chapter',
          topic: 'Algorithms',
          videoLink: '',
          file: ''
        }
      ],
      studentList: [],
      studentTableColumnsHeaders: [
        { title: "USERNAME", sortKey: "username", sortOrder: 1, action: "viewschool", selectedFilters: []},
        { title: "FIRST NAME", sortKey: "firstName", sortOrder: 1, selectedFilters: [], hideOnScr: 'sm'},
        { title: "LAST NAME", sortKey: "lastName", sortOrder: 1, selectedFilters: [], },
        { title: "EMAIL", sortKey: "email", sortOrder: 1, selectedFilters: [],hideOnScr: 'sm'},
      ],
      studentTableRowActions: [
        { title: "View Student", name: "viewstudent", icon: "fa-info-circle"},
        { title: "Edit Student", name: "editstudent", icon: "fa-pen"},
        { title: "Delete Student", name: "deletestudent", icon: "fa-trash"},
      ],
      studentTableActions: [
        { title: "ADD STUDENT", name: "addstudent", icon: "fa-plus"},
        { title: "DELETE STUDENT", name: "deletestudent", icon: "fa-trash"},
      ],
    }
  },
  created() {
    this.course = this.$store.state.course;
    if(Object.keys(this.course).length === 0) {
      this.getCourse();
    }
    this.getCourseMessages();
    this.getCourseWorks();
    this.getCourseStudents();
  },
  methods: {
    getCourse() {
      let vm = this;
      let courseId = this.$route.params.id;
      this.axios.get(this.$constants().BASE_URL + "course/"+courseId, this.restCallHeaders()).then(
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
      this.axios.get(this.$constants().BASE_URL + "messages/allcoursemessages/"+courseId, this.restCallHeaders()).then(
          response => {
            vm.courseMessages = response.data;
          },
          error => {
            this.errorMsg = error.response.message;
          });
    },
    addCourseMessage() {
      this.getCourseMessages();

    },
    getCourseWorks() {
      let vm = this;
      let courseId = this.$route.params.id;
      this.axios.get(this.$constants().BASE_URL + "allcourseworks/"+courseId, this.restCallHeaders()).then(
          response => {
            vm.courseWorkList = response.data;
          },
          error => {
            this.errorMsg = error.response.message;
          });
    },
    downloadFile(classWork) {
      this.axios.get(this.$constants().BASE_URL + "coursework/"+classWork.id+"/downloadfile",  { headers: {'Authorization': 'Bearer ' + this.$store.state.user.token }, responseType: 'blob'}).then(
          response => {
              var fileURL = window.URL.createObjectURL(new Blob([response.data]));
              var fileLink = document.createElement('a');

              fileLink.href = fileURL;
              var fileName = classWork.fileName ? classWork.fileName : 'file.docx';
              fileLink.setAttribute('download', fileName);
              document.body.appendChild(fileLink);
              fileLink.click();
          },
          error => {
            this.errorMsg = error.response.message;
          });
    },
    addCourseWork() {

    },
    getCourseStudents() {
      let vm = this;
      let school = this.$store.state.school;
      if(school) {
        vm.school = school;
      }
      this.axios.get(this.$constants().BASE_URL + "students/"+vm.school.id, this.restCallHeaders()).then(
          response => {
            vm.studentList = response.data;
            vm.errorMsg = '';
          },
          error => {
            vm.errorMsg = error.response.error +": " + error.message;
          });
    },
    tableRowAction(actionName, rowData, index) {
      console.log("Row action is: "+actionName+ " Row data is: " +rowData.username+ " Index is: "+index);
    },
    tableAction(actionName, selectedTableData) {
      if (Array.isArray(selectedTableData) && (selectedTableData.length == 0)) {
        console.log("Empty selected data");
      } else {
        console.log("Table Action is: " +actionName+ " Table data is: "+selectedTableData);
      }
    },
    showModal(modal, action, data) {
      this.action = action;
      if(modal == 'courseMessageModal') {
        this.courseMessage = JSON.parse(JSON.stringify(data));
        this.courseMessage.courseId = this.course.id;
        this.showAddCourseMessageModal = true;
      } else if(modal == 'courseWorkModal') {
        this.courseWork = JSON.parse(JSON.stringify(data));
        this.courseWork.courseId = this.course.id;
        this.showAddCourseWorkModal = true;
      }

    },
    close() {
      this.showAddCourseMessageModal = false;
      this.showAddCourseWorkModal = false;
    }
  }
}
</script>

<style lang="scss">
  a.mi-linkbtn.active {

  }
  .media-body {
    /*border-bottom: 1px solid #9aa2a87d !important;*/
  }
</style>