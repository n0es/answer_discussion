Write-Host "Building Java application..."
jar -cmf src/manifest.mf answer_discussion.jar AnswerDiscussion
./answer_discussion.ps1