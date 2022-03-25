function timestamp() {
    return Get-Date -UFormat %s -Millisecond 0;
}

$r = Invoke-WebRequest -Method POST -Uri 'http://localhost:8080/login' `
-Headers @{
    'Accept' = 'application/json';
    'Content-Type' = 'application/json';
} `
-Body "{
  ""username"": ""aga"",
  ""password"": ""123456"",
  ""timestamp"": $(timestamp)
}"

Write-Output "aga: $r"

$r = Invoke-WebRequest -Method POST -Uri 'http://localhost:8080/login' `
-Headers @{
    'Accept' = 'application/json';
    'Content-Type' = 'application/json';
} `
-Body "{
  ""username"": ""fish"",
  ""password"": ""123456"",
  ""timestamp"": $(timestamp)
}"

Write-Output "fish: $r"