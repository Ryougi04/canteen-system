<template>
  <div class="dashboard-container">
    <h1 class="dashboard-title">食堂服务系统管理端</h1>

    <div class="card-grid">
      <DashboardCard title="菜品总览" :count="dishCount" @click="goToOverview" style="cursor: pointer;">
        <template #icon>
          <svg width="30" height="30" fill="none" stroke="#4a90e2" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" viewBox="0 0 24 24">
            <path d="M4 4h16v16H4z"></path>
            <circle cx="12" cy="12" r="6"></circle>
          </svg>
        </template>
      </DashboardCard>

      <DashboardCard title="菜单管理" :count="todayMenuCount" @click="goToMenu" style="cursor: pointer;">
        <template #icon>
          <svg width="30" height="30" fill="none" stroke="#4a90e2" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" viewBox="0 0 24 24">
            <path d="M3 12h18"></path>
            <path d="M6 6h12"></path>
            <circle cx="12" cy="17" r="3"></circle>
          </svg>
        </template>
      </DashboardCard>

      <DashboardCard title="包间预约" :count="roomReservationCount" @click="goToReserve" style="cursor: pointer;">
        <template #icon>
          <svg width="30" height="30" fill="none" stroke="#4a90e2" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" viewBox="0 0 24 24">
            <rect x="3" y="11" width="18" height="11" rx="2" ry="2"></rect>
            <path d="M7 11V7a5 5 0 0 1 10 0v4"></path>
          </svg>
        </template>
      </DashboardCard>

<!--      <DashboardCard title="菜品预订" :count="dishBookingCount">-->
<!--        <template #icon>-->
<!--          <svg width="30" height="30" fill="none" stroke="#4a90e2" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" viewBox="0 0 24 24">-->
<!--            <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>-->
<!--            <circle cx="12" cy="7" r="4"></circle>-->
<!--          </svg>-->
<!--        </template>-->
<!--      </DashboardCard>-->
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import DashboardCard from '@/components/DashboardCard.vue'
import axios from "axios";

const router = useRouter()

const overviewData = ref({})
const todayMenuCount = ref()
const dishCount = ref()
const roomReservationCount = ref()
const dishBookingCount = ref()

const goToOverview = () => {
  router.push('/overview')
}
const goToMenu = () => {
  router.push('/menu')
}
const goToReserve = () => {
  router.push('/reserve')
}

onMounted(async () => {
  axios.get('http://localhost:8080/overview/getAll')
      .then((res) => {
        res.data.overviewData.forEach(item => {
          overviewData.value[item.item] = item.num;
        });
        todayMenuCount.value = overviewData.value['menu']
        dishCount.value = overviewData.value['dish']
        roomReservationCount.value = overviewData.value['room_reservation']
        dishBookingCount.value = overviewData.value['dish_booking']
      })
      .catch((err) => {
        console.log(err)
      })
});
</script>

<style scoped>
.dashboard-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}

.dashboard-title {
  font-size: 28px;
  color: #333;
  font-weight: bold;
  margin-bottom: 40px;
}

.card-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 20px;
  margin-bottom: 50px;
}

/* 小屏时自动变一列 */
@media (max-width: 480px) {
  .card-grid {
    grid-template-columns: 1fr;
  }
}
</style>
