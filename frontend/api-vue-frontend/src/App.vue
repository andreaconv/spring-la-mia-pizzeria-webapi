<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <body class="bg-dark text-white">
    <header class="bg-dark text-white">
        <nav class="text-center py-3">
          <RouterLink to="/">Pizze</RouterLink>
          <RouterLink to="/create">New Pizza</RouterLink>
        </nav>
    </header>

    <RouterView />
  </body>
</template>

<style scoped>
nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

</style>
