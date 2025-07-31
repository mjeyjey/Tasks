
interface Animal {
    boolean feed(boolean timeToEat);
    void groom();
    void pet();
}

class gorilla implements Animal {

    public boolean feed(boolean timeToEat) {
        // put gorilla food into cage
        if (timeToEat) {
            System.out.println("gorilla is eating");
            return true;
        } else {
            System.out.println("gorilla is not eating"); 
            return false;
         }
    }
    public void groom() {
        //lather, rinse, repeat
        System.out.println("The gorilla is being groomed");
    }
    public void pet() {
        // pet at your own risk
        System.out.println("The gorilla is being Petted");
    }
}