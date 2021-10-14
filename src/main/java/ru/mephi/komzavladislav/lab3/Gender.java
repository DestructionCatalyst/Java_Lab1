package ru.mephi.komzavladislav.lab3;

public enum Gender {
    MALE{
        public String toString(){
            return "M";
        }
        public String getEnding(){
            return "ку";
        }
    },
    FEMALE{
        public String toString(){
            return "Ж";
        }
        public String getEnding(){
            return "це";
        }
    };

    @Override
    public abstract String toString();

    public abstract String getEnding();
}
