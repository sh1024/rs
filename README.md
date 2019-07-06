# rs

For qa.

1) Run backend build first:

./gradlew docker

2) Start with docker-compose

docker-compose up --build


For development.

1) Backend - start db.

docker-compose up postgres

2) Frontend - start db and server

docker-compose up app
