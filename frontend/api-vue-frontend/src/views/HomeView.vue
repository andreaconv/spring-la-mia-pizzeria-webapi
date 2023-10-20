<script setup>
//si usa ref bene o male come i data
import { onMounted, ref } from 'vue';
import axios from "axios";

const API_URL = "http://localhost:8080/api/v1.0";

const pizze = ref([]);

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
  <main class="bg-dark text-white">
    <div class="container py-5">

      <h1 class="text-danger text-center" v-if="pizze.length < 1">
        NON CI SONO PIZZE
      </h1>

      <div class="menu" v-if="pizze.length > 0">

        <h1>Le nostre pizze</h1>
        <form 
          @submit.prevent="getAllPizzas"
        >
          <!-- <input type="text" name="q" v-model="search" >
          <input type="submit" value="SEARCH"> -->
          <div class="d-flex justify-content-center">
            <div class="input-group my-3 w-25">
              <input
                type="text"
                class="form-control"
                name="q"
                v-model="search"
                placeholder="Cerca la pizza"
                aria-label="Example text with button addon"
                aria-describedby="button-addon1">
              <input class="btn btn-outline-success" type="submit" id="button-addon1" value="CERCA">
            </div>
          </div>
        </form>
        <table class="table table-striped table-dark">
          <thead>
            <tr>
              <th scope="col">Nome</th>
              <th scope="col">Descrizione</th>
              <th scope="col">Prezzo</th>
              <th scope="col">Dettagli</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="pizza in pizze"
              :key="pizza.id"
              >
              <td class="text-capitalize">{{ pizza.nome }}</td>
              <td>{{ pizza.descrizione }}</td>
              <td>€ {{ pizza.prezzo }}</td>
              <td>
<!-- TODO: aggiungere la rotta show per visualizzare il dettaglio -->
                <a title="Mostra" href="#" class="details">
                  <span class="btn btn-primary">
                    <i class="fa-solid fa-eye"></i>
                  </span>
                </a>
  
                <a title="Modifica" :href="`/edit/${pizza.id}`" class="details">
                  <span class="btn btn-warning">
                    <i class="fa-solid fa-pen-to-square text-white"></i>
                  </span>
                </a>
                
                <button title="Elimina" class="btn btn-danger" @click="pizzaDelete(pizza.id)">
                  <i class="fa-solid fa-trash-can"></i>
                </button>
  
              </td>
            </tr>
          </tbody>
        </table>
      </div>

    </div>
  </main>
</template>

<style scoped>
main{
  text-align: center;
}
ul{
  list-style: none;
}
a.details{
  margin-right: 0.5rem;
}

</style>
