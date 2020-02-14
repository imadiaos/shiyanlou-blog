<template>
    <div class="container">
        <el-card class="profile box">
            <h1>修改密码</h1>
            <el-form :model="form" :rules="rules" ref="edit" label-width="80px">
                <el-form-item label="新密码" prop="password">
                    <el-input type="password" v-model="form.password"></el-input>
                </el-form-item>
            </el-form>
             <el-button type="primary" @click="submitForm()">修改</el-button>
        </el-card>
    </div>
</template>

<script>
import { mapMutations } from 'vuex'
export default {
    name:'profile',
    data() {
        return {
            form:{
               password:'', 
            },            
            rules: {
                password: [
                    { required: true, message: '请输入新密码', trigger: 'blur'},
                    { min: 1, max: 100, message: '长度在1-100之间', trigger: 'blur' }
                ]
            }
        }
    },
    methods:{
        ...mapMutations(['changeLogin']),
        submitForm() {
            this.$refs['edit'].validate((valid) => {
                console.log(valid)
                if (valid) {
                    this.$axios.put('/user/password',{
                        password:this.form.password
                    }).then( res => {
                        if(res.status === 200){
                            this.$message({
                                message:'密码更新成功',
                                type:'success'
                            })
                            localStorage.removeItem('Authorization')
                            this.changeLogin({Authorization:''})
                            this.$router.push('login')
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