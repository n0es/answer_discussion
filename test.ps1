$envFilePath = ".\.env"
$jarFilePath = ".\target\answer_discussion-1.0-SNAPSHOT.jar"

# Check if the .env file exists
if (-Not (Test-Path $envFilePath)) {
    Write-Error "The .env file was not found at path: $envFilePath"
    exit 1
}

Get-Content $envFilePath | ForEach-Object {
    $splitIndex = $_.IndexOf("=")
    $name = $_.Substring(0, $splitIndex).Trim()
    $value = $_.Substring($splitIndex + 1).Trim()
    Set-Item -Path "Env:\$name" -Value $value
}

mvn test