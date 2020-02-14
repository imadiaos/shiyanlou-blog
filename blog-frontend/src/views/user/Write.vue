<template>
  <div class="write">
    <el-card>
      <div slot="header" class="clearfix">
        <span>创建文章</span>
      </div>
      <el-input class="each" placeholder="请输入文章标题" v-model="title" maxlength="50" show-word-limit></el-input>
      <el-input class="each" placeholder="请输入文章标签，用逗号分隔" v-model="tags" maxlength="10" show-word-limit></el-input>
      <el-input class="each" placeholder="请输入文章内容" v-model="content" type="textarea" :autosize="{ minRows: 35, maxRows: 36}"></el-input>
      <el-button type="primary" style="width:100%" @click="release()" :disabled="isDisabled">发布</el-button>
    </el-card> 
  </div>
</template>

<script>
export default {
  name: "write",
  data(){
    return{
      title:'',
      content:'',
      tags:'',
      cid:'',
      isDisabled:false
    }
  },
  methods:{
    release(){
      this.isDisabled = true
      if(this.cid === ''){
        this.$axios.post('/content',{
          title:this.title,
          content:this.content,
          tags:this.tags
        }).then(res => {
          console.log(res)
          if(res.status === 200){
            this.$router.push('/')
            this.isDisabled = false
          }
          
        })
      }else{
        this.$axios.put('/content/' + this.cid,{
          title:this.title,
          content:this.content,
          tags:this.tags,
        }).then((res) => {
          if(res.status === 200){
            this.$message({
              message:'修改成功',
              type:'success'
            })
            this.isDisabled = false
            this.$router.push('home')
          }
          
        })
      }
      
    },
    getContentDeatil(){
      let cid = this.$route.query.cid
      this.$axios.get('/content/'+ cid).then(res => {
        if(res.status === 200){
          this.title = res.data.article.title
          this.content = res.data.article.content
          // console.log(res.data.tags)
          let tags = res.data.tags
          let alltag = ''
          for(let i=0; i<tags.length;i++){
            let {name} = tags[i]
            alltag += name + ','
          }
          this.tags = alltag
          this.cid = res.data.article.cid
        }
        
      })
    }
  },
  mounted(){
    if(this.$route.query.cid){
      this.getContentDeatil()
    }
    
  }
};
</script>

<style lang="stylus" scoped>
.each
  margin-bottom .5rem
</style>