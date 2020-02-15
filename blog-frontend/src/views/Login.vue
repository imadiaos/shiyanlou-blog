<template>
    <div class="container">
        <el-card class="login box">
            <h1>博客登录</h1>
            <el-form :model="form" :rules="rules" ref="login" label-width="80px">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="form.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="form.password"></el-input>
                </el-form-item>
            </el-form>
             <el-button type="primary" @click="submitForm()">登入</el-button>
        </el-card>
    </div>
</template>

<script>
import { mapMutations } from 'vuex'
export default {
    data() {
        return {
            form: {
                username: '',
                password: ''
            },
            rules: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur'},
                    { min: 1, max: 100, message: '长度在1-100之间', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur'},
                    { min: 1, max: 100, message: '长度在1-100之间', trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        ...mapMutations(['changeLogin']),
        submitForm() {
            let that = this
            this.$refs['login'].validate((valid) => {
                if (valid) {
                    this.$axios.post('/api/login', this.form).then( res => {
                        if(res.status === 200){
                            localStorage.setItem('Authorization', res.data)
                            that.changeLogin({Authorization:res.data})
                            console.log(that.$store.state)
                            that.$router.push('/')
                            this.$message.success('登录成功')
                        }
                        
                    } )
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        }
    }
}
</script>

<style lang="stylus" scoped>

</style>