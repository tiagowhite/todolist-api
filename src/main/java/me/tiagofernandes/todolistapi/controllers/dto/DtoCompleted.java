package me.tiagofernandes.todolistapi.controllers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCompleted {

    private String id;
    private  Boolean completed;
}
