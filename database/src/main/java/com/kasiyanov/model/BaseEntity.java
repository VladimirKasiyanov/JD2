package com.kasiyanov.model;

import java.io.Serializable;

public interface BaseEntity <PK extends Serializable> {

    PK getId();
}
