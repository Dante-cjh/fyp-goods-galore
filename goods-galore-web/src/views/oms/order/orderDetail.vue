<template> 
  <div class="detail-container">
    <div>
      <el-steps :active="formatStepStatus(order.status)" finish-status="success" align-center>
        <el-step title="Submit" :description="formatTime(order.createTime)"></el-step>
        <el-step title="Payment" :description="formatTime(order.paymentTime)"></el-step>
        <el-step title="Delivery" :description="formatTime(order.deliveryTime)"></el-step>
        <el-step title="Confirm" :description="formatTime(order.receiveTime)"></el-step>
        <el-step title="Review" :description="formatTime(order.commentTime)"></el-step>
      </el-steps>
    </div>
    <el-card shadow="never" style="margin-top: 15px">
      <div class="operate-container">
        <i class="el-icon-warning color-danger" style="margin-left: 20px"></i>
        <span class="color-danger">Order Status: {{order.status | formatStatus}}</span>
        <div class="operate-button-container" v-show="order.status===0">
<!--          <el-button size="mini" @click="showUpdateReceiverDialog">Change Receiver Info</el-button>-->
<!--          <el-button size="mini" @click="showUpdateMoneyDialog">修改费用信息</el-button>-->
          <el-button size="mini" @click="showMessageDialog">Send Message</el-button>
          <el-button size="mini" @click="showCloseOrderDialog">Close Order</el-button>
          <el-button size="mini" @click="showMarkOrderDialog">Remark Order</el-button>
        </div>
        <div class="operate-button-container" v-show="order.status===1">
<!--          <el-button size="mini" @click="showUpdateReceiverDialog">修改收货人信息</el-button>-->
          <el-button size="mini" @click="showMessageDialog">Send Message</el-button>
          <el-button size="mini">Close Order</el-button>
          <el-button size="mini" @click="showMarkOrderDialog">Remark Order</el-button>
        </div>
        <div class="operate-button-container" v-show="order.status===2||order.status===3">
          <el-button size="mini" @click="showLogisticsDialog">Order Logistics</el-button>
          <el-button size="mini" @click="showMessageDialog">Send Message</el-button>
          <el-button size="mini" @click="showMarkOrderDialog">Remark Order</el-button>
        </div>
        <div class="operate-button-container" v-show="order.status===4">
          <el-button size="mini" @click="handleDeleteOrder">Delete Order</el-button>
          <el-button size="mini" @click="showMarkOrderDialog">Remark Order</el-button>
        </div>
      </div>
      <div style="margin-top: 20px">
        <svg-icon icon-class="marker" style="color: #606266"></svg-icon>
        <span class="font-small">Basic Info</span>
      </div>
      <div class="table-layout">
        <el-row>
          <el-col :span="6" class="table-cell-title">Order SN</el-col>
<!--          <el-col :span="4" class="table-cell-title">Shipment Number</el-col>-->
          <el-col :span="6" class="table-cell-title">User Account</el-col>
          <el-col :span="6" class="table-cell-title">Payment Type</el-col>
          <el-col :span="6" class="table-cell-title">Order Source</el-col>
<!--          <el-col :span="4" class="table-cell-title">订单类型</el-col>-->
        </el-row>
        <el-row>
          <el-col :span="6" class="table-cell">{{order.orderSn}}</el-col>
<!--          <el-col :span="4" class="table-cell">暂无</el-col>-->
          <el-col :span="6" class="table-cell">{{order.memberUsername}}</el-col>
          <el-col :span="6" class="table-cell">{{order.payType | formatPayType}}</el-col>
          <el-col :span="6" class="table-cell">{{order.sourceType | formatSourceType}}</el-col>
<!--          <el-col :span="4" class="table-cell">{{order.orderType | formatOrderType}}</el-col>-->
        </el-row>
