package me.xiaoman.medicalassistant;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * 测试
 *
 * @author pacman
 * @version 1.0
 * date: 2018/4/13 11:24
 */

public class HelloTest {

    @Test
    public void testPath() throws IOException {
        String path = new ClassPathResource("file").getFile().getAbsolutePath();
        System.out.println(path);
    }
}
