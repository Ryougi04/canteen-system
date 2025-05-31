import { createRouter, createWebHistory } from 'vue-router'
import HomeView from './views/HomeView.vue'
import CanteenListView from './views/CanteenListView.vue'
import CanteenDetailView from './views/CanteenDetailView.vue'
import PrivateRoomView from './views/PrivateRoomView.vue'
import NutritionAdvice from './views/NutritionAdvice.vue'
import DishRanking from "./views/DishRanking.vue";

const routes = [
  {
    path: '/home',
    name: 'home',
    component: HomeView
  },
  {
    path: '/canteens',
    name: 'canteen-list',
    component: CanteenListView
  },
  {
    path: '/canteens/:id',
    name: 'canteen-detail',
    component: CanteenDetailView,
    props: true
  },
  {
    path: '/profile',
    name: 'profile',
    component: () => import('./views/ProfileView.vue'),
    meta: { requiresAuth: true } // 如果需要登录才能访问
  },
  {
    path: '/private-room',
    name: 'PrivateRoom',
    component: PrivateRoomView,
  },
  {
    path: '/nutrition',
    name: 'NutritionAdvice',
    component: NutritionAdvice,
  },
  {
    path: '/daily-menu',
    name: 'daily-menu',
    component: () => import('./views/DailyMenuView.vue')
  },
  {
    path: '/dish-ranking',
    name: 'DishRanking',
    component: DishRanking,
  },
  {
    path: '/',
    name: 'login',
    component: () => import('./views/LoginView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})
router.beforeEach((to, from, next) => {
  const isAuthenticated = localStorage.getItem('auth') === 'true'
  if (to.meta.requiresAuth && !isAuthenticated) {
    next('/')
  } else {
    next()
  }
})

export default router
