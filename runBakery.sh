#!/bin/bash

javac -cp ".:mysql-connector-j-9.7.0.jar" \
src/com/bakery/model/*.java \
src/com/bakery/service/*.java \
src/com/bakery/utility/*.java \
src/com/bakery/controller/*.java

if [ $? -ne 0 ]; then
    echo "Compilation failed"
    exit 1
fi

# java -cp ".:mysql-connector-j-9.7.0.jar" src.com.bakery.controller.DBController
# java -cp ".:mysql-connector-j-9.7.0.jar" src.com.bakery.controller.DBController1

java -cp ".:mysql-connector-j-9.7.0.jar" src.com.bakery.controller.BakeryController