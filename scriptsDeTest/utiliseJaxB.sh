# $2 : json ou xml
# $1 : Les données à envoyé (dans un fichier)

service=http://localhost:8080/jb/wr/test

curl -X PUT -H "Content-Type: application/json"  -H "Accept: application/json" -d @$1 $service
