<template>
  <div>
    <el-card class="register-form-layout">
      <el-form :model="registerForm" :rules="rules" ref="registerForm" label-position="right" label-width="100px">
        <div style="text-align: center">
          <svg-icon icon-class="login-mall" style="width: 56px;height: 56px;color: #409EFF"></svg-icon>
        </div>
        <h2 class="login-title color-main">mall-register-web</h2>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="registerForm.username">
            <span slot="prefix">
              <svg-icon icon-class="user" class="color-main"></svg-icon>
            </span>
          </el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input :type="pwdType" v-model="registerForm.password">
            <span slot="prefix">
              <svg-icon icon-class="password" class="color-main"></svg-icon>
            </span>
            <span slot="suffix" @click="showPwd">
              <svg-icon icon-class="eye" class="color-main"></svg-icon>
            </span>
          </el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input :type="pwdType" v-model="registerForm.confirmPassword">
            <span slot="prefix">
              <svg-icon icon-class="password" class="color-main"></svg-icon>
            </span>
            <span slot="suffix" @click="showPwd">
              <svg-icon icon-class="eye" class="color-main"></svg-icon>
            </span>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleRegister">Register</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <img :src="login_center_bg" class="login-center-layout">
  </div>
</template>

<script>

import login_center_bg from '@/assets/images/login_center_bg.png';

export default {
  name: 'Register',
  data() {
    const validateConfirmPassword = (rule, value, callback) => {
      if (value !== this.registerForm.password) {
        callback(new Error('两次输入的密码不一致!'));
      } else {
        callback();
      }
    };

    return {
      registerForm: {
        username: '',
        password: '',
        confirmPassword: ''
      },
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 20, message: '用户名长度在 3 到 20 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, message: '密码长度不能少于 6 个字符', trigger: 'blur'}
        ],
        confirmPassword: [
          {required: true, message: '请再次输入密码', trigger: 'blur'},
          {validator: validateConfirmPassword, trigger: 'blur'}
        ]
      },
      loading: false,
      pwdType: 'password',
      login_center_bg,
      supportDialogVisible:false
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
      this.$refs.registerForm.validate((valid) => {
        if (valid) {
          // 这里处理注册逻辑
          console.log('注册信息：', this.registerForm);
          this.loading = true;
          this.$store.dispatch('Register', this.registerForm).then(() => {
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
  width: 360px;
  margin: 140px auto;
  border-top: 10px solid #409EFF;
}

.login-title {
  text-align: center;
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
