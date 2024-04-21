<template>
  <div class="app-container">
    <div class="address-layout">
    </div>
    <div class="total-layout">
      <el-row :gutter="20">
        <el-col :span="6">
          <div class="total-frame">
            <img :src="img_home_order" class="total-icon">
            <div class="total-title">Total Orders</div>
            <div class="total-value">10</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="total-frame">
            <img :src="img_home_today_amount" class="total-icon">
            <div class="total-title">Total Sales</div>
            <div class="total-value">￥2560.00</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="total-frame">
            <img :src="img_home_yesterday_amount" class="total-icon">
            <div class="total-title">Last Total Sales</div>
            <div class="total-value">￥2560.00</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="total-frame">
            <svg-icon icon-class="total-week" class="total-icon">
            </svg-icon>
            <div class="total-title">Total Last 7 Days</div>
            <div class="total-value">￥2560.00</div>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="un-handle-layout">
      <div class="layout-title">Pending Matters</div>
      <div class="un-handle-content">
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="un-handle-item">
              <span class="font-medium">Pending Orders</span>
              <span style="float: right" class="color-danger">(1)</span>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="un-handle-item">
              <span class="font-medium">Completed Orders</span>
              <span style="float: right" class="color-danger">(2)</span>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="un-handle-item">
              <span class="font-medium">Pending Confirm Orders</span>
              <span style="float: right" class="color-danger">(2)</span>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="un-handle-item">
              <span class="font-medium">Pending shipment orders</span>
              <span style="float: right" class="color-danger">(2)</span>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="un-handle-item">
              <span class="font-medium">Shipped Orders</span>
              <span style="float: right" class="color-danger">(3)</span>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="un-handle-item">
              <span class="font-medium">Pending Refund Requests</span>
              <span style="float: right" class="color-danger">(0)</span>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
    <div class="overview-layout">
      <el-row :gutter="20">
        <el-col :span="12">
          <div class="out-border">
            <div class="layout-title">Product Overview</div>
            <div style="padding: 40px">
              <el-row>
                <el-col :span="8" class="color-danger overview-item-value">5</el-col>
                <el-col :span="8" class="color-danger overview-item-value">17</el-col>
                <el-col :span="8" class="color-danger overview-item-value">21</el-col>
              </el-row>
              <el-row class="font-medium">
                <el-col :span="8" class="overview-item-title">Off-Shelves</el-col>
                <el-col :span="8" class="overview-item-title">On-Shelves</el-col>
                <el-col :span="8" class="overview-item-title">All Products</el-col>
              </el-row>
            </div>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="out-border">
            <div class="layout-title">Supermarket Overview</div>
            <div style="padding: 40px">
              <el-row>
                <el-col :span="6" class="color-danger overview-item-value">16</el-col>
                <el-col :span="10" class="color-danger overview-item-value">15</el-col>
                <el-col :span="6" class="color-danger overview-item-value">21</el-col>
              </el-row>
              <el-row class="font-medium">
                <el-col :span="6" class="overview-item-title">New Product</el-col>
                <el-col :span="10" class="overview-item-title">Recommend Product</el-col>
                <el-col :span="6" class="overview-item-title">All Products</el-col>
              </el-row>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="statistics-layout">
      <div class="layout-title">Order Statistics</div>
      <el-row>
        <el-col :span="4">
          <div style="padding: 20px">
            <div>
              <div style="color: #909399;font-size: 14px">Total orders for the month</div>
              <div style="color: #606266;font-size: 20px;padding: 8px 0">10000</div>
              <div>
                <span class="color-success" style="font-size: 14px">+10%</span>
                <span style="color: #C0C4CC;font-size: 14px">compared to last month</span>
              </div>
            </div>
            <div style="margin-top: 20px;">
              <div style="color: #909399;font-size: 14px">Total orders for the week</div>
              <div style="color: #606266;font-size: 20px;padding: 8px 0">1000</div>
              <div>
                <span class="color-danger" style="font-size: 14px">-10%</span>
                <span style="color: #C0C4CC;font-size: 14px">compared to last week</span>
              </div>
            </div>
            <div style="margin-top: 20px;">
              <div style="color: #909399;font-size: 14px">Total sales for the month</div>
              <div style="color: #606266;font-size: 20px;padding: 8px 0">100000</div>
              <div>
                <span class="color-success" style="font-size: 14px">+10%</span>
                <span style="color: #C0C4CC;font-size: 14px">compared to last month</span>
              </div>
            </div>
            <div style="margin-top: 20px;">
              <div style="color: #909399;font-size: 14px">Total sales for the week</div>
              <div style="color: #606266;font-size: 20px;padding: 8px 0">50000</div>
              <div>
                <span class="color-danger" style="font-size: 14px">-10%</span>
                <span style="color: #C0C4CC;font-size: 14px">Compared to last week</span>
              </div>
            </div>
          </div>
        </el-col>
        <el-col :span="20">
          <div style="padding: 10px;border-left:1px solid #DCDFE6">
            <el-date-picker
              style="float: right;z-index: 1"
              size="small"
              v-model="orderCountDate"
              type="daterange"
              align="right"
              unlink-panels
              range-separator="to"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              @change="handleDateChange"
              :picker-options="pickerOptions">
            </el-date-picker>
            <div>
              <ve-line
                :data="chartData"
                :legend-visible="false"
                :loading="loading"
                :data-empty="dataEmpty"
                :settings="chartSettings"></ve-line>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
  import {str2Date} from '@/utils/date';
  import img_home_order from '@/assets/images/home_order.png';
  import img_home_today_amount from '@/assets/images/home_today_amount.png';
  import img_home_yesterday_amount from '@/assets/images/home_yesterday_amount.png';
  const DATA_FROM_BACKEND = {
    columns: ['date', 'orderCount','orderAmount'],
    rows: [
      {date: '2024-02-01', orderCount: 10, orderAmount: 1093},
      {date: '2024-02-02', orderCount: 20, orderAmount: 2230},
      {date: '2024-02-03', orderCount: 33, orderAmount: 3623},
      {date: '2024-02-04', orderCount: 50, orderAmount: 6423},
      {date: '2024-02-05', orderCount: 80, orderAmount: 8492},
      {date: '2024-02-06', orderCount: 60, orderAmount: 6293},
      {date: '2024-02-07', orderCount: 20, orderAmount: 2293},
      {date: '2024-02-08', orderCount: 60, orderAmount: 6293},
      {date: '2024-02-09', orderCount: 50, orderAmount: 5293},
      {date: '2024-02-10', orderCount: 30, orderAmount: 3293},
      {date: '2024-02-11', orderCount: 20, orderAmount: 2293},
      {date: '2024-02-12', orderCount: 80, orderAmount: 8293},
      {date: '2024-02-13', orderCount: 100, orderAmount: 10293},
      {date: '2024-02-14', orderCount: 10, orderAmount: 1293},
      {date: '2024-02-15', orderCount: 40, orderAmount: 4293}
    ]
  };
  export default {
    name: 'home',
    data() {
      return {
        pickerOptions: {
          shortcuts: [{
            text: 'This week',
            onClick(picker) {
              const end = new Date();
              let start = new Date();
              start.setFullYear(2024);
              start.setMonth(1);
              start.setDate(1);
              end.setTime(start.getTime() + 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: 'This month',
            onClick(picker) {
              const end = new Date();
              let start = new Date();
              start.setFullYear(2024);
              start.setMonth(1);
              start.setDate(1);
              end.setTime(start.getTime() + 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        orderCountDate: '',
        chartSettings: {
          xAxisType: 'time',
          area:true,
          axisSite: { right: ['orderAmount']},
        labelMap: {'orderCount': '订单数量', 'orderAmount': '订单金额'}},
        chartData: {
          columns: [],
          rows: []
        },
        loading: false,
        dataEmpty: false,
        img_home_order,
        img_home_today_amount,
        img_home_yesterday_amount
      }
    },
    created(){
      this.initOrderCountDate();
      this.getData();
    },
    methods:{
      handleDateChange(){
        this.getData();
      },
      initOrderCountDate(){
        let start = new Date();
        start.setFullYear(2024);
        start.setMonth(1);
        start.setDate(1);
        const end = new Date();
        end.setTime(start.getTime() + 1000 * 60 * 60 * 24 * 7);
        this.orderCountDate=[start,end];
      },
      getData(){
        setTimeout(() => {
          this.chartData = {
            columns: ['date', 'orderCount','orderAmount'],
            rows: []
          };
          for(let i=0;i<DATA_FROM_BACKEND.rows.length;i++){
            let item=DATA_FROM_BACKEND.rows[i];
            let currDate=str2Date(item.date);
            let start=this.orderCountDate[0];
            let end=this.orderCountDate[1];
            if(currDate.getTime()>=start.getTime()&&currDate.getTime()<=end.getTime()){
              this.chartData.rows.push(item);
            }
          }
          this.dataEmpty = false;
          this.loading = false
        }, 1000)
      }
    }
  }
</script>

<style scoped>
  .app-container {
    margin-top: 40px;
    margin-left: 120px;
    margin-right: 120px;
  }

  .address-layout {
  }

  .total-layout {
    margin-top: 20px;
  }

  .total-frame {
    border: 1px solid #DCDFE6;
    padding: 20px;
    height: 100px;
  }

  .total-icon {
    color: #409EFF;
    width: 60px;
    height: 60px;
  }

  .total-title {
    position: relative;
    font-size: 16px;
    color: #909399;
    left: 70px;
    top: -50px;
  }

  .total-value {
    position: relative;
    font-size: 18px;
    color: #606266;
    left: 70px;
    top: -40px;
  }

  .un-handle-layout {
    margin-top: 20px;
    border: 1px solid #DCDFE6;
  }

  .layout-title {
    color: #606266;
    padding: 15px 20px;
    background: #F2F6FC;
    font-weight: bold;
  }

  .un-handle-content {
    padding: 20px 40px;
  }

  .un-handle-item {
    border-bottom: 1px solid #EBEEF5;
    padding: 10px;
  }

  .overview-layout {
    margin-top: 20px;
  }

  .overview-item-value {
    font-size: 24px;
    text-align: center;
  }

  .overview-item-title {
    font-size: 14px;
    margin-top: 10px;
    text-align: center;
  }

  .out-border {
    border: 1px solid #DCDFE6;
  }

  .statistics-layout {
    margin-top: 20px;
    border: 1px solid #DCDFE6;
  }
  .mine-layout {
    position: absolute;
    right: 140px;
    top: 107px;
    width: 250px;
    height: 235px;
  }
  .address-content{
    padding: 20px;
    font-size: 18px
  }
</style>
