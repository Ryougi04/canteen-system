<template>
  <div class="canteen-list-container">
    <!-- 新增的返回按钮 -->
    <button class="back-button" @click="goToHome">
      <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
        <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
        <polyline points="9 22 9 12 15 12 15 22"></polyline>
      </svg>
      返回主页
    </button>
    
    <h1 class="title">食堂一览</h1>
    <div class="canteen-grid">
      <CanteenCard 
        v-for="canteen in canteens" 
        :key="canteen.id"
        :canteen="canteen"
        @click="goToDetail(canteen.id)"
      />
    </div>
  </div>
</template>

<script>
import CanteenCard from '../components/CanteenCard.vue'
import xueyuanImg from '../assets/xueyuan.jpg'
import xueshiImg from '../assets/xueshi.jpg'
import xueziImg from '../assets/xuezi.jpg'
export default {
  name: 'CanteenListView',
  components: {
    CanteenCard
  },
  data() {
    return {
      canteens: [
        {
          id: 'xueyuan',
          name: '学苑楼食堂',
          hours: '6:30-19:00',
          image: xueyuanImg
        },
        {
          id: 'xueshi',
          name: '学士楼食堂',
          hours: '6:00-20:30',
          image: xueshiImg
        },
        {
          id: 'xuezi',
          name: '学子楼食堂',
          hours: '10:00-19:00',
          image: xueziImg
        }
      ]
    }
  },
  methods: {
    goToDetail(id) {
      this.$router.push({ name: 'canteen-detail', params: { id } })
    },
    // 新增的返回主页方法
    goToHome() {
      this.$router.replace({ name: 'home' })
    }
  }
}
</script>

<style scoped>
.canteen-list-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  position: relative; /* 为绝对定位的按钮提供参考 */
}

/* 新增的返回按钮样式 */
.back-button {
  position: absolute;
  top: 20px;
  left: 20px;
  display: flex;
  align-items: center;
  gap: 5px;
  background: #f0f7ff;
  border: none;
  border-radius: 6px;
  padding: 8px 12px;
  color: #4a90e2;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.back-button:hover {
  background: #e0efff;
  transform: translateY(-2px);
}

.back-button svg {
  width: 16px;
  height: 16px;
}

.title {
  text-align: center;
  margin-bottom: 30px;
  color: #333;
  font-size: 28px;
}

.canteen-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 30px;
  padding: 20px;
}

@media (max-width: 768px) {
  .canteen-grid {
    grid-template-columns: 1fr;
  }
  
  /* 移动端调整按钮位置 */
  .back-button {
    top: 10px;
    left: 10px;
    padding: 6px 10px;
    font-size: 12px;
  }
}
</style>