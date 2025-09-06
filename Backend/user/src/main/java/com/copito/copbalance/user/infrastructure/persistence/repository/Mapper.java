package com.copito.copbalance.user.infrastructure.persistence.repository;

public interface Mapper<D, E> {
    E toEntity(D domain);
    D toDomain(E entity);
}