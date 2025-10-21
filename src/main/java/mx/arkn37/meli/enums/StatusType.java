package mx.arkn37.meli.enums;

import lombok.Getter;

@Getter
public enum StatusType {
    CREATED("CREATED"),
    SHIPPED("SHIPPED"),
    DELIVERED("DELIVERED"),
    CANCELED("CANCELED");

    private final String code;

    StatusType(String code) {
        this.code = code;
    }

}
