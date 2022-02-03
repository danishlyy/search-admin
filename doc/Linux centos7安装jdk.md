![img_2.png](img_2.png)
配置环境变量，编辑文件 vim /etc/profile

```shell
JAVA_HOME=/data/app/javajdk
PATH=$JAVA_HOME/bin:$PATH
CLASSPATH=$JAVA_HOME/jre/lib/ext:$JAVA_HOME/lib/tools.jar
export PATH JAVA_HOME CLASSPATH

```
