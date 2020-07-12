package com.lkker.sampleauth.common.utils;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author liliang
 * @Date 2020/7/12 17:40
 * @Description
 **/
public class IdGenerator implements Serializable {

    /**
     * 默认包名
     */
    private static final String DEFAULT_PROJECT_NAME = "LY";

    /**
     * 默认表名
     */
    private static final String DEFAULT_TABLE_NAME = "LYZS";

    /**
     * 默认IP
     */
    private static final String DEFAULT_IP = "127001";


    private static final Map<Class, TabEntity> TAB_NAME_MAP = new ConcurrentHashMap<>();


    private final static Logger logger = LoggerFactory.getLogger(IdGenerator.class);


    private final int maxNum = 89999;


    private final String formatStr = "%06d";


    private String curName;

    private String ip;

    private static IdGenerator idGenerator;

    private IdGenerator() {
        getCurName();
        getIp();
    }

    public static IdGenerator getInstace() {
        if (null == idGenerator) {
            synchronized (IdGenerator.class) {
                if (null == idGenerator) {
                    idGenerator = new IdGenerator();
                }
            }
        }
        return idGenerator;
    }

    /**
     * 获取包名 前两位
     *
     * @param entity
     * @param <T>
     * @return
     */
    private <T> String getProjectName(Class<T> entity) {
        String pageName = entity.getPackage().getName();
        String lkker = "com.lkker.";
        if (pageName.contains(lkker)) {
            final int index = pageName.indexOf(lkker) + lkker.length();
            return pageName.substring(index, index + 2).toUpperCase();
        }

        return DEFAULT_PROJECT_NAME;
    }


