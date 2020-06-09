# vuejs config
# install NODE
FROM node:lts-alpine as build-stage
LABEL MAINTAINER SungUk Hong "hsw924@gmail.com"

# app 만들기- nodejs application folder
#RUN mkdir -p /homepage
WORKDIR /homepage
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build


# nginx config
FROM nginx:stable-alpine as production-stage
RUN rm /etc/nginx/conf.d/default.conf
COPY ./nginx/homepage.conf /etc/nginx/conf.d/homepage.conf

COPY --from=build-stage ./homepage/dist /usr/share/nginx/html/homepage
EXPOSE 8081
CMD ["nginx", "-g", "daemon off;"]