package demo.concurrent.producerconsumer;

public interface Resource {
    void push();

    void pop();
}
