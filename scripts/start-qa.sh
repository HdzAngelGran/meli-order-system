#!/usr/bin/env bash
# Script to run the Spring Boot application with the QA profile
gradlew bootRun --args='--spring.profiles.active=qa'