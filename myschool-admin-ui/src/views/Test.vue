<template>
  <div class="test">
    <h2 class="mi-page-title">UI Play Ground</h2>
    <div class="mi-error-label">
      <i class="fas fa-exclamation-triangle" /> Please remove this tab in the production
    </div>

    <a class="mi-linkbtn" @click="submit('Hello All')">Submit</a>

    <select class="form-control selectpicker my-5" data-live-search="true" id="state_list" name="">
      <option value="" selected>Manufacturer's Name</option>
      <option value="">Aarkay Engineering Corpot</option>
      <option value="">HAAS GROUP INTERNATIONAL INC., USA</option>
      <option value="">HOLLANDSE SIGNAAL APPARATEN GMBH,NETHERLAND</option>
      <option value="">KLIMAAT TOTAAL, NEDERLAND</option>
      <option value="">LIAAEN BAMFORD LTD,ENGLAND</option>
      <option value="">SAAB, SWEDEN</option>
    </select>

    <label class="pure-material-checkbox my-5">
      <input type="checkbox">
     <span></span>
    </label>

    <MiTable :columns-headers="columnsHeaders" :data-list="tableData" :row-actions="tableRowActions" :table-actions="tableActions" :show-row-check-box="true"
             @rowAaction="tableRowAction" @tableAction="tableAction"></MiTable>

    <!--<Toasts></Toasts>-->
    <Toasts
        :toastMessage="$tc('user',1) +' '+ $t(toastMsg)"
        :show-progress="true"
        :rtl="false"
        :max-messages="7"
        :time-out="7000"
        :closeable="true"
    ></Toasts>
  </div>
</template>

<script>
import MiTable from "@/components/MiTable";
export default {
  name: "Test",
  components: {
    MiTable,
  },
  data () {
    return {
      error: false,
      errorMsg: '',
      toastMsg:'toast.added',
      columnsHeaders: [
        { title: "SCHOOL NAME", sortKey: "name", sortOrder: 1, action: "viewschool", selectedFilters: []},
        { title: "DISPLAY NAME", sortKey: "displayName", sortOrder: 1, selectedFilters: [], hideOnScr: 'sm'},
        { title: "FRANCHISE", sortKey: "franchiseName", sortOrder: 1, selectedFilters: [], },
        { title: "CORRESPONDENT", sortKey: "correspondent", sortOrder: 1, selectedFilters: [],hideOnScr: 'sm'},
        { title: "STATUS", sortKey: "status", sortOrder: 1, selectedFilters: [],},
      ],
      tableData: [
        { id: 1, name: "Tiny Tots", displayName: "Tiny Tots", franchiseName: "Alphores", correspondent: "Narendra Reddy", status: "Active", clientFeature: "feature1",},
        { id: 2, name: "Alphores eTechno", displayName: "eTechno High School", franchiseName: "Alphores", correspondent: "Narendra Reddy", status: "Active", clientFeature: "feature1",},
        { id: 3, name: "Trinity Peddaplly", displayName: "Trinity High School", franchiseName: "Trinity", correspondent: "Manohar Reddy", status: "Active", clientFeature: "feature2",},
      ],
      tableRowActions: [
        { title: "View School", name: "viewschool", icon: "fa-info-circle"},
        { title: "Edit School", name: "editschool", icon: "fa-pen"},
        { title: "Archive School", name: "archiveschool", icon: "fa-ban"},
      ],
      tableActions: [
        { title: "ADD SCHOOL", name: "addschool", icon: "fa-plus"},
        { title: "ADD USER", name: "adduser", icon: "fa-plus"},
      ]
    }
  },
  created() {
  },
  mounted () {
  },
  methods: {
    submit(message) {
      this.$toast.success("School added successfully!!");
    },
    tableRowAction(actionName, rowData, index) {
      console.log("Row action is: "+actionName+ " Row data is: " +rowData.name+ " Index is: "+index);
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

<style lang="scss">

</style>