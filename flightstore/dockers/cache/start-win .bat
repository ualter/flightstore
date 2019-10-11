@ECHO OFF
rm -f c:/Users/redis.conf
cp redis.conf c:/Users/
ECHO.
ECHO. *** Creating REDIS Docker
ECHO.
docker run -d -p 6379:6379 -v /c/Users/redis.conf:/usr/local/etc/redis/redis.conf --name redis-flightstore redis redis-server /usr/local/etc/redis/redis.conf
ECHO.

