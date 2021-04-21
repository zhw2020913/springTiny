## Spring 源码解析

------



###### 1.创建一个存放bean 的类，就是各种属性的封装(最基本的容器)

```java
PropertyValue  PropertyValues  BeanDefinition
```

首先PropertyValue 作为一个bean ,也就是变量为 name 和value（value为object类型） 组成,作用用于存储生成class对象的属性，比如name属性 及其对应的值

PropertyValues （一个bean可能一次要存储多个方法和属性再次进行封装）是将 PropertyValue 为ArrayList数组中的一个元素进行封装，并设置add 元素和getPropertyValues(就是返回list)方法

BeanDefinition再次进行封装，成为一个真正可以装bean的容器。其属性更多，有对应一个object 类型的bean(应该是对应类的实例)，beanClass,beanClassName,以及PropertyValues。

###### 2.factory中的java 文件之间的关系以及操作(将bean创建放入工厂)

  

```
BeanFactory AbstractBeanFactory AutowireCapableBeanFactory
```

BeanFactory 从单词意思bean工厂，是一个接口类。主要方法有

```java
Object getBean(String name);
```

```java
void registerBeanDefinition(String name, BeanDefinition beanDefinition);
```

一个是获得bean ,一个是注册bean (其中bean 的获得和注册都是通过map实现的，即键值对的方式，查询速度快)。

AbstractBeanFactory   (从字面意思)BeanFactory的抽象类实现BeanFactory

创建Map<String, BeanDefinition>实现以键值对的方式存储 BeanDefinition,实现BeanFactory 中的所有接口。并创建一个doCreateBean接口。

**AutowireCapableBeanFactory**(有流水线能力组装bean的工厂) 继承自AbstractBeanFactory   实现doCreateBean 方法,并创建bean对应的实例，以及**通过java 的反射机制**对propertyValue中的name 字段重新赋值。

------

###### 第四步骤：读取xml 中的配置初始化bean

1.**io 文件夹** 

```java
 Resource  ResourceLoader  UrlResource
```

**接口Resource**  定义 getInputStream()  返回InputStream 

**UrlResource**  实现Resource  接口并定义一个url类型的 变量，通过url 自带方法获得InputStream

**类ResourceLoader**  定义getResource(String location)  通过子实父用 ，生成URL，借助UrlResource返回一个Resource对象，进而获得输入流。

三者作用：根据文件地址获得一个输入流，

 **2.与PropertyValue 同目录位置增加 接口BeanDefinitionReader 和** 

**抽象类AbstractBeanDefinitionReader**

**接口BeanDefinitionReader**  定义loadBeanDefinitions  接口，通过位置加载beanDefinitions(也就是bean)。作用：用于定义加载Xml的接口

**抽象类AbstractBeanDefinitionReader**  实现BeanDefinitionReader（方法未实现）定义两个属性ResourceLoader 和 Map<String,BeanDefinition> registry ，以及抽象类的有参构造方法，属性的get方法。

作用：将xml 资源加载的resource实现类(用于得到输入流)和加载Xml的接口，以及bean 的实体进行融合。相当于合成一锅饭。

**3.xml 文件夹**

```java
XmlBeanDefinitionReader
```

**XmlBeanDefinitionReader** 继承 AbstractBeanDefinitionReader  实现

loadBeanDefinitions  通过层层方法嵌套加载Xml文件解析bean

作用：从函数名可以看出是用于读取Xml资源文件中的标签属性，以封装出一个beanDefinitions(也就是一个bean)。

比第三步增加的就是一个从配置文件中读取bean，完成的只有这一个作用。测试样例中未使用factory包下的任何方法，除了测试，变成自动封装bean的属性和注册bean

------

###### 第五步骤：为bean 注入bean

在AutowireCapableBeanFactory中doCreateBean中增加bean的设置

1.创建BeanReference类  两个属性 String name ,Object bean 设置get,set方法。作用：解决bean 到bean的注入（我们无法处理bean之间的依赖，无法将bean注入到bean中，所以它无法称之为完整的IoC容器！如何实现呢？我们定义一个`BeanReference`，来表示这个属性是对另一个bean的引用）（bean 其实代表着类的实例，也就是类与类之间的关系引用）

2.AbstractBeanFactory 抽象类中增加List存储bean的名字，增加方法preInstantiateSingletons() 批量根据名称返回bean，并改变getBean

3.更改xml 解析器XmlBeanDefinitionReader ，增加判断是否有ref标签并加入到相应的属性中,应该是涉及到类的引用

------

###### 第六步ApplicationContext登场

1.改变BeanFactory的接口

```java
ApplicationContext  AbstractApplicationContext                       ClassPathXmlApplicationContext
```

**ApplicationContext接口**  继承  BeanFactory

