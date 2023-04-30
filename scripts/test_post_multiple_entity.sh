#!/bin/bash

print_usage() {
    echo "Correct usage: ./$0 <COUNT> <OFFSET>"
    echo "Examples: ./$0 50 0 - generates 50 objects from 0 to 49 , ./$0 50 25  - generates 25 objects from 25 to 74"
}

case $1 in
    ''|*[!0-9]*) echo "Enter a valid number for count!" && print_usage && exit ;;
    *) COUNT=$1 ;;
esac

case $2 in
    ''|*[!0-9]*) echo "Enter a valid number for offset!" && print_usage  && exit ;;
    *) COUNT=$2 ;;
esac

COUNT=$(($1+$2))
i=$2
while [ $i -lt $COUNT ]
do
    curl -H "Content-Type: application/json" -X POST -d "{\"sourceCode\":\"mysourcecode$i\",\"targetCode\":\"mytargetcode$i\",\"realm\":\"myrealm$i\"}" http://localhost:8080/cache/postquestionquestion
    i=$(( $i + 1 ))
    echo ""
done

echo "Processing complete."
