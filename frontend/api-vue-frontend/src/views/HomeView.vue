<script setup>
//si usa ref bene o male come i data
import { onMounted, ref } from 'vue';
import axios from "axios";
const API_URL = "http://localhost:8080/api/v1.0";

onMounted(() => {

  axios.get(API_URL + "/pizze")
        .then(res => {

          const data = res.data;
          console.log("axios data: " + JSON.stringify(data, null, 2));
      })
      .catch(err => console.log(err));

})

</script>

<template>
  <main>
    <h1>HELLO WORLD</h1>
  </main>
</template>

<style scoped>
main{
  text-align: center;
}

</style>
