<template>
  <div v-if="errorMessage.trim()">
    <div class="alert alert-danger">{{ errorMessage }}</div>
  </div>
</template>

<script>
export default {
  name: "ErrorMessage",
  props: {
    errorMessage: {
      type: String,
      required: true,
    },
  },
  data() {
    return {

    }
  },
  created() {
  },
  methods: {

  }
}
</script>

<style scoped>

</style>