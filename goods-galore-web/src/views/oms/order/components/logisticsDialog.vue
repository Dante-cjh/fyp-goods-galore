<template> 
  <el-dialog title="Order Logistics"
             :visible.sync="visible"
             :before-close="handleClose"
             width="40%">
    <el-steps direction="vertical"
              :active="6"
              finish-status="success"
              space="50px">
      <el-step  v-for="item in logisticsList"
                :key="item.name"
                :title="item.name"
                :description="item.time"></el-step>
    </el-steps>
  </el-dialog>
</template>
<script>
  const defaultLogisticsList=[
    {name: 'Order submitted, awaiting payment',time:'2024-04-01 12:00:00 '},
    {name: 'Order Payment Successful',time:'2024-04-01 12:01:00 '},
    {name: 'Scanning for lower level locations',time:'2024-04-01 13:00:00 '},
    {name: 'Unloading and scanning at the distribution center',time:'2024-04-02 14:00:00 '},
    {name: 'Delivering from the company',time:'2024-04-03 12:00:00 '},
    {name: 'Arrive at the destination network, express delivery will come soon',time:'2024-04-05 10:00:00 '},
    {name: 'Order signed for and looking forward to serving you again!',time:'2024-04-06 9:00:00 '}
  ];
  export default {
    name:'logisticsDialog',
    props: {
      value: Boolean
    },
    computed:{
      visible: {
        get() {
          return this.value;
        },
        set(visible){
          this.value=visible;
        }
      }
    },
    data() {
      return {
        logisticsList:Object.assign({},defaultLogisticsList)
      }
    },
    methods:{
      emitInput(val) {
        this.$emit('input', val)
      },
      handleClose(){
        this.emitInput(false);
      }
    }
  }
</script>
<style></style>


