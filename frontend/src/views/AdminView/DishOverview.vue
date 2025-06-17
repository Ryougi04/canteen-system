<template>
  <div class="ranking-container">
    <!-- 返回按钮 -->
    <div class="top-bar">
      <button class="back-button" @click="goToHome">返回主页</button>
    </div>

    <!-- 标题 -->
    <h1 class="title">菜品总览</h1>

    <!-- 菜品展示 -->
    <div class="dish-list">
      <!-- 菜品卡片 -->
      <div
          v-for="(dish,index) in dishes"
          :key="dish.dish_id"
          class="dish-card"
          @click="goToManage(dish.dish_id)"
      >
        <img :src='`src/assets/${dish.image}.jpg`' alt="dish image" class="dish-image" />
        <div class="dish-info">
          <h2 class="dish-name">{{ dish.dish_name }}</h2>
          <div class="dish-wish">预订人数：{{ dish.bookings }}</div>
        </div>
      </div>

      <!-- 添加菜品卡片 -->
      <div class="dish-card add-dish-card" @click="goToManage(0)">
        <img :src="`src/assets/add.png`" alt="dish image" class="dish-image" />
        <div class="dish-info">
          <h2 class="dish-name">添加菜品</h2>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'DishOverview',
  data() {
    return {
      dishes: [
        {
          dish_id: 1,
          dish_name: '红烧肉',
          image: 'hongshaorou',
          rating: 4.8,
          rating_num: 120,
          bookings: 80,
        },
        {
          dish_id: 2,
          dish_name: '宫保鸡丁',
          image: 'yuxiangrousi',
          rating: 4.7,
          rating_num: 110,
          bookings: 70,
        },
        {
          dish_id: 3,
          dish_name: '鱼香肉丝',
          image: 'yuxiangrousi',
          rating: 4.6,
          rating_num: 98,
          bookings: 90,
        },
        {
          dish_id: 4,
          dish_name: '酸菜鱼',
          image: 'suancaiyu',
          rating: 4.3,
          rating_num: 93,
          bookings: 85,
        },
        {
          dish_id: 5,
          dish_name: '油炸梅朗潇',
          image: 'suancaiyu',
          rating: 4.9,
          rating_num: 100,
          bookings:125,
        },
      ],
    };
  },
  mounted() {
    axios.get("http://localhost:8080/dish/getAll")
        .then((res) => {
          if(res.data.code === 200) {
            this.dishes = res.data.dish;
          }
        })
        .catch(console.error)
  },
  methods: {
    goToHome() {
      this.$router.replace({ name: 'admin' });
    },
    goToManage(dishID) {
      console.log(dishID);
      this.$router.push({ name: 'manage', params: { id: dishID } })
    }
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
.dish-wish {
  position: absolute;
  bottom: 10px;
  right: 10px;
  font-size: 13px;
  color: #888;
  background-color: rgba(255, 255, 255, 0.7);
  padding: 2px 6px;
  border-radius: 8px;
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
