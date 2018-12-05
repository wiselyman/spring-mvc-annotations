package top.wisely.springmvcannotations;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private Long id;
    private String name;
}
