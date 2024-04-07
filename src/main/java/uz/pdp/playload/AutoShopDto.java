package uz.pdp.playload;

import lombok.Data;

import java.util.List;


@Data
public class AutoShopDto {

    private String    name;

    private Integer   gmId;

    private String    city;

    private String    country;

    private List<Integer> carIds;

}
