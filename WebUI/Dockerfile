FROM node:14.3.0-alpine3.10 as build-stage
WORKDIR /app
COPY package.json ./
RUN npm install
COPY . /app/
RUN npm install ng2-completer --save-dev
ARG configuration=production
RUN npm run build -- --output-path=./dist/out --configuration

FROM nginx:1.15
COPY --from=build-stage /app/dist/out/ /usr/share/nginx/html
EXPOSE 80
COPY --from=build-stage /app/nginx.conf /etc/nginx/conf.d/default.conf
# CMD ["./node_modules/.bin/ng build --prod"]