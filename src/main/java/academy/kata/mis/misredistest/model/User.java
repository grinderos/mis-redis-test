package academy.kata.mis.misredistest.model;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User implements Serializable {
    private Long id;
    private String name;
    private String lastName;
}
