<script setup>
//si usa ref bene o male come i data
import { onMounted, ref } from 'vue';
import axios from "axios";

const API_URL = "http://localhost:8080/api/v1.0";

const pizze = ref(null);

const search = ref("");

function getAllPizzas() {

  let param = "";
  if (search.value != null && search.value.length > 0)
    param = "?q=" + search.value;

  axios.get(API_URL + "/pizze" + param)
        .then(res => {

          const data = res.data;
          pizze.value = data;
      })
      .catch(err => console.log(err));

}

function pizzaDelete(id) {

  axios.delete(API_URL + "/pizze/" + id)
      .then(() => {

        getAllPizzas();
      })
      .catch(err => console.log(err))
}

onMounted(() => {

  getAllPizzas();

})

</script>

<template>
  <main>
    <h1>PIZZE</h1>
    <form 
      @submit.prevent="getAllPizzas"
    >
      <input type="text" name="q" v-model="search" >
      <input type="submit" value="SEARCH">
    </form>
    <ul>
      <li
        v-for="pizza in pizze"
        :key="pizza.id"
      >
        {{ pizza.nome }}

        <a :href="`/edit/${pizza.id}`">MODIFICA</a>

        <button @click="pizzaDelete(pizza.id)">X</button>
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
