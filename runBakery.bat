@echo off


javac src/com/bakery/controller/*.java
javac src/com/bakery/model/*.java
javac src/com/bakery/service/*.java
javac src/com/bakery/utility/*.java

if %errorlevel% neq 0 (
    echo Compilation failed
    pause
    exit /b
)

java src.com.bakery.controller.BakeryController

