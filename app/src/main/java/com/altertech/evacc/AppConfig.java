package com.altertech.evacc;

/**
 * Created by oshevchuk on 20.03.2019
 */
public class AppConfig {

    public final static String NAME = "EVA ICS Control Center", VERSION = "7.0", COPYRIGHT = "© 2019 Altertech Group";

    public final static ServerConfig CONFIG = new ServerConfig(false, false, "", 443);

    public final static boolean AUTHENTICATION = true;

    public static class ServerConfig {

        final private boolean enabled, useHttps;
        final private String address;
        final private int port;

        /**
         * @param enabled
         * @param useHttps
         * @param address
         * @param port
         */
        ServerConfig(boolean enabled, boolean useHttps, String address, int port) {
            this.enabled = enabled;
            this.useHttps = useHttps;
            this.address = address;
            this.port = port;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public boolean isUseHttps() {
            return useHttps;
        }

        public String getAddress() {
            return address;
        }

        public int getPort() {
            return port;
        }
    }
}
