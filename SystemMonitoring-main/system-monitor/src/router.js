 import { createRouter, createWebHistory } from 'vue-router';
 import Dashboard from './components/Dashboard.vue'; // Import Dashboard component
 import SystemHealth from './components/SystemHealth.vue'; // Import System Health component

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Dashboard, // Set default to Dashboard
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: Dashboard,
  },
  {
    path: '/system-health',
    name: 'SystemHealth',
    component: SystemHealth,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// export default router;
import { createRouter, createWebHistory } from 'vue-router';
import SystemHealth from './components/SystemHealth.vue'; // Import System Health component

const routes = [
  {
    path: '/',
    name: 'Home',
    redirect: '/dashboard', // Redirect root to /dashboard
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: SystemHealth, // Render SystemHealth component for the Dashboard route
  },
  {
    path: '/system-health',
    name: 'SystemHealth',
    component: SystemHealth, // Optional if you want a separate route
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
