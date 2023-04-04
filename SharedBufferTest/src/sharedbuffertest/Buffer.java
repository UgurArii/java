package sharedbuffertest;

public interface Buffer {
    public void set(int value) throws InterruptedException;
    
    public int get() throws InterruptedException;
    
}
