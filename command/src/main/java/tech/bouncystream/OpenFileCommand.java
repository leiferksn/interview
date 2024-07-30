package tech.bouncystream;

public class OpenFileCommand implements Command {

    private  FileSystemReceiver fileSystemReceiver;

    public OpenFileCommand(final FileSystemReceiver fsr) {
        this.fileSystemReceiver = fsr;
    }

    @Override
    public void execute() {
        this.fileSystemReceiver.openFile();
    }
}
