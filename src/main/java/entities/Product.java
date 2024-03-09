package entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Product {
    private String productName;
    private String productBrand;
    private String productSeller;
    private String productSize;

}
