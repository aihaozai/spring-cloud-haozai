package spring.cloud.base.datasource.request;


import lombok.Getter;

/**
 * @author haozai
 * @date 2021/4/30  16:07
 */
@Getter
public enum QueryEnum {
    /**
     * 模糊查询
     */
    LIKE("@Like"),

    /**
     *  In查询
     */
    IN("@In");

    private final String keyword;

    QueryEnum(String keyword) {
        this.keyword = keyword;
    }
}
