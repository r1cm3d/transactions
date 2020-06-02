all: clean build-production run

all-local: clean build-local run-local

clean:
	@echo "\nCleaning with Gradle\n"
	gradle clean spotlessApply
	@echo "\n\nRemoving docker images\n"
	-docker rm -f transactions_db_local 2>/dev/null || \
	docker rm -f transactions_db 2>/dev/null  || \
	docker rm -f transactions_application 2>/dev/null

build-local:
	@echo "\nBuilding Postgres container to run locally\n"
	gradle spotlessApply build && \
    docker-compose -f scripts/docker-compose/postgres/docker-compose.yaml build

build-production:
	@echo "\nBuilding stack compose to run the application inside the container\n"
	gradle spotlessApply build && \
	docker-compose -f scripts/docker-compose/stack/docker-compose.yaml build

run-local-db:
	@echo "\nRunning Postgres locally container\n"
	docker-compose -f scripts/docker-compose/postgres/docker-compose.yaml up &

run-local: run-local-db
	@echo "\nRunning Spring Boot application locally\n"
	gradle bootRun

run:
	@echo "\nRunning the full stack (db and application) to run inside the container\n"
	-docker-compose -f scripts/docker-compose/stack/docker-compose.yaml up
