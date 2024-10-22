package hello.typeconverter.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTest {

    @Test
    void test() {
        String s = null + "";
        Assertions.assertThat(s).isEqualTo("null");
    }
}
