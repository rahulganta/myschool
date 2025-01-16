<template>
  <div class="course">
    <div class="row">
      <div class="col-lg-3 col-sm-12 justify-content-center"><h4>{{course.name}} <small>{{course.grade}}</small></h4></div>
      <div class="col-lg-6 col-sm-6">
        <ul class="nav nav-pills mb-3 justify-content-center" id="pills-tab" role="tablist">
          <li class="nav-item mr-2" role="presentation">
            <a class="mi-linkbtn" :class="{ active: activeTab === 'messages' }" id="pills-home-tab" data-toggle="pill" href="#pills-messages" @click="manageTab('messages')" role="tab" aria-controls="pills-messages" aria-selected="true">{{$t('nav.messages')}}</a>
          </li>
          <li class="nav-item mr-2" role="presentation">
            <a class="mi-linkbtn" :class="{ active: activeTab === 'classwork' }" id="pills-profile-tab" data-toggle="pill" href="#pills-classwork" @click="manageTab('classwork')" role="tab" aria-controls="pills-classwork" aria-selected="false">{{$t('nav.classwork')}}</a>
          </li>
          <li class="nav-item" role="presentation">
            <a class="mi-linkbtn" :class="{ active: activeTab === 'students' }" id="pills-contact-tab" data-toggle="pill" href="#pills-students" @click="manageTab('students')" role="tab" aria-controls="pills-students" aria-selected="false">{{$t('nav.students')}}</a>
          </li>
          <li class="nav-item" role="presentation">
            <a class="mi-linkbtn" :class="{ active: activeTab === 'attendance' }" id="pills-contact-tab" data-toggle="pill" href="#pills-attendance" @click="manageTab('attendance')" role="tab" aria-controls="pills-attendance" aria-selected="false">{{$t('nav.attendance')}}</a>
          </li>
        </ul>
      </div>
    </div>

    <div class="tab-content" id="pills-tabContent">
      <div class="tab-pane fade" :class="{ 'active show': activeTab === 'messages' }"id="pills-messages" role="tabpanel" aria-labelledby="pills-messages-tab">
        <div class="card mi-card h-100">
          <div class="card-body">
            <div class="row">
              <div class="col-lg-6 col-sm-12 col-xs-12">
                <h5 class="card-title">{{ $t('hdr.coursemessages') }}</h5>
              </div>
              <div class="col-lg-6 col-sm-12 col-xs-12 text-right">
                <button id="addcoursemessage" class="btn mi-linkbtn" @click="showModal('courseMessageModal', 'add', initialCourseMessage)" :aria-expanded="showAddCourseMessageModal ? 'true':'false'">
                  <i class="fas fa-plus"/> {{$t('btn.addcourseannouncement')}}</button>
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
                    <button class="dropdown-item" name="edit" @click="showModal('courseMessageModal', 'update', courseMessage)" :aria-expanded="showAddCourseMessageModal ? 'true':'false'"><i class="fas fa-pen pr-1"/> {{ $t('btn.edit') }}</button>
                    <hr>
                    <button class="dropdown-item" name="delete"><i class="fas fa-ban pr-1"/> {{ $t('btn.delete') }}</button>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="tab-pane fade" :class="{ 'active show': activeTab === 'classwork' }" id="pills-classwork" role="tabpanel" aria-labelledby="pills-classwork-tab">
        <div class="card mi-card h-100">
          <div class="card-body">
            <div class="row">
              <div class="col-lg-6 col-sm-12 col-xs-12">
                <h5 class="card-title">{{ $t('hdr.coursework') }}</h5>
              </div>
              <div class="col-lg-6 col-sm-12 col-xs-12 text-right">
                <button id="createclasswork" class="btn mi-linkbtn" @click="showModal('courseWorkModal', 'add', initialCourseWork)" :aria-expanded="showAddCourseWorkModal ? 'true':'false'">
                  <i class="fas fa-plus"/> {{$t('btn.addcoursework')}}</button>
              </div>
            </div>

            <div class="mb-3 mi-card-border" v-for="courseWork in courseWorkList">
              <div class="row">
                <div class="col-10">
                  <h5 class="card-title">{{courseWork.title}}<br/>
                    <small class="text-muted">{{courseWork.createdTimeStamp | formatDateTime}}</small><br/>
                    <small><span class="text-muted">Topic:</span> <strong>{{courseWork.topic}}</strong></small>
                  </h5>

                </div>
                <div class="col-2 text-right mi-menu dropdown">
                  <a class="btn mi-linkbtn" href="#" role="button" id="courseWorkDropdownMenuButton" name="dropdown" data-toggle="dropdown" aria-haspopup="true"
                     aria-expanded="false"> <i class="fas fa-ellipsis-v"></i>
                  </a>
                  <div class="dropdown-menu dropdown-menu-right mi-dropdown-menu " aria-labelledby="courseWorkDropdownMenuButton">
                    <button class="dropdown-item" name="copy" @click="showModal('courseWorkModal', 'update', courseWork)"><i class="fas fa-pen pr-1"/> {{ $t('btn.edit') }}</button>
                    <button class="dropdown-item" name="edit" @click="showModal('courseWorkModal', 'delete', courseWork)"><i class="fas fa-info-circle pr-1"/> {{ $t('btn.delete') }}</button>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-lg-6 col-sm-12 col-xs-12">
                  <div class="mb-3">{{courseWork.description}}</div>
                    <div class="media mb-3" v-if="courseWork.fileSize > 0">
                      <!--TODO: depending on the file type change the icon-->
                      <i class="far fa-file-alt fa-4x"/>
                      <div class="media-body ml-3">
                        <a class="mi-text-primary" @click="downloadFile(courseWork)">{{courseWork.fileName}} (DownLoad)</a>
                        <p class="text-muted">File Size: {{courseWork.fileSize | formatBytes}}</p>
                      </div>
                    </div>
                  <div v-if="courseWork.videoLink">
                    <iframe width="320" height="245" v-if="courseWork.videoLink" :src="courseWork.videoLink" frameborder="0" allowfullscreen></iframe>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="tab-pane fade" :class="{ 'active show': activeTab === 'students' }" id="pills-students" role="tabpanel" aria-labelledby="pills-students-tab">
        <div class="card mi-card h-100">
          <div class="card-body">
            <h5 class="card-title">{{ $t('hdr.students') }}</h5>
            <MiTable :columns-headers="studentTableColumnsHeaders" :data-list="studentList" :row-actions="studentTableRowActions" :table-actions="studentTableActions" :show-row-check-box="true"
                     @rowAaction="tableRowAction" @tableAction="tableAction"></MiTable>
          </div>
        </div>
      </div>

      <div class="tab-pane fade" :class="{ 'active show': activeTab === 'attendance' }" id="pills-attendance" role="tabpanel" aria-labelledby="pills-attendance-tab">
        <div class="card mi-card h-100">
          <div class="card-body">
            <div class="row">
              <div class="col-lg-6 col-sm-12 col-xs-12">
                <h5 class="card-title">{{ $t('hdr.courseattendance') }}</h5>
              </div>
              <div class="col-lg-6 col-sm-12 col-xs-12 text-right">
                <input type="text" class="datefield" v-model.trim="moment(attendanceDate).format('ddd, DD MMM YYYY')" readonly/>
                <input type="date"  :data-date="moment().format('DD MMM YYYY')" data-date-format="DD MMMM YYYY" id="attendanceDate"
                       :min="moment().subtract(30, 'days').format('YYYY-MM-DD')"
                       :max="moment().format('YYYY-MM-DD')"
                       v-model="attendanceDate">
                <button id="createclasswork" class="btn mi-linkbtn" @click="showModal('addAttendanceModal', 'add', initialCourseWork)" :aria-expanded="showAddAttendanceModal ? 'true':'false'">
                  <i class="fas fa-plus"/> {{ $t('btn.takeattendance') }}</button>
              </div>
            </div>

