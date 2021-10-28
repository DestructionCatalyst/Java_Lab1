package ru.mephi.komzavladislav.lab3;

public enum Gender {
    MALE{
        public String toString(){
            return "M";
        }
    },
    FEMALE{
        public String toString(){
            return "F";
        }
    };

    @Override
    public abstract String toString();

}
