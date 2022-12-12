package org.example.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDo {
    long toDoId;
    String m_name;
    String toDo;
    boolean status;

}
