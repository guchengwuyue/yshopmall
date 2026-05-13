PID=$(ps -ef | grep yshop-app-2.0.0.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ];then
    echo Application is already stopped
else
    echo kill $PID
    kill $PID
fi
