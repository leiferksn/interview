package tech.bouncystream;

interface Anon {
    String getWhoAmI();
}

public class Anonymous {

    public static void main(final String[] args) {

        Anon anon = new Anon() {
            private String whoAmI = "Who am I?!";

            public String getWhoAmI() {
                return this.whoAmI;
            }
        };

        System.out.println(anon.getWhoAmI());
    }

}