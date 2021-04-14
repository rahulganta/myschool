<template>
  <div class="mi-datatable">
    <div class="row mb-3">
      <div class="col-lg-5 col-sm-9">
        <div class="input-group input-group-lg" v-if="showSearchInput">
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

        <button v-for="(tAction, index) in tableActions" :id="tAction.name" class="btn mi-linkbtn" @click="tableAction(tAction.name, selectedTableRows)">
          <i class="fas" :class="tAction.icon"/> {{tAction.title}}</button>
      </div>
    </div>

    <table class="table table-hover" id="schools">
      <thead>
      <tr>
        <th v-if="showRowCheckBox">
          <label class="form-checkbox sr-only" for="selectallchkbox">Select all rows checkbox</label>
          <input type="checkbox" id="selectallchkbox" class="mi-tablecheckbox" v-model="selectAllTableRows" @click="selectAllRows">
        </th>

        <th v-for="columnHeader in columnsHeaders" :class="columnHeader.hideOnScr? 'd-md-block d-none': '' ">
          <a class="mi-text-primary" v-bind:id="columnHeader.sortKey" v-on:click="sortBy(columnHeader.sortKey)" :class="{ active: sortKey == columnHeader.sortKey }" v-html="columnHeader.title">
            {{ columnHeader.title }}
          </a>
          <i v-if="columnHeader.sortKey == sortKey" class="mi-text-primary ml-2 fas" :class="columnHeader.sortOrder > 0 ? 'fa-caret-down' : 'fa-caret-up'"></i>
          <i v-else class="text-muted ml-2 fas" :class="columnHeader.sortOrder > 0 ? 'fa-caret-down' : 'fa-caret-up'"/>
        </th>
        <th v-if="rowActions"><span class="sr-only">Menu</span></th>
      </tr>
      </thead>
      <tbody v-if="tableFilteredData.length > 0">
      <tr v-for="(rowData, index) in tableFilteredData">
        <td v-if="showRowCheckBox">
          <label class="form-checkbox sr-only">Select row checkbox</label>
          <input type="checkbox" class="mi-tablecheckbox" :value="rowData" v-model="selectedTableRows" @change="selectRow(rowData)">
        </td>

        <td v-for="(columnHeader, cHeaderindex) in columnsHeaders" :id="rowData[columnHeader.sortKey]" :class="columnHeader.hideOnScr? 'd-md-block d-none': '' ">
          <a class="mi-text-primary" v-if="columnHeader.action" @click="rowAction(columnHeader.action, rowData, index)">{{ rowData[columnHeader.sortKey] }}</a>
          <span v-else>{{ rowData[columnHeader.sortKey] }}</span>
        </td>
        <td class="text-right" v-if="rowActions">
          <div class="userprofilemenu dropdown">
            <a class="btn mi-linkbtn" href="#" role="button" id="dropdownMenuButton" name="dropdown" data-toggle="dropdown" aria-haspopup="true"
               aria-expanded="false"> <i class="fas fa-ellipsis-v"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-right mi-dropdown-menu " aria-labelledby="dropdownMenuButton">
              <button v-for="action in rowActions" class="dropdown-item" :name="action.name" @click="rowAction(action.name, rowData, index)">
                <i class="fas" :class="action.icon"/> {{ action.title}}</button>
            </div>
          </div>
        </td>
      </tr>
      </tbody>
      <tbody v-else>
      <tr>
        <td colspan="100%" class="text-center">{{ noTableDataMessage }}</td>
      </tr>
      </tbody>
    </table>
  </div>

</template>

<script>
import TableMixin from "@/mixins/TableMixin";
export default {
  name: "MiTable",
  components: {

  },
  mixins:[TableMixin],
  props: {
    columnsHeaders: {
      type: Array,
      required: true
    },
    dataList: {
      type: Array,
      required: true
    },
    rowActions: {
      type: Array,
      required: false
    },
    tableActions: {
      type: Array,
      required: false
    },
    initialSortKey: {
      type: String,
      required: false,
    },
    showRowCheckBox: {
      type: Boolean,
      default: true
    },
    showSearchInput: {
      type: Boolean,
      default: true
    },
    noTableDataMessage: {
      type: String,
      default: "No data found"
    }
  },
  data() {
    return {
      sortKey: typeof this.initialSortKey !== "undefined" ? this.initialSortKey : this.columnsHeaders[0].sortKey,
    }
  },
  created() {
    /*this.sortKey = typeof this.initialSortKey !== "undefined" ? this.initialSortKey : this.columnsHeaders[0].sortKey;*/
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
    rowAction(actionName, rowData, index) {
      this.$emit("rowAaction", actionName, rowData, index);
    },
    tableAction(actionName, selectedData) {
      this.$emit("tableAction", actionName, selectedData);
    },
  }
}
</script>

<style scoped>

</style>