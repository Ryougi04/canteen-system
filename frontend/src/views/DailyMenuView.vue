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
                <button
                    class="book-button"
                    :class="{ booked: isBooked(item.dish_id) }"
                    @click="toggleBooking(item)"
                >
                  {{ isBooked(item.dish_id) ? '取消预订' : '预订' }}
                </button>
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
      dishBookings: {},
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
      dishes: [
        {
          dish_id: 1,
          dish_name: '豆浆油条',
          price: 5.0,
          category: ['早餐', '午餐'],
          canteen_id: ['1', '2'],
          floor: [1, 2],
          rating: 4.2,
          available_flag: '11' // 两天都供应
        },
        {
          dish_id: 2,
          dish_name: '皮蛋瘦肉粥',
          price: 4.5,
          category: ['早餐'],
          canteen_id: ['2'],
          floor: [2],
          rating: 4.0,
          available_flag: '10' // 只在第一天供应
        },
        {
          dish_id: 3,
          dish_name: '宫保鸡丁',
          price: 12.0,
          category: ['午餐'],
          canteen_id: ['1'],
          floor: [2],
          rating: 4.5,
          available_flag: '01' // 只在第二天供应
        },
        {
          dish_id: 4,
          dish_name: '红烧排骨',
          price: 15.0,
          category: ['午餐'],
          canteen_id: ['3'],
          floor: [1],
          rating: 4.3,
          available_flag: '11'
        },
        {
          dish_id: 5,
          dish_name: '麻辣香锅',
          price: 18.0,
          category: ['晚餐'],
          canteen_id: ['2'],
          floor: [3],
          rating: 4.7,
          available_flag: '10'
        },
        {
          dish_id: 6,
          dish_name: '鱼香肉丝',
          price: 11.0,
          category: ['晚餐'],
          canteen_id: ['1'],
          floor: [3],
          rating: 4.4,
          available_flag: '01'
        },
        {
          dish_id: 7,
          dish_name: '酸辣粉',
          price: 7.5,
          category: ['夜宵'],
          canteen_id: ['3'],
          floor: [2],
          rating: 4.1,
          available_flag: '11'
        },
        {
          dish_id: 8,
          dish_name: '炒米粉',
          price: 6.0,
          category: ['夜宵'],
          canteen_id: ['2'],
          floor: [1],
          rating: 4.0,
          available_flag: '00' // 两天都不供应
        }
      ],
      menuItems: [],
      menuCategories: ['早餐', '午餐', '晚餐', '夜宵']
    }
  },
  mounted() {
    // axios.get("http://localhost:8080/dish/getAll")
    //     .then((res) => {
    //       if(res.data.code === 200) {
    //         this.dishes = res.data.dish
    //         // sessionStorage.setItem("dishNum", this.dishes.length)
    //         this.fetchMenu()
    //       }
    //     })
    //     .catch(console.error)
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
      this.$router.replace({ name: 'home' })
    },
    formatDate(dateStr) {
      const date = new Date(dateStr)
      return `${date.getMonth() + 1}月${date.getDate()}日`
    },
    generateDates() {
      const dates = []
      const today = new Date()
      for (let i = 0; i < 2; i++) {
        const date = new Date(today)
        date.setDate(today.getDate() + i)
        dates.push(date.toISOString().split('T')[0])
      }
      return dates
    },
    fetchMenu() {
      const dateIndex = this.availableDates.findIndex(date => date === this.selectedDate)
      if (dateIndex === -1) return

      const flagIndex = dateIndex  // 0 or 1

      this.menuItems = (this.dishes || []).filter(item =>
          item.canteen_id.includes(this.selectedCanteen) &&
          item.floor.includes(this.selectedFloor) &&
          item.available_flag &&
          item.available_flag[flagIndex] === '1'
      )

      this.menuItems.forEach(item => {
        item.rating = this.dishRatings[item.dish_id] || 0
      })
    },
    rateDish(dishId, rating) {
      const flag = dishId in this.dishRatings;
      const dish = this.menuItems.find(item => item.dish_id === dishId)
      dish.rating = rating
      axios.post(`http://localhost:8080/dish/updateRating?dish_id=${dishId}&rating=${rating}&flag=${!flag}&last_rating=${flag ? this.dishRatings[dishId] : 0.0}`)
          .then((res) => {
            if(res.data.code === 200) {
              console.log(res.data.msg)
            }
          })
          .catch(console.error)
      this.dishRatings[dishId] = rating
    },
    isBooked(dishId) {
      return this.dishBookings[dishId] === true
    },
    toggleBooking(dish) {
      const id = dish.dish_id
      const currentlyBooked = this.dishBookings[id] === true

      this.dishBookings[id] = !currentlyBooked

      axios.post(`http://localhost:8080/dish/updateBooking?dish_id=${id}&change=${currentlyBooked ? -1 : 1}`)
          .then((res) => {
            if(res.data.code === 200) {
              console.log(res.data.msg)
            }
          })
          .catch(console.error)

      if (currentlyBooked) {
        alert(`已取消 ${dish.dish_name} 的预订`)
      } else {
        alert(`你已预订 ${dish.dish_name}`)
      }
    },
    getItemsByCategory(category) {
      return this.menuItems.filter(item => item.category && item.category.includes(category))
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
  grid-template-columns: repeat(auto-fit, minmax(400px, 1fr));
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

.book-button {
  margin-left: 10px;
  padding: 4px 10px;
  background-color: #4CAF50;
  border: none;
  border-radius: 4px;
  color: white;
  font-size: 12px;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.book-button.booked {
  background-color: #f44336; /* 红色表示已预订，可取消 */
}
</style>
