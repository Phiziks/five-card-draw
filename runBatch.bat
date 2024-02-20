@echo off

if ERRORLEVEL 1 GOTO error


call mvn clean package

pushd .\target
call java -jar five-card-draw-0.0.1-SNAPSHOT.jar
pause

:error
ECHO BUILD ERRORS OCCURED!!!
goto :EOF


:end
ECHO app has started