package softwaredesign.UI;

public class ProgressBar {
    private static String taskName;
    private static int progress;
    private static final int barWidth = 35;


    public ProgressBar(String myTaskName) {
        taskName = myTaskName;
        progress = 0;
    }

    /**
     * <p>
     *     This class is constructed with a string argument taskName(), which is displayed to the user
     *      when the progress bar is started. This starting process happens with the start() method.
     *
     * </p>
     * @author Ammar
     */
    public void start() {
        System.out.print(taskName + ": [");
        for (int i = 0; i < barWidth; i++) {
            System.out.print("░");
        }
        System.out.print("] 0%");
    }

    /**
     * <p>
     *     The method used to increment the progress bar is called setProgress(), which takes a single
     *      float argument, which is the percentage the bar should be updated to.
     * </p>
     * @author Ammar
     */
    public void setProgress(float percent) {
        progress = (int) (percent * barWidth / 100);
        System.out.print("\r" + taskName + ": [");
        for (int i = 0; i < progress; i++) {
            System.out.print("█");
        }
        for (int i = progress; i < barWidth; i++) {
            System.out.print(" ");
        }
        System.out.print("] " + (int) percent + "%");
    }

    /**
     * <p>
     *     Once the calculation of results is done, the finish() method should be called, which takes a
     *      string input argument. In this method, the progress bar is cleared from the terminal and the
     *      given result is printed.
     * </p>
     * @author ammar
     */
    public void finish(String result) {
        System.out.print("\r" + taskName + ": [");
        for (int i = 0; i < barWidth; i++) {
            System.out.print("█");
        }
        System.out.print("] 100%\r");
        System.out.println(result);
        System.out.println("\r");


    }
}