package tta.orderCollector.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.context.WebApplicationContext;

import tta.orderCollector.tasks.FixRouter;
import tta.orderCollector.utils.OrderCollectorUtils;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class RouterConfig {

	@Value("${config.ordersPath}")
	private String ordersPath;

	@Value("${rest.urlReferentiel}")
	private String urlReferentiel;

	@Value("${bt.proxyHost}")
	private String proxyHost;

	@Value("${bt.proxyPort}")
	private int proxyPort;

	// this bean needed to resolve ${property.name} syntax
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public void setProperties() {
		
		OrderCollectorUtils.ordersPath=ordersPath;
		OrderCollectorUtils.urlReferentiel=urlReferentiel;
		OrderCollectorUtils.proxyHost=proxyHost;
		OrderCollectorUtils.proxyPort=proxyPort;
	}

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
	public FixRouter fixRouter() {
		try {
			FixRouter fix = new FixRouter();
			return fix;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}





}
