echo off
cls
set MONGO_HOME="c:\Utils\mongodb2_4_3\"
set DB_PATH="data\db"
title Mongo on %MONGO_HOME%
c:
cd %MONGO_HOME%
mongod --dbpath=%DB_PATH%

