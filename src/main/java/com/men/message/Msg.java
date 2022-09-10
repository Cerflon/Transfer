package com.men.message;

import lombok.*;

import java.io.Serializable;

@Data
public class Msg implements Serializable {

    private static final long serialVersionUID = 1L;

    private int versionProtocol;
    int a;
    int b;
    int result;

    public Msg() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Msg;
    }

}
