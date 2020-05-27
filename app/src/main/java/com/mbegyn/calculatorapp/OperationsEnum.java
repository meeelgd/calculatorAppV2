package com.mbegyn.calculatorapp;

public enum OperationsEnum {
        add("+"),
        subtract("-"),
        multiply("*"),
        divide("/");

        public final String symbol;

        private OperationsEnum(String symbol) {
            this.symbol = symbol;
        }
    }