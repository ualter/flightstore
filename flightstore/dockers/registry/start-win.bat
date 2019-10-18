@ECHO OFF
echo.
echo. Wait...
echo.
echo. Copying CERTIFICATE
echo. *******************
if not exist "C:\Users\docker-registry-certs\" mkdir C:\Users\docker-registry-certs\
copy certs\*.* c:\Users\docker-registry-certs /Y
echo.
echo. Start Docker Compose
echo. ********************
docker-compose up -d
echo.
echo. Let's follow the Logs (Ctrl+C)
echo. ****************************** 
docker-compose logs -f --tail=30
echo.
echo. Ok!
echo.