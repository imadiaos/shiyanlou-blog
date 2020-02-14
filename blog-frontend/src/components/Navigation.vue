<template>
    <div class="navigation">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" router>
            <el-menu-item index="/">首页</el-menu-item>
            <el-menu-item v-if="islogin" @click="logout()" class="right">登出</el-menu-item>
            <el-menu-item v-else index="/login" class="right">登入</el-menu-item>
            <el-menu-item index="/register" class="right" v-show="!islogin">注册</el-menu-item>

            <el-submenu index="4" class="right" v-show="islogin">
                <template slot="title">个人中心</template>
                <el-menu-item index="/write">写博客</el-menu-item>
                <el-menu-item index="/home">博客管理</el-menu-item>
                <el-menu-item index="/profile">修改密码</el-menu-item>
            </el-submenu>
        </el-menu>
    </div>
</template>

<script>
import { mapMutations } from 'vuex'
export default {
    data() {
        return {
            activeIndex: "1",
        }
    },
    methods:{
        ...mapMutations(['changeLogin']),
        logout(){
            localStorage.removeItem('Authorization')
            this.changeLogin({Authorization:''})
            this.$message({
                message:'登出成功',
                type:'success'
            })
            this.$router.push('login')
        }
    },
    computed:{
        islogin(){
            console.log('~~~~~~~~~~~~~~~~~~~' + this.$store.state.Authorization)
            if(this.$store.state.Authorization){
                return true
            }else{
                return false
            }
        },
        
        
    }
}
</script>

<style lang="stylus" scoped>
    .navigation
        margin-bottom 1rem
        a
            text-decoration none
            display block
        .right
            float right
</style>