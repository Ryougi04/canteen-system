<template>
  <div class="canteen-detail-container">
    <button class="back-button" @click="$router.go(-1)">
      <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
        <line x1="19" y1="12" x2="5" y2="12"></line>
        <polyline points="12 19 5 12 12 5"></polyline>
      </svg>
      返回
    </button>
    
    <div class="detail-header">
      <h1>{{ currentCanteen.name }}</h1>
      <div class="opening-hours">
        <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
          <circle cx="12" cy="12" r="10"></circle>
          <polyline points="12 6 12 12 16 14"></polyline>
        </svg>
        <span>营业时间: {{ currentCanteen.hours }}</span>
      </div>
    </div>
    
    <div class="detail-content">
      <!-- 这里可以添加食堂的详细信息 -->
      <p>这里是{{ currentCanteen.name }}的详细介绍...</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CanteenDetailView',
  props: {
    id: {
      type: String,
      required: true
    }
  },
  computed: {
    currentCanteen() {
      const canteens = {
        xueyuan: {
          name: '学苑楼食堂',
          hours: '6:30-21:00',
          description: '学苑楼食堂是学校最大的食堂，提供各种风味的菜品...'
        },
        xueshi: {
          name: '学士楼食堂',
          hours: '6:00-20:30',
          description: '学士楼食堂以健康营养为特色，提供多种套餐选择...'
        },
        xuezi: {
          name: '学子楼食堂',
          hours: '6:30-22:00',
          description: '学子楼食堂营业时间最长，夜宵选择丰富...'
        }
      }
      return canteens[this.id] || {}
    }
  }
}
</script>

<style scoped>
.canteen-detail-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.back-button {
  display: flex;
  align-items: center;
  gap: 5px;
  background: none;
  border: none;
  color: #4a90e2;
  font-size: 16px;
  cursor: pointer;
  padding: 10px 0;
  margin-bottom: 20px;
}

.back-button:hover {
  text-decoration: underline;
}

.detail-header {
  margin-bottom: 30px;
  padding-bottom: 15px;
  border-bottom: 1px solid #eee;
}

.detail-header h1 {
  color: #333;
  margin-bottom: 10px;
}

.opening-hours {
  display: flex;
  align-items: center;
  gap: 8px;
  color: #666;
  font-size: 16px;
}

.detail-content {
  line-height: 1.6;
  color: #444;
}
</style>