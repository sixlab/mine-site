package tech.minesoft.mine.site.core.utils;

import tech.minesoft.mine.site.core.vo.MineException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlUtils {
    public static void checkInject(String cnd) {
        Pattern pattern= Pattern.compile("\\b(and|exec|insert|select|drop|grant|alter|delete|update|count|chr|mid|master|truncate|char|declare|or)\\b|(\\*|;|\\+|'|%)");
        Matcher matcher=pattern.matcher(cnd);
        if (matcher.find()) {
            throw MineException.error(Err.ERR_SQL_INJECT,"sql.inject");
        }
    }

    public static void main(String[] args) {
        checkInject(" select_ ");
        checkInject("select");
    }
}
