package Useful;

public enum EnTrace {
    DEBUG(0),INFO(1),ERROR(2);
    private int attr; 
    EnTrace(int attr) {
        this.attr = attr;
    }
}

