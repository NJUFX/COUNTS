<template>
  <div>
    <a href="/static/balabala.csv" v-bind:download="downloadFileName">download data</a>
    <vuejs-heatmap v-bind:entries="myEntries" v-bind:color-range="color_range" tooltip-enabled="true" locale="myLocale">

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
        myEntries:[
          {
            'counting':100,
            'created_at':'2018-06-21'
          }
        ],
        color_range:['#c9ecec','#09b3af'],
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
