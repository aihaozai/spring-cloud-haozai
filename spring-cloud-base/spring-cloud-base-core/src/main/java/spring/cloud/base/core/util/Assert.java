package spring.cloud.base.core.util;

/**
 * @author haozai
 * @description 断言
 * @date 2021/8/28  20:24
 */
public class Assert {
    public Assert() {
    }
    public static void thanZero(int count,String msg) throws Exception {
        if(count>0){
            throw new Exception(msg);
        }
    }
}
