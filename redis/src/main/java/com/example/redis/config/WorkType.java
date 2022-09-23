package com.example.redis.config;

/**
 * <code>WorkType</code> 
 *
 * @author    dengrijin<dengrijin@cecdat.com>
 * @version  v0.1 2019/08/14
 *  
 * @see      
 * @since    JDK1.8 
 */
public enum WorkType {
    /**
     *  工作模式,默认单机
     * */
    SINGLE(0, "单机"),
    SENTINEL(1, "哨兵"),
    CLUSTER(2, "集群");

    /**
     * 主键
     */
    private final int key;

    /**
     * 描述
     */
    private final String desc;

    WorkType(final int key, final String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static WorkType convert(String type) {
        if (type == null){
            return SINGLE;
        }
        type = type.toUpperCase();
        for (WorkType workType: WorkType.values()) {
            if (workType.name().equals(type)){
                return workType;
            }
        }
        return SINGLE;
    }
}
