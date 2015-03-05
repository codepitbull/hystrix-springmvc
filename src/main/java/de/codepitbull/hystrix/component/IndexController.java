package de.codepitbull.hystrix.component;

import de.codepitbull.hystrix.CommandHelloWorld;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;
import static rx.Observable.merge;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String showIndex() throws Exception{
        StringBuilder stringBuilder = new StringBuilder();
        merge(range(0, 10)
                .mapToObj(val -> new CommandHelloWorld("Bob").observe())
                .collect(Collectors.toList()))
                .forEach(s -> stringBuilder.append(s));

        return stringBuilder.toString();
    }

}