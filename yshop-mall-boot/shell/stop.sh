PID=$(ps -ef | grep yshop-app-1.9.0.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ];then
    echo Application is already stopped
else
    echo kill $PID
    kill $PID
fi
