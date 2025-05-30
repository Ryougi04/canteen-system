# Canteen System 食堂系统

本项目是一个基于 Vue.js 的食堂信息展示与预订系统，支持查看菜品、食堂信息、预约包间、查看营养建议等功能。

## 🧩 项目结构

canteen-system/
├── public/ # 公共资源（如图片）
│ └── images/ # 页面展示用图片
├── src/ # 源代码目录
│ ├── assets/ # 静态资源
│ ├── components/ # 可复用组件（如 CanteenCard）
│ ├── views/ # 各页面视图（如主页、菜单、预约等）
│ ├── App.vue # 根组件
│ ├── main.js # 项目入口
│ ├── router.js # Vue Router 路由配置
│ └── style.css # 全局样式


## 🚀 功能介绍

- 🏠 **首页（HomeView）**：展示欢迎信息或入口导航。
- 📋 **食堂列表（CanteenListView）**：列出所有食堂。
- 🏢 **食堂详情（CanteenDetailView）**：显示食堂信息及其菜单。
- 🍽 **每日菜单（DailyMenuView）**：展示每天推荐的菜单。
- 📈 **菜品排行（DishRanking）**：展示热门菜品排名。
- 📅 **包间预约（PrivateRoomView）**：提交预约信息。
- 💡 **营养建议（NutritionAdvice）**：展示饮食建议。
- 👤 **个人中心（ProfileView）**：查看用户信息。

## 🛠️ 使用技术

- [Vue 3](https://vuejs.org/) + Composition API
- Vue Router
- 原生 CSS（可集成 Tailwind 或其他框架）
- 使用本地静态图片资源

## 📦 安装与运行

```bash
# 克隆项目（使用你自己的分支或仓库地址）
git clone -b 5func https://github.com/Ryougi04/canteen-system.git

cd canteen-system

# 安装依赖
npm install

# 启动开发服务器
npm run dev




# Vue 3 + Vite

This template should help get you started developing with Vue 3 in Vite. The template uses Vue 3 `<script setup>` SFCs, check out the [script setup docs](https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup) to learn more.

Learn more about IDE Support for Vue in the [Vue Docs Scaling up Guide](https://vuejs.org/guide/scaling-up/tooling.html#ide-support).
