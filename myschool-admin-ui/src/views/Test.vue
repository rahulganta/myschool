<template>
  <div class="test">
    <h2 class="mi-page-title">UI Play Ground</h2>
    <div class="mi-error-label">
      <i class="fas fa-exclamation-triangle" /> Please remove this tab in the production
    </div>

    <select class="form-control selectpicker" data-live-search="true" id="state_list" name="">
      <option value="" selected>Manufacturer's Name</option>
      <option value="">Aarkay Engineering Corpot</option>
      <option value="">HAAS GROUP INTERNATIONAL INC., USA</option>
      <option value="">HOLLANDSE SIGNAAL APPARATEN GMBH,NETHERLAND</option>
      <option value="">KLIMAAT TOTAAL, NEDERLAND</option>
      <option value="">LIAAEN BAMFORD LTD,ENGLAND</option>
      <option value="">SAAB, SWEDEN</option>
    </select>

  </div>
</template>

<script>
export default {
  name: "Test",
  components: {

  },
  data () {
    return {
      error: false,
      errorMsg: '',
    }
  },
  created() {
  },
  mounted () {
  },
  methods: {

  }
}
</script>

<style lang="scss">

</style>