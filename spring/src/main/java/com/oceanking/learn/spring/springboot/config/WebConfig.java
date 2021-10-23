package com.oceanking.learn.spring.springboot.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.oceanking.learn.spring.springboot.filter.MyFilter;
import com.oceanking.learn.spring.springboot.interceptor.MyInterceptor;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean<MyFilter> filter = new FilterRegistrationBean<>(new MyFilter());
        filter.addUrlPatterns("/*");
        return filter;
    }

    /**
     * token 拦截器
     *
     * @return
     */
    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor()).addPathPatterns("/**").excludePathPatterns();
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse
        );
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastConverter.setFastJsonConfig(fastJsonConfig);
        List<MediaType> fastJsonMediaTypes = new ArrayList<>();
        fastJsonMediaTypes.add(MediaType.APPLICATION_JSON);
        fastJsonMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        fastJsonMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        fastJsonMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        fastJsonMediaTypes.add(MediaType.APPLICATION_PDF);
        fastJsonMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        fastJsonMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        fastJsonMediaTypes.add(MediaType.APPLICATION_XML);
        fastConverter.setSupportedMediaTypes(fastJsonMediaTypes);

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        List<MediaType> stringMediaTypes = new ArrayList<>();
        stringMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        stringMediaTypes.add(MediaType.TEXT_HTML);
        stringMediaTypes.add(MediaType.TEXT_MARKDOWN);
        stringMediaTypes.add(MediaType.TEXT_PLAIN);
        stringMediaTypes.add(MediaType.TEXT_XML);
        stringHttpMessageConverter.setSupportedMediaTypes(stringMediaTypes);
        return new HttpMessageConverters(fastConverter, stringHttpMessageConverter);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}
