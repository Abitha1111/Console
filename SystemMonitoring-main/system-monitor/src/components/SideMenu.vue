<template>
  <div class="side-menu" v-for="(systemInfo, key) in systemData" :key="key">
    <h1 class="title">System Health Monitor</h1>
    <ul>
       <li><router-link to="/system-health">Dashboard</router-link></li>


       <!-- mouse hover function -->
    
      <li @mouseover="showDropdown = true" @mouseleave="showDropdown = false">
        <a href="#">Systems</a> 
        <ul v-show="showDropdown" class="dropdown">
          <li v-for="(systemInfo, key, index) in systemData" :key="index">
  <router-link :to="`/system-${index+1}`">{{ key }}</router-link>
</li>

        </ul>
      </li>
      
    </ul>
  </div>
</template>

<script>
export default {
  name: 'SideMenu',
  data() {
    return {
      showDropdown: false, // State to control dropdown visibility
      systemData: {} // Store system data as an object instead of an array
      };
    },
    methods: {
      async fetchData() {
        try {
          const response = await fetch('/datas.json');
          if (!response.ok) throw new Error('Failed to fetch data');
          const data = await response.json();
          
          // Store the system data directly as an object, with each system being accessible by key
          this.systemData = data;
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      }
    },
    mounted() {
      this.fetchData();
    }
  };
</script>

#### Styles:

```vue
<style scoped>
.side-menu {
  width: 250px;
  background-color: #140244;
  color: white;
  padding: 20px;
  position: fixed;
  height: 100%;
  left: 0;
  top: 0;
}

.side-menu h1 {
  margin-top: 0;
}

.side-menu ul {
  list-style: none;
  padding: 0;
}

.side-menu li {
  margin: 15px 0;
  font-size: large;
}

.side-menu h1 {
  font-weight: 500;
  font-size: larger;
}

.side-menu a {
  color: white;
  text-decoration: none;
}

.side-menu a:hover {
  text-decoration: underline;
}

.dropdown {
  list-style: none;
  grid-area: 10;
  padding-left: 20px; /* Adds some indentation for dropdown items */
  background-color:#140244; /* Darker background for dropdown */
  /* border-left: 2px solid white; */
  display: none;
  position: relative;
}

.dropdown li {
  margin: 10px 0;
}

.dropdown a {
  color: white;
}

/* .dropdown a:hover {
  text-decoration: underline;
} */

/* Show dropdown on hover */
li:hover .dropdown {
  display: block;
}
/* .button{
  colour:rgb(2, 3, 61)
  

} */
</style>