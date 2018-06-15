<template>
  <div>
    <el-button type="primary" @click="downloadData">click it</el-button>
  </div>
</template>

<script>
  export default {
    name: "test",
    data(){
      return{
        content : '时间,正常(0<=评分<70),疑似(70<=评分<90),欺诈(90<=评分<=100),\r\n' +
        '2017/09/07 00:00,0,0,0,\r\n' +
        '2017/09/07 01:00,0,0,0,\r\n' +
        '2017/09/07 02:00,0,0,0,\r\n' +
        '2017/09/07 03:00,0,0,0,\r\n' +
        '2017/09/07 04:00,0,0,0,\r\n' +
        '2017/09/07 05:00,0,0,0,\r\n' +
        '2017/09/07 06:00,0,0,0,\r\n' +
        '2017/09/07 07:00,0,0,0,\r\n' +
        '2017/09/07 08:00,0,0,0,\r\n' +
        '2017/09/07 09:00,0,0,0,\r\n',
        fileName : 'balabala.csv',
        type : 'text/csv; chartset=utf-8'
      }
    },
    methods:{
      downloadData(){
        const funcDownload = (content, filename, type = 'text/plain') => {
          let downLink = document.createElement('a');
          // 支持a链接下载？
          if (!('download' in downLink)) return false;
          downLink.download = filename;
          downLink.style.display = 'none';
          // 字符串内容转blob地址
          let blobURL = new Blob([content], {type});
          downLink.href = URL.createObjectURL(blobURL);
          // 触发下载
          document.body.appendChild(downLink);
          downLink.click();
          // 移除 a 节点
          document.body.removeChild(downLink);
        };
        funcDownload(this.content,this.fileName,this.type)
      }
    }
  }
</script>

<style scoped>

</style>
