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
Remove-Item -Path "$TestInstancePath\blocksnack-*.jar"
Copy-Item -Path ".\build\libs\blocksnack-*.jar" -Destination $TestInstancePath
Write-Host ""
Write-Host "Mod file copied to '$TestInstancePath'." -ForegroundColor Green