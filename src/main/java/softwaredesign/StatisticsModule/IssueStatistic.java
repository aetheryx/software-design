package softwaredesign.StatisticsModule;

import softwaredesign.UI.TerminalIO;

import java.util.Map;

public class IssueStatistic extends GitHubStatistic {
    public IssueStatistic() {
        super("issues");
    }

    @Override
    public void calculate(Map<String, String> arguments) {
        TerminalIO.write("hi from issues");
    }
}
