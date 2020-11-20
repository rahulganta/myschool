<template>
  <div class="course">
    <div class="row">
      <div class="col-lg-3 col-sm-12 justify-content-center"><h4>Course</h4></div>
      <div class="col-lg-6 col-sm-6">
        <ul class="nav nav-pills mb-3 justify-content-center" id="pills-tab" role="tablist">
          <li class="nav-item" role="presentation">
            <a class="nav-link mi-linkbtn active" id="pills-home-tab" data-toggle="pill" href="#pills-messages" role="tab" aria-controls="pills-messages" aria-selected="true">Messages</a>
          </li>
          <li class="nav-item" role="presentation">
            <a class="nav-link mi-linkbtn" id="pills-profile-tab" data-toggle="pill" href="#pills-classwork" role="tab" aria-controls="pills-classwork" aria-selected="false">Classwork</a>
          </li>
          <li class="nav-item" role="presentation">
            <a class="nav-link mi-linkbtn" id="pills-contact-tab" data-toggle="pill" href="#pills-students" role="tab" aria-controls="pills-students" aria-selected="false">Students</a>
          </li>
        </ul>
      </div>
    </div>

    <div class="tab-content" id="pills-tabContent">
      <div class="tab-pane fade show active" id="pills-messages" role="tabpanel" aria-labelledby="pills-messages-tab">
        Home...
      </div>

      <div class="tab-pane fade" id="pills-classwork" role="tabpanel" aria-labelledby="pills-classwork-tab">
        Profile...
      </div>

      <div class="tab-pane fade" id="pills-students" role="tabpanel" aria-labelledby="pills-students-tab">
        Contact...
      </div>

    </div>
  </div>
</template>

<script>
export default {
  name: "Course"
}
</script>

<style scoped>
  .mi-linkbtn.active {
    background-color: ;
  }
</style>