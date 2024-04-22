<template>
  <div>
    <el-card class="register-form-layout">
      <el-form :model="admin" :rules="rules" ref="admin" label-position="right" label-width="100px">
        <div style="text-align: center">
          <el-image :src="url" :fit="fill" style="width: 350px"></el-image>
        </div>
        <el-form-item label="Account" prop="username">
          <el-input v-model="admin.username">
            <span slot="prefix">
              <svg-icon icon-class="user" class="color-main"></svg-icon>
            </span>
          </el-input>
        </el-form-item>
        <el-form-item label="Name" prop="nickname">
          <el-input v-model="admin.nickName">
            <span slot="prefix">
              <svg-icon icon-class="user" class="color-main"></svg-icon>
            </span>
          </el-input>
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input v-model="admin.email">
            <span slot="prefix">
              <svg-icon icon-class="form" class="color-main"></svg-icon>
            </span>
          </el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input :type="pwdType" v-model="admin.password">
            <span slot="prefix">
              <svg-icon icon-class="password" class="color-main"></svg-icon>
            </span>
            <span slot="suffix" @click="showPwd">
              <svg-icon icon-class="eye" class="color-main"></svg-icon>
            </span>
          </el-input>
        </el-form-item>
        <el-form-item label="ConfirmPwd" prop="confirmPassword">
          <el-input :type="pwdType" v-model="admin.confirmPassword">
            <span slot="prefix">
              <svg-icon icon-class="password" class="color-main"></svg-icon>
            </span>
            <span slot="suffix" @click="showPwd">
              <svg-icon icon-class="eye" class="color-main"></svg-icon>
            </span>
          </el-input>
        </el-form-item>
        <el-form-item class="register-button">
          <el-button type="primary" @click="handleRegister" >Register</el-button>
        </el-form-item>
        <div>
          <el-link type="primary" @click="$router.push({ path: '/login' })">I have an account?</el-link>
        </div>
      </el-form>
    </el-card>
    <img :src="login_center_bg" class="login-center-layout">
  </div>
</template>

<script>

import login_center_bg from '@/assets/images/login_center_bg.png';

const defaultAdmin = {
  id: null,
  username: null,
  password: null,
  confirmPassword: null,
  nickName: null,
  email: null,
  note: null,
  status: 1
};

export default {
  name: 'Register',
  data() {
    const validateConfirmPassword = (rule, value, callback) => {
      if (value !== this.admin.password) {
        callback(new Error('The passwords entered twice do not match!'));
      } else {
        callback();
      }
    };

    return {
      admin: Object.assign({}, defaultAdmin),
      rules: {
        username: [
          {required: true, message: 'Please enter username', trigger: 'blur'},
          {min: 3, max: 20, message: 'Username length from 3 to 20 characters', trigger: 'blur'}
        ],
        password: [
          {required: true, message: 'Please enter password', trigger: 'blur'},
          {min: 6, message: 'Password length must not be less than 6 characters', trigger: 'blur'}
        ],
        confirmPassword: [
          {required: true, message: 'Please enter password again', trigger: 'blur'},
          {validator: validateConfirmPassword, trigger: 'blur'}
        ]
      },
      loading: false,
      pwdType: 'password',
      login_center_bg,
      supportDialogVisible:false,
      url: 'http://localhost:9000/mall/login/goods-galore.png'
    };
  },
  methods: {
    showPwd() {
      if (this.pwdType === 'password') {
        this.pwdType = ''
      } else {
        this.pwdType = 'password'
      }
    },
    handleRegister() {
      this.$refs.admin.validate((valid) => {
        if (valid) {
          // 这里处理注册逻辑
          console.log('注册信息：', this.admin);
          this.loading = true;
          this.$store.dispatch('Register', this.admin).then(() => {
            this.loading = false;
            this.$router.push({path: '/login'})
          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('参数验证不合法！');
          return false;
        }
      });
    }
  }
};
</script>


<style scoped>
.register-form-layout {
  position: absolute;
  left: 0;
  right: 0;
  width: 480px;
  margin: 140px auto;
  border-top: 10px solid #409EFF;
}

.login-title {
  text-align: center;
}

.register-button {
  text-align: center;
  margin-right: 80px;
}

.login-center-layout {
  background: #409EFF;
  width: auto;
  height: auto;
  max-width: 100%;
  max-height: 100%;
  margin-top: 200px;
}
</style>
