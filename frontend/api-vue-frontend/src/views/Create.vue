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

function clearForm() {
  newPizza.value = ref({...emptyPizza.value});
}

function storePizza() {

  axios.post(API_URL + "/pizze", newPizza.value)
      .then(() => {clearForm();})
      .catch(err => console.log(err));
  
  console.log("pizza creata", newPizza.value);
}

</script>

<template>

  <div class="container bg-dark text-white">

    <h1 class="text-center">Create New Pizza</h1>

    <form @submit.prevent="storePizza">

      <div class="d-flex justify-content-between">

        <div class="mb-3 pe-3 w-25">
				
          <label for="nome" class="form-label">Nome</label>
          <input 
            type="text" 
            class="form-control" 
            id="nome" name="nome" 
            v-model="newPizza.nome"
            placeholder="Inserire il nome">
        
        </div>

        <div class="mb-3 pe-3 w-75">
				
          <label for="descrizione" class="form-label">Descrizione</label>
          <input 
            type="text" 
            class="form-control" 
            id="descrizione" name="descrizione" 
            v-model="newPizza.descrizione"
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
						v-model="newPizza.prezzo"
						placeholder="Inserire il prezzo">

				</div>
				
				<div class="mb-3 pe-3 d-inline-block w-75">
				
					<label for="foto" class="form-label">Foto</label>
					<input 
						type="text" 
						class="form-control" 
						id="foto" name="foto" 
						v-model="newPizza.foto"
						placeholder="Inserire il link della foto">
					
				</div>
				
			</div>

      <input type="submit" class="btn btn-primary mb-3 me-3" value="CREATE">
      <button class="btn btn-danger mb-3" @click="clearForm">CANCEL</button>

    </form>
  </div>
</template>

<style scoped>
</style>
