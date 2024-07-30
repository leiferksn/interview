package tech.bouncystream;

public class FileInvoker {

    public Command command;

    public FileInvoker(final Command c) {
        this.command = c;
    }

    public void execute() {
        this.command.execute();
    }

}