**AbstractApplicationContext 抽象类** 实现 ApplicationContext接口

属性：创建AbstractBeanFactory beanFactory ；属性

方法：通过AbstractBeanFactory类 实现getBean 方法

接口：创建refresh接口

**ClassPathXmlApplicationContext**类 继承 AbstractApplicationContext

属性：String configLocation

方法：实现 抽象类中的refresh（）方法

作用：实现AbstractBeanFactory，定义xml的位置实现对特定位置xml的读取。主要对读取配置文件，注册bean等操作封装到一个类中，方便使用。

简化用户的操作，基本就是第五步中BeanFactoryTest的封装

IOC基本实现

------

##### IOC （依赖注入） 控制反转

1.代码中创建bean 容器，类似盛菜的盘子来装载xml 中的信息。通过容器来管理对象的创建以及是否有对象相关信息的需要注入。传统是主动创建对象将信息主动注入。

2.**即由IoC容器帮对象找相应的依赖对象并注入，而不是由对象主动去找**

3.由相关容器对对象数据进行封装以及放入相应对象。spring就是通过反射来实现注入的。

4.**对于某个具体的对象而言，以前是它控制其他对象，现在是所有对象都被spring控制，所以这叫控制反转。**

5.控制反转是从spring 作为一个管理者创建对象，管理对象的角度出发。依赖注入是从对象对其他对象依赖或则方法依赖的角度来说，也就是你需要什么spring就会给你什么。

**加载bean的过程**

1.

------

###### 第七步骤：使用jdk动态代理实现aop（Aspect-oriented Programming 定向的切面编程）织入

**aop文件夹**

```java
TargetSource AdvisedSupport ReflectiveMethodInvocation AopProxy JdkDynamicAopProxy
```

**TargetSource**类

属性：Class targetClass，Object target

方法：一个有参构造方法和属性对应的set,get方法

**AdvisedSupport类**

属性：TargetSource targetSource，MethodInterceptor methodInterceptor

方法：属性对应的set,get方法

作用：将实体和拦截器进行封装成一个类.也就是将要拦截的目标和要使用的拦截器，进行整合，并作为整体为后面的类使用。

**ReflectiveMethodInvocation类 实现 MethodInvocation**（方法调用类）

属性：Object target，Method method，Object[] args

方法: 有参构造方法以及实现MethodInvocation中有关属性处理的方法

作用:将拦截器接口中对类方法的相关操作进行自定义实现，为后面调用方法进行拦截做准备。

**JdkDynamicAopProxy类 实现 AopProxy, InvocationHandler**

属性：AdvisedSupport advised

方法： 有参构造方法以及实现接口的方法

作用：将上述的实体类，接口，方法进行整合形成一个能使用的动态aop方法调用代理。

------

###### **第八步骤：**使用Aspectj管理界面

1.beans下增加BeanPostProcessor接口改变AbstractBeanFactory，和 AutowireCapableBeanFactory 中相关方法。

2.AbstractApplicationContext 改写refresh方法增加 loadBeanDefinitions抽象接口,同时改变ClassPathXmlApplicationContext中的方法

3.Aop文件夹中增加

```java
Advisor ClassFilter MethodMatcher Pointcut PointcutAdvisor
AspectJExpressionPointcut AspectJExpressionPointcutAdvisor
```

代码块中上面部分都是接口，下面部分都是具体的实现类

**Advisor 接口**  只有一个获得通知的接口 Advice getAdvice();

作用：用于打印通知类信息

**ClassFilter接口** (类过滤，获得切入点的类)  定义一个获得匹配类的接口boolean matches(Class targetClass);

作用：获得类的过滤

 **MethodMatcher接口**(方法匹配获得指定类中的指定方法)

作用：指定类中的方法过滤

```java
boolean matches(Method method, Class targetClass);
```

**Pointcut (切入点)接口** 定义两个接口返回ClassFilter和MethodMatcher 接口类对象

作用：将类过滤和类中方法过滤整合，形成切入点接口

```java
ClassFilter getClassFilter();
MethodMatcher getMethodMatcher();
```

**PointcutAdvisor接口**继承Advisor 定义一个接口返回Pointcut接口类对象

作用：切入点和通知进行整合

```java
Pointcut getPointcut();
```

最主要的就是下面两个类，其他都是在定义接口或者将接口组装整合。

**AspectJExpressionPointcut(纵向比切入点表达式)类**实现 Pointcut, ClassFilter, MethodMatcher

属性：PointcutParser ,String expression,PointcutExpression pointcutExpression, Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES

方法：两个构造方法,以及checkReadyToMatch()等

作用：将类过滤，类中方法过滤以及对表达式的解析进行获取。功能根据表达式以及切入点判断目标类或方法是否是表达式要匹配的。以及返回类匹配接口和类中方法匹配的接口。

