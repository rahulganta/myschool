<template type="text/x-template">
  <form>
    <modal class="mi-custom-modal addschool-modal">
      <div slot="header" class="mi-custom-header">
        <div class="row no-gutters">
          <h4 class="col-10 title"><span v-if="action=='update'">Update</span><span v-else>Add</span> student to course</h4>
          <div class="col-2 text-right">
            <a class="btn mi-linkbtn" @click="close"><i class="fas fa-times"/></a>
          </div>
        </div>
      </div>
      <template slot="body">
        <div class="form-group">
          <label for="schoolName">Student *</label>
          <input id="schoolName" type="text" class="form-control" aria-describedby="schoolNameHelp">
        </div>

      </template>
      <div slot="footer">
        <button type="button" class="btn mi-linkbtn mx-3" @click="close" id="cancel-button">Cancel</button>
        <button type="button" class="btn mi-primarybtn" id="add-button" @click="addStudent"><span v-if="action=='update'">Update</span><span v-else>Add</span> Student</button>
      </div>
    </modal>
  </form>
</template>

<script>
import Modal from '@/components/Modal';
export default {
  name: "AddStudentToCourse",
  components: {
    Modal
  },
  props: {
    course: {
      type: Object,
      required: true
    },
    action: {
      type: String,
      required: false
    }
  },
  data() {
    return {
      errorMsg: '',
    }
  },
  mounted() {
  },
  methods: {
    addStudent() {

    },
    close() {
      this.$emit("close")
    },
  }
}
</script>

<style lang="scss">

</style>