<template>
  <div class="canteen-detail-container">
    <button class="back-button" @click="$router.go(-1)">
      <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
        <line x1="19" y1="12" x2="5" y2="12"></line>
        <polyline points="12 19 5 12 12 5"></polyline>
      </svg>
      返回
    </button>
    
    <div class="detail-header">
      <h1>{{ currentCanteen.name }}</h1>
      <div class="opening-hours">
        <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
          <circle cx="12" cy="12" r="10"></circle>
          <polyline points="12 6 12 12 16 14"></polyline>
        </svg>
        <span>营业时间: {{ currentCanteen.hours }}</span>
      </div>
    </div>
    
    <div class="detail-content">
      <!-- 食堂的详细信息 -->
      <p>{{ currentCanteen.description }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CanteenDetailView',
  props: {
    id: {
      type: String,
      required: true
    }
  },
  computed: {
    currentCanteen() {
      const canteens = {
        xueyuan: {
          name: '学苑楼食堂',
          hours: '6:30-19:00',
          description: '学苑餐厅：学苑餐厅建成于2002年初，餐厅无论从硬件设备，还是管理、服务、经营、队伍建设都是哈工大饮食中心多年经验的积累体现。餐厅设有餐桌290套，可容纳1100人同时就餐。' +
              '美食林餐厅：餐厅的投入使用，改进了食堂多年来单一的饮食结构。形成了口味多样、特色突出、层次鲜明的校园饮食新格局。餐厅以地方风味小吃为主，极大的丰富了校园餐饮文化，得到了校内师生的一直好评。参厅设有餐桌240套，可容纳990人同时就餐。' +
              '阳光餐厅：阳光餐厅位于我校区学苑楼内三层，是我中心最有特色的餐厅。餐厅以多功能餐饮服务为主，是学校大型活动的主要举办场所。设备齐全，环境幽雅，并备有小型宴会厅，可承接各种会议、婚宴、发布会、文娱演出等。可容纳1100人同时就餐。'
        },
        xueshi: {
          name: '学士楼食堂',
          hours: '6:00-20:30',
          description: '学士民族餐厅：主要由德澳士快餐、旋转小火锅、江原道韩餐和新疆特色食品几大地域特色美食组成，除此之外，还有旋转小火锅、真味烤吧等特色美食。' +
              '百味佳餐厅：位于学士楼二楼，建于1996年，于1997年6月投入使用，楼层面积2300平方米，餐厅面积1150平方米，由自选餐厅和风味餐厅组成，餐厅布局合理，环境幽雅，明亮、桌椅美观舒适，备有闭路电视，可同时容纳1300余人进餐。设备先进功能齐全，严格按照高校标准餐厅设计，为黑龙江省高等院校标准的餐厅之一。'
        },
        xuezi: {
          name: '学子楼食堂',
          hours: '10:00-19:00',
          description: '学子餐厅：位于学子楼二层，餐厅经过了数次改造和装修。以12元自助秤重为主要业态，餐厅加工制作所需设施、设备齐全、炊具全部不锈钢化、餐具全部是密胺、卫生防疫及消毒设施完备、并严格执行卫生制度、经多次检验，均符合卫生要求。' +
              '学子真味饺子园：位于学子楼一层，紧临学苑楼、图书馆，是继清泽真味饺子园开业后，饮食中心第二个真味品牌连锁餐厅。营业时间10：00至19：00。配有宽敞明亮的就餐大厅、先进专业的全套设施、一流完善的服务队伍，主营水饺、蒸饺、切面、凉菜等品项，以三鲜馅、玉米猪肉馅水饺，驴肉馅蒸饺，金牌炸酱面，葱油拌面为主打产品，深受广大师生顾客认可。'
        }
      }
      return canteens[this.id] || {}
    }
  }
}
</script>

<style scoped>
.canteen-detail-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.back-button {
  display: flex;
  align-items: center;
  gap: 5px;
  background: none;
  border: none;
  color: #4a90e2;
  font-size: 16px;
  cursor: pointer;
  padding: 10px 0;
  margin-bottom: 20px;
}

.back-button:hover {
  text-decoration: underline;
}

.detail-header {
  margin-bottom: 30px;
  padding-bottom: 15px;
  border-bottom: 1px solid #eee;
}

.detail-header h1 {
  color: #333;
  margin-bottom: 10px;
}

.opening-hours {
  display: flex;
  align-items: center;
  gap: 8px;
  color: #666;
  font-size: 16px;
}

.detail-content {
  line-height: 1.6;
  color: #444;
}
</style>