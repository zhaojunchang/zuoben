package com.zuoben.util.resultUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.function.Supplier;

/**
 * 异常处理工具类
 * Created by gaoxiang on 2016-11-09.
 */
public class SafeExecutor {
    private static final Logger LOG = LoggerFactory.getLogger(SafeExecutor.class);

    /**
     * 有事务的方式调用 eg: save update delete  do
     *
     * @param innerProcessor
     * @return
     */
    public static <E> JsonResult<E> execute(Supplier<JsonResult<E>> innerProcessor) {
        try {
            return innerProcessor.get();
        } catch (IllegalArgumentException e) {
            LOG.error(e.getMessage() + e);
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage() + e);
            e.printStackTrace();
            try {
                //add insert save update delete 方法需要回滚
                // get find is 方法不需要回滚
                // 其他方法 则会报错
//                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
            return JsonResult.fail("出了一点问题，请稍后再试");
        }
    }

    /**
     * 没有事务的方法调用 比如： find get query  select 等
     *
     * @param innerProcessor
     * @return
     */
    public static <E> JsonResult<E> noTranExecute(Supplier<JsonResult<E>> innerProcessor) {
        try {
            return innerProcessor.get();
        } catch (IllegalArgumentException e) {
            LOG.error(e.getMessage() + e);
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage() + e);
            e.printStackTrace();
            return JsonResult.fail("出了一点问题，请稍后再试");
        }
    }
}
