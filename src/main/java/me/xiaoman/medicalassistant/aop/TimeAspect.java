package me.xiaoman.medicalassistant.aop;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.xiaoman.medicalassistant.util.JsonParser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 记录时间
 *
 * @author pacman
 * @version 1.0
 * date: 2018/4/6 17:00
 */

@Component
@Aspect
public class TimeAspect {
    private static final Logger logger = LoggerFactory.getLogger(TimeAspect.class);

    /**
     * 定义一个切入点.
     * 解释下：
     * <p>
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 定义在web包或者子包
     * ~ 第三个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    @Pointcut("execution(* me.xiaoman.medicalassistant.ocr.SmartOcr.recognize(..))")
    public void logPointcut() {
    }

    @Around("logPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
//    	 LOG.debug("logPointcut " + joinPoint + "\t");
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long end = System.currentTimeMillis();
            logger.error("+++++around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
            return result;

        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            logger.error("+++++around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
            throw e;
        }

    }

//    private String buildJson(Object result, long time) {
//        JSONObject json = new JSONObject();
//        if (result instanceof Iterable){
//            json.put("result", JsonParser.toObject(String.valueOf(result),JSONArray.class));
//        }else {
//            json.put("result", JsonParser.toObject(String.valueOf(result),JSONObject.class));
//        }
//        json.put("elapsed", time);
//        return json.toString();
//    }

}
