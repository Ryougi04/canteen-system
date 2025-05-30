<template>
  <div class="daily-menu-container">
    <button class="back-button" @click="goToHome">
      <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
        <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
        <polyline points="9 22 9 12 15 12 15 22"></polyline>
      </svg>
      返回主页
    </button>

    <h1 class="title">每日菜单</h1>
    
    <div class="filter-section">
      <div class="filter-item">
        <label for="date">日期：</label>
        <select id="date" v-model="selectedDate" @change="fetchMenu">
          <option v-for="date in availableDates" :key="date" :value="date">
            {{ formatDate(date) }}
          </option>
        </select>
      </div>
      
      <div class="filter-item">
        <label for="canteen">食堂：</label>
        <select id="canteen" v-model="selectedCanteen" @change="fetchMenu">
          <option v-for="canteen in canteens" :key="canteen.id" :value="canteen.id">
            {{ canteen.name }}
          </option>
        </select>
      </div>
      
      <div class="filter-item">
        <label for="floor">楼层：</label>
        <select id="floor" v-model="selectedFloor" @change="fetchMenu">
          <option v-for="floor in floors" :key="floor" :value="floor">
            {{ floor }}层
          </option>
        </select>
      </div>
    </div>
    
    <div class="menu-content" v-if="menuItems.length > 0">
      <h2 class="menu-title">{{ selectedCanteenName }} {{ selectedFloor }}层 菜单</h2>
      <p class="menu-date">{{ formatDate(selectedDate) }}</p>
      
      <div class="menu-categories">
        <div class="menu-category" v-for="category in menuCategories" :key="category">
          <h3>{{ category }}</h3>
          <ul>
            <li v-for="item in getItemsByCategory(category)" :key="item.dish_id" class="dish-item">
              <span class="dish-name">{{ item.dish_name }}</span>
              <span class="dish-price">{{ item.price }}元</span>
              <div class="rating">
                <span
                  v-for="star in 5"
                  :key="star"
                  class="star"
                  :class="{ filled: item.rating >= star }"
                  @click="rateDish(item.dish_id, parseFloat(star))"
                >★</span>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
    
    <div class="no-menu" v-else>
      <p>暂无菜单数据</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'DailyMenuView',
  data() {
    return {
      dishRatings:{},
      selectedDate: new Date().toISOString().split('T')[0],
      selectedCanteen: '1',
      selectedFloor: 1,
      availableDates: this.generateDates(),
      canteens: [
        { id: '1', name: '学苑楼食堂' },
        { id: '2', name: '学士楼食堂' },
        { id: '3', name: '学子楼食堂' }
      ],
      floors: [1, 2, 3],
      dishes: [],
      menuItems: [],
      menuCategories: ['早餐', '午餐', '晚餐', '夜宵']
    }
  },
  mounted() {
    axios.get("http://localhost:8080/dish/getAll")
        .then((res) => {
          if(res.data.code === 200) {
            this.dishes = res.data.dish
            // sessionStorage.setItem("dishNum", this.dishes.length)
            this.fetchMenu()
          }
        })
        .catch(console.error)
  },
  computed: {
    selectedCanteenName() {
      const canteen = this.canteens.find(c => c.id === this.selectedCanteen)
      return canteen ? canteen.name : ''
    }
  },
  created() {
    this.fetchMenu()
  },
  methods: {
    goToHome() {
      this.$router.push({ name: 'home' })
    },
    formatDate(dateStr) {
      const date = new Date(dateStr)
      return `${date.getMonth() + 1}月${date.getDate()}日`
    },
    generateDates() {
      const dates = []
      const today = new Date()
      for (let i = 0; i < 7; i++) {
        const date = new Date(today)
        date.setDate(today.getDate() + i)
        dates.push(date.toISOString().split('T')[0])
      }
      return dates
    },
    fetchMenu() {
      this.menuItems = (this.dishes || []).filter(
          item => item.canteen_id === this.selectedCanteen && item.floor === this.selectedFloor
      )

      this.menuItems.forEach(item => {
        item.rating = this.dishRatings[item.id] || 0
      })
    },
    rateDish(dishId, rating) {
      const flag = dishId in this.dishRatings;
      const dish = this.menuItems.find(item => item.dish_id === dishId)
      dish.rating = rating
      axios.post(`http://localhost:8080/dish/update?dish_id=${dishId}&rating=${rating}&flag=${!flag}&last_rating=${flag ? this.dishRatings[dishId] : 0.0}`)
          .then((res) => {
            if(res.data.code === 200) {
              console.log(res.data)
            }
          })
          .catch(console.error)
      this.dishRatings[dishId] = rating
    },
    getItemsByCategory(category) {
      return this.menuItems.filter(item => item.category === category)
    }
  }
}
</script>

<style scoped>
.daily-menu-container {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
  position: relative;
  color: #000; /* ✅ 默认全局文字为黑色 */
}

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

.title {
  text-align: center;
  margin: 20px 0 30px;
  font-size: 28px;
}

.filter-section {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-bottom: 30px;
  flex-wrap: wrap;
}

.filter-item {
  display: flex;
  align-items: center;
  gap: 10px;
}

.filter-item label {
  font-weight: 500;
  color: #000; /* ✅ 黑色 */
}

.filter-item select {
  padding: 8px 12px;
  border-radius: 6px;
  border: 1px solid #ddd;
  min-width: 120px;
  color: #000; /* ✅ 黑色文字 */
}

.menu-content {
  background: white;
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.menu-title {
  color: #000; /* ✅ 黑色 */
  margin-top: 0;
  border-bottom: 1px solid #eee;
  padding-bottom: 10px;
}

.menu-date {
  color: #000; /* ✅ 黑色 */
  margin-top: -10px;
  margin-bottom: 20px;
}

.menu-categories {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
}

.menu-category {
  background: #f9f9f9;
  border-radius: 8px;
  padding: 15px;
}

.menu-category h3 {
  margin-top: 0;
  color: #000; /* ✅ 黑色 */
  border-bottom: 1px solid #ddd;
  padding-bottom: 8px;
}

.menu-category ul {
  list-style: none;
  padding: 0;
  margin: 0;
}
.dish-item {
  display: flex;
  align-items: center;
  gap: 10px;
  margin: 6px 0;
}

.rating {
  display: inline-block;
  cursor: pointer;
}

.star {
  font-size: 18px;
  color: #ccc;
  margin-left: 2px;
  transition: color 0.2s;
}

.star.filled {
  color: gold;
}
.menu-category li {
  display: flex;
  justify-content: space-between;
  padding: 8px 0;
  border-bottom: 1px dashed #0f0909;
}

.dish-name {
  font-weight: 500;
  color: #000; /* ✅ 黑色 */
}

.dish-price {
  color: #000; /* ✅ 黑色 */
}

.no-menu {
  text-align: center;
  padding: 40px;
  color: #666;
}

@media (max-width: 768px) {
  .filter-section {
    flex-direction: column;
    align-items: center;
  }
  

  .menu-categories {
    grid-template-columns: 1fr;
  }
}
</style>