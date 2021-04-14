<template>
  <modal class="mi-custom-modal confirm-modal">
    <div slot="header" class="mi-custom-header">
      <div class="row no-gutters">
        <h4 class="col-10 title">{{title}}</h4>
        <div class="col-2 text-right">
          <a class="btn mi-linkbtn" @click="close"><i class="fas fa-times"/></a>
        </div>
      </div>
    </div>

    <template slot="body">

      <div class="media mb-3">
        <i class="fas fa-exclamation-triangle fa-2x"/>
        <div class="media-body ml-3">
          <p v-html="warningMessage"></p>
        </div>
      </div>
    </template>

    <div slot="footer">
      <button type="button" class="btn mi-linkbtn mx-3" @click="close" id="cancel-button">Cancel</button>
      <button type="button" class="btn mi-primarybtn" id="add-button" @click="confirm">Confirm</button>
    </div>

  </modal>
</template>

<script>
import Modal from '@/components/Modal'
export default {
  name: "ConfirmModal",
  components: {
    Modal,
  },
  props: {
    title: {
      type: String,
      required: false
    },
    warningMessage: {
      type: String,
      required: false
    }
  },
  data() {
    return {

    }
  },
  methods: {
    confirm() {
      this.$emit("confirm");
      this.$emit("close");
    },
    close() {
      this.$emit("close")
    },
  }
}
</script>

<style lang="scss">
.confirm-modal .modal-container {
  max-width: 500px;
}
</style>