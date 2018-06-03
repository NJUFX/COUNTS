<template>
    <div>
      <div>
        <happy-scroll class="happy_scroll" color="rgba(51,51,51,0.2)" hide-vertical="false">
          <div v-for="item in messageList" :key="item">
            <div class="info" @click="showInfo(item.index)">
              <span style="position:absolute; font-size: 16px; top: 5px; font-weight: bold;left: 5px; text-align: left;width: 200px; overflow: hidden; text-overflow:ellipsis;white-space: nowrap;">{{item.topic}}</span>
              <p style="position: absolute; font-size: 14px; top: 10px;overflow: hidden; text-overflow:ellipsis;white-space: nowrap; width: 275px; left: 5px">{{item.content}}</p>
              <div v-show="item.unread" style="background-color: #ff732f; position: absolute;right: 6px; top: 10px; width: 6px;height: 6px;border-radius: 50%"></div>
            </div>
          </div>
        </happy-scroll>
      </div>
      <div style="color:#4CAF50;font-size: 30px;position: absolute; top: 60px;left: 310px">系统消息丨Message</div>
      <div style="position: absolute; width: 800px;top: 130px; left: 330px; height: 350px;">
        <span style="font-size: 24px">{{topic}}</span>
        <p style="top: 150px; text-align: justify;font-size: 16px;">{{mainInfo}}</p>
      </div>
    </div>
</template>

<script>
  export default {
    name: "message",
    data(){
      return{
        mainInfo: '',
        messageList:[]
      }
    },
    created(){
      for(var i=0;i<15;i++){
        this.messageList.push({
          index: i,
          topic: 'topic '+i,
          content: '<el-badge :value="12" class="item">\n' +
          '  <el-button size="small">评论</el-button>\n' +
          '</el-badge>\n' +
          '<el-badge :value="3" class="item">\n' +
          '  <el-button size="small">回复</el-button>\n' +
          '</el-badge>\n' +
          '\n' +
          '<el-dropdown trigger="click">\n' +
          '  <span class="el-dropdown-link">\n' +
          '    点我查看<i class="el-icon-caret-bottom el-icon--right"></i>\n' +
          '  </span>\n' +
          '  <el-dropdown-menu slot="dropdown">\n' +
          '    <el-dropdown-item class="clearfix">\n' +
          '      评论\n' +
          '      <el-badge class="mark" :value="12" />\n' +
          '    </el-dropdown-item>\n' +
          '    <el-dropdown-item class="clearfix">\n' +
          '      回复\n' +
          '      <el-badge class="mark" :value="3" />\n' +
          '    </el-dropdown-item>\n' +
          '  </el-dropdown-menu>\n' +
          '</el-dropdown>' ,
          unread:true,
        })
      }
    },
    methods:{
      showInfo(i){
        this.topic = this.messageList[i].topic;
        this.mainInfo = this.messageList[i].content;
        this.messageList[i].unread = false;
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
    position: relative; width: 280px; height: 50px;background-color: white; border-bottom: 1px solid #d1d1d1;
  }
  .info:hover{
    cursor: pointer;
    background-color: #dcdcdc;
  }
</style>
