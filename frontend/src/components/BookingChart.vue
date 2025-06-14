<template>
  <v-chart class="h-96 w-full" :option="option" autoresize />
</template>

<script setup>
import { ref } from 'vue'
import { use } from 'echarts/core'
import VChart from 'vue-echarts'
import {
  CanvasRenderer
} from 'echarts/renderers'
import {
  LineChart
} from 'echarts/charts'
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent
} from 'echarts/components'

use([
  CanvasRenderer,
  LineChart,
  TitleComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent
])

const option = ref({
  title: {
    text: '包间预订趋势'
  },
  tooltip: {
    trigger: 'axis'
  },
  legend: {
    data: ['小包间', '中包间', '大包间']
  },
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  xAxis: {
    type: 'category',
    boundaryGap: false,
    data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      name: '小包间',
      type: 'line',
      data: [10, 12, 21, 24, 30, 35, 40]
    },
    {
      name: '中包间',
      type: 'line',
      data: [20, 28, 33, 45, 50, 60, 70]
    },
    {
      name: '大包间',
      type: 'line',
      data: [5, 8, 15, 18, 22, 30, 35]
    }
  ]
})
</script>

<style scoped>
.v-chart {
  width: 100%;
  height: 400px;
}
</style>
