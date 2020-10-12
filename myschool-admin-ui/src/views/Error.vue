<template>
  <div class="error">
    <h2 class="mi-page-title">Error</h2>
    <div class="mi-error-label">
      <i class="fas fa-exclamation-triangle" /> {{this.$store.state.globalError}}
    </div>
  </div>
</template>

<script>
export default {
  name: "Error",
  computed: {
    globalError() {
      return this.$store.state.globalError;
    },
  },
}
</script>

<style scoped>

</style>