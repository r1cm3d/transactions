all: clean build run

all-local: clean build-local run-local

clean:
	@echo "\nCleaning with Gradle\n"
	gradle clean
	@echo "\n\nRemoving docker images\n"
	-docker rm -f transactions_db_local 2>/dev/null || \
	docker rm -f transactions_db 2>/dev/null  || \
	docker rm -f transactions_application 2>/dev/null

build-local:
	@echo "\nBuilding Postgres container to run locally\n"
	gradle build && \
    docker-compose -f scripts/docker-compose/postgres/docker-compose.yaml build

build:
	@echo "\nBuilding stack compose to run the application inside the container\n"
	gradle build && \
	docker-compose -f scripts/docker-compose/stack/docker-compose.yaml build

run-local:
	@echo "\nRunning Postgres locally container\n"
	docker-compose -f scripts/docker-compose/postgres/docker-compose.yaml up &
	gradle bootRun

run:
	@echo "\nRunning the full stack (db and application) to run inside the container\n"
	-docker-compose -f scripts/docker-compose/stack/docker-compose.yaml up