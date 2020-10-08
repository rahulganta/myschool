<template>
  <div class="home">
    <h2 class="mi-page-title">My Schools</h2>
    <!--<img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to my school App"/>-->

    <div class="row mb-3">
      <div class="col-lg-5 col-sm-9">
        <div class="input-group input-group-lg mi-searchbox">
          <div class="input-group-prepend">
            <span class="input-group-text" id="basic-addon1"><i class="fas fa-search"></i></span>
          </div>
          <input type="text" name="search" class="form-control" aria-label="Username" aria-describedby="basic-addon1" v-model="tableSearchString"/>
        </div>
        <!--<button class="btn mi-linkbtn ml-1" @click="showFilters = !showFilters"><i class="fas fa-filter text-fui-blue align-self-center"></i></button>-->
      </div>
      <div class="col-lg-1 col-sm-3">
        <button class="btn mi-linkbtn ml-3" @click="showFilters = !showFilters"><i class="fas fa-filter text-fui-blue align-self-center"></i></button>
      </div>
      <div class="col-lg-6 col-sm-12 text-right">
        <span class="mr-4" v-if="selectedTableRows.length > 0">{{ selectedTableRows.length }} item(s) selected</span>

        <button id="addschool" class="btn mi-linkbtn" @click="showAddSchoolModal = !showAddSchoolModal" :aria-expanded="showAddSchoolModal ? 'true':'false'">
          <i class="fas fa-plus"/> ADD SCHOOL</button>
        <button id="addadmin" class="btn mi-linkbtn" @click="showAddAdminModal = !showAddAdminModal" :aria-expanded="showAddAdminModal ? 'true':'false'">
          <i class="fas fa-plus"/> ADD ADMIN</button>
      </div>
    </div>

    <table class="table table-hover" id="schools">
      <thead>
      <tr>
        <th v-for="columnHeader in columnsHeaders" :class="columnHeader.hideOnScr? 'd-none d-md-block': '' ">
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
        <td :id="rowData.schoolName" @click="viewSchool(index, rowData)">{{ rowData.schoolName }}</td>
        <td :id=" rowData.displayName">{{ rowData.displayName }}</td>
        <td :id="rowData.franchiseName">{{ rowData.franchiseName }}</td>
        <!--This column will be hidden on xs and sm screens-->
        <td :id="rowData.correspondent" class="d-none d-md-block" >{{ rowData.correspondent }}</td>
        <td :id="rowData.status">{{ rowData.status }}</td>
        <td>
          <div class="userprofilemenu dropdown">
            <a class="btn mi-linkbtn" href="#" role="button" id="dropdownMenuButton" name="dropdown" data-toggle="dropdown" aria-haspopup="true"
               aria-expanded="false"> <i class="fas fa-ellipsis-v"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-right fui-dropdown-menu " aria-labelledby="dropdownMenuButton">
              <button class="dropdown-item" name="edit" @click="viewSchool(index, rowData)">View School</button>
              <button class="dropdown-item" name="copy" @click="editSchool('edit', rowData)">Edit School</button>
              <hr>
              <button class="dropdown-item" name="delete">Delete School</button>
            </div>
          </div>
        </td>
      </tr>
      </tbody>
    </table>

    <!--Modals-->
    <AddSchool v-if="showAddSchoolModal" @close="close" :school="school" @addschool="addSchool"></AddSchool>
    <AddAdmin v-if="showAddAdminModal" @close="close" :admin="admin" @addadmin="addAdmin"></AddAdmin>
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
  data () {
    return {
      error: false,
      errorMsg: "",
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
        roles: 'ROLE_SCHOOLADMIN' /*ROLE_FRANCHISEADMIN*/
      },
      /*Table Data*/
      sortKey: "schoolName",
      showFilters: false,
      columnsHeaders: [
        { title: "SCHOOL NAME", sortKey: "schoolName", sortOrder: 1, selectedFilters: []},
        { title: "DISPLAY NAME", sortKey: "displayName", sortOrder: 1, selectedFilters: []},
        { title: "FRANCHISE NAME", sortKey: "franchiseName", sortOrder: 1, selectedFilters: [],},
        { title: "CORRESPONDENT", sortKey: "correspondent", sortOrder: 1, selectedFilters: [], hideOnScr: 'sm'},
        { title: "STATUS", sortKey: "status", sortOrder: 1, selectedFilters: []},
      ],
      dataList: [
        { schoolName: "Tiny Tots", displayName: "Tiny Tots", franchiseName: "Alphores", correspondent: "Narendra Reddy", status: "Active", clientFeature: "feature1",},
        { schoolName: "Alphores eTechno", displayName: "eTechno High School", franchiseName: "Alphores", correspondent: "Narendra Reddy", status: "Active", clientFeature: "feature1",},
      ],
    }
  },
  created() {
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
    addSchool () {

    },
    viewSchool(index, rowData) {

    },
    editSchool(actionName, rowData) {

    },
    addAdmin () {

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