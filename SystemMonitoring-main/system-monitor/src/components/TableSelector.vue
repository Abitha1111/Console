<template>
  <div>
    <h1>Select a Table</h1>
    <select v-model="selectedTable" @change="fetchData" style="margin-bottom: 20px;">
      <option value="" disabled>Select a table</option>
      <option v-for="(table, index) in tableNames" :key="index" :value="table">{{ table }}</option>
    </select>

    <table v-if="tableData.length" class="modern-table">
      <thead>
        <tr>
          <th v-for="(column, index) in displayedColumns" :key="index">{{ column.label }}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(row, rowIndex) in tableData" :key="rowIndex" @click="showDrawer(row)">
          <td>{{ row.hostname }}</td>
          <td>{{ row.ip }}</td>
          <td>{{ row.cpu_model }}</td>
          <td>{{ row.total_memory }}</td>
          <td>{{ row.used_memory }}</td>
        </tr>
      </tbody>
    </table>
    <div ref="observer" class="scroll-trigger"></div>

    <div v-if="drawerVisible" class="drawer" @click.self="closeDrawer">
      <div class="drawer-content">
        <button class="close-button" @click="closeDrawer">x</button>
        <h3>{{ selectedTable }}</h3>
        <table class="drawer-table">
          <tbody>
            <tr>
              <td class="key">Hostname:</td>
              <td class="value">{{ selectedRow.hostname }}</td>
            </tr>
            <tr>
              <td class="key">IP:</td>
              <td class="value">{{ selectedRow.ip }}</td>
            </tr>
            <tr>
              <td class="key">CPU Model:</td>
              <td class="value">{{ selectedRow.cpu_model }}</td>
            </tr>
            <tr>
              <td class="key">Total Memory:</td>
              <td class="value">{{ selectedRow.total_memory }}</td>
            </tr>
            <tr>
              <td class="key">Used Memory:</td>
              <td class="value">{{ selectedRow.used_memory }}</td>
            </tr>
            <tr>
              <td class="key">Uptime:</td>
              <td class="value">{{ selectedRow.uptime }}</td>
            </tr>
            <tr>
              <td class="key">WiFi:</td>
              <td class="value">{{ selectedRow.wifi }}</td>
            </tr>
            <tr>
              <td class="key">Battery:</td>
              <td class="value">{{ selectedRow.battery }}</td>
            </tr>
            <tr>
              <td class="key">SSH Info:</td>
              <td class="value">{{ selectedRow.ssh_info }}</td>
            </tr>
            <tr>
              <td class="key">Timestamp:</td>
              <td class="value">{{ formatTimestamp(selectedRow.timestamp) }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TableSelector',
  data() {
    return {
      tableNames: ['thulasi_info', 'praveen_info', 'mounish_info'], // Your table names
      selectedTable: '',
      tableData: [],
      displayedColumns: [
        { label: 'Hostname' },
        { label: 'IP Address' },
        { label: 'CPU Model' },
        { label: 'Total Memory' },
        { label: 'Used Memory' },
      ],
      drawerVisible: false,
      selectedRow: null,
      offset: 0,
      limit: 20,
      loading: false,
      observer: null,
    };
  },
  methods: {
    async fetchData() {
      if (!this.selectedTable || this.loading) return; // Avoid loading multiple times
      this.loading = true;

      try {
        // Update the URL with your server's IP and context path
        const response = await fetch(`http://192.168.209.121:8088/SystemMonitor/fetchData?table=${this.selectedTable}&offset=${this.offset}&limit=${this.limit}`);
        if (!response.ok) throw new Error('Failed to fetch data');
        
        const newData = await response.json();
        this.tableData = [...this.tableData, ...newData]; // Append new data
        this.offset += this.limit;
        this.loading = false;
      } catch (error) {
        console.error('Error fetching data:', error);
        this.loading = false;
      }
    },
    showDrawer(row) {
      this.selectedRow = row; // Set the selected row data
      this.drawerVisible = true; // Open the drawer
    },
    closeDrawer() {
      this.drawerVisible = false; // Close the drawer
      this.selectedRow = null; // Clear selected row
    },
    setupObserver() {
      this.observer = new IntersectionObserver(entries => {
        if (entries[0].isIntersecting && !this.loading) {
          this.fetchData(); // Load more data when user scrolls to the bottom
        }
      });
      this.observer.observe(this.$refs.observer);
    },
  },
  mounted() {
    this.setupObserver();
  },
  beforeUnmount() {
    if (this.observer) {
      this.observer.disconnect();
    }
  },
};
</script>

<style scoped>
/* Modern table design */
.modern-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
  background-color: #f9f9f9;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
}

.modern-table th {
  background-color: #51575e;
  color: white;
  padding: 12px;
  text-align: left;
}

.modern-table td {
  padding: 12px;
  text-align: left;
  color: #333;
  border-bottom: 1px solid #ddd;
  transition: background-color 0.2s ease-in-out;
}

.modern-table tr:hover {
  background-color: #e0e0e0;
}

/* Drawer styles */
.drawer {
  position: fixed;
  top: 0;
  right: 0;
  width: 30%;
  height: 100%;
  background-color: #d1d9e0;
  color: rgb(5, 3, 3);
  box-shadow: -2px 0 10px rgba(0, 0, 0, 0.2);
  z-index: 1000;
  padding: 20px;
  display: flex;
  flex-direction: column;
}

.drawer-content {
  overflow-y: auto;
  margin-top: 20px;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  background-color: transparent;
  border: none;
  font-size: 24px;
  color: rgb(0, 0, 0);
  cursor: pointer;
  transition: transform 0.2s ease-in-out;
}
.close-button:hover {
  transform: scale(1.1);
}

/* Drawer table styles */
.drawer-table {
  width: 100%;
  border-collapse: collapse;
}

.drawer-table tr {
  border-bottom: 1px solid #080303;
}

.drawer-table td {
  padding: 10px;
}

.drawer-table .key {
  font-weight: bold;
  width: 30%;
  border-right: 1px solid #030000;
}

.drawer-table .value {
  width: 70%;
}
</style>
