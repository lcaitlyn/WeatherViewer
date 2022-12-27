package edu.lcaitlyn.weatherviewer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sessions {
    private String id;
    private Long userId;
    private LocalDateTime expiresTime;
}
