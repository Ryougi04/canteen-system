<template>
  <div class="ranking-container">
    <div class="top-bar">
      <button class="back-button" @click="goToHome">返回主页</button>
    </div>
    <h1 class="title">菜品排行</h1>
    <div class="dish-list">
      <div v-for="(dish, index) in dishes" :key="dish.id" class="dish-card">
        <span class="rank-number">#{{ index + 1 }}</span>
        <img :src="dish.image" alt="dish image" class="dish-image" />
        <div class="dish-info">
          <h2 class="dish-name">{{ dish.name }}</h2>
          <p class="dish-rating">评分：{{ dish.rating }} / 5</p>
          <p class="dish-likes">👍 {{ dish.likes }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'DishRanking',
  data() {
    return {
      dishes: [
        {
          id: 1,
          name: '红烧肉',
          image: 'src/assets/hongshaorou.jpg',
          rating: 4.8,
          likes: 120,
        },
        {
          id: 2,
          name: '宫保鸡丁',
          image: 'src/assets/yuxiangrousi.jpg',
          rating: 4.7,
          likes: 110,
        },
        {
          id: 3,
          name: '鱼香肉丝',
          image: 'src/assets/yuxiangrousi.jpg',
          rating: 4.6,
          likes: 98,
        },
        {
          id: 4,
          name: '酸菜鱼',
          image: 'src/assets/suancaiyu.jpg',
          rating: 4.3,
          likes: 93,
        },
        {
          id: 5,
          name: '油炸梅朗潇',
          image: 'src/assets/suancaiyu.jpg',
          rating: 4.9,
          likes: 100,
        },
        {
          id: 6,
          name: '油炸梅朗潇',
          image: 'src/assets/suancaiyu.jpg',
          rating: 4.9,
          likes: 100,
        },
      ],
    };
  },
  methods: {
    goToHome() {
      this.$router.push({ name: 'home' });
    },
  },
};
</script>


<style scoped>
.ranking-container {
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
  width: auto;
  align-self: flex-start;
  background: #f0f7ff;
  color: #4a90e2;
  padding: 6px 10px;
  font-size: 12px;
  border: none;
  outline: none;
  border-radius: 5px;
  cursor: pointer;
  margin-left: auto;
  margin-bottom: 10px;
  transition: all 0.2s ease;
}
.back-button:hover {
  background: #e0efff;
  transform: translateY(-2px);
}

.title {
  font-size: 28px;
  color: #333;
  margin-bottom: 30px;
}

.dish-list {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
}

.dish-card {
  position: relative;
  width: 300px;
  background: #f9f9f9;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 15px;
  text-align: left;
  transition: transform 0.2s ease;
}

.dish-card:hover {
  transform: translateY(-5px);
}

.rank-number {
  position: absolute;
  top: 10px;
  left: 10px;
  background-color: #4a90e2;
  color: white;
  padding: 2px 8px;
  border-radius: 8px;
  font-weight: bold;
  font-size: 14px;
}

.dish-image {
  width: 100%;
  height: 150px;
  object-fit: cover;
  border-radius: 8px;
}

.dish-info {
  margin-top: 10px;
}

.dish-name {
  font-size: 18px;
  font-weight: bold;
  color: #333;
  margin: 5px 0;
}

.dish-rating,
.dish-likes {
  font-size: 14px;
  color: #666;
}
</style>
