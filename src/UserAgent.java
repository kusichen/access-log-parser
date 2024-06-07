public class UserAgent {

    private final OperSystem opersys;
    private final Browser br;

    public UserAgent(String str) {
        String[] properties = str.split(" ");

        opersys = OperSystem.parse(properties[2]);
        br = Browser.parse(properties[4]);
    }

    public OperSystem getOs() {
        return opersys;
    }

    public Browser getBrowser() {
        return br;
    }

    @Override
    public String toString() {
        return "UserAgent{" +
                "os=" + opersys +
                ", browser=" + br +
                '}';
    }

    public enum OperSystem {
        WINDOWS, MACOS, LINUX, OTHER;

        public static OperSystem parse(String str) {
            if (str.startsWith("Windows")) {
                return WINDOWS;
            } else if (str.startsWith("Macintosh")) {
                return MACOS;
            } else if (str.startsWith("Linux")) {
                return LINUX;
            } else {
                return OTHER;
            }
        }
    }

    public enum Browser {
        EDGE, FIREFOX, CHROME, OPERA, OTHER;

        public static Browser parse(String str) {
            if (str.startsWith("Edge")) {
                return EDGE;
            } else if (str.startsWith("Firefox")) {
                return FIREFOX;
            } else if (str.startsWith("Chrome")) {
                return CHROME;
            } else if (str.startsWith("Opera")) {
                return OPERA;
            } else {
                return OTHER;
            }
        }
    }
}
