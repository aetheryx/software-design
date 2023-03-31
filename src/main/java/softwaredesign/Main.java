package softwaredesign;

import softwaredesign.RepositoryModule.Commit;
import softwaredesign.RepositoryModule.Repository;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main (String[] args){
        Application application = Application.getInstance();
        application.run();
//        testRepositoryModule();
    }
    /**
     * <p>
     *     something left over from the developement
     *
     * </p>
     * @auhtor Joachim*/
    private static void testRepositoryModule() {
        try {
            Repository repository = new Repository("https://github.com/Tysab/webtech-lab37-assign");
            repository.switchActiveBranch("joa");
            List<Commit> commits = repository.getCommits();
            for (Commit commit : commits) {
                System.out.println(commit.toString());
            }
            repository.delete();
        } catch (IOException e) {
            System.out.println("Io exception during test");
            return;
        } catch (InterruptedException e) {
            System.out.println("interupted exception during test");
            return;
        }
    }
}
