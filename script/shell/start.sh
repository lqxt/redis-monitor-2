#!/bin/sh
web_home='src/main/webapp/WEB-INF/classes'
package_name='com/redis/monitor/server/jetty'
class_name='JettyServer'
lib_home='src/main/webapp/WEB-INF'
var=`pwd`
echo $var
cd ../../
pro_path=`pwd`
cd $web_home/$package_name
java -classpath  $pro_path/$web_home -Djava.ext.dirs=$pro_path/$lib_home/lib com.redis.monitor.server.jetty.JettyServer