<!--        <el-row>-->
<!--          <el-col :span="4" class="table-cell-title">配送方式</el-col>-->
<!--          <el-col :span="4" class="table-cell-title">物流单号</el-col>-->
<!--          <el-col :span="4" class="table-cell-title">自动确认收货时间</el-col>-->
<!--          <el-col :span="4" class="table-cell-title">订单可得优币</el-col>-->
<!--          <el-col :span="4" class="table-cell-title">订单可得成长值</el-col>-->
<!--          <el-col :span="4" class="table-cell-title">活动信息</el-col>-->
<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-col :span="4" class="table-cell">{{order.deliveryCompany | formatNull}}</el-col>-->
<!--          <el-col :span="4" class="table-cell">{{order.deliverySn | formatNull}}</el-col>-->
<!--          <el-col :span="4" class="table-cell">{{order.autoConfirmDay}}天</el-col>-->
<!--          <el-col :span="4" class="table-cell">{{order.integration}}</el-col>-->
<!--          <el-col :span="4" class="table-cell">{{order.growth}}</el-col>-->
<!--          <el-col :span="4" class="table-cell">-->
<!--            <el-popover-->
<!--              placement="top-start"-->
<!--              title="活动信息"-->
<!--              width="200"-->
<!--              trigger="hover"-->
<!--              :content="order.promotionInfo">-->
<!--              <span slot="reference">{{order.promotionInfo | formatLongText}}</span>-->
<!--            </el-popover>-->
<!--          </el-col>-->
<!--        </el-row>-->
      </div>
      <div style="margin-top: 20px">
        <svg-icon icon-class="marker" style="color: #606266"></svg-icon>
        <span class="font-small">Supermarket Info</span>
      </div>
      <div class="table-layout">
        <el-row>
          <el-col :span="6" class="table-cell-title">Name</el-col>
          <el-col :span="6" class="table-cell-title">Phone</el-col>
          <el-col :span="6" class="table-cell-title">Post Code</el-col>
          <el-col :span="6" class="table-cell-title">Address</el-col>
        </el-row>
        <el-row>
          <el-col :span="6" class="table-cell">{{order.receiverName}}</el-col>
          <el-col :span="6" class="table-cell">{{order.receiverPhone}}</el-col>
          <el-col :span="6" class="table-cell">{{order.receiverPostCode}}</el-col>
          <el-col :span="6" class="table-cell">{{order | formatAddress}}</el-col>
        </el-row>
      </div>
      <div style="margin-top: 20px">
        <svg-icon icon-class="marker" style="color: #606266"></svg-icon>
        <span class="font-small">Product List</span>
      </div>
      <el-table
        ref="orderItemTable"
        :data="order.orderItemList"
        style="width: 100%;margin-top: 20px" border>
          <el-table-column label="Pic" width="120" align="center">
          <template slot-scope="scope">
            <img :src="scope.row.productPic" style="height: 80px">
          </template>
        </el-table-column>
        <el-table-column label="Name" align="center">
          <template slot-scope="scope">
            <p>{{scope.row.productName}}</p>
            <p>Brand: {{scope.row.productBrand}}</p>
          </template>
        </el-table-column>
        <el-table-column label="Price/SN." width="120" align="center">
          <template slot-scope="scope">
            <p>Price: ￥{{scope.row.productPrice}}</p>
            <p>SN: {{scope.row.productSn}}</p>
          </template>
        </el-table-column>
        <el-table-column label="Attr" width="120" align="center">
          <template slot-scope="scope">
            {{scope.row.productAttr | formatProductAttr}}
          </template>
        </el-table-column>
        <el-table-column label="Quantity" width="120" align="center">
          <template slot-scope="scope">
            {{scope.row.productQuantity}}
          </template>
        </el-table-column>
        <el-table-column label="Total" width="120" align="center">
          <template slot-scope="scope">
            ￥{{scope.row.productPrice*scope.row.productQuantity}}
          </template>
        </el-table-column>
      </el-table>
      <div style="float: right;margin: 20px">
        Total: <span class="color-danger">￥{{order.totalAmount}}</span>
      </div>
      <div style="margin-top: 60px">
        <svg-icon icon-class="marker" style="color: #606266"></svg-icon>
        <span class="font-small">Operation History</span>
      </div>
      <el-table style="margin-top: 20px;width: 100%"
                ref="orderHistoryTable"
                :data="order.historyList" border>
        <el-table-column label="Operation Time"  width="180" align="center">
          <template slot-scope="scope">
            {{formatTime(scope.row.createTime)}}
          </template>
        </el-table-column>
        <el-table-column label="Order Status"  width="140" align="center">
          <template slot-scope="scope">
            {{scope.row.orderStatus | formatStatus}}
          </template>
        </el-table-column>
        <el-table-column label="Payment Status"  width="140" align="center">
          <template slot-scope="scope">
            {{scope.row.orderStatus | formatPayStatus}}
          </template>
        </el-table-column>
        <el-table-column label="Delivery Status"  width="140" align="center">
          <template slot-scope="scope">
            {{scope.row.orderStatus | formatDeliverStatus}}
          </template>
        </el-table-column>
        <el-table-column label="Remark" align="center">
          <template slot-scope="scope">
            {{scope.row.note}}
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="Send Message"
               :visible.sync="messageDialogVisible"
               width="40%">
      <el-form :model="message"
               ref="receiverInfoForm"
               label-width="150px">
        <el-form-item label="Title:">
          <el-input v-model="message.title" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="Content:">
          <el-input v-model="message.content" type="textarea" rows="3">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="messageDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="handleSendMessage">Confirm</el-button>
      </span>
    </el-dialog>
    <el-dialog title="Close Order"
               :visible.sync="closeDialogVisible"
               width="40%">
      <el-form :model="closeInfo"
               label-width="150px">
        <el-form-item label="Operation Remark:">
          <el-input v-model="closeInfo.note" type="textarea" rows="3">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="handleCloseOrder">Confirm</el-button>
      </span>
    </el-dialog>
    <el-dialog title="Remark Order"
               :visible.sync="markOrderDialogVisible"
               width="40%">
      <el-form :model="markInfo"
               label-width="150px">
        <el-form-item label="Operation Remark:">
          <el-input v-model="markInfo.note" type="textarea" rows="3">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="markOrderDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="handleMarkOrder">Confirm</el-button>
      </span>
    </el-dialog>
    <logistics-dialog v-model="logisticsDialogVisible"></logistics-dialog>
  </div>
