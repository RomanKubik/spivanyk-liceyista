package com.roman.kubik.songer.domain;

/**
 * Data mapper used to map database entities to model classes and vice versa.
 * Created by kubik on 1/14/18.
 */

public interface EntityModelMapper<E, M> {

    M fromEntity(E from);
    E toEntity(M from);
}

