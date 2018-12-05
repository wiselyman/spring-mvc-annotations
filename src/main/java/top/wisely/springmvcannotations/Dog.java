package top.wisely.springmvcannotations;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dog  {
    private Long id;
    private String name;
}
