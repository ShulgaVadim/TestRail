
package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Milestone {

    String name;
    String references;
    String parent;
    String description;
    String startDate;
    String endDate;
}