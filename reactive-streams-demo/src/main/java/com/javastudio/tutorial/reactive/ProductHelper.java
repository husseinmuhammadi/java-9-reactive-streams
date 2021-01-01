package com.javastudio.tutorial.reactive;

import com.javastudio.tutorial.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductHelper {
    private static Logger logger = LoggerFactory.getLogger(ProductHelper.class);

    private long count;

    public List<Product> products() {
        return Arrays.asList(
                Product.builder().id(10L).name("Alex").build(),
                Product.builder().id(11L).name("Sandy").build(),
                Product.builder().id(12L).name("Sue").build(),
                Product.builder().id(13L).name("Bobby").build(),
                Product.builder().id(14L).name("James").build(),
                Product.builder().id(15L).name("William").build(),
                Product.builder().id(16L).name("Mason").build()
        );
    }

    public void process(String fileName, Consumer<Product> consumer) {
        URL resource = getClass().getClassLoader().getResource(fileName);
        try (Stream<String> stream = Files.lines(Paths.get(resource.toURI()))) {
            stream.map(name->Product.builder().id(count++).name(name).build()).forEach(p -> consumer.accept(p));
            logger.info("All people published!");
        } catch (IOException | URISyntaxException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public long getCount() {
        return count;
    }
}
