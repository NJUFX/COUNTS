<template>
  <div>
    <div class="modal" style="display: block;">  </div>
    <my-loading style="position: absolute;left: 50%; margin-left: -82px;top: 180px;" type="rainbow"></my-loading>
    <span style="position: absolute;left: 50%; margin-left: -60px;top: 310px;font-size: 18px" >正在执行自动化标注</span>
    <span style="position: absolute;left: 50%; margin-left: -75px;top: 335px;font-size: 12px;color: #747474" >执行完成后将会发送邮件至您的邮箱</span>
    <el-button type="primary" size="small" style="position: absolute;left: 50%;margin-left: -35px;top: 358px;font-size: 16px">后台执行</el-button>
  </div>

</template>

<script>
  import MyLoading from './myLoading/myLoading'
    export default {
      components: {MyLoading},
      name: "test"
    }
</script>

<style scoped>
  .modal {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background-color: white;
  //  filter:alpha(opacity:60);
    opacity:0.3;
 //   -moz-opacity:0.3;
  }
</style>
