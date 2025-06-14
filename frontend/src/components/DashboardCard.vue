<template>
  <div class="dashboard-card" @click="$emit('click')">
    <div class="icon">
      <slot name="icon"></slot>  <!-- 用插槽渲染图标 -->
    </div>
    <div class="title">{{ title }}</div>
    <div class="count">{{ count }}</div>
  </div>
</template>

<script>
export default {
  props: {
    title: String,
    count: Number
  }
}
</script>

<style scoped>
.dashboard-card {
  background: #fff;
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
  text-align: center;
}
.icon {
  margin-bottom: 12px;
}
.title {
  font-weight: 600;
  margin-bottom: 6px;
  color: #555;
}
.count {
  font-size: 28px;
  font-weight: bold;
  color: #222;
}
</style>
