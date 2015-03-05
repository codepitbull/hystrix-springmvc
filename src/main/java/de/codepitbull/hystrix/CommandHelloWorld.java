package de.codepitbull.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("wuhu")));
        this.name = name;
    }

    @Override
    protected String run() {
        return "Hello " + name + "!";
    }

    @Override
    protected String getFallback() {
        return "FAIIIIIILLL";
    }
}