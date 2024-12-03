package hello.typeconverter.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//@EqualsAndHashCode
@AllArgsConstructor
public class IpPort {

    private String ip;
    private int port;
}
