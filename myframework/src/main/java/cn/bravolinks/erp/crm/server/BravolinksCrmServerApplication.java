package cn.bravolinks.erp.crm.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.bravolinks.framework.pangu.provider.PanguServiceProvider;
import cn.bravolinks.framework.pangu.provider.springboot.PanguProviderSpringBootApplication;
@PanguServiceProvider
@SpringBootApplication
public class BravolinksCrmServerApplication extends PanguProviderSpringBootApplication{

	public static void main(String[] args) {
		SpringApplication.run(BravolinksCrmServerApplication.class, args);
		System.out.println("BRAVOLINKS_CRM_SERVER 启动完成！");
	}
}
