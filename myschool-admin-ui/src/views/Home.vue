<template>
  <div class="home">
    <h2 class="mi-page-title">School List</h2>
    <!--<img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to my school App"/>-->
    <div class="mi-card">
      <div class="row mb-3">
        <div class="col-lg-5 col-sm-9">
          <div class="input-group input-group-lg">
            <div class="input-group-prepend">
              <span class="input-group-text" id="basic-addon1"><i class="fas fa-search"></i></span>
            </div>
            <input type="text" name="search" class="form-control" aria-label="Username" aria-describedby="basic-addon1" v-model="tableSearchString"/>
          </div>
        </div>
        <div class="col-lg-1 col-sm-3">
          <!--<button class="btn mi-linkbtn" @click="showFilters = !showFilters"><i class="fas fa-filter align-self-center"></i></button>-->
        </div>
        <div class="col-lg-6 col-sm-12 col-xs-12 text-right">
          <span class="mr-4" v-if="selectedTableRows.length > 0">{{ selectedTableRows.length }} item(s) selected</span>

          <button id="addschool" class="btn mi-linkbtn" @click="showAddSchool('add', {})" :aria-expanded="showAddSchoolModal ? 'true':'false'">
            <i class="fas fa-plus"/> ADD SCHOOL</button>
          <!--<button id="addadmin" class="btn mi-linkbtn" @click="showAddAdminModal = !showAddAdminModal" :aria-expanded="showAddAdminModal ? 'true':'false'">
            <i class="fas fa-plus"/> ADD ADMIN</button>-->
        </div>
      </div>

      <table class="table table-hover" id="schools">
        <thead>
        <tr>
          <th v-for="columnHeader in columnsHeaders" :class="columnHeader.hideOnScr? 'd-md-block d-none': '' ">
            <a class="mi-text-primary" v-bind:id="columnHeader" v-on:click="sortBy(columnHeader.sortKey)" :class="{ active: sortKey == columnHeader.sortKey }" v-html="columnHeader.title">
              {{ columnHeader.title }}
            </a>
            <i v-if="columnHeader.sortKey == sortKey" class="mi-text-primary ml-2 fas" :class="columnHeader.sortOrder > 0 ? 'fa-caret-down' : 'fa-caret-up'"></i>
            <i v-else class="text-muted ml-2 fas" :class="columnHeader.sortOrder > 0 ? 'fa-caret-down' : 'fa-caret-up'"/>
          </th>
          <th><span class="sr-only">Menu</span></th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(rowData, index) in tableFilteredData">
          <td :id="rowData.name" @click="viewSchool(index, rowData)"><a class="mi-text-primary">{{ rowData.name }}</a></td>
          <td :id=" rowData.displayName" class="d-md-block d-none">{{ rowData.displayName }}</td>
          <td :id="rowData.franchiseName">{{ rowData.franchiseName }}</td>
          <!--This column will be hidden on xs and sm screens-->
          <td :id="rowData.correspondent" class="d-md-block d-none">{{ rowData.correspondent }}</td>
          <td :id="rowData.status">{{ rowData.status }}</td>
          <td class="text-right">
            <div class="userprofilemenu dropdown">
              <a class="btn mi-linkbtn" href="#" role="button" id="dropdownMenuButton" name="dropdown" data-toggle="dropdown" aria-haspopup="true"
                 aria-expanded="false"> <i class="fas fa-ellipsis-v"></i>
              </a>
              <div class="dropdown-menu dropdown-menu-right mi-dropdown-menu " aria-labelledby="dropdownMenuButton">
                <button class="dropdown-item" name="edit" @click="viewSchool(index, rowData)"><i class="fas fa-info-circle"/> View School</button>
                <button class="dropdown-item" name="copy" @click="showAddSchool('update', rowData)"><i class="fas fa-pen"/> Edit School</button>
                <hr>
                <button class="dropdown-item" name="delete"><i class="fas fa-ban"/> Inactivate School</button>
                <button class="dropdown-item" @click="showAddAdmin('add', rowData)" :aria-expanded="showAddAdminModal ? 'true':'false'"><i class="fas fa-plus"/> Add AdminUser</button>
              </div>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <!--Modals-->
    <AddSchool v-if="showAddSchoolModal" @close="close" :school="school" :action="action" @addschool="addSchool"></AddSchool>
    <AddAdmin v-if="showAddAdminModal" @close="close" :school="school" :admin="admin" :action="action" @addadmin="addAdmin"></AddAdmin>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import AddSchool from "@/components/modals/AddSchool";
