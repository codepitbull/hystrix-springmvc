package de.codepitbull.hystrix.component;

import de.codepitbull.hystrix.CommandHelloWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;
import static rx.Observable.merge;

@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String showIndex() throws Exception{
        LOGGER.info("Entering showIndex");
        StringBuilder stringBuilder = new StringBuilder();
        merge(range(0, 10)
                .mapToObj(val -> new CommandHelloWorld("Bob").observe())
                .collect(Collectors.toList()))
                .forEach(s -> stringBuilder.append(s));
        LOGGER.info("Leaving showIndex");
        return stringBuilder.toString();
    }

}