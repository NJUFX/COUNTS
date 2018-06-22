<template>
  <div>
    <a href="/static/balabala.csv" v-bind:download="downloadFileName">download data</a>
    <vuejs-heatmap selector="mySelector" v-bind:entries="myEntries" v-bind:color-range="color_range" tooltip-enabled="true" locale="myLocale">

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
        myEntries:[],
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
