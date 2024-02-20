package com.ishmael.fivecarddraw.enums;

public enum Suit {
    CLUBS{

        @Override
        public String toString() {
            return "\u2663";
        }
    }, DIAMONDS{
        @Override
        public String toString() {
            return "\u2666";
        }
    }, HEARTS{
        @Override
        public String toString() {
            return "\u2665";
        }
    }, SPADES{
        @Override
        public String toString() {
            return "\u2660";
        }
    }
}
