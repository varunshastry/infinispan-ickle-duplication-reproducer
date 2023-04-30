#!/bin/bash

curl -H "Content-Type: application/json" -X POST -d "{\"sourceCode\":\"mysourcecode\",\"targetCode\":\"mytargetcode\",\"realm\":\"myrealm\"}" http://localhost:8080/cache/postquestionquestion
