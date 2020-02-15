<template>
    <div class="container">
        <el-card class="register box">
            <h1>博客注册</h1>
            <el-form :model="form" :rules="rules" ref="register" label-width="80px">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="form.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="form.password"></el-input>
                </el-form-item>
            </el-form>
            <el-button type="primary" @click="submitForm()">注册</el-button>
        </el-card>
    </div>
    
</template>

<script>
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
        submitForm() {
            this.$refs['register'].validate((valid) => {
                if (valid) {
                    this.$axios.post('/api/register', this.form).then( res => {
                        console.log(res)
                        if(res.status === 200){
                            this.$message({
                                message:'注册成功',
                                type:'success'
                            })
                            this.$router.push('/login')
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