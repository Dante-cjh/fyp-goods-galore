<template> 
  <el-card class="form-container" shadow="never">
    <el-form :model="brand" :rules="rules" ref="brandFrom" label-width="150px">
      <el-form-item label="Brand Name:" prop="name">
        <el-input v-model="brand.name"></el-input>
      </el-form-item>
      <el-form-item label="Brand First Letter:">
        <el-input v-model="brand.firstLetter"></el-input>
      </el-form-item>
      <el-form-item label="LOGO:" prop="logo">
        <single-upload v-model="brand.logo"></single-upload>
      </el-form-item>
      <el-form-item label="Brand Banner:">
        <single-upload v-model="brand.bigPic"></single-upload>
      </el-form-item>
      <el-form-item label="Description:">
        <el-input
          placeholder="Please input brand description"
          type="textarea"
          v-model="brand.brandStory"
          :autosize="true"></el-input>
      </el-form-item>
      <el-form-item label="Sort:" prop="sort">
        <el-input v-model.number="brand.sort"></el-input>
      </el-form-item>
      <el-form-item label="Visible:">
        <el-radio-group v-model="brand.showStatus">
          <el-radio :label="1">yes</el-radio>
          <el-radio :label="0">no</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="Show in Navigation:">
        <el-radio-group v-model="brand.factoryStatus">
          <el-radio :label="1">yes</el-radio>
          <el-radio :label="0">no</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('brandFrom')">submit</el-button>
        <el-button v-if="!isEdit" @click="resetForm('brandFrom')">reset</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>
  import {createBrand, getBrand, updateBrand} from '@/api/brand'
  import SingleUpload from '@/components/Upload/singleUpload'
  const defaultBrand={
    bigPic: '',
    brandStory: '',
    factoryStatus: 0,
    firstLetter: '',
    logo: '',
    name: '',
    showStatus: 0,
    sort: 0
  };
  export default {
    name: 'BrandDetail',
    components:{SingleUpload},
    props: {
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        brand:Object.assign({}, defaultBrand),
        rules: {
          name: [
            {required: true, message: 'Please input brand name', trigger: 'blur'},
            {min: 2, max: 140, message: '2 to 140 characters in length', trigger: 'blur'}
          ],
          logo: [
            {required: true, message: 'Please upload logo', trigger: 'blur'}
          ],
          sort: [
            {type: 'number', message: 'Must be numeric'}
          ],
        }
      }
    },
    created() {
      if (this.isEdit) {
        getBrand(this.$route.query.id).then(response => {
          this.brand = response.data;
        });
      }else{
        this.brand = Object.assign({},defaultBrand);
      }
    },
    methods: {
      onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$confirm('Confirm to submit', 'Tip', {
              confirmButtonText: 'Confirm',
              cancelButtonText: 'Cancel',
              type: 'warning'
            }).then(() => {
              if (this.isEdit) {
                updateBrand(this.$route.query.id, this.brand).then(response => {
                  this.$refs[formName].resetFields();
                  this.$message({
                    message: 'Update success',
                    type: 'success',
                    duration:1000
                  });
                  this.$router.back();
                });
              } else {
                createBrand(this.brand).then(response => {
                  this.$refs[formName].resetFields();
                  this.brand = Object.assign({},defaultBrand);
                  this.$message({
                    message: 'Submit Success',
                    type: 'success',
                    duration:1000
                  });
                });
              }
            });

          } else {
            this.$message({
              message: 'Validation failed',
              type: 'error',
              duration:1000
            });
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
        this.brand = Object.assign({},defaultBrand);
      }
    }
  }
</script>
<style>
</style>


