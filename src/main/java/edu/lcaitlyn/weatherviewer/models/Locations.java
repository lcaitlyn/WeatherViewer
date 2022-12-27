package edu.lcaitlyn.weatherviewer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Locations {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Long userId;
    @NonNull
    private BigDecimal latitude;
    @NonNull
    private BigDecimal longitude;
}
