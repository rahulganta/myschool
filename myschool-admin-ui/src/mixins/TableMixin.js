export default {
    data() {
        return {
            selectedTableRows: [],
            selectAllTableRows: false,
            tableSearchString: "",
        }
    },
    computed: {
        /**
         * @vuese
         * Used for filter data
         */
        tableFilteredData: function() {
            var vm = this;
            var data = this.dataList;
            var sortKey = this.sortKey;
            var filterString = this.tableSearchString && this.tableSearchString.toLowerCase();
            var currentSortOrder = 0;

            this.columnsHeaders.forEach(function(columnHeader) {
                if (columnHeader.sortKey === sortKey) {
                    currentSortOrder = columnHeader.sortOrder || 1;
                }
            });

            //Exact Match Filter
            /*data = data.filter(function (row) {
              console.log("the keys are: " + Object.keys(row));
              return Object.keys(row).some(function (key) {
                for(let i = 0; i < vm.columnsHeaders.length; i ++) {
                  if(vm.columnsHeaders[i].sortKey === key && vm.columnsHeaders[i].selectedFilters.length > 0)  {
                    console.log("The key is: "+key);
                    console.log("found the filter: "+ vm.columnsHeaders[i].selectedFilters.includes(String(row[key])))
                    return vm.columnsHeaders[i].selectedFilters.includes(String(row[key]))
                  }
                }

              });
            });*/

            //String Searching
            if (filterString) {
                data = data.filter(function(row) {
                    console.log("the keys are: " + Object.keys(row));
                    return Object.keys(row).some(function(key) {
                        console.log("The key is: " + key);
                        return (String(row[key]).toLowerCase().indexOf(filterString) > -1);
                    });
                });
            }

            if (sortKey) {
                data = data.slice().sort(function(a, b) {
                    a = a[sortKey];
                    b = b[sortKey];
                    return (a === b ? 0 : a > b ? 1 : -1) * currentSortOrder;
                });
            }

            return data;
        }
    },
    methods: {
        /**
         * @vuese
         * Used for select data
         */
        selectAllRows: function() {
            this.selectedTableRows = [];
            if (!this.selectAllTableRows) {
                for (var i in this.tableFilteredData) {
                    this.selectedTableRows.push(this.tableFilteredData[i]);
                }
            }
        },
        selectRow(rowData) {
            if(this.selectedTableRows.length == this.tableFilteredData.length) {
                this.selectAllTableRows = true;
            } else {
                this.selectAllTableRows = false;
            }
        },
        /**
         * @vuese
         * Used for sort by data
         */
        sortBy: function(key) {
            this.sortKey = key;
            this.columnsHeaders.forEach(function(columnHeader) {
                if (columnHeader.sortKey === key) {
                    columnHeader.sortOrder = columnHeader.sortOrder * -1;
                }
            });
        },
    }

}