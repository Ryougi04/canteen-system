<template>
  <div class="chat-container">
    <h1 class="header-with-button">
      你的专属AI营养助手
      <button class="back-button" @click="goToHome">返回主页</button>
    </h1>

    <!-- 显示对话内容 -->
    <div class="chat-box">
      <div v-for="(message, index) in messages" :key="index" class="message">
        <div :class="{'user-message': message.sender === 'user', 'model-message': message.sender === 'model'}">
          <strong>{{ message.sender === 'user' ? '你' : '大模型' }}:</strong> {{ message.text }}
        </div>
      </div>
    </div>

    <!-- 加载提示 -->
    <div v-if="loading" class="loading">正在加载...</div>

    <!-- 输入框和发送按钮 -->
    <div class="input-section">
      <input v-model="userInput" type="text" placeholder="请输入你的问题..." @keyup.enter="sendMessage" />
      <button @click="sendMessage">发送</button>
    </div>

  </div>
</template>


<script>
import axios from 'axios';

export default {
  name: 'NutritionAdvice',
  data() {
    return {
      userInput: '', // 用户输入的问题
      messages: [],  // 存储对话内容
      loading: false  // 加载状态
    };
  },
  methods: {
    // 新增的返回主页方法
    goToHome() {
      this.$router.push({ name: 'home' })
    },

    // 发送用户输入的消息
    sendMessage() {
      if (this.userInput.trim() === '') return;

      // 添加用户消息到对话框
      this.messages.push({ sender: 'user', text: this.userInput });

      const question = this.userInput;
      this.userInput = ''; // 清空输入框
      this.loading = true;

      // 滚动到底部
      this.$nextTick(() => this.scrollToBottom());

      // 向大模型发送请求
      axios.get(`https://big-model-api.com/ask?question=${encodeURIComponent(question)}`)
          .then(response => {
            this.messages.push({
              sender: 'model',
              text: response.data.answer || '抱歉，我无法回答这个问题。'
            });
            this.loading = false;

            // 滚动到底部
            this.$nextTick(() => this.scrollToBottom());
          })
          .catch(error => {
            this.messages.push({
              sender: 'model',
              text: '获取答案失败，请稍后再试。'
            });
            this.loading = false;

            // 滚动到底部
            this.$nextTick(() => this.scrollToBottom());
          });
    },

    // 滚动聊天框到底部
    scrollToBottom() {
      const chatBox = this.$el.querySelector('.chat-box');
      if (chatBox) {
        chatBox.scrollTop = chatBox.scrollHeight;
      }
    }
  }
};
</script>


<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  height: 94vh; /* 设置容器高度为100vh，充满整个视口 */
}

h1 {
  font-size: 24px;
  color: #4a90e2;
  margin-bottom: 20px;
}

.chat-box {
  flex-grow: 1; /* chat-box 填充剩余空间 */
  max-height: 70vh; /* 可选：设置最大高度避免超出视口 */
  overflow-y: auto;
  margin-bottom: 20px;
  background-color: #fff;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ddd;
}

.message {
  margin: 10px 0;
}

.user-message {
  text-align: left;
  color: #333;
}

.model-message {
  text-align: right;
  color: #4a90e2;
}

.input-section {
  display: flex;
  justify-content: center;
  gap: 10px;
  margin-top: 10px;
}

input {
  width: 80%;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 5px;
}

button {
  padding: 10px 20px;
  font-size: 16px;
  background-color: #4a90e2;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #3578b8;
}

.loading {
  font-size: 16px;
  color: #888;
  margin-top: 20px;
}

.header-with-button {
  display: flex;
  justify-content: space-between; /* 左右对齐 */
  align-items: center;
  font-size: 24px;
  color: #4a90e2;
  margin-bottom: 20px;
}

.back-button {
  align-self: flex-end;
  top: 10px;
  left: 10px;
  padding: 6px 10px;
  font-size: 12px;
}
</style>
