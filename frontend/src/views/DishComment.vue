<template>
  <div class="review-container">
    <!-- 返回按钮 -->
    <div class="top-bar">
      <button class="back-button" @click="goToRanking">返回排行</button>
    </div>

    <!-- 标题 -->
    <h1 class="title">菜品点评</h1>

    <!-- 菜品展示 -->
    <div class="dish-card">
      <img :src="`src/assets/${dish.image}.jpg`" alt="dish" class="dish-image" />
      <div class="dish-info">
        <h2 class="dish-name">{{ dish.dish_name }}</h2>
        <p class="dish-description">{{ dish.description }}</p>
      </div>
    </div>

    <!-- 评论列表 -->
    <div class="comment-list">
      <div v-for="(comment, index) in comments" :key="index" class="comment-item">
        <img :src="comment.avatar || defaultAvatar" alt="用户头像" class="comment-avatar" />
        <div class="comment-content">
          <span class="comment-user">{{ comment.user }}：</span>
          <span class="comment-text">{{ comment.text }}</span>
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
export default {
  name: 'DishReview',
  data() {
    return {
      dish: {
        dish_id: 1,
        dish_name: '红烧肉',
        image: 'hongshaorou',
        description: '色泽红亮，肥而不腻，入口即化的传统美食。'
      },
      defaultAvatar: 'src/assets/default-avatar.png',
      comments: [
        {
          user: '小张',
          text: '这道菜真的很好吃！',
          avatar: 'src/assets/avatar1.jpg'
        },
        {
          user: '小李',
          text: '感觉有点咸，但还可以。',
          avatar: 'src/assets/avatar2.jpg'
        },
        {
          user: '小王',
          text: '超喜欢这个味道！',
          avatar: '' // 没有头像时会显示默认头像
        },
        {
          user: '小张',
          text: '这道菜真的很好吃！',
          avatar: 'src/assets/avatar1.jpg'
        },
        {
          user: '小李',
          text: '感觉有点咸，但还可以。',
          avatar: 'src/assets/avatar2.jpg'
        },
        {
          user: '小王',
          text: '超喜欢这个味道！',
          avatar: '' // 没有头像时会显示默认头像
        }
      ],
      newComment: ''
    };
  },
  methods: {
    submitComment() {
      if (this.newComment.trim()) {
        this.comments.push({ user: '你', text: this.newComment });
        this.newComment = '';
      }
    },
    goToRanking() {
      this.$router.push({ name: 'DishRanking' });
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
