##注意事项
###布局使用tiles3还是thymeleaf layout？
tiles用在jsp解析上可以，但html模板还是用thymeleaf,
结论：推荐使用thymeleaf layout

###thymeleaf的布局使用offical layout 还是 layout-dialect?
都可以，但要注意layout-dialect对于thymeleaf3的支持，当前存在警告，部分语法后续版本会失效


##版本关注

servlet-api待升级到4.0

##异常信息归属
###[WARNING] Error page loop /404.html
是由于thymeleaf在处理th:src="@{/js/security/mainframe.js}"时，该文件不存在引起的


需要注意的是监听器不受Spring 容器的管理,所以不能使用注解进行注入,
同理,如果使用Spring Aop 也不能切入,这时可以考虑使用原生AspectJ代替Spring Aop

可以将控制器写在别的jar中，但要注意 autodetect的问题：
一定要给外部jar的Controller类设定@Controller("testdemo") 其中testdemo根据实际情况设置

 *重要：
 *
 *spring MVC Controller默认是单例的，单例的原因有二：
1、为了性能。
2、不需要多例。
 *
 *Controller最佳实践：
1、不要在controller中定义成员变量。
2、万一必须要定义一个非静态成员变量时候，则通过注解@Scope("prototype")，将其设置为多例模式。



	加入RequestContextListener后 能够支持 在非controller中注入Session和Request:
	@Autowired  
    private HttpSession session;  
      
    @Autowired  
    private HttpServletRequest request;  
    
    可以使用代码的方式获取reuqest对像
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();


//--------------------------------------------------
mybatis generator一些小技巧：
特别注意：mybatis-generator 仅适合在建表完成后使用一次!!
执行命令:mvn mybatis-generator:generate
a) 建表时，字段名称建议用"_"分隔多个单词，比如:AWB_NO、REC_ID...，这样生成的entity，属性名称就会变成漂亮的驼峰命名，即：awbNo、recId
b)oracle中，数值形的字段，如果指定精度，比如Number(12,2)，默认生成entity属性是BigDecimal型 ，如果不指定精度，比如:Number(9)，指默认生成的是Long型
c)oracle中的nvarchar/nvarchar2，mybatis-generator会识别成Object型，建议不要用nvarchar2，改用varchar2