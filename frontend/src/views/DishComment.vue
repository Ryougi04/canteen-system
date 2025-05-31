<template>
  <div class="review-container">
    <!-- 返回按钮 -->
    <div class="top-bar">
      <button class="back-button" @click="goToRanking">返回排行</button>
    </div>

    <!-- 标题 -->
    <h1 class="title">菜品点评</h1>

    <!-- 菜品点评 -->
    <div class="dish-card">
      <img :src="`src/assets/${dish.image}.jpg`" alt="dish image" class="dish-image" />
      <div class="dish-info">
        <h2 class="dish-name">{{ dish.dish_name }}</h2>
        <p class="dish-description">{{ dish.description }}</p>
      </div>
    </div>

    <!-- 评论列表 -->
    <div class="comment-list">
      <div v-for="(comment, index) in comments" :key="index" class="comment-item">
<!--        <img :src="comment.avatar || defaultAvatar" alt="user avatar" class="comment-avatar" />-->
        <div class="comment-content">
          <span class="comment-user">{{ comment.username === this.username ? '你' : comment.username }}：</span>
          <span class="comment-text">{{ comment.content }}</span>
        </div>
      </div>
    </div>

    <!-- 评论输入 -->
    <div class="comment-input">
      <input
          v-model="newComment"
          type="text"
          placeholder="写下你的评论..."
          class="input-box"
      />
      <button @click="submitComment" class="submit-button">发送</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'DishReview',
  data() {
    return {
      dishID: this.$route.params.id,
      dish: {},
      username: sessionStorage.getItem('username'),
      // defaultAvatar: 'src/assets/image.jpg',
      comments: [],
      newComment: ''
    };
  },
  mounted() {
    axios.get(`http://localhost:8080/dish/getById?dish_id=${this.dishID}`)
        .then((res) => {
          if(res.data.code === 200) {
            this.dish = res.data.dish;
          }
        })
        .catch(console.error)
    axios.get(`http://localhost:8080/comment/getById?dish_id=${this.dishID}`)
        .then((res) => {
          if(res.data.code === 200) {
            this.comments = res.data.comment
          }
        })
        .catch(console.error)
  },
  methods: {
    submitComment() {
      if (this.newComment.trim()) {
        axios.post(`http://localhost:8080/comment/add?content=${this.newComment}&dish_id=${this.dishID}&username=${this.username}`)
            .then((res) => {
              if(res.data.code === 200) {
                console.log(res.data.msg)
              }
            })
            .catch(console.error)
        this.comments.push({ username: this.username, content: this.newComment })
        this.newComment = ''
      }
    },
    goToRanking() {
      this.$router.push({ name: 'DishRanking' })
    }
  }
}
</script>

<style scoped>
.review-container {
  background: #f9f9f9;
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}

.top-bar {
  width: 100%;
  text-align: left;
  margin-bottom: 10px;
}

.back-button {
  background: #f0f7ff;
  color: #4a90e2;
  padding: 6px 10px;
  font-size: 12px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.2s ease;
}
.back-button:hover {
  background: #e0efff;
  transform: translateY(-2px);
}

.title {
  font-size: 28px;
  color: #333;
  margin-bottom: 20px;
}

.dish-card {
  display: flex;
  align-items: center;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0,0,0,0.1);
  padding: 10px;
  margin-bottom: 20px;
}

.dish-image {
  width: 125px;
  height: 125px;
  border-radius: 8px;
  object-fit: cover;
  margin-right: 20px;
}

.dish-info {
  text-align: left;
}

.dish-name {
  font-size: 22px;
  font-weight: bold;
  color: #333;
}

.dish-description {
  font-size: 14px;
  color: #666;
  margin-top: 10px;
}

.comment-list {
  max-height: 300px;
  overflow-y: auto;
  overflow-x: hidden; /* 禁止横向滚动 */
  padding: 10px;
}

.comment-item {
  display: flex;
  align-items: flex-start;
  margin-bottom: 12px;
  background: #f5f5f5;
  padding: 10px;
  border-radius: 8px;
  width: 100%; /* 确保 item 不超过容器宽度 */
  box-sizing: border-box;
}

.comment-avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  object-fit: cover;
  margin-right: 10px;
}

.comment-content {
  display: flex;
  flex-direction: column;
  word-wrap: break-word;
  overflow-wrap: break-word;
  flex: 1; /* 占满剩余空间 */
}

.comment-user {
  align-self: flex-start;
  font-weight: bold;
  margin-bottom: 4px;
  color: #333;
}

.comment-text {
  align-self: flex-start;
  color: #555;
  word-break: break-word;
  white-space: pre-wrap;
  text-align: left;
}

.comment-input {
  display: flex;
  gap: 10px;
}

.input-box {
  flex: 1;
  padding: 15px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 6px;
}

.submit-button {
  background: #4a90e2;
  color: white;
  padding: 8px 20px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: all 0.2s ease;
}

.submit-button:hover {
  background: #357ab7;
}
</style>
