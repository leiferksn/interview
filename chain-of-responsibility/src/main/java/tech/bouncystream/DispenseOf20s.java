package tech.bouncystream;

public class DispenseOf20s implements Dispenser {

    private Dispenser nextDispenser;

    @Override
    public void nextDispenser(Dispenser d) {
        this.nextDispenser = d;
    }

    @Override
    public void dispense(Currency c) {
        if (c.amount() >= 20) {
            int num = c.amount() / 20;
            int remainder = c.amount() % 20;
            System.out.println("Dispensing " + num + " 20s note");
            if (remainder != 0) {
                this.nextDispenser.dispense(new Currency(remainder));
            }
        } else {
            this.nextDispenser.dispense(c);
        }
    }
}
