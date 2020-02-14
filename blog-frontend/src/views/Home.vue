<template>
  <div class="home">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>博客管理</span>
      </div>
      <div v-for="item in contents" :key="item.cid" class="text item" >
        <el-row >
          <el-col :span="20">
            <div style="line-height: 32px">
              <router-link :to="{path: '/contentDetail?cid='+item.cid}">{{item.title}}</router-link>
            </div>
          </el-col>
          <el-col :span="4">
            <el-button type="primary" size="small" @click="editContent(item.cid)">修改</el-button>
            <el-button type="danger" size="small" @click="delContent(item.cid)" :disabled="isDisabled">删除</el-button>
          </el-col>
        </el-row>
        <el-divider></el-divider>
      </div>
      <el-pagination
        layout="prev, pager, next"
        :page-count=count
        style="text-align: center"
        :current-page.sync=current_page
        @current-change="currentPageChange">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "home",
  data() {
    return {
      contents: [],
      count:0,
      current_page:1,
      isDisabled:false
    };
  },
  methods: {
    //获取博客列表
    getContentsList() {
      this.$axios.get("/user/contents", {
          params: {
            page: this.current_page,
            page_size: 10
          }
        }).then(res => {
          if(res.status === 200){
            this.contents = res.data.articles;
            this.count = res.data.count;
          }
          
        });
    },
    //删除博客
    delContent(cid){
      this.isDisabled = true
      this.$axios.delete('/content/'+ cid).then((res) => {
        if(res.status === 200){
          this.getContentsList()
          this.isDisabled = false
        }
        
      })
    },
    //修改博客
    editContent(cid){
      this.isDisabled = true
      this.$router.push({
        name:'write',
        query:{
          cid:cid
        }
      })
    },
    //获取当前页
    currentPageChange(val){
      this.current_page = val
      this.getContentsList()
    }
  },
  created(){
    this.getContentsList()
  }
};
</script>

<style lang="stylus" scoped>

</style>
