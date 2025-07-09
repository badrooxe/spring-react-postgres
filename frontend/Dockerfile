# -----------------------------
# STEP 1: Build the frontend
# -----------------------------
FROM node:20-alpine
# AS builder

# Set working directory inside the container
WORKDIR /app

# Copy package.json & package-lock.json (or yarn.lock)
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the project files
COPY . .

# Build the static files (Vite → creates /dist)
#RUN npm run build

EXPOSE 5173

# Run dev server
CMD ["npm", "run", "dev"]


# -----------------------------
# STEP 2: Serve with Nginx
# -----------------------------
#FROM nginx:alpine

# Copy built frontend from builder stage into Nginx's default public folder
#COPY --from=builder /app/dist /usr/share/nginx/html

# Copy custom nginx.conf if you have one (optional)
# COPY nginx.conf /etc/nginx/nginx.conf

# Expose port 80 to the host
#EXPOSE 80

# Start Nginx server (default CMD in nginx:alpine does this automatically)
