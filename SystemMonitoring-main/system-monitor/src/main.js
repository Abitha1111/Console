 import { createApp } from 'vue'
 import App from './App.vue'

// createApp(App).mount('#app')
//import { createApp } from 'vue';
//import App from './App.vue';
import { createRouter, createWebHistory } from 'vue-router';
//import Dashboard from './components/DashBoard.vue'; // Import your components
import SystemHealth from './components/SystemHealth.vue'; 
import SystemList from './components/SystemList.vue';// Import your components
//import ElementPlus from 'element-plus';
//import 'element-plus/dist/index.css'; // Import Element Plus CSS

// Define your routes


 const routes = [
   // { path: '/', component: Dashboard },
  { path: '/', component: SystemHealth },
   { path: '/system-list', component: SystemList },
 ];

// Create the router instance


 const router = createRouter({
  history: createWebHistory(),
   routes,
 });

// Create and mount the app
createApp(App)
  .use(router) 
  //.use(ElementPlus)
  .mount('#app');
