package org.example.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDo {
    long toDoId;
    String m_id;
    String toDo;
    int status;
}
