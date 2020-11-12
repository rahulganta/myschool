<template type="text/x-template" id="modal-template">
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">

          <div class="mi-modal-header">
            <h1>{{tittle}}</h1>
            <slot name="header">
              default header
            </slot>
          </div>

          <div class="modal-body">
            <slot name="body">
              default body
            </slot>
          </div>

          <div class="modal-footer text-right">
            <slot name="footer">
              <!--default footer-->
              <button class="btn btn-primary" @click="$emit('close')"> OK </button>
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: 'Modal',
  props: {
    tittle: String
  }
}
</script>

<style lang="scss">
/* Confirmation Modal */
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  max-height: calc(100vh - 50px);
  max-width: 80%;
  min-width: 300px;
  overflow-y: auto;

  margin: 40px auto;
  padding: 10px 0px 0px 10px;
  background-color: #ffffff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
  transition: all .3s ease;
  font-family: Helvetica, Arial, sans-serif;
}
@media screen and (max-width: 768px) { /* Specific to this particular image */
  .modal-container {
    min-width: 90%;
  }
}

.mi-modal-header {
  border-bottom: 0px;
  padding: 0 1rem;
  display: block;
}

.modal-body {
  /*padding: 0px;*/
}

.modal-footer {
  padding: 10px;
  border-top: 0px;
  text-align: right;
  /*font-family: 'Roboto Medium', 'Roboto Regular', 'Roboto';*/
}
.modal-default-button {
  float: right;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>