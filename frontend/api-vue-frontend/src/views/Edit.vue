<script setup>
import { onMounted, ref } from 'vue';
import axios from "axios";
import { useRoute } from 'vue-router';

//con route posso risalire all'id passato come parametro dalla rotta
const route = useRoute();
const API_URL = "http://localhost:8080/api/v1.0";

const oldPizza = ref({
  nome: null,
  descrizione: null,
  foto: null,
  prezzo: null
});

function storePizza() {

  // console.log("pizza modificata", JSON.stringify(oldPizza.value, null, 2));
  console.log("pizza modificata", oldPizza.value);
  
  axios.post(API_URL + "/pizze/" + pizzaId.value, oldPizza.value)
  .then(() => {})
  .catch(err => console.log(err));
}

const pizzaId = ref(null);

onMounted(() => {
  //recupero l'id della pizza da modificare
  pizzaId.value = route.params.id;

axios.get(API_URL + "/pizze/" + pizzaId.value)
      .then(res => {

        const data = res.data;
        oldPizza.value = data;
        // console.log("pizza da modificare", JSON.stringify(oldPizza.value, null, 2));
    })
    .catch(err => console.log(err));

})

</script>

<template>
  <div class="edit">
    <h1>Edit Pizza</h1>
    <form @submit.prevent="storePizza">

      <label for="nome">Name</label>
      <input type="text" name="nome" placeholder="Name" v-model="oldPizza.nome">
      <br>

      <label for="descrizione">Description</label>
      <input type="text" name="descrizione" placeholder="Description" v-model="oldPizza.descrizione">
      <br>

      <label for="foto">Photos</label>
      <input type="text" name="foto" placeholder="Photos" v-model="oldPizza.foto">
      <br>

      <label for="prezzo">Price</label>
      <input type="number" step="0.01" 
						min="0.00" name="prezzo" placeholder="Price" v-model="oldPizza.prezzo">
      <br>

      <input type="submit" value="UPDATE">

    </form>
  </div>
</template>

<style>
.edit{
  width: 1200px;
  margin: auto;
}

h1{
  text-align: center;
}
</style>