import AddAdmin from "@/components/modals/AddAdmin";
import TableMixin from "@/mixins/TableMixin";

export default {
  name: 'Home',
  components: {
    HelloWorld,
    AddSchool,
    AddAdmin
  },
  mixins:[TableMixin],
  data() {
    return {
      error: false,
      errorMsg: "",
      action: 'add',
      showAddSchoolModal: false,
      showAddAdminModal: false,
      school: {
        name: '',
        displayName: '',
        franchiseName: '',
        correspondent: '',
      },
      admin: {
        username: '',
        password: '',
        firstName: '',
        lastName: '',
        email: '',
        roles: 'ROLE_SCHOOLADMIN' /*ROLE_FRANCHISEADMIN*/,
        schoolId: 0
      },
      /*Table Data*/
      sortKey: "name",
      showFilters: false,
      columnsHeaders: [
        { title: "SCHOOL NAME", sortKey: "name", sortOrder: 1, selectedFilters: []},
        { title: "DISPLAY NAME", sortKey: "displayName", sortOrder: 1, selectedFilters: [], hideOnScr: 'sm'},
        { title: "FRANCHISE", sortKey: "franchiseName", sortOrder: 1, selectedFilters: [],},
        { title: "CORRESPONDENT", sortKey: "correspondent", sortOrder: 1, selectedFilters: [], hideOnScr: 'sm'},
        { title: "STATUS", sortKey: "status", sortOrder: 1, selectedFilters: []},
      ],
      dataList: [
        { id: 1, name: "Tiny Tots", displayName: "Tiny Tots", franchiseName: "Alphores", correspondent: "Narendra Reddy", status: "Active", clientFeature: "feature1",},
        { id: 2, name: "Alphores eTechno", displayName: "eTechno High School", franchiseName: "Alphores", correspondent: "Narendra Reddy", status: "Active", clientFeature: "feature1",},
      ],
    }
  },
  created() {
    let user = this.$store.state.user;
    const sListUserRoles= ["ROLE_SUPERADMIN", "ROLE_FRANCHISEADMIN", "ROLE_PARENT"];
    let gotoHomePage = false;

    user.roles.forEach(function (userRole) {
      if (sListUserRoles.includes(userRole)) {
        gotoHomePage = true;
      }
    });

    if(gotoHomePage) {
      this.getAllSchools();
    } else {
      this.viewSchool(0, user.school)
    }

  },
  watch: {
    /**
     * @vuese
     * Used for table coulumn headers
     */
    columnsHeaders: {
      deep: true,
      handler(newColumnsHeaders, oldColumnsHeaders) {
          //Filters
      }
    }
  },

  methods: {
    getAllSchools() {
      let vm = this;
      this.axios.get(this.$constants().BASE_URL+ "allschools", this.restCallHeaders()).then(
          response => {
            let res = response.data;
            vm.dataList = res;
            vm.error = false;
          },
          error => {
            this.error = true;
            this.errorMsg = error.response.data.message;
          });
    },
    addSchool() {
      this.getAllSchools()
    },
    viewSchool(index, rowData) {
      this.$store.commit('saveSchool', {school: rowData});
      this.$router.push(`/school/${rowData.id}`)
    },
    showAddSchool(actionName, school) {
      this.action = actionName;
      this.school = school;
      this.showAddSchoolModal = true;
    },
    showAddAdmin(actionName, rowData) {
      this.action = actionName;
      this.admin.schoolId = rowData.id;
      this.school = rowData;
      this.showAddAdminModal = true;
    },
    addAdmin() {

    },
    close() {
      this.showAddSchoolModal = false;
      this.showAddAdminModal = false;
    },

  }
}
</script>
<style>
.table thead th {
  border-top: none;
}
</style>