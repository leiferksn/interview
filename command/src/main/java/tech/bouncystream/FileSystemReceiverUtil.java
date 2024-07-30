package tech.bouncystream;

public class FileSystemReceiverUtil {

    public static FileSystemReceiver underlyingFileSystem() {
        final var systemOS = System.getProperty("os.name");
        if (systemOS.contains("Windows")) {
            return new WindowsFileSystemReceiver();
        } else {
            return new LinuxFileSystemReceiver();
        }
    }
}