<!--            <table class="table table-hover" id="attendance">
              <thead>
              <tr>
                <th>Student Id</th>
                <th>Date</th>
                <th>Status</th>
                <th>Comments</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(attendance, index) in courseAttendanceData">
                <td :id="attendance.attendancePK.studentId" @click="viewSchool(index, rowData)"><a class="mi-text-primary">{{ attendance.attendancePK.studentId }}</a></td>
                <td>{{attendance.attendancePK.attendanceDate}}</td>
                <td>{{attendance.attendanceStatus}}</td>
                &lt;!&ndash;This column will be hidden on xs and sm screens&ndash;&gt;
                <td>{{attendance.comments}}</td>
              </tr>
              </tbody>
            </table>-->

            <table class="table table-sm table-bordered table-hover" id="attendancebystudent">
              <thead>
              <tr>
                <th>{{ $t('tbl.student')}}</th>
                <th v-for="date in availableDates" :key="date">{{moment(date).format('MMM DD, ddd')}}</th>
                <th>P</th>
                <th>L</th>
                <th>E</th>
                <th>U</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(dateList, student) in attendanceByStudent" :key="student">
                <td>{{ student }}</td>
                <td v-for="date in availableDates" :key="date">{{ dateList[date] && dateList[date].attendanceStatus}}
                  <button v-if="dateList[date] && dateList[date].comments" type="button" class="btn mi-linkbtn" data-toggle="tooltip" data-placement="left" :title="dateList[date] && dateList[date].comments">
                    <i class="fas fa-info-circle"/>
                  </button>
                </td>
                <td>{{ dateList.totalPresent }}</td>
                <td>{{ dateList.totalLate }}</td>
                <td>{{ dateList.totalExcusedAbsence }}</td>
                <td>{{ dateList.totalUnExcusedAbsence }}</td>
              </tr>
              </tbody>
            </table>

          </div>
        </div>
      </div>

    </div>

    <!--Modal Components-->
    <AddCourseMessage v-if="showAddCourseMessageModal" @close="close" :message="courseMessage" :action="action" @addMessage="addCourseMessage"></AddCourseMessage>
    <AddClassWork v-if="showAddCourseWorkModal" @close="close" :course-work="courseWork" :action="action" @AddCourseWork="addCourseWork"></AddClassWork>
    <AddAttendance v-if="showAddAttendanceModal" @close="close" :student-list="studentList" :course="course" :courseATData="courseATDataForADate" :attendance-date="attendanceDate" :action="action" @AddAttendance="addAttendance"></AddAttendance>
    <AddStudentToCourse v-if="showAddStudentToCourseModal" @close="close" :course="course" :action="action" @AddStudentToCourse="addStudentToCourse"></AddStudentToCourse>
    <ConfirmModal v-if="showConfirmModal" @close="close" :title="cModalTitle" :warning-message="cModalWarningMessage" @confirm="modalConfirm"></ConfirmModal>
    <!--Toast-->
    <Toasts></Toasts>
  </div>
