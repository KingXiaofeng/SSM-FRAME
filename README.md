# SSM-FRAME
ssm基本框架


# SSMFrame

	SSMFrame基本项目架构
	|---java
	|---|---com.ssm.frame
	|---|---|---entity					
	|---|---|---web						
	|---|---|---service					
	|---|---|---dao						
	|---|---|---dto						
	|---|---|---servlet					
	|---|---|---listener				
	|---|---|---exception				
	|---|---|---enums					
	|---|---|---cache					
	|---|---|---aop						
	
	|---resources
	|---|---generator
	|---|---|---generatorConfig.xml		
	|---|---mybatis
	|---|---|---mybatis-config.xml		
	|---|---spring
	|---|---|---spring-core.xml			
	|---|---|---spring-init.xml			
	|---|---|---spring-dao.xml			
	|---|---|---spring-web.xml			
	|---|---|---spring-redis.xml		
	|---|---|---spring-ehcache.xml		
	|---|---|---spring-thread-pool.xml	
	|---|---ehcache.xml					
	|---|---conf.properties				
	|---|---logback.xml					
	
	|---webapp
	|---|---resources					
	|---|---|---css
	|---|---|---js
	|---|---|---image
	|---|---WEB-INF
	|---|---|---jsp
	|---|---|---web.xml

## 一、在写代码之前我们先了解一下这三个框架分别是干什么的？

1、SpringMVC：它用于web层，相当于controller（等价于传统的servlet和struts的action），
    用来处理用户请求。举个例子，用户在地址栏输入http://网站域名/login，那么springmvc就会拦
    截到这个请求，并且调用controller层中相应的方法，（中间可能包含验证用户名和密码的业务逻辑，
    以及查询数据库操作，但这些都不是springmvc的职责），最终把结果返回给用户，并且返回相应的页面
    （当然也可以只返回json/xml等格式数据）。springmvc就是做前面和后面过程的活，与用户打交道！！

2、Spring：太强大了，以至于我无法用一个词或一句话来概括它。但与我们平时开发接触最多的估计就是
    IOC容器，它可以装载bean（也就是我们java中的类，当然也包括service dao里面的），有了这个机制，
    我们就不用在每次使用这个类的时候为它初始化，很少看到关键字new。另外spring的aop，事务管理等等都
    是我们经常用到的。

3、MyBatis：如果你问我它跟鼎鼎大名的Hibernate有什么区别？我只想说，他更符合我的需求。第一，它
    能自由控制sql，这会让有数据库经验的人（当然不是说我啦捂脸）编写的代码能搞提升数据库访问的效率。第
    二，它可以使用xml的方式来组织管理我们的sql，因为一般程序出错很多情况下是sql出错，别人接手代码后
    能快速找到出错地方，甚至可以优化原来写的sql。

 ## 二、maven的目录规范

|目录名|描述|
|-|-|
|src|根目录，没什么好说的，下面有main和test。|
|main|主要目录，可以放java代码和一些资源文件。|
|java|存放我们的java代码，这个文件夹要使用Build Path -> Use as Source Folder，这样看包结构会方便很多，新建的包就相当于在这里新建文件夹咯。|
|resources|存放资源文件，譬如各种的spring，mybatis，log配置文件。|
|mapper|存放dao中每个方法对应的sql，在这里配置，无需写daoImpl。|
|spring|这里当然是存放spring相关的配置文件，有dao service web三层。|
|sql|其实这个可以没有，但是为了项目完整性还是加上吧。|
|webapp|这个貌似是最熟悉的目录了，用来存放我们前端的静态资源，如jsp js css。|
|resources|这里的资源是指项目的静态资源，如js css images等。|
|WEB-INF|很重要的一个目录，外部浏览器无法访问，只有羡慕内部才能访问，可以把jsp放在这里，另外就是web.xml了。你可能有疑问了，为什么上面java中的resources里面的配置文件不妨在这里，那么是不是会被外部窃取到？你想太多了，部署时候基本上只有webapp里的会直接输出到根目录，其他都会放入WEB-INF里面，项目内部依然可以使用classpath:XXX来访问，好像IDE里可以设置部署输出目录，这里扯远了~|
|test|这里是测试分支。|
|java|测试java代码，应遵循包名相同的原则，这个文件夹同样要使用Build Path -> Use as Source Folder，这样看包结构会方便很多。|
|resources|没什么好说的，好像也很少用到，但这个是maven的规范。|

