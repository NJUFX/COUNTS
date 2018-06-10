<template>
    <div>
      <div>
        <happy-scroll class="happy_scroll" color="rgba(51,51,51,0.2)" hide-vertical="false">
          <div v-for="item in messageList" :key="item">
            <div class="info" @click="showInfo(item.index)">
              <span style="position:absolute; font-size: 15px; top: 5px; font-weight: bold;left: 5px; text-align: left;width: 200px; overflow: hidden; text-overflow:ellipsis;white-space: nowrap;">{{item.topic}}</span>
              <p style="position: absolute; font-size: 12px; top: 18px;overflow: hidden; text-overflow:ellipsis;white-space: nowrap; width: 275px; left: 5px">{{item.content}}</p>
              <div v-show="item.unread" style="background-color: #ff732f; position: absolute;right: 6px; top: 10px; width: 6px;height: 6px;border-radius: 50%"></div>
            </div>
          </div>
        </happy-scroll>
      </div>
      <div style="color:#4CAF50;font-size: 30px;position: absolute; top: 60px;left: 310px">系统消息丨Message</div>
      <div style="position: absolute; width: 800px;top: 130px; left: 330px; height: 350px;">
        <span style="font-size: 24px">{{topic}}</span>
        <p style="top: 150px; text-align: justify;font-size: 16px;">{{mainInfo}}</p>
        <el-button v-show="goProject" type="text" style="font-size: 16px">点此进入项目</el-button>
      </div>
    </div>
</template>

<script>
  export default {
    name: "message",
    data(){
      return{
        topic: '',
        goProject:false,
        mainInfo: '',
        messageList:[]
      }
    },
    created(){
      for(var i=0;i<15;i++){
        this.messageList.push({
          index: i,
          topic: '自动化标注 审查',
          content: "自动化项目已添加到您的项目列表中，您需要对自动化标注的结果进行审查。\n审查过程：对每张图片的结果正确与否选择“是”或“否”。",
          unread:true,

        })
      }
    },
    methods:{
      showInfo(i){
        this.topic = this.messageList[i].topic;
        this.mainInfo = this.messageList[i].content;
        this.messageList[i].unread = false;
        this.goProject = true;
      }
    }
  }
</script>

<style scoped>
  .happy_scroll{
    width: 300px;
    left: 10px;
    position: absolute;
    top: 60px;
    height: 480px;
 //  bottom: 60px;

  }
  .info{
    position: relative; width: 280px; height: 60px;background-color: white; border-bottom: 1px solid #d1d1d1;
  }
  .info:hover{
    cursor: pointer;
    background-color: #dcdcdc;
  }
</style>
