import java.time.Duration;
import java.time.LocalDateTime;

public class Statistics {

    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    public Statistics() {
        totalTraffic = 0;
        minTime = LocalDateTime.MIN;
        maxTime = LocalDateTime.MAX;
    }

    public void addEntry(LogEntry entry) {
        totalTraffic += entry.getResponseSize();

        if (entry.getTime().isBefore(minTime)) {
            minTime = entry.getTime();
        }

        if (entry.getTime().isAfter(maxTime)) {
            maxTime = entry.getTime();
        }
    }

    public double getTrafficRate() {
        long hourDifference = Duration.between(minTime, maxTime).toHours();

        return (double) totalTraffic / hourDifference;
    }
}