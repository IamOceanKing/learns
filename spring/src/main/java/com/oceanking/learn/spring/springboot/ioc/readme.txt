Bean生命周期
1. 实例化Bean对于BeanFactory容器，当客户向容器请求一个尚未初始化的bean时，或初始化bean的时候需要注入另一个尚未初始化的依赖时，容器就会调用createBean进行实例化。
 对于ApplicationContext容器，当容器启动结束后，便实例化所有的bean。 容器通过获取BeanDefinition对象中的信息进行实例化。并且这一步仅仅是简单的实例化，并未进行依赖注入。 实例化对象被包装在BeanWrapper对象中，BeanWrapper提供了设置对象属性的接口，从而避免了使用反射机制设置属性。
2. 设置对象属性（依赖注入）实例化后的对象被封装在BeanWrapper对象中，并且此时对象仍然是一个原生的状态，并没有进行依赖注入。
紧接着，Spring根据BeanDefinition中的信息进行依赖注入。 并且通过BeanWrapper提供的设置属性的接口完成依赖注入。
3. 注入Aware接口紧接着，Spring会检测该对象是否实现了xxxAware接口，并将相关的xxxAware实例注入给bean。
4. BeanPostProcessor当经过上述几个步骤后，bean对象已经被正确构造，但如果你想要对象被使用前再进行一些自定义的处理，就可以通过BeanPostProcessor接口实现。
该接口提供了两个函数：postProcessBeforeInitialzation( Object bean, String beanName ) 当前正在初始化的bean对象会被传递进来，我们就可以对这个bean作任何处理。
这个函数会先于InitialzationBean执行，因此称为前置处理。 所有Aware接口的注入就是在这一步完成的。postProcessAfterInitialzation( Object bean, String beanName )
当前正在初始化的bean对象会被传递进来，我们就可以对这个bean作任何处理。 这个函数会在InitialzationBean完成后执行，因此称为后置处理。
5. InitializingBean与init-method当BeanPostProcessor的前置处理完成后就会进入本阶段。
InitializingBean接口只有一个函数：afterPropertiesSet()这一阶段也可以在bean正式构造完成前增加我们自定义的逻辑，但它与前置处理不同，由于该函数并不会把当前bean对象传进来，
因此在这一步没办法处理对象本身，只能增加一些额外的逻辑。 若要使用它，我们需要让bean实现该接口，并把要增加的逻辑写在该函数中。然后Spring会在前置处理完成后检测当前bean是否实现了该接口，
并执行afterPropertiesSet函数。当然，Spring为了降低对客户代码的侵入性，给bean的配置提供了init-method属性，该属性指定了在这一阶段需要执行的函数名。
Spring便会在初始化阶段执行我们设置的函数。init-method本质上仍然使用了InitializingBean接口。
6. DisposableBean和destroy-method和init-method一样，通过给destroy-method指定函数，就可以在bean销毁前执行指定的逻辑。



1. 容器初始化前准备及检查工作：
   * 调用子类容器准备刷新的方法，
   * 获取容器的当时时间
   * 同时给容器设置同步标识
   * 验证需要的属性文件是否都已经放入环境中
prepareRefresh();

2. 获得BeanFactory:调用子类refreshBeanFactory()方法，获得默认DefaultListableBeanFactory。
ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

3. BeanFactory初始化：设置类加载器，增加对属性编辑器的支持，增加对一些内置类(addBeanPostProcessor)，向容器注入bean(systemProperties,systemEnvironment等)，
增加对AspectJ的支持,忽略指定依赖注入bean
prepareBeanFactory(beanFactory);


4. 为容器的某些子类指定特殊的BeanPost事件处理器
   try {
   postProcessBeanFactory(beanFactory);

5. 调用所有注册的BeanFactoryPostProcessor的Bean
   invokeBeanFactoryPostProcessors(beanFactory);

6. 为BeanFactory注册BeanPost事件处理器.
   registerBeanPostProcessors(beanFactory);

7. 初始化信息源，和国际化相关.
   initMessageSource();

8. 初始化容器事件传播器(new SimpleApplicationEventMulticaster(beanFactory))
当产生Spring事件的时候会默认使用SimpleApplicationEventMulticaster的multicasterEvent来广播事件，遍历所有监听器，并使用监听器中的onApplicationEvent方法来进行监听器的处理
   initApplicationEventMulticaster();

9. 调用子类的某些特殊Bean初始化方法
   onRefresh();

10. 为事件传播器注册事件监听器.
   registerListeners();

11. 完成BeanFactory的初始化工作:其中包括ConversionService的设置、配置冻结以及非延迟加载的bean的初始化工作。
   finishBeanFactoryInitialization(beanFactory);

12.完成刷新过程，通知声明周期处理器lifecycleProcessor刷新过程，同时发出ContextRefreshEven通知别人
   finishRefresh();
}
catch (BeansException ex) {
   //销毁以创建的单态Bean
   destroyBeans();
   //取消refresh操作，重置容器的同步标识.
   cancelRefresh(ex);
   throw ex;
}

