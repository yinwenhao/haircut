启动请使用：
jetty:run -DskipTests -DconfPath=/opt/conf/haircut

web.xml中，velocity.properties的位置配置是写死的，不能用file:${confPath}/velocity.properties，不知道为啥。。。
