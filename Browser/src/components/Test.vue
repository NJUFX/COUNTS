<template>
  <div>
<<<<<<< HEAD
    <vuejs-heatmap v-bind:selector="mySelector" v-bind:entries="myEntries" tooltip-unit="Action" tooltip-enabled='true'>
=======
    <a href="/static/balabala.csv" v-bind:download="downloadFileName">download data</a>
    <vuejs-heatmap selector="mySelector" v-bind:entries="myEntries" v-bind:color-range="color_range" tooltip-enabled="true" locale="myLocale">
>>>>>>> 8a2315d878402e4fbfc5d295f42e54f31fb58acc

    </vuejs-heatmap>
  </div>
</template>

<script>
  import vueheadmap from 'vuejs-heatmap'
  import VuejsHeatmap from "vuejs-heatmap/src/VuejsHeatmap";
  export default {
    components: {VuejsHeatmap},
    name: "test",
    data(){
      return{
        mySelector:'mySelector',
        enable:true,
        myEntries:[
          {
            'counting':100,
            'created_at':'2018-06-21'
          },
          {
            'counting':100,
<<<<<<< HEAD
            'created_at':'2017-07-21'
          },
          {
            'counting':10,
            'created_at':'2018-04-21'
          },
          {
            'counting':60,
            'created_at':'2018-04-30'
          },
          {
            'counting':4,
            'created_at':'2018-01-21'
=======
            'created_at':'2018-06-18'
>>>>>>> 8a2315d878402e4fbfc5d295f42e54f31fb58acc
          },
        ],
        color_range:['#c9ecec','#09b3af'],
        myLocale:{
          months: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
          days: ['S', 'M', 'T', 'W', 'T', 'F', 'S'],
          No: 'No',
          on: 'on',
          Less: 'Less',
          More: 'More'
<<<<<<< HEAD
        }
=======
        },
        downloadFileName:'test csv'
>>>>>>> 8a2315d878402e4fbfc5d295f42e54f31fb58acc
      }
    },
    methods:{
      downloadData(){

      }
    }
  }
</script>

<style scoped>

</style>
