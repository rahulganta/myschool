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
        <ErrorMessage :error-message="errorMsg"></ErrorMessage>

        <div class="form-group">
          <!--<b>Type like assignment, study material...etc, Title, Description, Topic, Add attachment, Youtube link and Vimeo link</b><br/>-->
          <label for="title">Title</label>
          <input id="title" type="text" class="form-control" v-model="courseWork.title">
        </div>

        <div class="form-group">
          <label for="description">Description</label>
          <textarea id="description" rows="5" maxlength="10000" class="form-control" :placeholder="$t('plch.classworkdesc')" v-model="courseWork.description"></textarea>
        </div>

        <div class="form-group">
          <label for="topic">Topic</label>
          <input id="topic" type="text" class="form-control" v-model="courseWork.topic">
        </div>

        <div class="form-group">
          <label for="videolink">Youtube Link</label>
          <input id="videolink" type="text" class="form-control" v-model="courseWork.videoLink">
        </div>

        <!--<div class="form-group">
          <input type="file" id="myfile" name="myfile" @change="handleFileUpload($event)">
          <div class="mi-error-label" v-if="fileErrorMsg"><i class="fas fa-exclamation-triangle" /> {{fileErrorMsg}} </div>
        </div>-->

        <div class="form-group">
          <!--<label>Attach File</label>-->
          <div class="input-group">
            <span class="input-group-btn">
                  <input id="fileUploadInput" name="uploaded_file" @change="handleFileUpload($event)" style="display: none;" type="file">
                  <span class="btn uploadFileBtn" @click="uploadFile">ADD FILE...</span>
            </span>
            <span class="form-control filenameInput ml-2" v-if="uploadedFileName">{{uploadedFileName}}
              <i class="far fa-times-circle ml-1 mi-text-primary" @click="deleteFileUpload"/>
            </span>
          </div>
          <div class="mi-error-label" v-if="fileErrorMsg"><i class="fas fa-exclamation-triangle" /> {{fileErrorMsg}} </div>
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
import ErrorMessage from "@/components/ErrorMessage";

export default {
  name: "AddClassWork",
  components: {
    ErrorMessage,
    Modal
  },
  props: {
    courseWork: {
      type: Object,
      required: true
    },
    /*school: {
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
      uploadedFileName: '',
      uploadedFile: '',
      fileErrorMsg: '',
      errorMsg: '',
    }
  },
  mounted () {
  },
  methods: {
    async handleFileUpload(e) {
      const allowedFileExtensions = /(\.jpg|\.jpeg|\.png|\.gif|\.doc|\.docx|\.pdf|\.txt|\.text|\.ppt|\.pptx|\.xls|\.xlsx)$/i;

      let files = e.target.files || e.dataTransfer.files;
      this.fileErrorMsg = '';

      if (!files.length) {
        return;
      }

      this.uploadedFile = files[0];
      this.uploadedFileName = this.uploadedFile.name

      if(!allowedFileExtensions.exec(this.uploadedFileName)) {
        this.fileErrorMsg = "Wrong file type."
      }

      if(this.uploadedFile.size > 9000000) {
        this.fileErrorMsg = 'Too large, max size allowed is 9000kb.'
      }

      /*this.courseWork.uploadedFile = this.uploadedFile;*/

      let formData = new FormData();
      formData.append('file', this.uploadedFile);
    },
    deleteFileUpload() {
      this.uploadedFile = '';
      this.uploadedFileName = '';
      this.fileErrorMsg = '';
    },
    uploadFile() {
      document.getElementById("fileUploadInput").click();
    },
    addClassWork() {
      let vm = this;
      let embedURL = vm.getEmbedURL(vm.courseWork.videoLink);
      vm.courseWork.videoLink = embedURL;

      this.axios.post(this.$constants().BASE_URL + "addcoursework", this.courseWork, this.restCallHeaders()).then(
          response => {
            /*$('.toast').toast('show');*/
            let res = response.data;
            //Clear the form data
            /*event.target.reset();*/
            /*vm.contact = JSON.parse(JSON.stringify(vm.initContact));*/
            if(vm.uploadedFile) {
              vm.uploadClassWorkFile(res);
            }

            vm.errorMsg = '';
            if(vm.action == "update") {
              this.$toast.success("Coursework updated successfully!!");
            } else {
              this.$toast.success("Coursework added successfully!!");
            }

            this.$emit("close")
          },
          error => {
            vm.errorMsg = error.response.data.message;
          });
    },
    uploadClassWorkFile(classWork) {
      let vm = this;
      let formData = new FormData();
      formData.append("uploadedFile", this.uploadedFile);

      this.axios.post(this.$constants().BASE_URL + "coursework/" + classWork.id + "/uploadfile", formData).then(
          response => {
            this.$emit("AddCourseWork")
          },
          error => {

          });
    },
    getEmbedURL(url) {
      var regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
      var match = url.match(regExp);

      if (match && match[2].length == 11) {
        return "//www.youtube.com/embed/" + match[2];
      } else {
        /*TODO: add regExp for vimeo link or others*/
        return '';
      }
    },
    close() {
      this.$emit("close")
    },
  }
}
</script>

<style lang="scss">
.addclasswork-modal .modal-container {
  max-width: 100%;
  width: 100%;
  height: 100%;
  max-height: 100%;
}
.filenameInput {
  background-color: #e9ecef;
  border: none;
}

.uploadFileBtn {
  background-color: $onPrimary;
  border: 1px solid $primaryColor;
  border-radius: 4px;
  font-family: 'Roboto Bold', 'Roboto Regular', 'Roboto', sans-serif;
  font-weight: 700;
  font-style: normal;
  color: $primaryColor;
}

.uploadFileBtn:hover {
  background-color: $onPrimary;
  color: $primaryVariant;
}
</style>