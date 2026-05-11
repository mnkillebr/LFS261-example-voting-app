const { io } = require('socket.io-client');

const resultUrl = process.argv[2] || process.env.RESULT_URL;
const timeoutMs = Number(process.env.RESULT_TIMEOUT_MS || 15000);

if (!resultUrl) {
  console.error('Result URL required');
  process.exit(2);
}

let settled = false;

const socket = io(resultUrl, {
  transports: ['websocket', 'polling'],
  reconnection: false,
  timeout: 5000,
});

const finish = (code, total) => {
  if (settled) {
    return;
  }

  settled = true;
  socket.disconnect();

  if (code === 0) {
    process.stdout.write(String(total));
  }

  process.exit(code);
};

const timer = setTimeout(() => finish(1), timeoutMs);

socket.on('connect_error', () => finish(1));

socket.on('scores', (payload) => {
  clearTimeout(timer);

  try {
    const votes = typeof payload === 'string' ? JSON.parse(payload) : payload;
    const total = Number(votes.a || 0) + Number(votes.b || 0);
    finish(0, total);
  } catch (error) {
    finish(1);
  }
});
