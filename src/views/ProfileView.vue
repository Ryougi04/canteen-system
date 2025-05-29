<template>
  <div class="profile-container">
    <button class="back-button" @click="goToHome">
      <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
        <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
        <polyline points="9 22 9 12 15 12 15 22"></polyline>
      </svg>
      返回主页
    </button>

    <div class="profile-header">
      <h1 class="title">个人中心</h1>
    </div>

    <div class="profile-card">
      <div class="avatar-section">
        <div class="avatar-container">
          <img :src="user.avatar || defaultAvatar" alt="用户头像" class="avatar">
          <button class="avatar-edit" @click="editAvatar">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
              <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
            </svg>
          </button>
        </div>
      </div>

      <div class="user-info">
        <div class="info-item">
          <label>用户名：</label>
          <span>{{ user.name }}</span>
        </div>
        <div class="info-item">
          <label>学号：</label>
          <span>{{ user.studentId }}</span>
        </div>
        <div class="info-item">
          <label>学院：</label>
          <span>{{ user.college }}</span>
        </div>
        <div class="info-item">
          <label>联系方式：</label>
          <span>{{ user.phone || '未设置' }}</span>
        </div>
      </div>

      <div class="action-buttons">
        <button class="action-btn" @click="changePassword">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <rect x="3" y="11" width="18" height="11" rx="2" ry="2"></rect>
            <path d="M7 11V7a5 5 0 0 1 10 0v4"></path>
          </svg>
          修改密码
        </button>
        
        <button class="action-btn" @click="viewReservations">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
            <line x1="16" y1="2" x2="16" y2="6"></line>
            <line x1="8" y1="2" x2="8" y2="6"></line>
            <line x1="3" y1="10" x2="21" y2="10"></line>
          </svg>
          查看预定
        </button>
        
        <button class="action-btn" @click="logout">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
            <polyline points="16 17 21 12 16 7"></polyline>
            <line x1="21" y1="12" x2="9" y2="12"></line>
          </svg>
          退出登录
        </button>
      </div>
    </div>

    <!-- 修改密码对话框 -->
    <div class="modal" v-if="showPasswordModal">
      <div class="modal-content">
        <h3>修改密码</h3>
        <form @submit.prevent="submitPasswordChange">
          <div class="form-group">
            <label>当前密码</label>
            <input type="password" v-model="passwordForm.currentPassword" required>
          </div>
          <div class="form-group">
            <label>新密码</label>
            <input type="password" v-model="passwordForm.newPassword" required>
          </div>
          <div class="form-group">
            <label>确认新密码</label>
            <input type="password" v-model="passwordForm.confirmPassword" required>
          </div>
          <div class="form-actions">
            <button type="button" class="cancel-btn" @click="showPasswordModal = false">取消</button>
            <button type="submit" class="confirm-btn">确认修改</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import defaultAvatar from '../assets/xueshi.jpg'

export default {
  name: 'ProfileView',
  data() {
    return {
      defaultAvatar,
      user: {
        name: '张三',
        studentId: '20230001',
        college: '计算机学院',
        phone: '13800138000',
        avatar: ''
      },
      showPasswordModal: false,
      passwordForm: {
        currentPassword: '',
        newPassword: '',
        confirmPassword: ''
      }
    }
  },
  created() {
    // 这里可以从Vuex或API获取用户信息
    // this.fetchUserInfo()
  },
  methods: {
    goToHome() {
      this.$router.push({ name: 'home' })
    },
    editAvatar() {
      // 实现头像上传逻辑
      alert('头像上传功能待实现')
    },
    changePassword() {
      this.showPasswordModal = true
    },
    viewReservations() {
      this.$router.push({ name: 'reservations' })
    },
    logout() {
      // 实现退出登录逻辑
      // 例如清除token和用户信息，然后跳转到登录页
      alert('确定要退出登录吗？')
      // this.$store.dispatch('logout')
      // this.$router.push({ name: 'login' })
        localStorage.removeItem('auth')

      // 跳转到登录页
      this.$router.push('/')
    },
    submitPasswordChange() {
      if (this.passwordForm.newPassword !== this.passwordForm.confirmPassword) {
        alert('两次输入的新密码不一致')
        return
      }
      
      // 这里调用API修改密码
      alert('密码修改成功')
      this.showPasswordModal = false
      this.passwordForm = {
        currentPassword: '',
        newPassword: '',
        confirmPassword: ''
      }
    }
  }
}
</script>

<style scoped>
.profile-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  position: relative;
}

.back-button {
  position: absolute;
  top: 20px;
  left: 20px;
  display: flex;
  align-items: center;
  gap: 5px;
  background: #f0f7ff;
  border: none;
  border-radius: 6px;
  padding: 8px 12px;
  color: #4a90e2;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.back-button:hover {
  background: #e0efff;
  transform: translateY(-2px);
}

.title {
  text-align: center;
  margin: 20px 0 30px;
  color: #333;
  font-size: 28px;
}

.profile-card {
  background: white;
  border-radius: 12px;
  padding: 30px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.avatar-section {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.avatar-container {
  position: relative;
  width: 120px;
  height: 120px;
}

.avatar {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  object-fit: cover;
  border: 3px solid #f0f7ff;
}

.avatar-edit {
  position: absolute;
  bottom: 5px;
  right: 5px;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  background: #4a90e2;
  border: none;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

.avatar-edit:hover {
  background: #3a80d0;
}

.user-info {
  margin-bottom: 30px;
}

.info-item {
  display: flex;
  margin-bottom: 15px;
  padding-bottom: 15px;
  border-bottom: 1px solid #f0f0f0;
}

.info-item label {
  font-weight: 500;
  min-width: 80px;
  color: #666;
}

.info-item span {
  flex: 1;
}

.action-buttons {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 15px;
}

.action-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  padding: 12px;
  background: #f8f9fa;
  border: 1px solid #ddd;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.action-btn:hover {
  background: #e9ecef;
  transform: translateY(-2px);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.action-btn svg {
  flex-shrink: 0;
}

/* 模态框样式 */
.modal {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  border-radius: 12px;
  padding: 25px;
  width: 100%;
  max-width: 400px;
}

.modal-content h3 {
  margin-top: 0;
  text-align: center;
  color: #333;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  color: #555;
}

.form-group input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 6px;
  box-sizing: border-box;
}

.form-actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  margin-top: 20px;
}

.cancel-btn {
  padding: 8px 16px;
  background: #f8f9fa;
  border: 1px solid #ddd;
  border-radius: 6px;
  cursor: pointer;
}

.confirm-btn {
  padding: 8px 16px;
  background: #4a90e2;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}

.confirm-btn:hover {
  background: #3a80d0;
}

@media (max-width: 600px) {
  .profile-card {
    padding: 20px;
  }
  
  .action-buttons {
    grid-template-columns: 1fr;
  }
  
  .avatar-container {
    width: 100px;
    height: 100px;
  }
}
</style>