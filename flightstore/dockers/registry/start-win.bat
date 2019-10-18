@ECHO OFF
echo.
echo. Wait...
echo.
echo. Copying CERTIFICATE
echo. *******************
if not exist "C:\Users\docker-registry-certs\" mkdir C:\Users\docker-registry-certs\
copy certs\*.* c:\Users\docker-registry-certs /Y
echo.
echo. Generate Registry Password
echo. **************************
if not exist "C:\Users\docker-registry-htpasswd\" mkdir C:\Users\docker-registry-htpasswd\
docker run --rm --entrypoint htpasswd registry:2 -Bbn ualter 1234 > c:\Users\docker-registry-htpasswd\htpasswd
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