**AspectJExpressionPointcutAdvisor类**实现 PointcutAdvisor

属性：AspectJExpressionPointcut pointcut，Advice advice

方法：实现PointcutAdvisor方法，并增加setAdvice和setExpression方法

作用：将设置通知和切入点的方法进行整合

------

###### 第九步骤：自动创建aop代理

1.AutowireCapableBeanFactory 中applyPropertyValues 装配bean值的方法发生改变。AbstractBeanFactory中getBean发生改变。

2.TargetSource底层类发生改变，JdkDynamicAopProxy中重写getProxy方法发生改变。以及XmlBeanDefinitionReader 中对元素的读取由name 变为id

2.AdvisedSupport 中增加MethodMatcher methodMatcher 属性并设置setget方法，

3.JdkDynamicAopProxy 中invoke方法进行了改变

4.增加BeanFactoryAware 接口，AspectJAwareAdvisorAutoProxyCreator类进行实现， AspectJAroundAdvice 类

**BeanFactoryAware 接口**  只有一个setBeanFactory(BeanFactory beanFactory)  接口

**AspectJAroundAdvice 类**实现Advice, MethodInterceptor接口

属性：BeanFactory beanFactory;Method aspectJAdviceMethod;String aspectInstanceName;

方法：属性的set,get方法。以及方法拦截器中invoke方法的重写

作用：

**AspectJAwareAdvisorAutoProxyCreator 类** 实现BeanPostProcessor, BeanFactoryAware 方法 

属性：AbstractBeanFactory beanFactory; （AbstractBeanFactory抽象类中主要实现了一些bean的操作，如设置生成等）

方法：重写两个接口中的方法

作用：整合bean的相关操作以及，在生成bean 前和生成bean后都进行方法的围绕。将上面的拦截器，目标资源，先进行目标类的匹配然后进行目标类中的方法匹配。通知设置拦截器等，自动生成一个aop代理。

------



###### 第十步骤：引入cglib 并创建aop代理工厂

Cglib是一个强大的、高性能的**代码生成包**，它广泛被许多AOP框架使用，为他们**提供方法的拦截**。

1.TargetSource 进行改变。ReflectiveMethodInvocation  中属性类型要变为protected。不然后面 Cglib2AopProxy中的内部类无法直接调用其属性

2.改变AbstractBeanFactory中getBean方法

aop 文件夹中增加类

```java
AbstractAopProxy ProxyFactory Cglib2AopProxy
```

**AbstractAopProxy抽象类** 实现 AopProxy 

属性:AdvisedSupport advised;

方法：一个有参构造方法

作用：将放置代理数据的AdvisedSupport 和 AopProxy进行组装，将代理和代理需要的数据对象合并

**ProxyFactory(代理工厂)类**继承AdvisedSupport 实现AopProxy 

属性：无

方法：通过Cglib2AopProxy 类返回一个AopProxy的接口类对象。用于重写getProxy().

作用：主要用于生成代理。

**Cglib2AopProxy类**继承AbstractAopProxy 

方法：有参构造方法，实现getProxy()方法。

两个静态内部类：

```java
DynamicAdvisedInterceptor CglibMethodInvocation
```

**DynamicAdvisedInterceptor**(动态提示拦截器) 实现 MethodInterceptor

属性：AdvisedSupport advised，private org.aopalliance.intercept.MethodInterceptor delegateMethodInterceptor

方法：有参构造方法以及重写intercept 方法

**CglibMethodInvocation 类**继承 ReflectiveMethodInvocation

属性：MethodProxy methodProxy;

方法：有参构造函数重写java反射中proceed() 方法

#####   思考：

ProxyFactory中getProxy是通过Cglib2AopProxy中方法实现的，而AspectJAwareAdvisorAutoProxyCreator中代理直接是Proxy这是不是直接把jdk这种方式的动态代理直接排除在外？

并没有那种是直接从bean中读取进行代理创建的方式，如测试jdk测试样例手动创建adviceSupport进行封装的方式不会调用这种代理工厂的模式生成代理。

------

###### **AOP**(Aspect-oriented Programming 定向的切面编程)

1.其主要通过代理的方式对xml 中的拦截器与相应的类或方法进行匹配，然后进行调用方法前后的相应操作类似一个中介。

2.主要有Jdk动态代理和Cglib2AopProxy中的动态代理，以及静态织入(AspectJ 实现)

3.其底层主要使用java 的反射机制以及aopalliance中拦截器，aspectj主要对切入点，以及解析表达式等进行操作。当然Cglib2AopProxy中aop代理的实现需要依赖Cglib中有关代理的相关方法。

4.具体代码实现加载目标资源的类TargetSource，将加载资源和方法匹配，拦截器融合的AdviseSupport.以及代理类和代理创建和代理工厂等。

