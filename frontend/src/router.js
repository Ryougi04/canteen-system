import { createRouter, createWebHistory } from 'vue-router'
import HomeView from './views/HomeView.vue'
import CanteenListView from './views/CanteenListView.vue'
import CanteenDetailView from './views/CanteenDetailView.vue'
import PrivateRoomView from './views/PrivateRoomView.vue'
import NutritionAdviceView from './views/NutritionAdviceView.vue'
import DishRanking from "./views/DishRanking.vue";
import DishComment from "./views/DishComment.vue";
import AdminView from './views/AdminView/AdminView.vue'
import DishOverview from "@/views/AdminView/DishOverview.vue";
import DishManageView from "@/views/AdminView/DishManageView.vue";

const routes = [
  {
    path: '/home',
    name: 'home',
    component: HomeView,
    meta: { requiresAuth: true } // ✅ 添加权限控制
  },
  {
    path: '/canteens',
    name: 'canteen-list',
    component: CanteenListView,
    meta: { requiresAuth: true } // ✅
  },
  {
    path: '/canteens/:id',
    name: 'canteen-detail',
    component: CanteenDetailView,
    props: true,
    meta: { requiresAuth: true } // ✅
  },
  {
    path: '/profile',
    name: 'profile',
    component: () => import('./views/ProfileView.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/private-room',
    name: 'PrivateRoom',
    component: PrivateRoomView,
    meta: { requiresAuth: true } // ✅
  },
  {
    path: '/nutrition',
    name: 'NutritionAdvice',
    component: NutritionAdviceView,
    meta: { requiresAuth: true } // ✅
  },
  {
    path: '/daily-menu',
    name: 'daily-menu',
    component: () => import('./views/DailyMenuView.vue'),
    meta: { requiresAuth: true } // ✅
  },
  {
    path: '/dish-ranking',
    name: 'DishRanking',
    component: DishRanking,
    meta: { requiresAuth: true } // ✅
  },
  {
    path: '/comment/:id',
    name: 'comment',
    component: DishComment,
    meta: { requiresAuth: true } // ✅ 评论应登录后才能写
  },
  {
    path: '/admin',
    name: 'admin',
    component: AdminView,
    meta: { requiresAuth: true }
  },
  {
    path: '/overview',
    name: 'overview',
    component: DishOverview,
    meta: { requiresAuth: true }
  },
  {
    path: '/manage',
    name: 'manage',
    component: DishManageView,
    meta: { requiresAuth: true }
  },
  {
    path: '/',
    name: 'login',
    component: () => import('./views/LoginView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return {
        ...savedPosition,
        behavior: 'smooth'
      }
    } else {
      return {
        top: 0,
        behavior: 'smooth'
      }
    }
  }
})

// 路由守卫
router.beforeEach((to, from, next) => {
  if (to.path === '/') {
    next()
  } else {
    const isAuthenticated = localStorage.getItem('auth') === 'true'
    if (to.meta.requiresAuth && !isAuthenticated) {
      next('/') // 未登录跳转登录页
    } else {
      next()
    }
  }
})

export default router
