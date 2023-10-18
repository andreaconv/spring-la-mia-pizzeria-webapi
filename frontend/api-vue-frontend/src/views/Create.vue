<script setup>
import { onMounted, ref } from 'vue';
import axios from "axios";
const API_URL = "http://localhost:8080/api/v1.0";

const emptyPizza = ref({
  nome: null,
  descrizione: null,
  foto: null,
  prezzo: null
});

const newPizza = ref({...emptyPizza.value});

console.log("empty pizza", emptyPizza.value);
console.log("new pizza", newPizza.value);

function clearForm() {
  newPizza.value = ref({...emptyPizza.value});
}

function storePizza() {

  axios.post(API_URL + "/pizze", newPizza.value)
      .then(() => {clearForm();})
      .catch(err => console.log(err));
}

</script>

<template>
  <div class="about">
    <h1>Create New Pizza</h1>
    <form @submit.prevent="storePizza">

      <label for="nome">Name</label>
      <input type="text" name="nome" placeholder="Name" v-model="newPizza.nome">
      <br>

      <label for="descrizione">Description</label>
      <input type="text" name="descrizione" placeholder="Description" v-model="newPizza.descrizione">
      <br>

      <label for="foto">Photos</label>
      <input type="text" name="foto" placeholder="Photos" v-model="newPizza.foto">
      <br>

      <label for="prezzo">Price</label>
      <input type="number" step="0.01" 
						min="0.00" name="prezzo" placeholder="Price" v-model="newPizza.prezzo">
      <br>

      <input type="submit" value="CREATE">
      <button @click="clearForm">CANCEL</button>

    </form>
  </div>
</template>

<style>
.about{
  width: 1200px;
  margin: auto;
}

h1{
  text-align: center;
}
</style>