## 三、项目目录常用结构

|目录|类型|描述|
|-|-|-|
|dao|数据访问层（接口）|与数据打交道，可以是数据库操作，也可以是文件读写操作，甚至是redis缓存操作，总之与数据操作有关的都放在这里，也有人叫做dal或者数据持久层都差不多意思。为什么没有daoImpl，因为我们用的是mybatis，所以可以直接在配置文件中实现接口的每个方法。|
|entity|实体类|一般与数据库的表相对应，封装dao层取出来的数据为一个对象，也就是我们常说的pojo，一般只在dao层与service层之间传输。|
|dto|数据传输层|刚学框架的人可能不明白这个有什么用，其实就是用于service层与web层之间传输，为什么不直接用entity（pojo）？其实在实际开发中发现，很多时间一个entity并不能满足我们的业务需求，可能呈现给用户的信息十分之多，这时候就有了dto，也相当于vo，记住一定不要把这个混杂在entity里面，答应我好吗？|
|service|业务逻辑（接口）|写我们的业务逻辑，也有人叫bll，在设计业务接口时候应该站在“使用者”的角度。额，不要问我为什么这里没显示！IDE调皮我也拿它没办法~|
|serviceImpl|业务逻辑（实现）|实现我们业务接口，一般事务控制是写在这里，没什么好说的。|
|web|控制器|springmvc就是在这里发挥作用的，一般人叫做controller控制器，相当于struts中的action。|

## 四、配置文件

    （一）、maven依赖配置：pom.xml

    （二）、jabc.properties配置

        1、配置常见的数据库连接属性

    （三）、mybatis-config.xml（配置mybatis核心文件）

        1、使用自增主键
        2、使用列别名
        3、开启驼峰命名转换 create_time -> createTime
		
	（四）、spring-init.xml
		
		1、全局异常捕获
		2、Spring容器初始化监听器

    （五）、spring-dao.xml 配置

        1、数据库连接池
        2、配置SqlSessionFactory对象
        3、Mapper接口所在包名，Spring会自动查找其下的Mapper
        4、配置事务管理器 
        5、注解方式配置事务

    （六）、spring-web.xml

        1、开启SpringMVC注解模式
        2、静态资源默认servlet配置
        3、避免IE执行AJAX时,返回JSON出现下载文件
        4、启动Spring MVC的注解功能，完成请求和注解POJO的映射
        5、配置视图解析器jsp 显示ViewResolver
        6、国际化
		7、基于Cookie的本地化解析器
		8、扫描web相关的bean
		
	（七）、spring-redis.xml
		
		1、扫描缓存的层级
		2、redis基本配置
		3、JedisCluster 集群高可用配置
		4、redis Sentinel主从高可用方案配置
		5、redis单节点数据库连接配置
		6、redisTemplate配置，redisTemplate是对Jedis的对redis操作的扩展，有更多的操作，封装使操作更便捷
		
	（八）、spring-ehcache.xml
	
		1、支持缓存注解
		2、默认是cacheManager
		3、cache管理器配置
		
	（九）、spring-thread-pool.xml
	
		1、线程池配置
		
	（十）、spring-core.xml
	
		1、过滤支持
		2、切面配置
		3、激活组件扫描功能,扫描aop的相关组件组件
		4、启动组件扫描，排除@Controller组件，该组件由SpringMVC配置文件扫描
		5、引入配置文件conf.properties
		6、路注册模板：spring-init、spring-dao、spring-redis、spring-thread-pool

    （十一）、web.xml

        1、加载spring配置文件：spring-core.xml
		2、spring监听器
		3、防止Spring内存溢出监听器
		4、阿里数据监控
		5、统一编码格式控制
		6、配置DispatcherServlet
		7、session周期配置
		8、欢迎界面
		9、错误配置

    （十二）、logback.xml

        1、日志输出格式也是最基本的控制台s呼出基本配置

