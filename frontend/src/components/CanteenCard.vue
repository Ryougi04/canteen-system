<template>
  <div class="canteen-card" @click="$emit('click')">
    <div class="image-container">
      <img :src="canteen.image" :alt="canteen.name" class="canteen-image">
      <div class="overlay"></div>
      <h2 class="canteen-name">{{ canteen.name }}</h2>
    </div>
    <div class="info-container">
      <div class="opening-hours">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
          <circle cx="12" cy="12" r="10"></circle>
          <polyline points="12 6 12 12 16 14"></polyline>
        </svg>
        <span>{{ canteen.hours }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CanteenCard',
  props: {
    canteen: {
      type: Object,
      required: true
    }
  }
}
</script>

<style scoped>
.canteen-card {
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
  cursor: pointer;
  background: white;
}

.canteen-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
}

.image-container {
  position: relative;
  height: 200px;
}

.canteen-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: linear-gradient(to top, rgba(0,0,0,0.7), rgba(0,0,0,0.2));
}

.canteen-name {
  position: absolute;
  bottom: 20px;
  left: 20px;
  color: white;
  font-size: 22px;
  margin: 0;
  text-shadow: 0 2px 4px rgba(0,0,0,0.3);
}

.info-container {
  padding: 15px 20px;
}

.opening-hours {
  display: flex;
  align-items: center;
  gap: 8px;
  color: #666;
  font-size: 14px;
}

.opening-hours svg {
  flex-shrink: 0;
}
</style>