</template>

<script>
import AddCourseMessage from "@/components/modals/AddCourseMessage";
import MiTable from "@/components/MiTable";
import AddClassWork from "@/components/modals/AddClassWork";
import AddStudentToCourse from "@/components/modals/AddStudentToCourse";
import ConfirmModal from "@/components/modals/ConfirmModal";
import AddAttendance from "@/components/modals/AddAttendance";

import $ from 'jquery';
export default {
  name: "Course",
  components: {
    AddAttendance,
    ConfirmModal,
    AddStudentToCourse,
    AddCourseMessage,
    MiTable,
    AddClassWork
  },
  data() {
    return {
      activeTab: 'messages',
      errorMsg: '',
      action: 'add',
      showAddCourseMessageModal: false,
      showAddCourseWorkModal: false,
      showAddAttendanceModal: false,
      showAddStudentToCourseModal: false,
      showConfirmModal: false,
      cModalTitle: '',
      cModalWarningMessage: '',
      cModalData: {},
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
        { title: 'Material 1',
          description: 'This is the material for first chapter',
          topic: 'Algorithms',
          videoLink: 'https://www.youtube.com/embed/E2vCWDLEkoo',
          file: '',
          createdTimeStamp: 'Jan 21st 2021 05:14:13 pm'
        },
        { title: 'Material 2',
          description: 'This is the material for first chapter',
          topic: 'Algorithms',
          videoLink: 'https://youtube.com/embed/rL8X2mlNHPM',
          file: ''
        },
        { title: 'Assignment',
          description: 'This is the material for first chapter',
          topic: 'Algorithms',
          videoLink: '',
          file: ''
        }
      ],
      courseRegistrations:[],
      studentTableColumnsHeaders: [
        { title: "username", sortKey: "username", sortOrder: 1, action: "viewstudent", selectedFilters: []},
        { title: "firstname", sortKey: "firstName", sortOrder: 1, selectedFilters: [], hideOnScr: 'sm'},
        { title: "lastname", sortKey: "lastName", sortOrder: 1, selectedFilters: [], },
        { title: "email", sortKey: "email", sortOrder: 1, selectedFilters: [],hideOnScr: 'sm'},
        { title: "status", sortKey: "status", sortOrder: 1, selectedFilters: [],},
      ],
      studentTableRowActions: [
        { title: "viewstudent", name: "viewstudent", icon: "fa-info-circle"},
        /*{ title: "Edit Student", name: "editstudent", icon: "fa-pen"},*/
        { title: "deletestudent", name: "deletestudent", icon: "fa-trash"},
      ],
      studentTableActions: [
        { title: "addstudent", name: "addstudent", icon: "fa-plus"},
        { title: "deletestudent", name: "deletestudent", icon: "fa-trash"},
      ],
      attendanceDate: this.moment().format('YYYY-MM-DD'),
      courseAttendanceData: [],
      courseATDataForADate: [],
    }
  },
  created() {
    let activeTab = localStorage.getItem('activeTab');
    if (activeTab) {
      this.activeTab = activeTab;
    }

    this.course = this.$store.state.course;
    if(Object.keys(this.course).length === 0) {
      this.getCourse();
    }
    this.getCourseMessages();
    this.getCourseWorks();
    this.getCourseStudents();
    this.getCourseAttendance();
  },
  computed: {
    formatBytes(bytes, decimals = 2) {
      if (bytes === 0) return '0 Bytes';

      const k = 1024;
      const dm = decimals < 0 ? 0 : decimals;
      const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];

      const i = Math.floor(Math.log(bytes) / Math.log(k));

      return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
    },
    studentList() {
      let stuList = [];
      this.courseRegistrations.forEach(function(courseRegistration) {
          stuList.push(courseRegistration.student);
      });
      return stuList;
    },
    attendanceByStudent() {
      let list = this.courseAttendanceData.reduce((r, o) => {
        r[o.attendancePK.studentId] ??= {};
        r[o.attendancePK.studentId][o.attendancePK.attendanceDate] ??= {attendanceStatus: "", comments: ""};
        r[o.attendancePK.studentId][o.attendancePK.attendanceDate].attendanceStatus = o.attendanceStatus;
        r[o.attendancePK.studentId][o.attendancePK.attendanceDate].comments = o.comments;
        r[o.attendancePK.studentId].totalPresent ??= 0
        r[o.attendancePK.studentId].totalLate ??= 0
        r[o.attendancePK.studentId].totalExcusedAbsence ??= 0
        r[o.attendancePK.studentId].totalUnExcusedAbsence ??= 0
        r[o.attendancePK.studentId].totalPresent += (o.attendanceStatus == "P" ? 1 : 0)
        r[o.attendancePK.studentId].totalLate += (o.attendanceStatus == "L" ? 1 : 0)
        r[o.attendancePK.studentId].totalExcusedAbsence += (o.attendanceStatus == "E" ? 1 : 0)
        r[o.attendancePK.studentId].totalUnExcusedAbsence += (o.attendanceStatus == "U" ? 1 : 0)
        return r;
      }, {});
      return list;
    },
    availableDates() {
      let vm = this;
      const dates = [...new Set(this.courseAttendanceData.map(r => r.attendancePK.attendanceDate))]
      return dates.sort((a,b) => new Date(a) - new Date(b))
    },
  },
  methods: {
    manageTab(activeTabName) {
      console.log("the current tab is: "+activeTabName);
      this.activeTab = activeTabName;
      localStorage.setItem('activeTab', activeTabName);
    },
    getCourse() {
      let vm = this;
      let courseId = this.$route.params.id;
      this.axios.get(this.$constants().BASE_URL + "course/"+courseId, this.restCallHeaders()).then(
          response => {
            vm.course = response.data;
            vm.$store.commit('saveCourse', {course: vm.course});
          },
          error => {
            vm.errorMsg = error.response.data.message;
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
            vm.errorMsg = error.response.data.message;
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
            vm.errorMsg = error.response.data.message;
          });
    },
    downloadFile(classWork) {
      let vm = this;
      this.axios.get(this.$constants().BASE_URL + "coursework/"+classWork.id+"/downloadfile",  this.restCallHeaders('blob')).then(
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
            vm.errorMsg = error.response.data.message;
          });
    },
    addCourseWork() {
      //make rest call to get all the course works or just push the latest coursework to existing arraylist of courseworks
      this.getCourseWorks()

    },
    addAttendance() {
      this.getCourseAttendance();
    },
    getCourseStudents() {
      let vm = this;
      let school = this.$store.state.school;
      let courseId = this.$route.params.id;

      if(school) {
        vm.school = school;
      }
      this.axios.get(this.$constants().BASE_URL + "courseregistrations/?courseid="+courseId, this.restCallHeaders()).then(
          response => {
            vm.courseRegistrations = response.data;
            vm.errorMsg = '';
          },
          error => {
            vm.errorMsg = error.response.data.message;
          });
    },
    getCourseAttendance() {

      let vm = this;
      let courseId = this.$route.params.id;

      this.axios.get(this.$constants().BASE_URL + "attendance/course/"+courseId, this.restCallHeaders()).then(
          response => {
            vm.courseAttendanceData = response.data;
            vm.errorMsg = '';
          },
          error => {
            vm.errorMsg = error.response.data.message;
          });

    },
    addStudentToCourse() {
      this.getCourseStudents();
    },
    modalConfirm() {
      let vm = this;
      console.log("Modal Confirm function");
      /*let courseRegistrationPK = new Object({studentId: vm.cModalData.username, courseId: vm.course.id});*/
      this.axios.delete(this.$constants().BASE_URL + "deletestudentfromcourse/"+vm.course.id+"?studentid="+vm.cModalData.username, this.restCallHeaders()).then(
      response => {
        let res = response.data;
        vm.errorMsg = '';
        vm.getCourseStudents();
      },
      error => {
        vm.errorMsg = error.response.data.message;
      });

    },
    tableRowAction(actionName, rowData, index) {
      let vm = this;
      let courseId = this.$route.params.id;
      console.log("Row action is: "+actionName+ " Row data is: " +rowData.username+ " Index is: "+index);
      if(actionName == 'viewstudent') {
        this.$router.push({
          path:'/student/'+rowData.username+'/'+courseId,
          query:{username: rowData.username}
        })
      } else if(actionName == 'deletestudent') {
        vm.cModalTitle = "Delete student from course";
        vm.cModalWarningMessage = "Are you sure to delete the student <b>" + rowData.firstName + " " + rowData.lastName+ "</b> from this course?";
        vm.showConfirmModal = true;
        vm.cModalData = rowData;

        /*TODO -- Cant send array of student and course object in request body for delete*/
        /*this.axios.delete(this.$constants().BASE_URL + "deletecourseregistration", {headers: {'Authorization': 'Bearer ' + this.$store.state.user.token}, data: {studentId:'ganta', courseId: 38}}).then(
            response => {
              let res = response.data;
              vm.errorMsg = '';
              vm.getCourseStudents();
            },
            error => {
              vm.errorMsg = error.response.error +": " + error.message;
            });*/
      }
    },
    tableAction(actionName, selectedTableData) {
      let vm = this;
      if(actionName == 'addstudent') {
        this.showModal('addStudentToCourse', 'add')
      } else if(actionName == 'deletestudent') {
        if (Array.isArray(selectedTableData) && (selectedTableData.length == 0)) {
          console.log("Table Action name is:" + actionName + " and no data selected");
          vm.cModalTitle = "Delete student from course";
          vm.cModalWarningMessage = "Please select at least one checkbox from the table to perform this action!";
          vm.showConfirmModal = true;
        } else {
          console.log("Table Action name is:" + actionName + " and selected table data is: "+selectedTableData);
        }
      }
    },
    showModal(modalName, action, data) {
      let vm = this;
      this.action = action;
      if(modalName == 'courseMessageModal') {
        this.courseMessage = JSON.parse(JSON.stringify(data));
        this.courseMessage.courseId = this.course.id;
        this.showAddCourseMessageModal = true;
      } else if(modalName == 'courseWorkModal') {
        if(action == 'delete') {
          this.axios.delete(this.$constants().BASE_URL + "deletecoursework/"+data.id, this.restCallHeaders()).then(
              response => {
                vm.getCourseWorks();
              },
              error => {
                vm.errorMsg = error.response.data.message;
              });
        } else {
          this.courseWork = JSON.parse(JSON.stringify(data));
          this.courseWork.courseId = this.course.id;
          this.showAddCourseWorkModal = true;
        }
      } else if(modalName == 'addStudentToCourse') {
        this.showAddStudentToCourseModal = true;
      } else if (modalName == 'addAttendanceModal') {
        this.moment(vm.attendanceDate).format('YYYY-MM-DD');
        let filteredATDataByDate = vm.courseAttendanceData.filter(item => {
          return item.attendancePK.attendanceDate.indexOf(vm.attendanceDate) > -1
        })
        vm.courseATDataForADate = [];
        vm.studentList.forEach(student => {
            let obj = filteredATDataByDate.find(o => o.attendancePK.studentId === student.username);
            if(typeof obj === "undefined") {
              vm.courseATDataForADate.push({ firstName: student.firstName, lastName: student.lastName, attendancePK: {studentId: student.username, courseId: vm.course.id, attendanceDate:vm.attendanceDate },
                attendanceStatus: "",
                comments: ""})
            } else {
              obj.firstName = student.firstName;
              obj.lastName = student.lastName;
              vm.courseATDataForADate.push(obj);
            }
            console.log("The student that searched is: "+ student.username);
            console.log("the value of the object is: "+obj);
        })

        vm.courseATDataForADate.forEach(data => {
          console.log("The filterd data is: " + data.firstName + data.lastName + data.attendancePK.studentId + data.attendancePK.courseId + data.attendancePK.attendanceDate + data.attendanceStatus + data.comments);
        });

        this.showAddAttendanceModal = true;
      }
    },
    close() {
      this.showAddCourseMessageModal = false;
      this.showAddCourseWorkModal = false;
      this.showAddAttendanceModal = false;
      this.showAddStudentToCourseModal = false;
      this.showConfirmModal = false;
    },
  }
}
</script>

<style lang="scss">
  a.mi-linkbtn.active {

  }
  .media-body {
    /*border-bottom: 1px solid #9aa2a87d !important;*/
  }

  input[type="date"] {
    position: relative;
    padding: 5px;
  }

  input[type="date"]::-webkit-calendar-picker-indicator {
    color: #6200EE !important;;
  }

  .btn .tooltip{
    font-size:10px;
  }

</style>