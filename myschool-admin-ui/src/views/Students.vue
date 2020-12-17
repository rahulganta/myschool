<template>
  <div class="students">
    <h2 class="mi-page-title">Students</h2>
    <div class="mi-card">
      <!--<h4 class="mi-card-title">Students</h4>-->
      <MiTable :columns-headers="studentTableColumnsHeaders" :data-list="studentList" :row-actions="studentTableRowActions" :table-actions="studentTableActions" :show-row-check-box="true"
               @rowAaction="tableRowAction" @tableAction="tableAction"></MiTable>
    </div>
  </div>
</template>

<script>
import MiTable from "@/components/MiTable";

export default {
  name: "Students",
  components: {
    MiTable,
  },
  data() {
    return {
      errorMsg: '',
      school: this.$store.state.user.school,
      studentList: [],
      studentTableColumnsHeaders: [
        { title: "USERNAME", sortKey: "username", sortOrder: 1, action: "viewschool", selectedFilters: []},
        { title: "FIRST NAME", sortKey: "firstName", sortOrder: 1, selectedFilters: [], hideOnScr: 'sm'},
        { title: "LAST NAME", sortKey: "lastName", sortOrder: 1, selectedFilters: [], },
        { title: "EMAIL", sortKey: "email", sortOrder: 1, selectedFilters: [],hideOnScr: 'sm'},
        { title: "STATUS", sortKey: "status", sortOrder: 1, selectedFilters: [],},
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
      console.log("Row action is: "+actionName+ " Row data is: " +rowData.username+ " Index is: "+index);
    },
    tableAction(actionName, selectedTableData) {
      if (Array.isArray(selectedTableData) && (selectedTableData.length == 0)) {
        console.log("Empty selected data");
      } else {
        console.log("Table Action is: " +actionName+ " Table data is: "+selectedTableData);
      }
    },
  }
}
</script>

<style scoped>

</style>