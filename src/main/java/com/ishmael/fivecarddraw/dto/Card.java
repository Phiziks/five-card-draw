package com.ishmael.fivecarddraw.dto;

import com.ishmael.fivecarddraw.enums.Suit;
import com.ishmael.fivecarddraw.enums.Value;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    Value value;
    Suit suit;
}
