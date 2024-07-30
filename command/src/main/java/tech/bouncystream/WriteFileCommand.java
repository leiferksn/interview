package tech.bouncystream;

public class WriteFileCommand implements Command {

    private  FileSystemReceiver fileSystemReceiver;

    public WriteFileCommand(final FileSystemReceiver fsr) {
        this.fileSystemReceiver = fsr;
    }

    @Override
    public void execute() {
        this.fileSystemReceiver.writeFile();
    }
}
