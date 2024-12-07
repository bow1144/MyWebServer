> 这是一个问题日志，记录一些~~奇怪~~的问题

## 初创时期

### 在IDEA中引入SpringBoot?
* 用maven直接复制粘贴就行
```dtd
<dependencies>
    <!-- Spring Boot Web Starter: 包含了 Spring MVC 和 Tomcat -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot DevTools (可选，用于热部署) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Spring Boot Starter Data JPA (可选，用于与数据库集成) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- MySQL 驱动（如果你需要连接 MySQL 数据库） -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
</dependencies>

```

### Spring如何启动？
* 需要加入Spring主类
```dtd
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### Spring命令行如何启动？
* 非必要不适用shell工具，会导致循环依赖

### SQL.Exception报错
> java.sql.SQLException: Access denied for user 'root'@'localhost' (using password: YES)

* 数据库密码错了
* 主机名不同

### Spring 报错 文档根元素 "mapper" 必须匹配 DOCTYPE 根 "null"
* `UserMapper.xml`放在`main/resources.mapper`下
* 在文件头添加`<?xml version="1.0" encoding="UTF-8"?>`
* XML 配置文件与注解冲突，一般保留xml

### Spring shell导致的循环依赖
* 在程序中去除shell，在maven中删除shell，二者缺一不可
* 在yml中禁用shell

### 浏览器页面Whitelabel Error Page
* 一般而言是`.html`文件出错或者路径出错


## 前端部分
### 传参失误：前端没有数据库数据
* 前后端连接的数据结构问题（一般是）

