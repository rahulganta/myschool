<template>
  <div class="teachers">
    <h2 class="mi-page-title">{{ $t('hdr.staff')}}</h2>
    <div class="mi-card">
      <h4 class="mi-card-title">{{$t('hdr.teachers')}}</h4>
     <MiTable :columns-headers="columnsHeaders" :data-list="teacherList" :row-actions="teacherTableRowActions" :table-actions="teacherTableActions" :show-row-check-box="true"
             @rowAaction="tableRowAction" @tableAction="tableAction"></MiTable>
    </div>

    <div class="mi-card mt-4">
      <h4 class="mi-card-title">{{$t('hdr.admins')}}</h4>
     <MiTable :columns-headers="columnsHeaders" :data-list="adminList" :row-actions="adminTableRowActions" :table-actions="adminTableActions" :show-row-check-box="true"
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
  name: "Teachers",
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
      teacherList: [],
      adminList: [],
      columnsHeaders: [
        { title: 'username', sortKey: "username", sortOrder: 1, action: "viewschool", selectedFilters: []},
        { title: "firstname", sortKey: "firstName", sortOrder: 1, selectedFilters: [], hideOnScr: 'sm'},
        { title: "lastname", sortKey: "lastName", sortOrder: 1, selectedFilters: [], },
        { title: "email", sortKey: "email", sortOrder: 1, selectedFilters: [],hideOnScr: 'sm'},
        { title: "status", sortKey: "status", sortOrder: 1, selectedFilters: [],},
      ],
      teacherTableRowActions: [
        { title: "viewteacher", name: "viewteacher", icon: "fa-info-circle"},
        { title: "editteacher", name: "editteacher", icon: "fa-pen"},
        { title: "deleteteacher", name: "deleteteacher", icon: "fa-trash"},
      ],
      teacherTableActions: [
        { title: "addteacher", name: "addteacher", icon: "fa-plus"},
        { title: "deleteteacher", name: "deleteteacher", icon: "fa-trash"},
      ],
      adminTableRowActions: [
        { title: "viewadmin", name: "viewadmin", icon: "fa-info-circle"},
        { title: "editadmin", name: "editadmin", icon: "fa-pen"},
        { title: "deleteadmin", name: "deleteadmin", icon: "fa-trash"},
      ],
      adminTableActions: [
        { title: "addadmin", name: "addadmin", icon: "fa-plus"},
        { title: "deleteadmin", name: "deleteadmin", icon: "fa-trash"},
      ],
      user: {
        username: '',
        password: '',
        firstName: '',
        lastName: '',
        email: '',
        roles: 'ROLE_TEACHER',
        schoolId: 0
      },
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
      if(actionName == 'viewteacher') {

      } else if(actionName == 'editteacher') {
        this.showModal('userModal', 'update', rowData);
      } else if(actionName == 'deleteteacher') {

      } else if (actionName == 'viewadmin') {

      } else if (actionName == 'editadmin') {
        this.showModal('userModal', 'update', rowData);
      } else if (actionName == 'deleteadmin') {

      } else {

      }
    },
    tableAction(actionName, selectedTableData) {
      if(actionName == 'addteacher') {
        this.showModal('userModal');
      } else if(actionName == 'addadmin') {
        this.user.roles = 'ROLE_SCHOOLADMIN'
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
      this.getTeachers();
      this.getAdmins();
    },
    close() {
      this.showAddAdminModal = false;
    }
  }
}
</script>

<style scoped>

</style>