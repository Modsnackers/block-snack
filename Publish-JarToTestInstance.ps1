[CmdletBinding()]
param (
    [Parameter(Mandatory=$true)]
    [String]
    $TestInstancePath
)
Clear-Host
.\gradlew.bat build
.\gradlew.bat runData
.\gradlew.bat jar
Copy-Item -Path ".\build\libs\blocksnack-0.0.1.jar" -Destination $TestInstancePath -Force
Write-Host ""
Write-Host "Mod file copied to '$TestInstancePath'." -ForegroundColor Green