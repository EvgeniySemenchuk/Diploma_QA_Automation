package api.pojos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Address {

    private Integer type_point;
    private Boolean is_site_active;
    private Integer office_id;
    private String locale;
    private String address;
    private Integer sm_id;
    private String name;
    private String way_info;
    private String rate;
    private String work_time;
    private Boolean overload;
    private Boolean courier_delivery;
    private String dest;
    private String sign;
    private Integer fitting_rooms;

}