</template>
<script>
  import {getOrderDetail,updateReceiverInfo,updateMoneyInfo,closeOrder,updateOrderNote,deleteOrder} from '@/api/order';
  import LogisticsDialog from '@/views/oms/order/components/logisticsDialog';
  import {formatDate} from '@/utils/date';
  import VDistpicker from 'v-distpicker';
  const defaultReceiverInfo = {
    orderId:null,
    receiverName:null,
    receiverPhone:null,
    receiverPostCode:null,
    receiverDetailAddress:null,
    receiverProvince:null,
    receiverCity:null,
    receiverRegion:null,
    status:null
  };
  export default {
    name: 'orderDetail',
    components: { VDistpicker, LogisticsDialog},
    data() {
      return {
        id: null,
        order: {},
        receiverDialogVisible:false,
        receiverInfo:Object.assign({},defaultReceiverInfo),
        moneyDialogVisible:false,
        moneyInfo:{orderId:null, freightAmount:0, discountAmount:0,status:null},
        messageDialogVisible:false,
        message: {title:null, content:null},
        closeDialogVisible:false,
        closeInfo:{note:null,id:null},
        markOrderDialogVisible:false,
        markInfo:{note:null},
        logisticsDialogVisible:false
      }
    },
    created() {
      this.id = this.list = this.$route.query.id;
      getOrderDetail(this.id).then(response => {
        this.order = response.data;
      });
    },
    filters: {
      formatNull(value) {
        if(value===undefined||value===null||value===''){
          return 'None';
        }else{
          return value;
        }
      },
      formatLongText(value) {
        if(value===undefined||value===null||value===''){
          return 'None';
        }else if(value.length>8){
          return value.substr(0, 8) + '...';
        }else{
          return value;
        }
      },
      formatPayType(value) {
        if (value === 1) {
          return 'Alipay';
        } else if (value === 2) {
          return 'WeChat Pay';
        } else {
          return 'Unpaid';
        }
      },
      formatSourceType(value) {
        if (value === 1) {
          return 'APP order';
        } else {
          return 'PC order';
        }
      },
      formatOrderType(value) {
        if (value === 1) {
          return '秒杀订单';
        } else {
          return '正常订单';
        }
      },
      formatAddress(order) {
        let str = order.receiverProvince;
        if (order.receiverCity != null) {
          str += "  " + order.receiverCity;
        }
        str += "  " + order.receiverRegion;
        str += "  " + order.receiverDetailAddress;
        return str;
      },
      formatStatus(value) {
        if (value === 1) {
          return 'Undelivered';
        } else if (value === 2) {
          return 'Delivered';
        } else if (value === 3) {
          return 'Done';
        } else if (value === 4) {
          return 'Closed';
        } else if (value === 5) {
          return 'Invalid';
        } else {
          return 'Unpaid';
        }
      },
      formatPayStatus(value) {
        if (value === 0) {
          return 'Unpaid';
        } else if(value===4){
          return 'Refunded';
        }else{
          return 'Paid';
        }
      },
      formatDeliverStatus(value) {
        if (value === 0||value === 1) {
          return 'Undelivered';
        } else {
          return 'Delivered';
        }
      },
      formatProductAttr(value){
        if(value==null){
          return '';
        }else{
          let attr = JSON.parse(value);
          let result='';
          for(let i=0;i<attr.length;i++){
            result+=attr[i].key;
            result+=":";
            result+=attr[i].value;
            result+=";";
          }
          return result;
        }
      }
    },
    methods: {
      onSelectRegion(data){
        this.receiverInfo.receiverProvince=data.province.value;
        this.receiverInfo.receiverCity=data.city.value;
        this.receiverInfo.receiverRegion=data.area.value;
      },
      formatTime(time) {
        if (time == null || time === '') {
          return '';
        }
        let date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd hh:mm:ss')
      },
      formatStepStatus(value) {
        if (value === 1) {
          //待发货
          return 2;
        } else if (value === 2) {
          //已发货
          return 3;
        } else if (value === 3) {
          //已完成
          return 4;
        }else {
          //待付款、已关闭、无限订单
          return 1;
        }
      },
      showUpdateReceiverDialog(){
        this.receiverDialogVisible=true;
        this.receiverInfo={
          orderId:this.order.id,
          receiverName:this.order.receiverName,
          receiverPhone:this.order.receiverPhone,
          receiverPostCode:this.order.receiverPostCode,
          receiverDetailAddress:this.order.receiverDetailAddress,
          receiverProvince:this.order.receiverProvince,
          receiverCity:this.order.receiverCity,
          receiverRegion:this.order.receiverRegion,
          status:this.order.status
        }
      },
      handleUpdateReceiverInfo(){
        this.$confirm('是否要修改收货信息?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          updateReceiverInfo(this.receiverInfo).then(response=>{
            this.receiverDialogVisible=false;
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
            getOrderDetail(this.id).then(response => {
              this.order = response.data;
            });
          });
        });
      },
      showUpdateMoneyDialog(){
        this.moneyDialogVisible=true;
        this.moneyInfo.orderId=this.order.id;
        this.moneyInfo.freightAmount=this.order.freightAmount;
        this.moneyInfo.discountAmount=this.order.discountAmount;
        this.moneyInfo.status=this.order.status;
      },
      handleUpdateMoneyInfo(){
        this.$confirm('是否要修改费用信息?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          updateMoneyInfo(this.moneyInfo).then(response=>{
            this.moneyDialogVisible=false;
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
            getOrderDetail(this.id).then(response => {
              this.order = response.data;
            });
          });
        });
      },
      showMessageDialog(){
        this.messageDialogVisible=true;
        this.message.title=null;
        this.message.content=null;
      },
      handleSendMessage(){
        this.$confirm('Confirm to send message?', 'Tip', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          this.messageDialogVisible=false;
          this.$message({
            type: 'success',
            message: 'Send Success!'
          });
        });
      },
      showCloseOrderDialog(){
        this.closeDialogVisible=true;
        this.closeInfo.note=null;
        this.closeInfo.id=this.id;
      },
      handleCloseOrder(){
        this.$confirm('Confirm to close?', 'Tip', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
            let params = new URLSearchParams();
            params.append("ids",[this.closeInfo.id]);
            params.append("note",this.closeInfo.note);
            closeOrder(params).then(response=>{
              this.closeDialogVisible=false;
              this.$message({
                type: 'success',
                message: 'Order closed successfully!'
              });
              getOrderDetail(this.id).then(response => {
                this.order = response.data;
              });
            });
        });
      },
      showMarkOrderDialog(){
        this.markOrderDialogVisible=true;
        this.markInfo.id=this.id;
        this.closeOrder.note=null;
      },
      handleMarkOrder(){
        this.$confirm('Confirm to remark?', 'Tip', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          let params = new URLSearchParams();
          params.append("id",this.markInfo.id);
          params.append("note",this.markInfo.note);
          params.append("status",this.order.status);
          updateOrderNote(params).then(response=>{
            this.markOrderDialogVisible=false;
            this.$message({
              type: 'success',
              message: 'Remark success!'
            });
            getOrderDetail(this.id).then(response => {
              this.order = response.data;
            });
          });
        });
      },
      handleDeleteOrder(){
        this.$confirm('Confirm to delete?', 'Tip', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          let params = new URLSearchParams();
          params.append("ids",[this.id]);
          deleteOrder(params).then(response=>{
            this.$message({
              message: 'Delete Success!',
              type: 'success',
              duration: 1000
            });
            this.$router.back();
          });
        })
      },
      showLogisticsDialog(){
        this.logisticsDialogVisible=true;
      }
    }
  }
</script>
<style scoped>
  .detail-container {
    width: 80%;
    padding: 20px 20px 20px 20px;
    margin: 20px auto;
  }

  .operate-container {
    background: #F2F6FC;
    height: 80px;
    margin: -20px -20px 0;
    line-height: 80px;
  }

  .operate-button-container {
    float: right;
    margin-right: 20px
  }

  .table-layout {
    margin-top: 20px;
    border-left: 1px solid #DCDFE6;
    border-top: 1px solid #DCDFE6;
  }

  .table-cell {
    height: 60px;
    line-height: 40px;
    border-right: 1px solid #DCDFE6;
    border-bottom: 1px solid #DCDFE6;
    padding: 10px;
    font-size: 14px;
    color: #606266;
    text-align: center;
    overflow: hidden;
  }

  .table-cell-title {
    border-right: 1px solid #DCDFE6;
    border-bottom: 1px solid #DCDFE6;
    padding: 10px;
    background: #F2F6FC;
    text-align: center;
    font-size: 14px;
    color: #303133;
  }
</style>


