package tech.bouncystream;

public class LinuxFileSystemReceiver implements FileSystemReceiver {

    @Override
    public void openFile() {
        System.out.println("Open file in Linux");

    }

    @Override
    public void closeFile() {
        System.out.println("Close file in Linux");
    }

    @Override
    public void writeFile() {
        System.out.println("Write file in Linux");
    }
}
