<template>
  <div class="review-container">
    <!-- 返回按钮 -->
    <div class="top-bar">
      <button class="back-button" @click="goToOverview">返回总览</button>
    </div>

    <!-- 标题 -->
    <h1 class="title">菜品详情</h1>

    <!-- 可编辑菜品详情 -->
    <div class="dish-card">
      <!-- 添加隐藏的文件上传 input -->
      <input type="file" ref="fileInput" style="display: none" @change="onFileSelected" accept="image/*" />
      <!-- 修改图片绑定和点击事件 -->
      <img :src="previewImage" alt="dish image" class="dish-image" @click="triggerFileInput" />
      <div class="dish-info">
        <input v-model="dish.dish_name" class="input-box dish-name-input" />
        <textarea v-model="dish.description" placeholder="描述" class="input-box textarea"></textarea>
      </div>
    </div>

    <!-- 评论列表 -->
    <div class="comment-list">
      <div v-for="(comment, index) in comments" :key="index" class="comment-item">
        <div class="comment-content">
          <span class="comment-user">{{ comment.username === username ? '你' : comment.username }}：</span>
          <span class="comment-text">{{ comment.content }}</span>
        </div>
        <button class="delete-comment" @click="deleteComment(index)">删除</button>
      </div>
    </div>

    <!-- 保存按钮 -->
    <button class="save-button" @click="saveDish">保存修改</button>
    <button class="delete-button" @click="deleteDish">删除菜品</button>
  </div>
</template>


<script>
import axios from "axios";

export default {
  name: 'DishManage',
  data() {
    return {
      dishID: this.$route.params.id,
      dish: {
        dish_id: 1,
        dish_name: '红烧肉',
        image: 'hongshaorou',
        description: '红烧肉是一道传统名菜，色泽红亮，入口即化，肥而不腻，深受食客喜爱。'
      },
      selectedImageFile: null,
      username: sessionStorage.getItem('username') || '小王',
      comments: [
        { username: '小张', content: '味道太赞了，肉入口即化，五星推荐！' },
        { username: '小李', content: '有点油，但总体还不错，搭配米饭正合适。' },
        { username: '小王', content: '喜欢这种甜咸口味，就是分量再多点就好了~' },
        { username: '张三', content: '我觉得偏甜，不太适合我口味。' }
      ],
      deletedComments: [],
      newComment: ''
    };
  },
  computed: {
    previewImage() {
      // 优先显示本地预览图，否则使用 dish.image 字段拼接路径
      if (this.selectedImageFile) {
        return URL.createObjectURL(this.selectedImageFile);
      }
      return `/images/${this.dish.image}.jpg`;
    }
  },
  created() {
    if (this.dishID !== '0') {
      axios.get(`http://localhost:8080/dish/getById?dish_id=${this.dishID}`)
          .then((res) => {
            if(res.data.code === 200) {
              this.dish = res.data.dish;
            }
          })
          .catch(console.error);
      axios.get(`http://localhost:8080/comment/getById?dish_id=${this.dishID}`)
          .then((res) => {
            if(res.data.code === 200) {
              this.comments = res.data.comment;
            }
          })
          .catch(console.error);
    }
  },
  methods: {
    triggerFileInput() {
      this.$refs.fileInput.click();
    },
    onFileSelected(event) {
      const file = event.target.files[0];
      if (file) {
        this.selectedImageFile = file;
        // 更新 dish.image 为新图片名（可改为你想要的规则）
        this.dish.image = file.name;
      }
    },
    deleteComment(index) {
      const deleted = this.comments.splice(index, 1)[0];
      this.deletedComments.push(deleted);  // 记录被删的评论
    },
    saveDish() {
      if (!confirm("确认要保存菜品信息和评论修改吗？")) {
        return;
      }

      // 1. 上传图片（如果选择了新图片）
      const uploadImage = () => {
        if (!this.selectedImageFile) return Promise.resolve();

        const formData = new FormData();
        formData.append('file', this.selectedImageFile);

        return axios.post("http://localhost:8080/image/upload", formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });
      };

      // 2. 上传图片后再更新菜品信息
      uploadImage()
          .then(() => {
            if (this.dishID === '0') {
              return axios.post(`http://localhost:8080/dish/add?dish_name=${this.dish.dish_name}&description=${this.dish.description}`);
            } else {
              return axios.post(`http://localhost:8080/dish/modify?dish_id=${this.dishID}&dish_name=${this.dish.dish_name}&description=${this.dish.description}`);
            }
          })
          .then(res => {
            if (res.data.code === 200) {
              if (this.dishID === '0') {
                axios.post('http://localhost:8080/overview/update?item=dish&value=1');
              }
              alert("菜品信息已更新！");
            }
          })
          .catch(console.error);

      // 3. 删除评论
      this.deletedComments.forEach(comment => {
        axios.post(`http://localhost:8080/comment/delete?comment_id=${comment.comment_id}`).catch(console.error);
      });
      this.deletedComments = [];

      // this.$router.replace({ name: 'overview' });
    },
    deleteDish() {
      if (!confirm("确定要删除该菜品吗？此操作无法撤销！")) {
        return;
      }

      axios.post(`http://localhost:8080/dish/delete?dish_id=${this.dishID}`)
          .then(res => {
            if (res.data.code === 200) {
              alert("菜品已删除！");
              axios.post('http://localhost:8080/overview/update?item=dish&value=-1');
              this.$router.replace({ name: 'overview' });
            } else {
              alert("删除失败：" + res.data.msg);
            }
          })
          .catch(error => {
            console.error(error);
            alert("删除过程发生错误！");
          });
    },
    goToOverview() {
      this.$router.replace({ name: 'overview' });
    }
  }
};
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
  flex: 1;
  display: flex;
  text-align: left;
  flex-direction: column;
}

.dish-name-input {
  font-size: 22px;
  font-weight: bold;
  color: #333;
  border: 1px solid #ccc;
  margin-bottom: 10px;
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

.input-box {
  display: flex;
  padding: 15px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 6px;
}

.textarea {
  width: 100%;
  box-sizing: border-box;
  resize: vertical;
  min-height: 60px;
}

.delete-comment {
  margin-left: auto;
  background: #ff5c5c;
  color: white;
  border: none;
  padding: 4px 8px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 12px;
  height: 30px;
}

.save-button {
  background: #28a745;
  color: white;
  padding: 10px 25px;
  font-size: 16px;
  margin-top: 20px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: all 0.2s ease;
}

.save-button:hover {
  background: #218838;
}

.delete-button {
  background: #dc3545;
  color: white;
  padding: 10px 25px;
  font-size: 16px;
  margin-left: 10px;
  margin-top: 20px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: all 0.2s ease;
}

.delete-button:hover {
  background: #c82333;
}
</style>
