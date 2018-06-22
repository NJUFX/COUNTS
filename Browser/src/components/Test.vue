<template>
  <div>
    <a href="/static/balabala.csv" v-bind:download="downloadFileName">download data</a>
<<<<<<< HEAD
    <vuejs-heatmap selector="mySelector" v-bind:entries="myEntries" v-bind:color-range="color_range" tooltip-enabled="true" locale="myLocale"></vuejs-heatmap>
=======
    <vuejs-heatmap selector="mySelector" v-bind:entries="myEntries" v-bind:color-range="color_range" tooltip-enabled="true" locale="myLocale">

    </vuejs-heatmap>
>>>>>>> 5afa09f0dbfece29af3aeb4b6222405ef8a9798b
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
<<<<<<< HEAD
        myEntries:[
          {
            'counting':100,
            'created_at':'2018-06-21'
          },
          {
            'counting':100,
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
            'created_at':'2018-06-18'
          },
        ],
=======
        myEntries:[],
>>>>>>> 5afa09f0dbfece29af3aeb4b6222405ef8a9798b
        color_range:['#c9ecec','#09b3af'],
        myLocale:{
          months: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
          days: ['S', 'M', 'T', 'W', 'T', 'F', 'S'],
          No: 'No',
          on: 'on',
          Less: 'Less',
          More: 'More'
        },
        downloadFileName:'test csv'
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
