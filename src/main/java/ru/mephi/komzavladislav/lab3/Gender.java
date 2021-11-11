package ru.mephi.komzavladislav.lab3;

public enum Gender {
    MALE(65)/*{
        public String toString(){
            return "M";
        }
    }*/,
    FEMALE(60)/*{
        public String toString(){
            return "F";
        }
    }*/;

    public int retirementAge;

    Gender(int retirementAge) {
        this.retirementAge = retirementAge;
    }

    //@Override
    //public abstract String toString();

}
