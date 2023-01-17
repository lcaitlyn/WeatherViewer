package edu.lcaitlyn.weatherviewer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationTemperatureDto {
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Map<String, Double> main;
    private List<Map<String, Serializable>> weather;
    private Map<String, Serializable> sys;
    private String name;
}
