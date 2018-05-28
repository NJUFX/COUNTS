<template>
  <div>
    <div style="position: absolute; top: 57px;left:0px;width: 100%; height: 100px">
      <div style="position: absolute; left: 50px;top: 3px;">
        <span style="color:#ff8432;font-size: 32px;float: left; margin-right: 10px">分析结果 </span>
        <span style="color: grey;font-size: 32px;float: left; margin-right: 10px">丨 </span>
        <span style="color:#4CAF50;font-size: 32px;float: left; margin-right: 10px">{{projectInfo.name}} </span>
      </div>
      <div style="position: absolute; left: 50px; top: 40px;">
        <p style="position: absolute; left: 10px; width: 70%; top:0px; text-align: justify; font-size: 16px; color: #747474">{{projectInfo.details}}</p>
      </div>
      <div style="position: absolute;top: 85px;left: 53px;font-size: 20px;color: #4CAF50">{{projectInfo.type}}</div>
    </div>
    <div style="position: absolute;top: 182px;left: 53px;font-size: 14px">
      移动鼠标至图片上查看分析结果
    </div>
    <div style="position: absolute; top: 200px; background-color: white;width: 99%;left: 1%">
      <div style="float: left">
        <div v-for="item in 11" :key="item">
          <el-card class="el_card" :body-style="{padding:'0px'}">
            <div style="position: relative;">
              <img style="width: 100%" src="../assets/gakki.jpg">
            </div>
            <div class="imgOnClick">
              <div class="background" ></div>
              <div style="position: absolute;top: 0px;">
                <!--用下面这个div作图-->
                <div id="pie" style="position: absolute;left: 25%; top: 25%;width: 50%; height: 50%;"></div>
              </div>
            </div>
          </el-card>
        </div>
      </div>
      <div style="width: 100%;height: 40px;float: left">
        <span style="font-size: 15px">已拉到底了，</span>
        <el-button type="text" style="font-size: 15px" @click="goMyProject">点此返回个人中心</el-button>
      </div>
    </div>

  </div>
</template>

<script>
  import echarts from 'echarts'
  export default {
    name: "result",
    data(){
      return{
        projectInfo:{
          name: '一个测试的项目',
          details: 'testttttttttttffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff' +
          'fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffftttttttttttttt',
          type: 'Detection',

        }
      }
    },
    created(){
    //  this.drawPieCharts()
    },
    methods:{
      goMyProject () {
        var path = '/' + localStorage.getItem('username')
        if (localStorage.getItem('identify') == 'worker') {
          this.$router.push({path: path + '/myProject'})
        } else if (localStorage.getItem('identify') == 'requester') {
          this.$router.push({path: path + '/requester'})
        } else if (localStorage.getItem('identify') == 'logout' || localStorage.getItem('username') == 'visitor') {
          this.openInfo("You haven't logged in")
        }
        window.location.reload()
      },
    }
  }
</script>

<style scoped>
  .imgOnClick {
    display: none;
  }

  .background{
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    background-color: white;
    opacity:0.6;
  }
  .el_card:hover .imgOnClick{
    display:block;
  }

  .el_card{
    border-radius: 0;
    margin: 0.8%;
    padding: 5px;
    padding-bottom: 2px;
    width: 30.2%;
    float: left;
    position: relative;
  }

</style>
