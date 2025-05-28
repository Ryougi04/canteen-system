import { createRouter, createWebHistory } from 'vue-router'
import HomeView from './views/HomeView.vue'
import CanteenListView from './views/CanteenListView.vue'
import CanteenDetailView from './views/CanteenDetailView.vue'

const routes = [
  {
    path: '/',
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
  path: '/daily-menu',
  name: 'daily-menu',
  component: () => import('./views/DailyMenuView.vue')
}
  
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router 