    /**
     * 获取表名  大写 + 小写（共四位）
     *
     * @param entity
     * @param <T>
     * @return
     */
    private <T> String getTableName(Class<T> entity) {
        String name = entity.getSimpleName();
        int len = 4;
        char[] ch = name.toCharArray();
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= 'A' && ch[i] <= 'Z') {
                buffer.append(ch[i]);
                if (buffer.length() >= len) {
                    break;
                }
            }
        }

        if (buffer.length() < len) {
            for (int i = 0; i < ch.length; i++) {

                if (ch[i] >= 'a' && ch[i] <= 'z') {
                    buffer.insert(i, ch[i]);
                    if (buffer.length() >= len) {
                        break;
                    }
                }
            }
        }
        String tabName = buffer.toString();
        if (tabName.length() < len) {
            tabName = DEFAULT_TABLE_NAME;
            logger.warn("IdGenerator : class {},length < {}", name, len);
        }
        return tabName.toUpperCase();
    }


    /**
     * 获取 IP 六位
     *
     * @return
     */
    private String getIp() {
        try {
            if (this.ip == null) {
                String ip = InetAddress.getLocalHost().getHostAddress();
                if (StringUtils.isNotBlank(ip)) {
                    String replace = ip.replace(".", "");

                    int length = replace.length();

                    this.ip = replace.substring(length - 6, length);
                    return this.ip;
                } else {
                    String hostName = InetAddress.getLocalHost().getHostName();

                    if (StringUtils.isNotBlank(hostName)) {
                        int length = hostName.length();
                        if (length >= 6) {
                            hostName = hostName.substring(length - 6, length);
                        }

                        this.ip = hostName;
                        return hostName;
                    }
                }
            } else {
                return this.ip;
            }
        } catch (UnknownHostException e) {
            logger.error(e.getMessage(), e);
        }
        return DEFAULT_IP;
    }

    private String getCurName() {
        if (this.curName == null) {
            final String loadClass = System.getProperty("sun.java.command");
            String laiyuezs = "com.laiyuezs.";
            if (loadClass != null && loadClass.contains(laiyuezs)) {
                final int indexOf = loadClass.indexOf(laiyuezs) + laiyuezs.length();
                curName = loadClass.substring(indexOf, indexOf + 2).toUpperCase();
            } else {
                curName = RandomStringUtils.randomAlphabetic(2);
            }
        }
        return curName;
    }

    /**
     * 获取时间 十四位  yyyyMMddHHmmss
     *
     * @return
     */
    private String getTableDate() {
        return DateUtil.dateToString(new Date(), DateStyle.YYYYMMDDHHMM);
    }


    public static String getNextId(Class c) {
        return IdGenerator.getInstace().nextId(c);
    }

    public static String getNextShortId(Class c) {
        return toShortId(getNextId(c));
    }

    public static String toShortUserId(String id) {
        if (id == null) {
            return null;
        }
        if (id.length() == 32) {
            final String hex = Long.toUnsignedString(Long.parseLong(id.substring(10, 26)), 32);
            final String hex2 = Long.toUnsignedString(Long.parseLong(id.substring(26)), 32);
            return id.substring(0, 2).concat(id.substring(6, 8)).concat(hex.toUpperCase()).concat(StringUtils.leftPad(hex2.toUpperCase(), 3, '0'));
        }
        return id;
    }

    public static String toLongUserId(String id) {
        if (id == null) {
            return null;

        }
        if (id.length() == 32 || id.length() < 16) {
            return id;
        }
        final String hex = Long.toUnsignedString(Long.parseLong(id.substring(4, 15).toLowerCase(), 32), 10);
        final String hex2 = Long.toUnsignedString(Long.parseLong(id.substring(15).toLowerCase(), 32), 10);
        return id.substring(0, 2).concat("USER").concat(id.substring(2, 4)).concat("20").concat(hex).concat(StringUtils.leftPad(hex2, 6, '0'));
    }

    public static String toShortId(String id) {
        if (id == null) {
            return null;
        }
        final String hex = Long.toUnsignedString(Long.parseLong(id.substring(10, 26)), 32);
        final String hex2 = Long.toUnsignedString(Long.parseLong(id.substring(26)), 32);
        return id.substring(0, 8).concat(hex.toUpperCase()).concat(StringUtils.leftPad(hex2.toUpperCase(), 3, '0'));
    }

    public static String toLongId(String id) {
        if (id == null) {
            return null;
        }
        if (id.length() == 32) {
            return id;
        }
        final String hex = Long.toUnsignedString(Long.parseLong(id.substring(8, 19).toLowerCase(), 32), 10);
        final String hex2 = Long.toUnsignedString(Long.parseLong(id.substring(19).toLowerCase(), 32), 10);
        return id.substring(0, 8).concat("20").concat(hex).concat(StringUtils.leftPad(hex2, 6, '0'));
    }

    /**
     * 获取ID（32位） 格式：项目(2)  +  表名(4) +当前项目（2） + 时间（yyyyMMddHHmm）(12)+ ip(末尾6位) (6)+ (6)
     *
     * @param c 传入的对象
     * @return 当前流水id
     */
    public String nextId(Class c) {
        TabEntity tabEntity = null;
        int andIncrement = 1;
        if (TAB_NAME_MAP.containsKey(c)) {
            tabEntity = TAB_NAME_MAP.get(c);
            andIncrement = tabEntity.getAtomicInteger().incrementAndGet();
            boolean isNowTime = !tabEntity.getTableDate().equals(getTableDate());
            if (isNowTime) {
                synchronized (c.getName().intern()) {
                    isNowTime = !tabEntity.getTableDate().equals(getTableDate());
                    if (isNowTime) {
                        tabEntity.getAtomicInteger().set(1);
                        tabEntity.setTableDate(getTableDate());
                        andIncrement = 1;
                    } else {
                        andIncrement = tabEntity.getAtomicInteger().incrementAndGet();
                    }
                }
            }
        } else {
            synchronized (c.getName().intern()) {
                tabEntity = TAB_NAME_MAP.get(c);
                if (tabEntity == null) {
                    tabEntity = new TabEntity(getProjectName(c), getTableName(c), getCurName(), getIp(), getTableDate());
                    tabEntity.setAtomicInteger(new AtomicInteger(andIncrement));
                    TAB_NAME_MAP.put(c, tabEntity);
                } else {
                    andIncrement = tabEntity.getAtomicInteger().incrementAndGet();
                }

            }
        }
        return tabEntity.toString(andIncrement);
    }


    public static String randomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Data
    class TabEntity {

        private String projectName;

        private String tableName;

        private String curName;

        private String ip;

        private String tableDate;

        private AtomicInteger atomicInteger;

        public TabEntity(String projectName, String tableName, String curName, String ip, String tableDate) {
            this.projectName = projectName;
            this.tableName = tableName;
            this.curName = curName;
            this.ip = ip;
            this.tableDate = tableDate;
        }


        public String toString(String tableDate, int count) {
            return projectName + tableName + curName + tableDate + ip + idPlus(count);
        }

        public String toString(int count) {
            return projectName + tableName + curName + tableDate + ip + idPlus(count);
        }


        /**
         * 对当前id进行增加 返回6为0补齐的字符串
         *
         * @return
         */
        private String idPlus(int idCount) {
            return String.format(formatStr, idCount);
        }
    }

    /*public static void main(String[] args) {


        testNextIdMethod();

        String idStr = getNextId(IdGenerator.class);
        final String shortId = toShortId(idStr);

        System.out.println(shortId);


        final String longId = toLongId(shortId);

        System.out.println(longId);
        final String shortUserId = toShortUserId(longId);

        System.out.println(shortUserId);

        final String longUserId = toLongUserId("USAP1MA4U47B0OK001");

        System.out.println(longUserId);


    }

    static void testNextIdMethod() {
        Thread thread1 = new Thread(new IdTestThread());
        Thread thread2 = new Thread(new IdTestThread());
        thread1.start();
        thread2.start();
    }

    static class IdTestThread implements Runnable {
        private static final List<String> strList = new ArrayList<>();

        @Override
        public void run() {
            int time = DateUtil.curTime();
            for (; ; ) {
                final String nextId = IdGenerator.getInstace().nextId((IdGenerator.class));
                synchronized (strList) {
                    System.out.println(nextId + ":" + strList.contains(nextId));
                    if (strList.contains(nextId)) {
                        System.out.println(nextId + ":" + strList.contains(nextId));
                    } else {
                        strList.add(nextId);
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (DateUtil.curTime() - time >= 130) {
                    break;
                }

            }
        }
    }
*/

}


