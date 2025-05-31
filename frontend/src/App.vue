<template>
  <transition name="fade" mode="out-in">
    <router-view />
  </transition>
</template>

<style>
/* 页面切换动画：淡入淡出 */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
