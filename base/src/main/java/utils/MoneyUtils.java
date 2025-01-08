package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金额工具类
 *
 * @author xujie
 * @since 2025/01/07 20:37
 */
public class MoneyUtils {

    /**
     * 元转分
     *
     * @param number
     * @return
     */
    public static Long yuanToCent(BigDecimal number){
        return number.multiply(new BigDecimal("100")).longValue();
    }

    /**
     * 分转元
     *
     * @param number
     * @return
     */
    public static BigDecimal centToYuan(Long number){
        // RoundingMode.HALF_UP 表示四舍五入
        return new BigDecimal(number.toString()).divide(new BigDecimal("100"),2, RoundingMode.HALF_UP);
    }
}
