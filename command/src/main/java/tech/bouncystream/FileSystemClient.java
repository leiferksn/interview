package tech.bouncystream;

public class FileSystemClient {

    public static void main(String[] args) {

        final var fsReceiver = FileSystemReceiverUtil.underlyingFileSystem();

        final var openFileCommand = new OpenFileCommand(fsReceiver);

        final var fileInvoker = new FileInvoker(openFileCommand);
        fileInvoker.execute();

        final var writeFileCommand = new WriteFileCommand(fsReceiver);
        final var writeFileInvoker = new FileInvoker(writeFileCommand);
        writeFileInvoker.execute();

        final var closeFileCommand = new CloseFileCommand(fsReceiver);
        final var closeFileInvoker = new FileInvoker(closeFileCommand);
        closeFileInvoker.execute();



    }

}
