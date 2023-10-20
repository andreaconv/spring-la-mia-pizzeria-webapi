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

  <div class="container bg-dark text-white">

    <h1 class="text-center">Edit Pizza</h1>

    <form @submit.prevent="storePizza">

      <div class="d-flex justify-content-between">

        <div class="mb-3 pe-3 w-25">
        
          <label for="nome" class="form-label">Nome</label>
          <input 
            type="text" 
            class="form-control" 
            id="nome" name="nome" 
            v-model="oldPizza.nome"
            placeholder="Inserire il nome">
        
        </div>

        <div class="mb-3 pe-3 w-75">
        
          <label for="descrizione" class="form-label">Descrizione</label>
          <input 
            type="text" 
            class="form-control" 
            id="descrizione" name="descrizione" 
            v-model="oldPizza.descrizione"
            placeholder="Inserire la descrizione">
        
        </div>

      </div>

      <div class="d-flex justify-content-between">
        
        <div class="mb-3 pe-3 d-inline-block w-25">
        
          <label for="prezzo" class="form-label">Prezzo</label>
          <input 
            type="number"
            step="0.01" 
            min="0.00"
            class="form-control" 
            id="prezzo" name="prezzo" 
            v-model="oldPizza.prezzo"
            placeholder="Inserire il prezzo">

        </div>
        
        <div class="mb-3 pe-3 d-inline-block w-75">
        
          <label for="foto" class="form-label">Foto</label>
          <input 
            type="text" 
            class="form-control" 
            id="foto" name="foto" 
            v-model="oldPizza.foto"
            placeholder="Inserire il link della foto">
          
        </div>
        
      </div>

      <input type="submit" class="btn btn-primary mb-3 me-3" value="UPDATE">

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
