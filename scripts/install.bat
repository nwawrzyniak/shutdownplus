@echo off
pushd "%HOMEDRIVE%%HOMEPATH%"
if exist "User_Software\" (goto skipsoftwaremkdir) else (mkdir User_Software)
echo Created directory User_Software
:skipsoftwaremkdir
pushd User_Software
if exist "ShutDownPlus\" (goto skipshutdownmkdir) else (mkdir ShutDownPlus)
echo Created directory ShutDownPlus
:skipshutdownmkdir
popd
popd
copy /Y shutdownplus.jar "%HOMEDRIVE%%HOMEPATH%/User_Software/ShutDownPlus/shutdownplus.jar"
echo Installation successful.
pause
