<template>
  <div class="home-container">
    <div class="background-shapes">
      <div class="shape shape-1"></div>
      <div class="shape shape-2"></div>
      <div class="shape shape-3"></div>
    </div>
    
    <h1 class="title">哈工大食堂服务系统</h1>
    <p class="subtitle">便捷用餐，尽在掌握</p>
    
    <div class="menu-grid">
      <MenuButton text="食堂一览" to="/canteens">
        <template #icon>
          <svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
            <polyline points="9 22 9 12 15 12 15 22"></polyline>
          </svg>
        </template>
      </MenuButton>

      <MenuButton text="每日菜单" to="/daily-menu">
        <template #icon>
          <svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M6 2h12a2 2 0 0 1 2 2v16a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2z"></path>
            <line x1="8" y1="2" x2="8" y2="22"></line>
            <line x1="16" y1="2" x2="16" y2="22"></line>
          </svg>
        </template>
      </MenuButton>

      <MenuButton text="包间预约" to="/private-room">
        <template #icon>
          <svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <rect x="3" y="11" width="18" height="11" rx="2" ry="2"></rect>
            <path d="M7 11V7a5 5 0 0 1 10 0v4"></path>
          </svg>
        </template>
      </MenuButton>

      <MenuButton text="菜品排行" to="/dish-ranking">
        <template #icon>
          <svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <line x1="8" y1="6" x2="21" y2="6"></line>
            <line x1="8" y1="12" x2="21" y2="12"></line>
            <line x1="8" y1="18" x2="21" y2="18"></line>
            <line x1="3" y1="6" x2="3.01" y2="6"></line>
            <line x1="3" y1="12" x2="3.01" y2="12"></line>
            <line x1="3" y1="18" x2="3.01" y2="18"></line>
          </svg>
        </template>
      </MenuButton>

      <MenuButton text="营养建议" to="/nutrition">
        <template #icon>
          <svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M18 8h1a4 4 0 0 1 0 8h-1"></path>
            <path d="M2 8h16v9a4 4 0 0 1-4 4H6a4 4 0 0 1-4-4V8z"></path>
            <line x1="6" y1="1" x2="6" y2="4"></line>
            <line x1="10" y1="1" x2="10" y2="4"></line>
            <line x1="14" y1="1" x2="14" y2="4"></line>
          </svg>
        </template>
      </MenuButton>

      <MenuButton text="个人中心" to="/profile">
        <template #icon>
          <svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
            <circle cx="12" cy="7" r="4"></circle>
          </svg>
        </template>
      </MenuButton>
    </div>
  </div>
</template>

<script>
import MenuButton from '../components/MenuButton.vue'

export default {
  name: 'HomeView',
  components: {
    MenuButton
  }
}
</script>

<style scoped>
.home-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 40px 20px;
  text-align: center;
  position: relative;
  min-height: calc(100vh - 80px);
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.background-shapes {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  overflow: hidden;
}

.shape {
  position: absolute;
  border-radius: 50%;
  filter: blur(80px);
  opacity: 0.15;
}

.shape-1 {
  width: 400px;
  height: 400px;
  background: #4f46e5;
  top: -100px;
  left: -100px;
}

.shape-2 {
  width: 600px;
  height: 600px;
  background: #10b981;
  bottom: -150px;
  right: -150px;
}

.shape-3 {
  width: 300px;
  height: 300px;
  background: #3b82f6;
  top: 50%;
  left: 30%;
}

.title {
  color: #1f2937;
  margin-bottom: 16px;
  font-size: 2.5rem;
  font-weight: 700;
  background: linear-gradient(90deg, #3b82f6, #10b981);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  animation: fadeInUp 0.6s ease-out;
}

.subtitle {
  color: #6b7280;
  margin-bottom: 48px;
  font-size: 1.125rem;
  animation: fadeInUp 0.6s ease-out 0.1s forwards;
  opacity: 0;
}

.menu-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 30px;
  padding: 0 20px;
  animation: fadeInUp 0.6s ease-out 0.2s forwards;
  opacity: 0;
}

@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@media (max-width: 768px) {
  .home-container {
    padding: 40px 15px;
  }
  
  .title {
    font-size: 2rem;
  }
  
  .subtitle {
    font-size: 1rem;
    margin-bottom: 40px;
  }
  
  .menu-grid {
    gap: 20px;
  }
}

@media (max-width: 480px) {
  .home-container {
    padding: 30px 10px;
  }
  
  .title {
    font-size: 1.75rem;
  }
  
  .menu-grid {
    grid-template-columns: 1fr;
    gap: 15px;
  }
}
</style>