package com.crell.core.aspect;

import com.crell.common.model.User;
import com.crell.core.constant.MessageCode;
import com.crell.core.util.LogUtil;
import com.crell.core.util.SystemUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 切面，记录执行方法执行时间
 *
 * @Author crell
 * @Date 2016/1/7 10:52
 */
@Component("serviceAspect")
@Aspect
public class ServiceAspect {

	//定义切入点
	@SuppressWarnings("unused")
	@Pointcut("execution(* com.crell.common.service.*.*(..))")
	private  void pointCut(){}

	@Around("pointCut()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
		//计时时钟
		StopWatch clock = new StopWatch();
		clock.start();
		Object object = pjp.proceed();//执行该方法
        clock.stop();
		User user = SystemUtil.getUser();
		if(user == null) user = new User();
		//写入性能日志
		LogUtil.debug(pjp.getTarget().getClass().getName(), pjp.getSignature().getName(), MessageCode.SYSTEM_ASPECT_NORMAL, Long.toString(clock.getTotalTimeMillis()), user.getIp(), user.getUserName());
		return object;
    }
}
