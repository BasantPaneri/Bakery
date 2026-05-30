#!/bin/bash

javac src/com/bakery/controller/*.java
javac src/com/bakery/model/*.java
javac src/com/bakery/service/*.java
javac src/com/bakery/utility/*.java

if [ $? -ne 0 ]; then
    echo "Compilation failed"
    exit 1
fi

# java -cp src com.bakery.controller.BakeryController
java -cp . src.com.bakery.controller.BakeryController