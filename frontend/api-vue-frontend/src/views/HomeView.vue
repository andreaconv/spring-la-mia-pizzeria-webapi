<script setup>
//si usa ref bene o male come i data
import { onMounted, ref } from 'vue';
import axios from "axios";

const API_URL = "http://localhost:8080/api/v1.0";

const pizze = ref(null);

onMounted(() => {

  axios.get(API_URL + "/pizze")
        .then(res => {

          const data = res.data;
          pizze.value = data;
      })
      .catch(err => console.log(err));

})

</script>

<template>
  <main>
    <h1>PIZZE</h1>
    <ul>
      <li
        v-for="pizza in pizze"
        :key="pizza.id"
      >
        {{ pizza.nome }}

        <a :href="`/edit/${pizza.id}`">MODIFICA</a>
      </li>
    </ul>
  </main>
</template>

<style scoped>
main{
  text-align: center;
}
ul{
  list-style: none;
}

</style>
