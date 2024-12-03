package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConverterTest {

    @Test
    void stringToInteger() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");
        assertThat(10).isEqualTo(result);
    }

    @Test
    void integerToString() {
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(10);
        assertThat("10").isEqualTo(result);
    }

    @Test
    void stringToIpPort() {
        StringToIpPortConverter converter = new StringToIpPortConverter();
        IpPort ipPort = converter.convert("127.0.0.1:8080");
        String ip = ipPort.getIp();
        int port = ipPort.getPort();
        assertThat(ip).isEqualTo("127.0.0.1");
        assertThat(port).isEqualTo(8080);
    }

    @Test
    void ipPortToString() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        String convert = converter.convert(ipPort);
        assertThat(convert).isEqualTo("127.0.0.1:8080");
    }

}