package com.example.patients.dto;
import lombok.Data;

import java.util.Date;


@Data
public class BaseDto {

    private Long id;
    private String CreatedBy;
    private Date CreatedAt;
    private String UpdatedBy;
    private Date UpdatedAt;
}
