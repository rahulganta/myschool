<template>
  <div class="home">
    <i class="fas fa-warning" />
    <!--<img alt="Vue logo" src="../assets/logo.png">-->
    <HelloWorld msg="Welcome to my school App"/>
    <button id="button" class="btn" @click="showAddSchoolModal = !showAddSchoolModal" :aria-expanded="showAddSchoolModal ? 'true':'false'"><i class="fas fa-plus"/> ADD SCHOOL</button>
    <AddSchool v-if="showAddSchoolModal" @close="close" :school="school" @addschool="addEditSchool"></AddSchool>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import AddSchool from "@/components/modals/AddSchool";

export default {
  name: 'Home',
  components: {
    HelloWorld,
    AddSchool
  },
  data () {
    return {
      showAddSchoolModal: false,
      school: {
        name: '',
        displayName: '',
        franchiseName: '',
        correspondent: '',
      }
    }
  },
  created() {
  },
  methods: {
    addEditSchool () {

    },
    close() {
      this.showAddSchoolModal = false;
    }
  }
}
</script>
