<template>
  <div class="private-room">
    <button class="back-button" @click="goToHome">返回主页</button>
    <h1>包间预约</h1>
    <form @submit.prevent="submitReservation">
      <div class="form-group">
        <label>姓名：</label>
        <input v-model="form.username" type="text" disabled />
      </div>
      <div class="form-group">
        <label>电话：</label>
        <input v-model="form.phone" type="tel" disabled />
      </div>
      <div class="form-group">
        <label>预约时间：</label>
        <el-form :model="form" label-width="100px">
          <!-- 预约时间选择 -->
          <div class="form-group">
            <el-date-picker
                v-model="form.date"
                type="datetime"
                :disabled-date="disabledDate"
                :disabled-hours="disabledHours"
                :disabled-minutes="disabledMinutes"
                :format="'YYYY-MM-DD HH:mm'"
                :value-format="'YYYY-MM-DDTHH:mm:00'"
                placeholder="选择预约时间"
                :clearable="false"
                style="width: 100%;height: 45px;"
            />
            <small style="color: gray;">仅限 08:00 到 21:00 的整点</small>
          </div>
        </el-form>
      </div>
      <div class="form-group">
        <label>包间类型：</label>
        <select v-model="form.type" required>
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
        username: sessionStorage.getItem('username'),
        phone: '',
        date: '',
        type: 'small'
      },
      successMessage: ''
    };
  },
  created() {
    axios.get(`http://localhost:8080/user/get?username=${this.form.username}`)
        .then((res) => {
          if(res.data.code === 200) {
            this.form.phone = res.data.user.phone;
          }
        })
        .catch(console.error)
  },
  methods: {
    // 新增的返回主页方法
    goToHome() {
      this.$router.replace({ name: 'home' })
    },
    async submitReservation() {
      axios.post(`http://localhost:8080/private-room/add?username=${this.form.username}&phone=${this.form.phone}&date=${this.form.date}&type=${this.form.type}`)
          .then((res) => {
            if(res.data.code === 200) {
              this.successMessage = '预约提交成功！我们将尽快与您联系。';
              this.form.date = '';
              this.form.type = 'small';
            }
          })
          .catch((err) => {
            console.log(err);
            alert(err.response?.data?.message || '提交失败，请稍后重试');
          });
    },
    disabledDate(time) {
      // 不允许选择今天之前的日期
      return time.getTime() < new Date().setHours(0, 0, 0, 0);
    },
    disabledHours() {
      // 允许的小时：8 到 21
      const allowed = [];
      for (let i = 8; i <= 21; i++) {
        allowed.push(i);
      }
      return Array.from({ length: 24 }, (_, i) => i).filter(h => !allowed.includes(h));
    },
    disabledMinutes() {
      // 所有小时只能选00分（整点）
      return Array.from({ length: 60 }, (_, i) => i).filter(m => m !== 0);
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
