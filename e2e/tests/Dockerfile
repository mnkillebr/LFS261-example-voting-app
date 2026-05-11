FROM node:18-slim

RUN apt-get update -qq && apt-get install -y --no-install-recommends \
    ca-certificates \
    curl \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY package.json ./
RUN npm install --omit=dev --no-audit --no-fund \
    && rm -rf /root/.npm
COPY . .
RUN chmod +x ./tests.sh
CMD ["./tests.sh"]
