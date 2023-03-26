package softwaredesign.StatisticsModule;

import java.util.HashMap;
import java.util.Map;

public class StatisticsManager {
    private static final Map<String, Statistic> statistics = new HashMap<>();

    public static Statistic getStatisticByName(String name) {
        return statistics.get(name);
    }

    public static void registerStatistic(Statistic statistic) {
        statistics.put(statistic.getName(), statistic);
    }
}
