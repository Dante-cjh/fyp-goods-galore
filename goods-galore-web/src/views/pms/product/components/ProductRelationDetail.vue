<template>
  <div style="margin-top: 50px">
    <el-form :model="value"
             ref="productRelationForm"
             label-width="120px"
             class="form-inner-container"
             size="small">
      <el-form-item label="Related Topics:">
        <el-transfer
          style="display: inline-block"
          filterable
          :filter-method="filterMethod"
          filter-placeholder="Please enter topic name:"
          v-model="selectSubject"
          :titles="subjectTitles"
          :data="subjectList">
        </el-transfer>
      </el-form-item>
      <el-form-item label="Associated Preferences:">
        <el-transfer
          style="display: inline-block"
          filterable
          :filter-method="filterMethod"
          filter-placeholder="Please enter preferred name"
          v-model="selectPrefrenceArea"
          :titles="prefrenceAreaTitles"
          :data="prefrenceAreaList">
        </el-transfer>
      </el-form-item>
      <el-form-item style="text-align: center">
        <el-button size="medium" @click="handlePrev">Previous step, product attributes</el-button>
        <el-button type="primary" size="medium" @click="handleFinishCommit">Done, submit the product</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import {fetchListAll as fetchSubjectList} from '@/api/subject'
  import {fetchList as fetchPrefrenceAreaList} from '@/api/prefrenceArea'

  export default {
    name: "ProductRelationDetail",
    props: {
      value: Object,
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        //所有专题列表
        subjectList: [],
        //专题左右标题
        subjectTitles: ['To choose', 'Chosen'],
        //所有专题列表
        prefrenceAreaList: [],
        //专题左右标题
        prefrenceAreaTitles: ['To choose', 'Chosen']
      };
    },
    created() {
      this.getSubjectList();
      this.getPrefrenceAreaList();
    },
    computed:{
      //选中的专题
      selectSubject:{
        get:function () {
          let subjects =[];
          if(this.value.subjectProductRelationList==null||this.value.subjectProductRelationList.length<=0){
            return subjects;
          }
          for(let i=0;i<this.value.subjectProductRelationList.length;i++){
            subjects.push(this.value.subjectProductRelationList[i].subjectId);
          }
          return subjects;
        },
        set:function (newValue) {
          this.value.subjectProductRelationList=[];
          for(let i=0;i<newValue.length;i++){
            this.value.subjectProductRelationList.push({subjectId:newValue[i]});
          }
        }
      },
      //选中的优选
      selectPrefrenceArea:{
        get:function () {
          let prefrenceAreas =[];
          if(this.value.prefrenceAreaProductRelationList==null||this.value.prefrenceAreaProductRelationList.length<=0){
            return prefrenceAreas;
          }
          for(let i=0;i<this.value.prefrenceAreaProductRelationList.length;i++){
            prefrenceAreas.push(this.value.prefrenceAreaProductRelationList[i].prefrenceAreaId);
          }
          return prefrenceAreas;
        },
        set:function (newValue) {
          this.value.prefrenceAreaProductRelationList=[];
          for(let i=0;i<newValue.length;i++){
            this.value.prefrenceAreaProductRelationList.push({prefrenceAreaId:newValue[i]});
          }
        }
      }
    },
    methods: {
      filterMethod(query, item) {
        return item.label.indexOf(query) > -1;
      },
      getSubjectList() {
        fetchSubjectList().then(response => {
          let list = response.data;
          for (let i = 0; i < list.length; i++) {
            this.subjectList.push({
              label: list[i].title,
              key: list[i].id
            });
          }
        });
      },
      getPrefrenceAreaList() {
        fetchPrefrenceAreaList().then(response=>{
          let list = response.data;
          for (let i = 0; i < list.length; i++) {
            this.prefrenceAreaList.push({
              label: list[i].name,
              key: list[i].id
            });
          }
        });
      },
      handlePrev(){
        this.$emit('prevStep')
      },
      handleFinishCommit(){
        this.$emit('finishCommit',this.isEdit);
      }
    }
  }
</script>

<style scoped>

</style>
