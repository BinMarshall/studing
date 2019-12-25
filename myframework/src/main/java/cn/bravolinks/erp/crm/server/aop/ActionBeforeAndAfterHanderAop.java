package cn.bravolinks.erp.crm.server.aop;

import cn.bravolinks.erp.crm.server.feign.IMService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author yanqin
 * 所有action AOP 拦截处理
 */
@Component
@Aspect
public class ActionBeforeAndAfterHanderAop {

	private final static Logger logger = LoggerFactory.getLogger(ActionBeforeAndAfterHanderAop.class);
	@Autowired
	private IMService imService;
	@Value("${aopErrorSendMailReceiver}")
	private String errorMailReceiver;

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void action(){}

	/**
	 * 存留言
	 * @param point
	 */
	@Around(value = "action()")
	public Object doAround(ProceedingJoinPoint point) throws Throwable {
		StringBuilder sb;
		//参数值
		Object[] args = point.getArgs();
		MethodSignature methodSignature = (MethodSignature) point.getSignature();
		//参数名
		String[] parameters = methodSignature.getParameterNames();
		//类名和方法名
		String methodName = methodSignature.toShortString();
		long time = System.currentTimeMillis();
		try {
			/**
			 * 拼接参数信息
			 */
			sb = new StringBuilder();
			sb.append(methodName+"进入");
			sb.append("\nTime : "+time);
			for(int i=0;i<parameters.length;i++){
				sb.append("\n"+parameters[i]+" : ");
				if(i<args.length){
					sb.append(args[i]==null?"AOP获取传参为null":args[i].toString());
				}
			}
			/**
			 * 输出参数信息
			 */
			logger.warn(sb.toString());
			/**
			 * 执行action
			 */
			Object o = point.proceed();
			/**
			 * 拼接返回信息
			 */
			sb = new StringBuilder();
			sb.append(methodName+"返回");
			sb.append("\nTime : "+time);
			sb.append("\nResult : ");
			sb.append(o==null?"AOP获取返回为null":o.toString());
			/**
			 * 输出返回信息
			 */
			logger.warn(sb.toString());
			return o;
		}catch (Exception e){
			/**
			 * 拼接异常信息
			 */
			sb = new StringBuilder();
			sb.append(methodName+"异常");
			sb.append("\nTime : "+time);
			sb.append("\nAOP输出异常");
			/**
			 * 输出异常信息
			 */
			logger.error(sb.toString(),e);
			if(!StringUtils.isEmpty(errorMailReceiver)){
				errorMailReceiver = errorMailReceiver.replace(","," ");
				final String form = "admin";
				String title = "ERP-CRM-SERVICE : AOP拦截到异常信息";
				StringBuilder content = new StringBuilder();
				content.append(sb.toString() + "\n" + e.toString()+" : "+e.getMessage());
				StackTraceElement[] es = e.getStackTrace();
				for (StackTraceElement element : es) {
					content.append("\n"+element.toString());
				}
				imService.sendMail(form,errorMailReceiver,title,content.toString());
			}
			throw e;
		}
	}
}
