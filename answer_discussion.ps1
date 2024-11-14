$envFilePath = ".\.env"
$jarFilePath = ".\answer_discussion.jar"

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

# Verify that the JAR file exists
if (-Not (Test-Path $jarFilePath)) {
    Write-Error "The JAR file was not found at path: $jarFilePath"
    exit 1
}

# Run the Java application
java -jar $jarFilePath