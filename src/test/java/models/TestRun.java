package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TestRun {

    String name;
    String references;
    String milestone;
    String assignTo;
    String description;
}

