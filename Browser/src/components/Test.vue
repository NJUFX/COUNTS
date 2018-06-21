<template>
  <div>
    <vuejs-heatmap v-bind:selector="mySelector" v-bind:entries="myEntries" tooltip-unit="Action" tooltip-enabled='true'>

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
        }
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
