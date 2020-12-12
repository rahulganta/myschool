<template>
  <div class="teachers">
    <h2 class="mi-page-title">Staff</h2>
    <div class="mi-card">
      <h4 class="mi-card-title">Teachers</h4>
     <MiTable :columns-headers="columnsHeaders" :data-list="teacherList" :row-actions="teacherTableRowActions" :table-actions="teacherTableActions" :show-row-check-box="true"
             @rowAaction="tableRowAction" @tableAction="tableAction"></MiTable>
    </div>
    <div class="mi-card mt-4">
      <h4 class="mi-card-title">Admins</h4>
     <MiTable :columns-headers="columnsHeaders" :data-list="adminList" :row-actions="adminTableRowActions" :table-actions="adminTableActions" :show-row-check-box="true"
             @rowAaction="tableRowAction" @tableAction="tableAction"></MiTable>
    </div>
  </div>
</template>

<script>
import MiTable from "@/components/MiTable";
export default {
  name: "Teachers",
  components: {
    MiTable,
  },
  data() {
    return {
      errorMsg: '',
      school: this.$store.state.user.school,
      teacherList: [],
      adminList: [],
      columnsHeaders: [
        { title: "USERNAME", sortKey: "username", sortOrder: 1, action: "viewschool", selectedFilters: []},
        { title: "FIRST NAME", sortKey: "firstName", sortOrder: 1, selectedFilters: [], hideOnScr: 'sm'},
        { title: "LAST NAME", sortKey: "lastName", sortOrder: 1, selectedFilters: [], },
        { title: "EMAIL", sortKey: "email", sortOrder: 1, selectedFilters: [],hideOnScr: 'sm'},
        { title: "STATUS", sortKey: "status", sortOrder: 1, selectedFilters: [],},
      ],
      teacherTableRowActions: [
        { title: "View Teacher", name: "viewteacher", icon: "fa-info-circle"},
        { title: "Edit Teacher", name: "editteacher", icon: "fa-pen"},
        { title: "Delete Teacher", name: "deleteteacher", icon: "fa-trash"},
      ],
      teacherTableActions: [
        { title: "ADD TEACHER", name: "addteacher", icon: "fa-plus"},
        { title: "DELETE TEACHER", name: "deleteteacher", icon: "fa-trash"},
      ],
      adminTableRowActions: [
        { title: "View Admin", name: "viewadmin", icon: "fa-info-circle"},
        { title: "Edit Admin", name: "editadmin", icon: "fa-pen"},
        { title: "Delete Admin", name: "deleteadmin", icon: "fa-trash"},
      ],
      adminTableActions: [
        { title: "ADD ADMIN", name: "addadmin", icon: "fa-plus"},
        { title: "DELETE ADMIN", name: "deleteadmin", icon: "fa-trash"},
      ],
    }
  },
  created() {
    this.getTeachers();
    this.getAdmins();
  },
  methods: {
    getTeachers() {
      let vm = this;
      let school = this.$store.state.school;
      if(school) {
        vm.school = school;
      }
      this.axios.get(this.$constants().BASE_URL + "teachers/"+vm.school.id, this.restCallHeaders()).then(
          response => {
            vm.teacherList = response.data;
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