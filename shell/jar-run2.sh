#cription: 启动重启server服务 
#端口号，根据此端口号确定PID  
PORT=767
#启动命令所在目录  
HOME='/home/sszn/task-agc'

#查询出监听了test.jar端口TCP协议的程序  
pid=$(ps -ef | grep gdw-agc-task-1.0.0.0.jar | grep -v grep | awk '{print $2}')

start(){
   echo "start running cloud-core ............... "  
   if [ -n "$pid" ]; then
      echo "server already start,pid:$pid"  
      echo "pid:$pid agc-job port:$PORT 服务已经在运行了,请停止后再 执行 sh run.sh start "  
      return 0
   fi
   #进入命令所在目录  
   cd $HOME
   # 启动服务控制台日志输出到nohup.out文件中
   nohup java -jar gdw-agc-task-1.0.0.0.jar >> /home/sszn/task-agc/log/agc-$(date +%Y-%m-%d).log 2>&1 &
   echo "running success agc-job port:$PORT"
   echo "agc-job port:$PORT 服务启动成功 ..... "  
}

stop(){
   echo "stopping running cloud-core ............... "  
   if [ -z "$pid" ]; then
      echo "not find program on port:$PORT"  
      echo "agc-job port:$PORT 服务已经被关闭了请执行 sh run.sh start "   
      return 0
   fi
   #结束程序，使用讯号2，如果不行可以尝试讯号9强制结束  
   kill -9 $pid
   rm -rf $pid
   echo "kill program use signal 2,pid:$pid"  
}

status(){
   if [ -z "$pid" ]; then
      echo "not find program on port:$PORT"  
   else
      echo "program is running,pid:$pid"  
   fi
}

case $1 in
   start)
      start
   ;;
   stop)
      stop
   ;;
   restart)
      $0 stop
      sleep 2
      $0 start
    ;;
   status)
      status
   ;;
   *)
      echo $"Usage: $0 {start|stop|status}"  
      exit 0
esac
