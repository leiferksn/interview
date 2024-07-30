package tech.bouncystream;

public class CloseFileCommand implements Command {

    private  FileSystemReceiver fileSystemReceiver;

    public CloseFileCommand(final FileSystemReceiver fsr) {
        this.fileSystemReceiver = fsr;
    }

    @Override
    public void execute() {
        this.fileSystemReceiver.closeFile();
    }
}
