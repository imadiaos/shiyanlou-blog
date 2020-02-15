<template>
  <div class="content_detail">
    <el-card>
      <div slot="header" class="clearfix">
        <h1 style="margin: .3rem 0">{{content_detail.title}}</h1>
      </div>
      <p v-for="p in content_detail.content" :key="p" style="margin-bottom: .5rem; line-height: 1.5rem;letter-spacing: 1px;line-height: 2em;padding: .5em .3em;" >
        {{p}}
      </p>
      <p>
        <el-tag v-for="item in tagsList" :key="item.tid" style="margin-right: .5rem">{{item.name}}</el-tag>
      </p>
    </el-card>
  </div>
</template>

<script>
export default {
  name:'contentDetail',
  data(){
    return {
      content_detail:[],
      tagsList:[]
    }
  },
  methods:{
    getContentDeatil(){
      let cid = this.$route.query.cid
      this.$axios.get('/api/content/'+ cid).then(res => {
        if(res.status === 200){
          res.data.article.content = res.data.article.content.split('\n');
          this.content_detail = res.data.article;
          this.tagsList = res.data.tags
        }
        
      })
    }
  },
  created(){
    this.getContentDeatil()
  }
}
</script>

<style lang="stylus" scoped>

</style>