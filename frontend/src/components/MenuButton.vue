<!-- MenuButton.vue -->
<template>
  <router-link :to="to" class="menu-button" @click="handleClick">
    <slot name="icon"></slot>
    <span>{{ text }}</span>
  </router-link>
</template>

<script>
export default {
  name: 'MenuButton',
  props: {
    text: String,
    to: String
  },
  methods: {
    handleClick() {
      const audio = new Audio('/sounds/click.mp3');
      audio.play().catch((e) => {
        // 有些浏览器可能需要用户交互后才允许播放
        console.warn('音效播放失败:', e);
      });
    }
  }
}
</script>

<style scoped>
.menu-button {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 30px 20px;
  border-radius: 12px;
  background: white;
  text-decoration: none;
  transition: all 0.3s ease;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  position: relative;
  overflow: hidden;
  border: 1px solid #c4e2f6; /* 添加淡蓝色边框 */
}

.menu-button:hover {
  background: linear-gradient(to bottom, white, #f0f9ff);
  transform: translateY(-5px);
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.15);
}



.icon-wrapper {
  margin-bottom: 15px;
}

.button-text {
  font-size: 18px;
  color: #34495e;
  font-weight: 500;
  letter-spacing: 0.5px;
}
</style>