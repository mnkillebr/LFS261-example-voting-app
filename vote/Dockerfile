# Define base stage that uses official python runtime base image
FROM python:3.11-slim AS base

# Add curl for healthcheck
RUN apt-get update && apt-get install -y --no-install-recommends curl && rm -rf /var/lib/apt/lists/*

# Set application directory
WORKDIR /usr/local/app

# Install our requirements.txt
COPY requirements.txt ./requirements.txt
RUN pip install --no-cache-dir -r requirements.txt

# Define a stage for dev to  watch for filesys changes
FROM base AS dev
RUN pip install watchdog
ENV FLASK_ENV=development
CMD ["python", "app.py"]

# Define final stage that will bundle the app for production
FROM base AS prod

# Copy code from curr folder to working dir inside container
COPY . .

# Make port 80 available for links and/or publish
EXPOSE 80

# Define our command to run when launching container
CMD ["gunicorn", "app:app", "-b", "0.0.0.0:80", "--log-file", "-", "--access-logfile", "-", "--workers", "4", "--keep-alive", "0"]

