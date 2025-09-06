package com.copito.copbalance.security.infrastructure.persitence.repository;

public interface Mapper<D, E> {
    E toEntity(D domain);
    D toDomain(E entity);
}