<template>
  <div class="private-room">
    <button class="back-button" @click="goToHome">返回主页</button>
    <h1>包间预约</h1>
    <form @submit.prevent="submitReservation">
      <div class="form-group">
        <label>姓名：</label>
        <input v-model="form.name" type="text" required />
      </div>
      <div class="form-group">
        <label>电话：</label>
        <input v-model="form.phone" type="tel" required />
      </div>
      <div class="form-group">
        <label>预约时间：</label>
        <input v-model="form.date" type="datetime-local" required />
      </div>
      <div class="form-group">
        <label>包间类型：</label>
        <select v-model="form.roomType" required>
          <option value="small">小包间（1-4人）</option>
          <option value="medium">中包间（5-8人）</option>
          <option value="large">大包间（9-12人）</option>
        </select>
      </div>
      <button type="submit">提交预约</button>
    </form>
    <p v-if="successMessage" class="success">{{ successMessage }}</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'PrivateRoomView',
  data() {
    return {
      form: {
        name: '',
        phone: '',
        date: '',
        roomType: 'small'
      },
      successMessage: ''
    };
  },
  methods: {
    // 新增的返回主页方法
    goToHome() {
      this.$router.push({ name: 'home' })
    },
    async submitReservation() {
      axios.post(`http://localhost:8080/private-room/add?name=${this.form.name}&phone=${this.form.phone}&date=${this.form.date}&roomType=${this.form.roomType}`)
          .then((res) => {
            if(res.data.code === 200) {
              // ElMessage.success(res.data.msg);
              this.successMessage = '预约提交成功！我们将尽快与您联系。';
              this.form = { name: '', phone: '', date: '', roomType: 'small' };
            }
          })
          .catch((err) => {
            console.log(err);
            alert(err.response?.data?.message || '提交失败，请稍后重试');
          });
    }
  }
}
</script>

<style scoped>
.private-room {
  display: flex;
  flex-direction: column; /* 垂直排列 */
  align-items: center; /* 水平居中所有子元素 */
  max-width: 600px;
  min-height: 94vh;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  color: #333333;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.back-button {
  width: auto;
  align-self: flex-start;
  background: #f0f7ff;
  color: #4a90e2;
  top: 10px;
  left: 10px;
  padding: 6px 10px;
  font-size: 12px;
}
.back-button:hover {
  background: #e0efff;
  transform: translateY(-2px);
}
.back-button svg {
  width: 16px;
  height: 16px;
}

h1 {
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 20px;
  text-align: left;
}

.form-group label {
  font-size: 16px;
  display: block;
  margin-bottom: 8px;
}

.form-group input,
.form-group select {
  width: 100%;
  padding: 12px;
  font-size: 14px;
  border-radius: 5px;
  border: 1px solid #ddd;
}

button {
  padding: 12px 20px;
  font-size: 16px;
  background-color: #4a90e2;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width: 100%;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #3578b8;
}

.success {
  color: green;
  margin-top: 15px;
  text-align: center;
}
</style>
