<template>
  <div class="students">
    <h2 class="mi-page-title">{{ $t('hdr.students')}}</h2>
    <div class="mi-card">
      <!--<h4 class="mi-card-title">Students</h4>-->
      <MiTable :columns-headers="studentTableColumnsHeaders" :data-list="studentList" :row-actions="studentTableRowActions" :table-actions="studentTableActions" :show-row-check-box="true"
               @rowAaction="tableRowAction" @tableAction="tableAction"></MiTable>
    </div>

    <!--Modal Components-->
    <AddAdmin v-if="showAddAdminModal" @close="close" :school="school" :action="action" :admin="user" @addadmin="addUser"></AddAdmin>
  </div>
</template>

<script>
import MiTable from "@/components/MiTable";
import AddAdmin from "@/components/modals/AddAdmin";

export default {
  name: "Students",
  components: {
    MiTable,
    AddAdmin
  },
  data() {
    return {
      errorMsg: '',
      school: this.$store.state.user.school,
      showAddAdminModal: false,
      action: 'add',
      studentList: [],
      studentTableColumnsHeaders: [
        { title: "username", sortKey: "username", sortOrder: 1, action: "viewstudent", selectedFilters: []},
        { title: "firstname", sortKey: "firstName", sortOrder: 1, selectedFilters: [], hideOnScr: 'sm'},
        { title: "lastname", sortKey: "lastName", sortOrder: 1, selectedFilters: [], },
        { title: "email", sortKey: "email", sortOrder: 1, selectedFilters: [],hideOnScr: 'sm'},
        { title: "status", sortKey: "status", sortOrder: 1, selectedFilters: [],},
      ],
      studentTableRowActions: [
        { title: "View Student", name: "viewstudent", icon: "fa-info-circle"},
        { title: "Edit Student", name: "editstudent", icon: "fa-pen"},
        { title: "Delete Student", name: "deletestudent", icon: "fa-trash"},
      ],
      studentTableActions: [
        { title: "addstudent", name: "addstudent", icon: "fa-plus"},
        { title: "deletestudent", name: "deletestudent", icon: "fa-trash"},
      ],
      user: {
        username: '',
        password: '',
        firstName: '',
        lastName: '',
        email: '',
        roles: 'ROLE_STUDENT',
        schoolId: 0
      },
    }
  },
  created() {
    this.getStudents();
  },
  methods: {
    getStudents() {
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
    getAdmins() {
      let vm = this;
      let school = this.$store.state.school;
      if(school) {
        vm.school = school;
      }
      this.axios.get(this.$constants().BASE_URL + "admins/"+vm.school.id, this.restCallHeaders()).then(
          response => {
            vm.adminList = response.data;
            vm.errorMsg = '';
          },
          error => {
            vm.errorMsg = error.response.error +": " + error.message;
          });
    },
    tableRowAction(actionName, rowData, index) {
      console.debug("Row action is: "+actionName+ " Row data is: " +rowData.username+ " Index is: "+index);

      if(actionName == 'viewstudent') {
        this.$router.push({
          path:'/student/'+rowData.username,
                query:{username: rowData.username}
        })
      } else if(actionName == 'editstudent') {
        this.showModal('userModal', 'update', rowData);
      } else if(actionName == 'deletestudent') {

      } else {

      }

    },
    tableAction(actionName, selectedTableData) {
      if(actionName == 'addstudent') {
        this.showModal('userModal');
      }
      if (Array.isArray(selectedTableData) && (selectedTableData.length == 0)) {
        console.log("Empty selected data");
      } else {
        console.log("Table Action is: " +actionName+ " Table data is: "+selectedTableData);
      }
    },
    showModal(modal, action, data) {
      if(modal === 'userModal') {
        this.action = 'add';
        this.user.schoolId = this.school.id;
        this.showAddAdminModal = true;
        if(action == 'update') {
          this.action = action;
          this.user = data;
        }
      }
    },
    addUser() {
      this.getStudents();
    },
    close() {
      this.showAddAdminModal = false;
    }
  }
}
</script>

<style scoped>

</style>