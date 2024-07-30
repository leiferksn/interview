package tech.bouncystream;

public class DispenseOf10s implements Dispenser {

    private Dispenser nextDispenser;

    @Override
    public void nextDispenser(Dispenser d) {
        this.nextDispenser = d;
    }

    @Override
    public void dispense(Currency c) {
        if (c.amount() >= 10) {
            int num = c.amount() / 10;
            int remainder = c.amount() % 10;
            System.out.println("Dispensing " + num + " 10s note");
            if (remainder != 0) {
                this.nextDispenser.dispense(new Currency(remainder));

            }
        } else {
            this.nextDispenser.dispense(c);
        }
    }
}
