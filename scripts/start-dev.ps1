Param(
    [string]$Profile = "dev",
    [int]$Port = 8080
)

Write-Host "Starting Meli Order System with profile=$Profile on port $Port"

if (Test-Path ".\gradlew.bat") {
    .\gradlew.bat bootRun --args="--spring.profiles.active=$Profile --server.port=$Port"
} else {
    Write-Host "gradlew not found. Use 'gradlew.bat' from project root on Windows." -ForegroundColor Yellow
}
