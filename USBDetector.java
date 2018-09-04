
import java.io.File;

/**
 *
 * @author Randula
 */
public class PenDrive {

    public PenDrive() {
    }

    public static void main(String[] RK) {
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        File[] drives = new File[letters.length];
        boolean[] isDrive = new boolean[letters.length];
        int totFiles = 0, totDirs = 0;

// init the file objects and the initial drive state
        for (int i = 0; i < letters.length; ++i) {
            drives[i] = new File(letters[i] + ":/");
            isDrive[i] = drives[i].canRead();
        }

        System.out.println("FindDrive: waiting for devices {} …");

        while (true) {

// check each drive
            for (int i = 0; i < letters.length; ++i) {
                boolean pluggedIn = drives[i].canRead();

// if the state has changed output a message
                if (pluggedIn != isDrive[i]) {

                    if (pluggedIn) {
                        String path = letters[i] + "://";
                        File files = new File(path);
                        String allFiles[] = files.list();

                        for (int k = 0; k < allFiles.length; k++) {
                            File f = new File(path + "/" + allFiles[k]);
                            if (f.isDirectory()) {
                                totDirs++;
                                System.out.println(
                                        "Directory:" + allFiles[k]
                                );
                            } else {
                                totFiles++;
                                System.out.println("File:" + allFiles[k]);
                            }
                        }
                        System.out.println("Total Directories and Files :" + allFiles.length);
                        System.out.println("Total Directories :" + allFiles.length);
                        System.out.println("Total Files :" + allFiles.length);
                    } else {
                        System.out.println("Drive " + letters[i] + " has been unplugged");
                    }

                    isDrive[i] = pluggedIn;

                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
