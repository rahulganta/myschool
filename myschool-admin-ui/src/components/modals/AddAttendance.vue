<template type="text/x-template">
  <div>
    <form>
    <modal class="mi-custom-modal addattendance-modal">
      <div slot="header" class="mi-custom-header">
        <div class="row no-gutters">
          <h4 class="col-10 title"><span v-if="action =='update'">Update</span><span v-else>Take</span> Attendance
            <small>(Course:<b> {{course.name}}</b>, Date:<b> {{moment(attendanceDate).format('ddd MMM DD, YYYY')}}</b>)</small>
          </h4>
          <div class="col-2 text-right">
            <a class="btn mi-linkbtn" @click="close"><i class="fas fa-times"/></a>
          </div>
        </div>
      </div>
      <template slot="body">
        <ErrorMessage :error-message="errorMsg"></ErrorMessage>

        <table class="table table-hover" id="schools">
          <thead>
          <tr>
            <th>First Name</th>
            <th class="d-md-block d-none">LastName</th>
            <th style="width: 100px">Status</th>
            <th >Comments</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(student, index) in courseATData">
            <td :id="student.firstName" @click="viewSchool(index, rowData)"><a class="mi-text-primary">{{ student.firstName }}</a></td>
            <td :id="student.lastName" class="d-md-block d-none">{{ student.lastName }}</td>
            <td>
              <label class="sr-only" for="instructor">Attendance Statu</label>
              <select class="form-control" id="instructor" v-model="student.attendanceStatus">
                <option value="">Select</option>
                <option value="P" selected>P</option>
                <option value="U" selected>U</option>
                <option value="E" selected>E</option>
                <option value="L" selected>L</option>
              </select>
            </td>
            <!--This column will be hidden on xs and sm screens-->
            <td>
              <label class="sr-only" for="comments">comments</label>
              <input type="text" class="form-control" v-model.trim="student.comments"/>
            </td>
          </tr>
          </tbody>
        </table>

      </template>
      <div slot="footer">
        <button type="button" class="btn mi-linkbtn mx-3" @click="close" id="cancel-button">Cancel</button>
        <button type="button" class="btn mi-primarybtn" id="add-button" @click="addAttendance">Submit</button>
      </div>
    </modal>
  </form>
    <!--Toast-->
    <Toasts></Toasts>
  </div>
</template>

<script>
import Modal from '@/components/Modal';
import ErrorMessage from "@/components/ErrorMessage";
export default {
  name: "AddAttendance",
  components: {
    ErrorMessage,
    Modal
  },
  props: {
    studentList: {
      type: Array,
      required: true
    },
    courseATData: {
      type: Array,
      required: true
    },
    course: {
      type: Object,
      required: true
    },
    attendanceDate: {
      type: String,
      required: true
    },
    action: {
      type: String,
      required: false
    }
  },
  data () {
    return {
      error: false,
      errorMsg: '',
      attendanceData: {
        attendancePK: {
          studentId: null,
          courseId: null,
          attendanceDate:null
        },
        attendanceStatus: null,
        comments: null,
      },
      attendanceDataList: [],
      attendanceStatus: [],
      comments: []
    }
  },
  mounted () {

  },
  created() {

  },
  methods: {
    addAttendance() {
      let vm = this;
      /*for(let i = 0; i < this.studentList.length; i ++) {
        console.log("the attendance data is: "+ vm.studentList[i].username + " status: "+vm.attendanceStatus[i]+" comments: "+ vm.comments[i] );
        vm.attendanceDataList.push({ attendancePK: {studentId: vm.studentList[i].username, courseId: vm.course.id, attendanceDate:vm.attendanceDate },
          attendanceStatus: vm.attendanceStatus[i],
          comments: vm.comments[i]});
      }*/
      this.axios.post(this.$constants().BASE_URL+ "addattendance", vm.courseATData, this.restCallHeaders()).then(
          response => {
            //Clear the form data
            /*event.target.reset();*/
            /*vm.contact = JSON.parse(JSON.stringify(vm.initContact));*/

            //Toast
            if(vm.action == "update") {
              this.$toast.success("Course Attendance updated successfully!!");
            } else {
              this.$toast.success("Course Attendance added successfully!!");
            }
            vm.error = false;
            this.$emit("close");
            this.$emit("addAttendance")
          },
          error => {
            vm.attendanceDataList = [];
            vm.error = true;
            vm.errorMsg = error.response.data.message;
          });

    },
    close() {
      this.$emit("close")
    },
  }
}
</script>

<style lang="scss">
.addattendance-modal .modal-container {
  max-width: 100%;
  width: 100%;
  height: 100%;
  max-height: 100%;
}
.filenameInput {
  background-color: #e9ecef;
  border: none;
}

.uploadFileBtn {
  background-color: $onPrimary;
  border: 1px solid $primaryColor;
  border-radius: 4px;
  font-family: 'Roboto Bold', 'Roboto Regular', 'Roboto', sans-serif;
  font-weight: 700;
  font-style: normal;
  color: $primaryColor;
}

.uploadFileBtn:hover {
  background-color: $onPrimary;
  color: $primaryVariant;
}
</style>