package com.buffalo.gateway;

import com.alibaba.druid.pool.DruidDataSource;
import com.buffalo.gateway.authentication.service.AuthUserDetailsService;
import com.buffalo.gateway.druid.DruidSettings;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
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
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Properties;

@SpringBootApplication
@EnableWebMvc
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@MapperScan("com.buffalo.gateway.*.mapper")
@EnableConfigurationProperties(DruidSettings.class)
public class GatewayOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayOrderApplication.class, args);
	}


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




	//修改DispatcherServlet默认配置
	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
		return registration;
	}


	//提供SqlSeesion
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*/*.xml"));
		sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper()});
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws Exception {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public PageHelper pageHelper(){
		//分页插件
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("helperDialect", "mysql");
		properties.setProperty("reasonable", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("params", "count=countSql");
		pageHelper.setProperties(properties);
		return pageHelper;
	}


	@EnableWebSecurity
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	public class SecurityConfig extends WebSecurityConfigurerAdapter {



		@Autowired
		private AuthUserDetailsService userDetailsService;

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
			http.requestMatchers()
					.antMatchers("/oauth/**")
					.and()
					.authorizeRequests()
					.antMatchers("/oauth/**").authenticated();

		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService);
					//.passwordEncoder(new BCryptPasswordEncoder());
		}


		/**
		 * 需要配置这个支持password模式
		 * support password grant type
		 *
		 * @return
		 * @throws Exception
		 */
		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
	}


	@Configuration
	@EnableAuthorizationServer //提供/oauth/authorize,/oauth/token,/oauth/check_token,/oauth/confirm_access,/oauth/error
	public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			oauthServer
					.tokenKeyAccess("permitAll()") //url:/oauth/token_key,exposes public key for token verification if using JWT tokens
					.checkTokenAccess("isAuthenticated()") //url:/oauth/check_token allow check token
					.allowFormAuthenticationForClients();
		}

		/**
		 * 注入authenticationManager
		 * 来支持 password grant type
		 */
		@Autowired
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.authenticationManager(authenticationManager);
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory()
					.withClient("bfl")
					.secret("bfl")
					.authorizedGrantTypes("client_credentials", "password", "refresh_token")
					.scopes("all")
					.resourceIds("oauth2-resource")
					.accessTokenValiditySeconds(360000)
					.refreshTokenValiditySeconds(360000);
		}
	}


	@Configuration
	@EnableResourceServer
	public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

		/**
		 * 这里设置需要token验证的url
		 * 这些需要在WebSecurityConfigurerAdapter中排查掉
		 * 否则优先进入WebSecurityConfigurerAdapter,进行的是basic auth或表单认证,而不是token认证
		 *
		 * @param http
		 * @throws Exception
		 */
		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.requestMatchers().antMatchers("/**")
					.and()
					.authorizeRequests()
					.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
					.antMatchers("/**").authenticated();
		}
	}

	@Bean
	@Order(Integer.MAX_VALUE)
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
