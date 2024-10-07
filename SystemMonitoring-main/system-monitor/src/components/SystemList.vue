
   <template>
    <div class="system-list">
      <h1>Systems</h1>
      <select v-model="selectedSystem">
        <option value="" disabled>Select a system</option>
        <option
          v-for="(system, index) in validSystems"
          :key="index"
          :value="system.system1_info"
        >
          {{ system.system1_info.hostname || `System ${index + 1}` }}
        </option>
      </select>
  
      <h2>Details</h2>
      <div v-if="selectedSystem" class="system-details">
        <p><strong>CPU Model:</strong> {{ selectedSystem.cpu_model }}</p>
        <p><strong>Total Memory:</strong> {{ selectedSystem.total_memory }}</p>
        <p><strong>Used Memory:</strong> {{ selectedSystem.used_memory }}</p>
        <p><strong>Uptime:</strong> {{ selectedSystem.uptime }}</p>
        <p><strong>WiFi:</strong> {{ selectedSystem.wifi }}</p>
        <p><strong>Battery:</strong> {{ selectedSystem.battery }}</p>
        <p><strong>SSH Info:</strong> {{ selectedSystem.ssh_info }}</p>
        <p><strong>Active Users:</strong> {{ selectedSystem.active_users }}</p>
        <p><strong>Timestamp:</strong> {{ formatTimestamp(selectedSystem.timestamp) }}</p>
      </div>
      <div v-else>
        <p>No systems selected.</p>
      </div>
    </div>
  </template>
  
  <script>
  

  </script>
  
  <style scoped>
  .system-list {
    padding: 20px;
    max-width: 600px;
    margin: auto;
    background-color: #f9f9f9;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  }
  
  select {
    margin-bottom: 15px;
    padding: 10px;
    width: 100%;
    border: 1px solid #ccc;
    border-radius: 4px;
    transition: border-color 0.3s;
  }
  
  select:focus {
    border-color: #007BFF;
    outline: none;
  }
  
  .system-details {
    background-color: #ffffff;
    border: 1px solid #e0e0e0;
    border-radius: 4px;
    padding: 10px;
  }
  
  h1 {
    color: #333;
    font-size: 24px;
  }
  
  h2 {
    color: #333;
    font-size: 20px;
  }
  
  p {
    margin: 5px 0;
    color: #555;
  }
  </style>
  