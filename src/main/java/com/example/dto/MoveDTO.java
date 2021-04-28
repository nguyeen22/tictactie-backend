package com.example.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoveDTO {
    @NotNull
    private int boardColumn;
    @NotNull
    private int boardRow;
    @NotNull
    private Long playerID;
    @NotNull
    private Long gameID;
}
