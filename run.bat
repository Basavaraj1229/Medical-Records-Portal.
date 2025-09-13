@echo off
echo ========================================
echo  Doctor-Patient Portal - Manual Setup
echo ========================================

echo.
echo Step 1: Creating necessary directories...
if not exist "target" mkdir target
if not exist "target\classes" mkdir target\classes
if not exist "target\lib" mkdir target\lib

echo.
echo Step 2: Downloading required JAR files...

echo Downloading Servlet API...
powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/javax/servlet/javax.servlet-api/4.0.1/javax.servlet-api-4.0.1.jar' -OutFile 'target/lib/servlet-api.jar' } catch { Write-Host 'Download failed, continuing...' }"

echo Downloading MySQL Connector...
powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.28/mysql-connector-java-8.0.28.jar' -OutFile 'target/lib/mysql-connector.jar' } catch { Write-Host 'Download failed, continuing...' }"

echo Downloading H2 Database...
powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/h2database/h2/2.1.214/h2-2.1.214.jar' -OutFile 'target/lib/h2.jar' } catch { Write-Host 'Download failed, continuing...' }"

echo Downloading JSTL...
powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/javax/servlet/jstl/1.2/jstl-1.2.jar' -OutFile 'target/lib/jstl.jar' } catch { Write-Host 'Download failed, continuing...' }"

echo.
echo Step 3: Compiling Java source files...
javac -cp "target/lib/*" -d target/classes src/main/java/com/hms/*/*.java src/main/java/com/hms/*/*/*.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo.
echo Step 4: Creating simple HTTP server...
echo Your project structure is ready!
echo.
echo To run the application, you need a web server like Apache Tomcat.
echo For now, let's try to create a simple demonstration.
echo.

echo ========================================
echo  Setup Complete!
echo ========================================
echo.
echo Next steps:
echo 1. Install Apache Tomcat or use embedded server
echo 2. Or use the demonstration below
echo.
pause
