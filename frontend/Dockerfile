# -----------------------------
# STEP 1: Build frontend
# -----------------------------
FROM node:20-alpine AS build

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

# -----------------------------
# STEP 2: Serve with nginx
# -----------------------------
FROM nginx:alpine

COPY --from=build /app/dist /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

# nginx default CMD is sufficient to serve the static files.