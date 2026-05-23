@echo off

javac -cp ".;mysql-connector-j-9.7.0.jar" ^
src/com/bakery/model/*.java ^
src/com/bakery/service/*.java ^
src/com/bakery/utility/*.java ^
src/com/bakery/controller/*.java

if %errorlevel% neq 0 (
    echo Compilation failed
    pause
    exit /b
)

@REM java -cp ".;mysql-connector-j-9.7.0.jar" src.com.bakery.controller.DBController
@REM java -cp ".;mysql-connector-j-9.7.0.jar" src.com.bakery.controller.DBController1
java -cp ".;mysql-connector-j-9.7.0.jar" src.com.bakery.controller.BakeryController