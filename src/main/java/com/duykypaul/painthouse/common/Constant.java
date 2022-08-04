package com.duykypaul.painthouse.common;

public class Constant {

    public static class AUTH {
        public static final String ADMIN_EMAIL = "lminh9812@gmail.com";
        public static final String ADMIN_PASSWORD = "890*()iop";
        public static final String ADMIN_NAME = "admin";
        public static final String AVATAR = "https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png";
        public static final int EXPIRATION = 60 * 24;
        public static final String AVATAR_DEFAULT = "avatar_default.jpg";

        public enum ROLE {
            ROLE_USER,
            ROLE_MODERATOR,
            ROLE_ADMIN
        }

    }

    public static class FORMAT_DATE {
        public static final String DATE_TIME_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    }
}
