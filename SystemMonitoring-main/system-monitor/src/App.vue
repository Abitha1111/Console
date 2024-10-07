
<template>
  <div id="app">
    <!-- <SideMenu /> -->
    <TableSelector />
    <div class="main-content">
      <router-view /> <!-- This will render the currently active route component -->
    </div>
  </div>
</template>

<script>
//import SideMenu from './components/SideMenu.vue'; // Import the SideMenu component
import TableSelector from './components/TableSelector.vue';
export default {
  name: 'App',
  components: {
   // SideMenu,
     TableSelector,
  },
};
</script>

<style>
body {
  font-family: 'Arial', sans-serif;
  background-color: #f4f4f4;
  margin: 0;
  padding: 0;
}

.main-content {
  margin-left: 260px; /* Leave space for the side menu */
  padding: 20px;
}

.container {
  max-width: 1200px;
  margin: 20px auto;
  padding: 20px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}
</style>
