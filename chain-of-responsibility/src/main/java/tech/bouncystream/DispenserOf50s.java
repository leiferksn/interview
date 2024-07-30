package tech.bouncystream;

public class DispenserOf50s implements Dispenser {

    private Dispenser nextDispenser;

    @Override
    public void nextDispenser(Dispenser d) {
        this.nextDispenser = d;
    }

    @Override
    public void dispense(Currency c) {
        if (c.amount() >= 50) {
            int num = c.amount() / 50;
            int remainder = c.amount() % 50;
            System.out.println("Dispensing " + num + " 50s note");
            if (remainder != 0) {
                this.nextDispenser.dispense(new Currency(remainder));
            }
        } else {
            this.nextDispenser.dispense(c);
        }
    }


}
