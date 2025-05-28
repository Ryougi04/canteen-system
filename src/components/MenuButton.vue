<template>
  <router-link :to="to" class="menu-button">
    <div class="icon-container">
      <slot name="icon"></slot>
    </div>
    <span class="button-text">{{ text }}</span>
  </router-link>
</template>

<script>
export default {
  props: {
    text: {
      type: String,
      required: true
    },
    to: {
      type: String,
      default: '/'
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
  padding: 20px;
  background-color: #fff;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-decoration: none;
  color: #333;
  transition: all 0.3s ease;
}

.menu-button:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.icon-container {
  width: 60px;
  height: 60px;
  margin-bottom: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f0f7ff;
  border-radius: 50%;
}

.button-text {
  font-size: 16px;
  font-weight: 500;
}
</style>