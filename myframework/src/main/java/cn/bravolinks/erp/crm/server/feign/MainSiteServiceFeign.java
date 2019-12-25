package cn.bravolinks.erp.crm.server.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * mainsite service feign
 *
 * @author yanqin
 * @create 2018-06-14
 **/
@FeignClient(value = "${remote.server.pmserver.tomainsiteserver}")
public interface MainSiteServiceFeign {

	/**
	 *
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/searchBasicInfo/selectServiceRun",method = RequestMethod.POST)
	Map<String,Object> selectServiceRun(@RequestParam("methodName") String methodName, @RequestBody Map<String, Object> params);
}
