package softwaredesign;



public class ProgressBar {
    private static String taskName;
    private static int progress;
    private static int barWidth;
    private static boolean isProgressing;


    public ProgressBar(String taskName, int barWidth) {
        this.taskName = taskName;
        this.progress = 0;
        this.barWidth = barWidth;


    }

    public void start() {
        System.out.print(taskName + ": [");
        for (int i = 0; i < barWidth; i++) {
            System.out.print(" ");
        }
        System.out.print("] 0%");
    }

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

    public void finish(String result) {
        System.out.print("\r" + taskName + ": [");
        for (int i = 0; i < barWidth; i++) {
            System.out.print("=");
        }
        System.out.print("] 100%\r");
        System.out.println(result);
        System.out.println("\r");


    }
}