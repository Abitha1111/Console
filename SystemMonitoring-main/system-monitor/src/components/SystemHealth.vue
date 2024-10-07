
<template>
 
  <div class="grid">
    <div class="card" v-for="(systemInfo, key) in systemData" :key="key">
      <div class="card-header">
        <h2>{{ key }}</h2>
      </div>
      <div class="metric">
        <i class="fa-solid fa-user-tie"></i>
        <span class="label">Hostname:</span>
        <span>{{ systemInfo.hostname }}</span>
      </div>
      <div class="metric">
        <i class="fas fa-clock"></i>
        <span class="label">Timestamp:</span>
        <span class="timestamp">{{ formatTimestamp(systemInfo.timestamp) }}</span>
      </div>
      <div class="card-body">
        <div class="metric">
          <i class="fas fa-desktop"></i>
          <span class="label">CPU Model:</span>
          <span>{{ systemInfo.cpu_model }}</span>
        </div>
        <div class="metric">
          <i class="fas fa-memory"></i>
          <span class="label">Memory:</span>
          <span>{{ systemInfo.used_memory }} / {{ systemInfo.total_memory }} ({{ getMemoryPercentage(systemInfo.used_memory, systemInfo.total_memory) }}%)</span>
        </div>
        <div class="metric">
          <i class="fa-solid fa-gauge"></i>
          <span class="label">Uptime:</span>
          <span>{{ systemInfo.uptime }}</span>
        </div>
        <div class="metric">
          <i class="fas fa-wifi"></i>
          <span class="label">WiFi:</span>
          <span>{{ systemInfo.wifi }}</span>
        </div>
        <div class="metric">
          <i class="fas fa-network-wired"></i>
          <span class="label">IP Address:</span>
          <span>{{ systemInfo.ip }}</span>
        </div>
        <div class="metric battery-status">
          <span class="label">Battery:</span>
          <div class="battery-container">
            <div class="battery">
              <div class="battery-fill" :style="{ width: batteryPercentage(systemInfo.battery) + '%' }"></div>
            </div>
            <span class="percentage">{{ batteryPercentage(systemInfo.battery) }}%</span>
            <span class="battery-state">{{ batteryState(systemInfo.battery) }}</span>
            <span class="time-to-empty">{{ batteryTimeToEmpty(systemInfo.battery) }}</span>
          </div>
        </div>
        <div class="metric ssh-status">
          <i class="fas fa-server"></i>
          <span class="label">SSH Info:</span>
          <span :class="{'ssh-active': isSshActive(systemInfo.ssh_info), 'ssh-inactive': isSshInactive(systemInfo.ssh_info)}">
            {{ systemInfo.ssh_info }}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '@fortawesome/fontawesome-free/css/all.css';
import { reactive } from 'vue';

export default {
  setup() {
    const systemData = reactive({}); // Reactive object for system data

    const formatTimestamp = (timestamp) => {
      const date = new Date(timestamp);
      return date.toLocaleString();
    };

    const batteryPercentage = (batteryInfo) => {
      const percentageMatch = batteryInfo.match(/percentage:\s*(\d+)%/);
      return percentageMatch ? Math.min(100, parseInt(percentageMatch[1], 10)) : 0;
    };

    const batteryTimeToEmpty = (batteryInfo) => {
      const timeMatch = batteryInfo.match(/time to (full|empty):\s*([\d.]+ hours|[\d.]+ minutes)/);
      return timeMatch ? timeMatch[2] : 'N/A';
    };

    const batteryState = (batteryInfo) => {
      const stateMatch = batteryInfo.match(/state:\s*(\w+)/);
      return stateMatch ? stateMatch[1] : 'unknown';
    };

    const getMemoryPercentage = (usedMemory, totalMemory) => {
      const used = parseFloat(usedMemory);
      const total = parseFloat(totalMemory);
      return ((used / total) * 100).toFixed(2);
    };

    const isSshActive = (sshInfo) => {
      return sshInfo && sshInfo.toLowerCase().includes('active ssh connections');
    };

    const isSshInactive = (sshInfo) => {
      return sshInfo && sshInfo.toLowerCase().includes('no active ssh connections');
    };

    const fetchData = () => {
      try {
        const socket = new WebSocket('ws://192.168.54.226:8080/SystemMonitoring/clientws'); // Update with your WebSocket URL

        socket.onopen = () => {
          console.log('WebSocket connection established');
        };

        socket.onmessage = (event) => {
          try {
            const message = event.data.trim();

            if (message.includes('Message from queue:')) {
              const jsonData = message.split('Message from queue: ')[1].trim();
              console.log('Raw JSON Data:', jsonData);

              // Add a debugging log to see what we're trying to parse
              console.log('Attempting to parse:', jsonData);

              if (jsonData && jsonData !== "No message in the queue.") {
                // Attempt to parse the JSON data
                let data;
                try {
                  data = JSON.parse(jsonData);
                } catch (jsonError) {
                  console.error('JSON parsing error:', jsonError);
                  return; // Exit if JSON parsing fails
                }

                // Loop over the keys in the received data
                Object.keys(data).forEach((key) => {
                  // If the key already exists, update its values
                  systemData[key] = { ...systemData[key], ...data[key] };
                });

                console.log('Updated system data:', systemData);
              } else {
                console.warn('No valid JSON data received.');
              }
            }
          } catch (error) {
            console.error('Error handling WebSocket message:', error, event.data);
          }
        };

        socket.onerror = (error) => {
          console.error('WebSocket error:', error);
        };

        socket.onclose = () => {
          console.log('WebSocket connection closed');
        };
      } catch (error) {
        console.error('Error setting up WebSocket:', error);
      }
    };

    fetchData(); // Call fetchData when the component is mounted

    return {
      systemData,
      formatTimestamp,
      batteryPercentage,
      batteryTimeToEmpty,
      batteryState,
      getMemoryPercentage,
      isSshActive,
      isSshInactive,
    };
  }
};
</script>

<style scoped>
.grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
  margin-left: 30px;
  justify-content: center;
  align-items: center;
}

.card {
  background-color: #fff;
  border-radius: 8px;
  border: 1px solid #2196F3;
  padding: 15px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.3s;
  cursor: pointer;
}

.card:hover {
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-body {
  margin-top: 10px;
}

.metric {
  display: flex;
  align-items: center;
  margin: 18px 0;
}

.metric .label {
  font-weight: bold;
  margin-right: 5px;
}

.metric i {
  color: #2196F3;
  margin-right: 15px;
}

.battery-status {
  display: flex;
  align-items: center;
}

.battery-container {
  display: flex;
  align-items: center;
}

.battery {
  width: 40px;
  height: 15px;
  border: 1px solid #2196F3;
  border-radius: 5px;
  position: relative;
  overflow: hidden;
}

.battery-fill {
  height: 100%;
  background: #2196F3;
  transition: width 0.3s ease;
}

.percentage {
  font-weight: bold;
  color: #2196F3;
  font-size: 16px;
  margin-left: 10px;
}

.time-to-empty {
  margin-left: 10px;
  font-weight: bold;
  color: #333;
}

.battery-state {
  margin-left: 10px;
}

.ssh-active {
  color: red;
}

.ssh-inactive {
  color: green;
}
</style>
