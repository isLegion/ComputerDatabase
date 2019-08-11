package data;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class Computer {

    private String name;
    private String introducedDate;
    private String discontinuedDate;
    private String company;
}
