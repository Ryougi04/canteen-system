
<template>
  <div class="login-container">
    <h2>登录食堂服务系统</h2>
    <form @submit.prevent="handleLogin">
      <div class="form-group">
        <label for="username">用户名</label>
        <input id="username" v-model="username" required />
      </div>
      <div class="form-group">
        <label for="password">密码</label>
        <input id="password" v-model="password" type="password" required />
      </div>
      <button type="submit">登录</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const username = ref('')
const password = ref('')
const router = useRouter()

const handleLogin = () => {
  // 简单模拟认证逻辑
  if (username.value === 'admin' && password.value === '123456') {
    localStorage.setItem('auth', 'true') // 记录登录状态
    router.push('/home') // 跳转到主页
  } else {
    alert('用户名或密码错误')
  }
}
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 100px auto;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  background: white;
  text-align: center;
}

h2 {
  margin-bottom: 24px;
  color: #000;
}

.form-group {
  margin-bottom: 16px;
  text-align: left;
}

label {
  display: block;
  margin-bottom: 4px;
  font-weight: bold;
  color: #000;
}

input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 6px;
}

button {
  padding: 10px 20px;
  background: #2563eb;
  color: white;
  border: none;
  border-radius: 6px;
  font-weight: bold;
  cursor: pointer;
}

button:hover {
  background: #1d4ed8;
}
</style>