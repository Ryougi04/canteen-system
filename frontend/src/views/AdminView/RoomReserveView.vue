<template>
  <div class="reservation-container">
    <!-- 返回按钮 -->
    <div class="top-bar">
      <button class="back-button" @click="goToHome">返回主页</button>
    </div>

    <!-- 标题 -->
    <h1 class="title">包间预约总览</h1>

    <!-- 表格 -->
    <div class="table-wrapper">
      <table class="reservation-table">
        <thead>
        <tr>
          <th>包间名称</th>
          <th>包间类型</th>
          <th>预约时间</th>
          <th>预约姓名</th>
          <th>电话</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(room, index) in reservations" :key="index">
          <td>{{ room.name }}</td>
          <td>{{ room.type }}</td>
          <td>{{ room.time }}</td>
          <td>{{ room.user }}</td>
          <td>{{ room.phone }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'RoomReserve',
  data() {
    return {
      reservations: [
        {
          name: '贵宾包间A',
          type: '大包间',
          time: '2025-06-14 18:00',
          user: '张三',
          phone: '13812345678',
        },
        {
          name: '家庭包间B',
          type: '中包间',
          time: '2025-06-14 19:00',
          user: '李四',
          phone: '13987654321',
        },
        {
          name: '商务包间C',
          type: '小包间',
          time: '2025-06-14 20:00',
          user: '王五',
          phone: '13711223344',
        },
      ],
    };
  },
  mounted() {
    axios.get("http://localhost:8080/reservation/privateRoomList")
        .then((res) => {
          if (res.data.code === 200) {
            this.reservations = res.data.data;
          }
        })
        .catch(() => {
          // 请求失败时不做处理，保留默认静态数据
          console.warn('使用静态示例数据');
        });
  },
  methods: {
    goToHome() {
      this.$router.push({ name: 'admin' });
    }
  },
};
</script>

<style scoped>
.reservation-container {
  background: #f9f9f9;
  max-width: 900px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}

.top-bar {
  width: 100%;
  text-align: left;
  margin-bottom: 10px;
}

.back-button {
  background: #f0f7ff;
  color: #4a90e2;
  padding: 6px 10px;
  font-size: 12px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.2s ease;
}
.back-button:hover {
  background: #e0efff;
  transform: translateY(-2px);
}

.title {
  font-size: 28px;
  color: #333;
  margin-bottom: 30px;
}

.table-wrapper {
  overflow-x: auto;
}

.reservation-table {
  width: 100%;
  border-collapse: collapse;
  background: white;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 12px;
  overflow: hidden;
}

.reservation-table th,
.reservation-table td {
  color: #333;
  padding: 12px 16px;
  text-align: left;
  border-bottom: 1px solid #eee;
  font-size: 14px;
}

.reservation-table th {
  background-color: #f0f7ff;
  color: #4a90e2;
  font-weight: bold;
}

.reservation-table tbody tr:hover {
  background-color: #f9f9f9;
}
</style>
