package ru.mephi.komzavladislav.lab3;

public enum Role {
    STAFF(0.1) {
        public String toString(){
            return "Staff";
        }
    },
    MANAGER(0.2) {
        public String toString() {
            return "Manager";
        }
    },
    EXECUTIVE(0.3) {
        public String toString() {
            return "Executive";
        }
    },;

    public double premiumRate;

    Role(double premiumRate) {
        this.premiumRate = premiumRate;
    }

    @Override
    public abstract String toString();
}
