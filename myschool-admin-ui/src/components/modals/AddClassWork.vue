<template type="text/x-template">
  <form>
    <modal class="mi-custom-modal addclasswork-modal">
      <div slot="header" class="mi-custom-header">
        <div class="row no-gutters">
          <h4 class="col-10 title"><span v-if="action =='update'">Update</span><span v-else>Add</span> Classwork</h4>
          <div class="col-2 text-right">
            <a class="btn mi-linkbtn" @click="close"><i class="fas fa-times"/></a>
          </div>
        </div>
      </div>
      <template slot="body">
        <div class="form-group">
          <p>Title, Description, Topic, Add attachment</p>
          <label for="title">Title</label>
          <input id="title" type="text" class="form-control">
        </div>
        <div class="form-group">
          <label for="description">Description</label>
          <textarea id="description" rows="10" maxlength="10000" class="form-control" :placeholder="$t('plch.announcement')"></textarea>
        </div>

        <div class="form-group">
          <input type="file" id="myfile" name="myfile">
        </div>
      </template>

      <div slot="footer">
        <button type="button" class="btn mi-linkbtn mx-3" @click="close" id="cancel-button">Cancel</button>
        <button type="button" class="btn mi-primarybtn" id="add-button" @click="addClassWork"><span v-if="action=='update'">Update</span><span v-else>Add</span> Classwork</button>
      </div>
    </modal>
  </form>
</template>

<script>
import Modal from '@/components/Modal';

export default {
  name: "AddClassWork",
  components: {
    Modal
  },
  props: {
    /*admin: {
      type: Object,
      required: true
    },
    school: {
      type: Object,
      required: false
    },*/
    action: {
      type: String,
      required: false
    }
  },
  data () {
    return {
      errorMsg: '',
    }
  },
  mounted () {
  },
  methods: {
    addClassWork() {
      let vm = this;
    },
    close() {
      this.$emit("close")
    },
  }
}
</script>

<style lang="scss">
.addclasswork-modal .modal-container {
  width: 100%;
}
</style>