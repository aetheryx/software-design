package softwaredesign.ui;

public class ProgressBar {
    private String taskName;
    private int progress = 0;
    private static final int BAR_WIDTH = 35;


    public ProgressBar(String myTaskName) {
        taskName = myTaskName;
    }

    /**
     * This class is constructed with a string argument taskName(), which is displayed to the user
     * when the progress bar is started. This starting process happens with the start() method.
     *
     * @author Ammar
     */
    public void start() {
        TerminalIO.write(
                taskName
                        + ": ["
                        + (" ".repeat(BAR_WIDTH))
                        + "] 0%"
        );
    }

    /**
     * <p>
     * The method used to increment the progress bar is called setProgress(), which takes a single
     * float argument, which is the percentage the bar should be updated to.
     * </p>
     *
     * @author Ammar
     */
    public void setProgress(int newProcent) {
        int newProgress = (newProcent * BAR_WIDTH / 100);
        if (progress == newProgress) {
            return;
        }

        progress = newProgress;
        TerminalIO.writeInPlace(
                taskName + ": [" +
                        "█".repeat(progress) +
                        " ".repeat(BAR_WIDTH - progress)
                        + "] " + newProcent + "%"
        );
    }

    /**
     * <p>
     * Once the calculation of results is done, the finish() method should be called, which takes a
     * string input argument. In this method, the progress bar is cleared from the terminal and the
     * given result is printed.
     * </p>
     *
     * @author ammar
     */
    public void finish(String result) {
        TerminalIO.writeInPlace(result);
    }


    public void finish() {
        this.finish(" \r");
    }
}