package pers.tuershen.bladeplus;


import org.bukkit.Server;

public class BladePlusVersion {

    private static ServerVersion serverVersion;

    public static void init(Server server) {
        String version = paraphrase(server);
        serverVersion = getServerVersion(version);
    }

    private static String paraphrase(Server server) {
        return server.getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }

    private static ServerVersion getServerVersion(String version){
        ServerVersion[] serverVersions = ServerVersion.values();
        for (ServerVersion serverVersion : serverVersions) {
            if (serverVersion.version.equalsIgnoreCase(version)) return serverVersion;
        }
        return ServerVersion.V1_7_R1;
    }

    public static String getSoundName(){
        return serverVersion.getSoundType();
    }



    private enum ServerVersion {
        v1_6_R3("v1_6_R3") {
            @Override
            public String getSoundType() {
                return "ANVIL_LAND";
            }
        },
        V1_7_R1("v1_7_R1") {
            @Override
            public String getSoundType() {
                return "ANVIL_LAND";
            }
        },
        V1_7_R2("v1_7_R2") {
            @Override
            public String getSoundType() {
                return "ANVIL_LAND";
            }
        },
        V1_7_R3("v1_7_R3") {
            @Override
            public String getSoundType() {
                return "ANVIL_LAND";
            }
        },
        V1_7_R4("v1_7_R4") {
            @Override
            public String getSoundType() {
                return "ANVIL_LAND";
            }
        },
        V1_8_R1("v1_8_R1") {
            @Override
            public String getSoundType() {
                return "BLOCK_ANVIL_LAND";
            }

        },
        V1_8_R2("v1_8_R2") {
            @Override
            public String getSoundType() {
                return "BLOCK_ANVIL_LAND";
            }
        },
        V1_8_R3("v1_8_R3") {
            @Override
            public String getSoundType() {
                return "BLOCK_ANVIL_LAND";
            }
        },
        V1_9_R1("v1_9_R1") {
            @Override
            public String getSoundType() {
                return "BLOCK_ANVIL_LAND";
            }
        },
        V1_9_R2("v1_9_R2") {
            @Override
            public String getSoundType() {
                return "BLOCK_ANVIL_LAND";
            }
        },
        V1_10_R1("v1_10_R1") {
            @Override
            public String getSoundType() {
                return "BLOCK_ANVIL_LAND";
            }
        },
        V1_11_R1("v1_11_R1") {
            @Override
            public String getSoundType() {
                return "BLOCK_ANVIL_LAND";
            }
        },
        V1_12_R1("v1_12_R1") {
            @Override
            public String getSoundType() {
                return "BLOCK_ANVIL_LAND";
            }
        };

        String version;

        ServerVersion(String version) {
            this.version = version;
        }

        public abstract String getSoundType();

    }


}

