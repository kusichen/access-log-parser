import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogEntry {

    private final String ipAddress;
    private final LocalDateTime time;
    private final HttpMethod requestMethod;
    private final String requestPath;
    private final int responseCode;
    private final int responseSize;
    private final String referer;
    private final UserAgent userAgent;


    public LogEntry(String line) {
        String[] parts = line.split(" ");

        ipAddress = parts[0];
        time = LocalDateTime.parse(parts[3].substring(1),
                DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z"));
        requestMethod = HttpMethod.valueOf(parts[5]);
        requestPath = parts[6];
        responseCode = Integer.parseInt(parts[8]);
        responseSize = Integer.parseInt(parts[9]);
        referer = parts[10];
        userAgent = new UserAgent(parts[12]);
    }

    public enum HttpMethod {
        DELETE("DELETE"), GET("GET"), POST("POST"), PUT("PUT");

        HttpMethod(String txt) {
            this.txt = txt;
        }

        String txt;

        @Override
        public String toString() {
            return "HttpMethod{" +
                    "txt='" + txt + '\'' +
                    '}';
        }

    }

    public String getIpAddress() {
        return ipAddress;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public HttpMethod getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public long getResponseSize() {
        return responseSize;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "ipAddress='" + ipAddress + '\'' +
                ", timestamp=" + time +
                ", method=" + requestMethod +
                ", requestPath='" + requestPath + '\'' +
                ", responseCode=" + responseCode +
                ", responseSize=" + responseSize +
                ", referrer='" + referer + '\'' +
                ", userAgent=" + userAgent +
                '}';
    }

}
