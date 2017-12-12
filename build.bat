@ECHO OFF
:: script written by Nathan Asdarjian
javac -d src/cls -cp lib/* src/*.java
if %ERRORLEVEL% NEQ 0 goto branch
java -cp src/cls;lib/* -Djava.library.path=nat Main
exit /b 0
:branch
PAUSE
exit /b %ERRORLEVEL%