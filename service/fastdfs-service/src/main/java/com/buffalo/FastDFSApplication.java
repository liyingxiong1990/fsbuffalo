package com.buffalo;
import com.alibaba.druid.pool.DruidDataSource;
import com.buffalo.druid.DruidSettings;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.util.Properties;

@SpringBootApplication
@EnableEurekaClient
@ServletComponentScan
@ComponentScan
@EnableWebMvc
@Import(FdfsClientConfig.class)
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@EnableConfigurationProperties({DruidSettings.class})
@MapperScan("com.gdotc.*.mapper")
public class FastDFSApplication {

	@Autowired
	private DruidSettings druidSettings;

	//Druid DataSource配置
	@Bean
	@ConfigurationProperties("spring.datasource")
	public DruidDataSource dataSource() throws Exception{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(druidSettings.getDriverClassName());
		dataSource.setUrl(druidSettings.getUrl());
		dataSource.setUsername(druidSettings.getUsername());
		dataSource.setPassword(druidSettings.getPassword());
		dataSource.setInitialSize(druidSettings.getInitialSize());
		dataSource.setMinIdle(druidSettings.getMinIdle());
		dataSource.setMaxActive(druidSettings.getMaxActive());
		dataSource.setMaxWait(druidSettings.getMaxWait());
		dataSource.setTimeBetweenEvictionRunsMillis(druidSettings.getTimeBetweenEvictionRunsMillis());
		dataSource.setMinEvictableIdleTimeMillis(druidSettings.getMinEvictableIdleTimeMillis());
		String validationQuery = druidSettings.getValidationQuery();
		if (validationQuery != null && !"".equals(validationQuery)) {
			dataSource.setValidationQuery(validationQuery);
		}
		dataSource.setTestWhileIdle(druidSettings.isTestWhileIdle());
		dataSource.setTestOnBorrow(druidSettings.isTestOnBorrow());
		dataSource.setTestOnReturn(druidSettings.isTestOnReturn());
		if(druidSettings.isPoolPreparedStatements()){
			dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidSettings.getMaxPoolPreparedStatementPerConnectionSize());
		}
		dataSource.setFilters(druidSettings.getFilters());//这是最关键的,否则SQL监控无法生效
		String connectionPropertiesStr = druidSettings.getConnectionProperties();
		if(connectionPropertiesStr != null && !"".equals(connectionPropertiesStr)){
			Properties connectProperties = new Properties();
			String[] propertiesList = connectionPropertiesStr.split(";");
			for(String propertiesTmp:propertiesList){
				String[] obj = propertiesTmp.split("=");
				String key = obj[0];
				String value = obj[1];
				connectProperties.put(key,value);
			}
			dataSource.setConnectProperties(connectProperties);
		}
		dataSource.setUseGlobalDataSourceStat(druidSettings.isUseGlobalDataSourceStat());

		return dataSource;
	}

	//提供SqlSeesion
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws Exception {
		return new DataSourceTransactionManager(dataSource());
	}

	//修改DispatcherServlet默认配置
    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
        return registration;
    }

	//显示声明CommonsMultipartResolver为mutipartResolver
	@Bean(name = "multipartResolver")
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
		resolver.setMaxInMemorySize(40960);
		resolver.setMaxUploadSize(50*1024*1024);//上传文件大小 50M 50*1024*1024
		return resolver;
	}


	public static void main(String[] args) {
		SpringApplication.run(FastDFSApplication.class, args);
	}
}
