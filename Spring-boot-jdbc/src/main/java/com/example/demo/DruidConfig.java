package com.example.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfig {
	@ConfigurationProperties(prefix ="spring.datasource")
	@Bean
public DataSource druid() {
	return new DruidDataSource();
}
	 @Bean
 public ServletRegistrationBean<Servlet> servlets(){
	
	 ServletRegistrationBean<Servlet> s=new ServletRegistrationBean<Servlet>(new StatViewServlet(),"/druid/*");
      Map<String, String> m=new HashMap<>();
      m.put("loginUsername","cw");
      m.put("loginPassword","111");
      m.put("allow","localhost");
      s.setInitParameters(m);
	 return s;
	 }
	 @Bean
  public FilterRegistrationBean<Filter> filterr(){
	  FilterRegistrationBean<Filter> f=new FilterRegistrationBean<Filter>();
	  f.setFilter(new WebStatFilter());
	  Map<String,String> m=new HashMap<String, String>();
     f.setInitParameters(m);
     f.setUrlPatterns(Arrays.asList("/*"));
	  return f;
  }
}
