<template>
  <div>
    <div id="1"  style="position: absolute;left: 100px; top: 100px; right: 5%; bottom: 5%; width:640px; height: 300px;"></div>
  </div>

</template>

<script>
  import MyLoading from './myLoading/myLoading'
  import echarts from 'echarts'
  require('echarts-wordcloud');
  export default {
    components: {MyLoading},
    name: "test",
    data(){
      return{
        index: 1
      }
    },
    mounted(){
    //  console.log(document.getElementById('1'))
   //   window.onload(this.drawStringCloud())
      this.drawStringCloud()
    },
    methods:{
      drawStringCloud(){
        var container = document.getElementById('1');
        var myChart = echarts.init(container);
        var option = {
          title: {
            text: 'Google Trends',
          },
          tooltip: {
            show: true
          },
          series: [{
            name: 'Google Trends',
            type: 'wordCloud',
            size: ['80%', '80%'],
            textRotation: [0, 45, 90, -45],
            textPadding: 0,
            autoSize: {
              enable: true,
              minSize: 14
            },
            textStyle: {
              normal: {
                fontFamily: 'sans-serif',
                fontWeight: 'bold',
                // Color can be a callback function or a color string
                color: function () {
                  // Random color
                  return 'rgb(' + [
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160)
                  ].join(',') + ')';
                }
              },
              emphasis: {
                shadowBlur: 8,
                shadowColor: '#d1d1d1'
              }
            },
            data: [
              {
                name: "Sam S Club",
                value: 10000,
                itemStyle: {
                  normal:{
                    color: 'rgb(' + [
                      120,
                      16,
                      68
                    ].join(',') + ')'
                  }
                }
              },
              {
                name: "Helllo world",
                value: 6181,
                itemStyle: {
                  normal: {
                    color: 'black'
                  }
                }
              },
              {
                name: "gersgts",
                value: 6861,

              },

              {
                name: "Jurassic World",
                value: 4055,
              },
              {
                name: "Charter Communications",
                value: 2467,

              },
              {
                name: "Chick Fil A",
                value: 2244,

              },
              {
                name: "Planet Fitness",
                value: 1898,

              },
              {
                name: "Pitch Perfect",
                value: 1484,

              },
              {
                name: "Farrah Abraham",
                value: 366,

              },
              {
                name: "Rita Ora",
                value: 360,

              },
            ]
          }]
        }
        myChart.setOption(option);
      }
    },

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
