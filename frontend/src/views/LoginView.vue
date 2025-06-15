<template>
  <div class="login-container">
    <!-- 徽章图片 -->
    <img src="../assets/hit.jpg" alt="徽章" class="badge" />
    <h2>食堂服务系统</h2>
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
import axios from "axios";

const username = ref('')
const password = ref('')
const router = useRouter()

const handleLogin = () => {
  if (username.value === 'admin' && password.value === '123456') {
    localStorage.setItem('auth', 'true')
    sessionStorage.setItem('username', username.value);
    router.replace('/home')
  } else {
    alert('用户名或密码错误')
  }
  axios.get(`http://localhost:8080/user/get?username=${username.value}`)
      .then((res) => {
        if (res.data.code === 200 && res.data.user.password === password.value) {
          localStorage.setItem('auth', 'true')
          sessionStorage.setItem("username", username.value)
          if (res.data.user.admin) {
            router.replace('/admin')
          } else {
            router.replace('/home')
          }
        } else {
          alert("用户名或密码错误！")
        }
      })
      .catch((err) => {
        console.log(err)
        alert("用户不存在！")
      })
}
</script>

<style scoped>
.login-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 400px;
  margin: 100px auto;
  padding: 60px;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  background: white;
  text-align: center;
}

/* 徽章图片样式，保持正方形，宽高均为80px，可以根据需要调整 */
.badge {
  width: 90px;
  height: 90px;
  object-fit: contain; /* 保持图片比例，内容缩放 */
  margin-bottom: 0;
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
  width: auto;
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
