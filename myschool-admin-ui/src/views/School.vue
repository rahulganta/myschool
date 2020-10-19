<template>
<div class="school">
  <h2 class="mi-page-title">{{ school.name}}</h2>
  <div class="card mi-card mb-3">
    <div class="card-body">
      <h4 class="card-title text-uppercase">{{school.name}}</h4>
      <div class="card-text row">
        <div class="col-lg-3 col-md-6">
          <p><strong>Display Name: </strong>{{school.displayName}}</p>
          <p><strong>Franchise Name: </strong>{{school.franchiseName}}</p>
        </div>
        <div class="col-lg-3 col-md-6">
          <p><strong>Correspondant Name: </strong>{{school.correspondent}}</p>
          <p><strong>Status: </strong>{{school.status}}</p>
        </div>
        <!--<div class="col-lg-3 col-md-6">
          <p><strong>Address: </strong>{{school.line1}}, {{school.line2}}</p>
          <p>{{school.city}}, {{school.state}}-{{school.zipcode}}, {{school.country}}</p>
        </div>-->
      </div>
    </div>
  </div>

</div>
</template>

<script>
const API_URL = "/api/myschool/";

export default {
  name: "School",
  components: {},
  data() {
    return {
      error: false,
      errorMsg: '',
      school: {}
    }
  },
  created() {
    this.school = this.$store.state.school
    if(!this.school) {
      this.getSchool();
    }
  },
  methods: {
    getSchool() {
      let vm = this;
      let schoolId = this.$route.params.id;
      this.axios.get(API_URL+ "school/"+schoolId, {'headers': {'Authorization': 'Bearer ' + this.$store.state.user.token}}).then(
          response => {
            vm.school = response.data
          },
          error => {
            this.error = true;
            this.errorMsg = error.response.message;
          });

      vm.$store.commit('saveSchool', {school: vm.school});
    }
  }
}
</script>

<style scoped>

</style>