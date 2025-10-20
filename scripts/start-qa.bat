@echo off
REM Script to run the Spring Boot application with the QA profile
call gradlew bootRun --args="--spring.profiles.active=qa"
