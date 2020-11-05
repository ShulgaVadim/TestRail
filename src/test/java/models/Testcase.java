
package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class Testcase {

    String title;
    String sections;
    String template;
    String type;
    String priority;
    String estimate;
    String references;
    String preconditions;
    String automationType;
    String steps;
    String expectedResult;
}