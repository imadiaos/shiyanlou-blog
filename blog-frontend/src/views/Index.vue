<template>
  <div class="index">
    <el-card class="box-card">
      <el-input placeholder="请输入查找的标签" v-model="tag" style="margin-bottom:1rem" @change="searchContent()">
        <i class="el-icon-search el-input__icon" slot="suffix" @click="searchContent()"></i>
      </el-input>
      <el-row >
        <div
          class="grid-content"
          style="border-bottom:1px solid #f0f2f7"
          v-for="item in contents"
          :key="item.cid"
          @click="contentDetail(item.cid)"
        >
          <div class="title">{{item.title}}</div>
          <div class="content">{{item.content}}</div>
          <div class="des"></div>
        </div>
      </el-row>
      <el-pagination
        style="text-align: center"
        layout="prev, pager, next"
        :page-count="count"
        :current-page.sync="current_page"
        @current-change="currentPageChange"
      ></el-pagination>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "index",
  components: {},
  methods: {
    //获取文章列表
    getContentsList() {
      this.$axios
        .get("/contents", {
          params: {
            page: this.current_page,
            page_size: 8
          }
        })
        .then(res => {
          if (res.status === 200) {
            this.contents = res.data.articles;
            this.count = res.data.count;
          }
        });
    },
    //跳转详情页
    contentDetail(cid) {
      this.$router.push({
        name: "contentDetail",
        query: {
          cid: cid
        }
      });
    },
    //查找
    searchContent(){
      if(this.tag === ''){
        this.getContentsList()
      }else{
        this.$axios.get('/tag/'+ this.tag,{
          page:this.current_page,
          page_size:8
        }).then(res => {
          if(res.status === 200){
            this.contents = res.data.articles
            this.count = res.data.count
          }
        })
      }
      
    },
    //当前页
    currentPageChange(val) {
      this.current_page = val;
      this.getContentsList();
    }
  },
  created() {
    this.getContentsList();
  },
  data() {
    return {
      contents: [],
      tag: "",
      count: 0,
      current_page: 1
    };
  }
};
</script>

<style lang="stylus" scoped>
.el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.bg-purple-dark {
  background: #99a9bf;
}

.title {
  font-size: 30px;
  line-height: 30px;
  text-align: left;
  font-weight: bold;
  margin-bottom: 10px;
}

.content {
  text-align: left;
  line-height: 1.6rem;
  text-overflow: -o-ellipsis-lastline;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.tags {
  text-align: left;
}

.el-tag {
  margin: 5px 10px;
}

.grid-content
  &:hover
    cursor: pointer
</style>