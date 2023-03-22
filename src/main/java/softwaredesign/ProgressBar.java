package softwaredesign;

public class ProgressBar {
    private boolean isProgressing;
    private String taskName;
    private int progress = 0;
    private static int progressBarWidth = 100;
    private static char progressBarChar;

    public void setProgress(float percentage) {
        if (!isProgressing) {
            return;
        }
        progress = (int) (percentage * progressBarWidth / 100);
        System.out.print("\r" + taskName + ": [");
        for (int i = 0; i < progressBarWidth; i++) {
            System.out.print(i < progress ?  : " ");
        }
        System.out.printf("] %d%%", (int) percentage);
        if (progress == progressBarWidth) {
            System.out.println();
        }
    }
}
