## 前言

作为后端开发者，项目上线之后难免会遇到各种问题，一个良好且及时的异常通知机制可以让我们在项目的维护上避免很多不必要的麻烦。

本项目的开发愿景是为了给使用者在线上项目的问题排查方面能够带来帮助，简单配置，做到真正的开箱即用，同时异常信息尽量详细，帮助开发者快速定位问题。

目前支持基于钉钉,企业微信和邮箱的异常通知，以后有时间还会扩展其他通知方式，同时也欢迎有兴趣的开发者能够参与进来，共同完善

## 使用方式

pom.xml中增加项目依赖

本项目现已发布到maven中央仓库，可以直接通过以下坐标引入依赖
```
  <dependency>
     <groupId>com.github.kongchong</groupId>
     <artifactId>exception-notice-spring-boot-starter</artifactId>
     <version>1.2.3</version>
  </dependency>
```
#### 钉钉配置

第一步：创建钉钉群 并在群中添加自定义机器人
对于不太了解钉钉机器人配置的同学可以参考：[钉钉机器人](https://open-doc.dingtalk.com/microapp/serverapi2/krgddi "自定义机器人")


第二步：增加配置文件

以下以yml配置文件的配置方式为例
```
exception:
  notice:
    enable: 启用开关 false或不配置的话本项目不会生效
    projectName: 指定异常信息中的项目名，不填的话默认取 spring.application.name的值
    included-trace-package: 追踪信息的包含的包名，配置之后只通知此包下的异常信息
    period: 异常信息发送的时间周期 以秒为单位 默认值5，异常信息通知并不是立即发送的，默认设置了5s的周期，主要为了防止异常过多通知刷屏，同时钉钉针对异常通知刷屏的情况也增加了限流措施，建议不要修改
    exclude-exceptions:
      - 需要排除的异常通知，注意 这里是异常类的全路径，可多选
    ## 钉钉配置
    ding-talk:
      web-hook: 钉钉机器人的webHook地址，可依次点击钉钉软件的头像，机器人管理，选中机器人来查看
      at-mobiles: 
        - 钉钉机器人发送通知时 需要@的钉钉用户账户，可多选
      msg-type: 消息文本类型 目前支持 text markdown
```
#### 企业微信配置

第一步：创建企业微信群 并在群中添加自定义机器人
对于不太了解企业微信机器人配置的同学可以参考：[企业微信机器人](https://work.weixin.qq.com/api/doc/90000/90136/91770)


第二步：增加配置文件

以下以yml配置文件的配置方式为例
```
exception:
  notice:
    enable: 启用开关 false或不配置的话本项目不会生效
    projectName: 指定异常信息中的项目名，不填的话默认取 spring.application.name的值
    included-trace-package: 追踪信息的包含的包名，配置之后只通知此包下的异常信息
    period: 异常信息发送的时间周期 以秒为单位 默认值5，异常信息通知并不是立即发送的，默认设置了5s的周期，主要为了防止异常过多通知刷屏，同时钉钉针对异常通知刷屏的情况也增加了限流措施，建议不要修改
    exclude-exceptions:
      - 需要排除的异常通知，注意 这里是异常类的全路径，可多选
    ## 企业微信配置
    we-chat:
      web-hook: 企业微信webhook地址
      at-phones: 手机号列表，提醒手机号对应的群成员(@某个成员)，@all表示提醒所有人 当msg-type=text时才会生效
      at-user-ids: userid的列表，提醒群中的指定成员(@某个成员)，@all表示提醒所有人 当msg-type=text时才会生效
      msg-type: 消息格式 企业微信支持 （text）、markdown（markdown）、图片（image）、图文（news）四种消息类型 本项目中有 text和markdown两种可选

```

#### 邮箱配置
这里以qq邮箱为例 

第一步：项目中引入邮箱相关依赖
```
  <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-mail</artifactId>
  </dependency>
```

第二步：增加配置文件
 
 ```
 exception:
   notice:
     enable: 启用开关 false或不配置的话本项目不会生效
     projectName: 指定异常信息中的项目名，不填的话默认取 spring.application.name的值
     included-trace-package: 追踪信息的包含的包名，配置之后只通知此包下的异常信息
     period: 异常信息发送的时间周期 以秒为单位 默认值5，异常信息通知并不是立即发送的，默认设置了5s的周期，主要为了防止异常过多通知刷屏，同时钉钉针对异常通知刷屏的情况也增加了限流措施，建议不要修改
     exclude-exceptions:
       - 需要排除的异常通知，注意 这里是异常类的全路径，可多选
     ## 邮箱配置
     mail:
       from: 发送人地址
       to: 接收人地址
       cc: 抄送人地址
spring:
  mail:
    host: smtp.qq.com  邮箱server地址 
    username: 1182701220@qq.com  server端发送人邮箱地址
    password: 邮箱授权码
 
```

邮箱授权码可以按以下方法获取

打开QQ邮箱网页→设置→账户→POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务→开启POP3/SMTP服务，然后就能看到授权码了
 
注意：钉钉,企业微信和邮箱配置支持单独和同时启用

配置好了配置文件，接下来可以写个例子测试一下了

![](http://ww4.sinaimg.cn/large/006y8mN6ly1g687twjqbij30mk01wjrm.jpg)

如上图所示，在一个测试方法中手动抛出了一个参数错误异常，接下来运行一下看一下效果

钉钉效果：

![钉钉](https://tva1.sinaimg.cn/large/006y8mN6ly1g6ff4pixwbj30kc0enac9.jpg)

邮箱效果：

![邮箱](https://tva1.sinaimg.cn/large/006y8mN6gy1g6ffaykd5qj30n80gcwhx.jpg)


由于报错太多没有全部截图下来，感兴趣的同学可以自行测试一下

## 注意

本工具仅支持集成在springboot+mvc项目中，同时需要jdk版本1.8+


## 致谢

本项目参考以下项目：

1. [prometheus-spring-boot-starter](https://gitee.com/ITEater/prometheus-spring-boot-starter)

# 作者
- 孔冲 1182701220@qq.com
- 博客 http://www.kcblog.cn
