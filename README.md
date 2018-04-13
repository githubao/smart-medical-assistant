# smart-medical-assistant
Smart Medical Assistant, ideas and implements

### 打包
1. mvnw.cmd clean package -Dmaven.test.skip=true
2. nohup /usr/java8/bin/java -jar turing-bm-nlg-1.0.0-SNAPSHOT.jar >client.log 2>&1 &

C:\\Program Files\\Java\\jdk1.8.0_161\\bin\\java -jar smart-medical-assistant-0.0.1-SNAPSHOT.jar


### 使用指南：
1. 把文件“medical_proper_nouns.properties”拷贝的D盘的ocr文件夹路径下，新添加数据保存成key=value的格式（注意，新添加的数据，5分钟之后生效）
2. 在命令行运行“start /b run.bat”，输出日志文件保存在“medical-assistant.log”
3. 浏览器访问：http://127.0.0.1:9999
