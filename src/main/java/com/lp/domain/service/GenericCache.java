package com.lp.domain.service;


import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class GenericCache<T, K, R extends JpaRepository<T, Long>> {

    private final R repository;
    private final Function<T, K> keyMapper;
    private final Map<K, T> cache = new ConcurrentHashMap<>();

    public GenericCache(R repository, Function<T, K> keyMapper) {
        this.repository = repository;
        this.keyMapper = keyMapper;
    }

    @PostConstruct
    public void init() {
        repository.findAll().forEach(item -> cache.put(keyMapper.apply(item), item));
    }

    public Optional<T> get(K key) {
        return Optional.ofNullable(cache.get(key));
    }

    public void add(T item) {
        cache.put(keyMapper.apply(item), item);
    }

    public void refresh() {
        cache.clear();
        init();
    }

    public Collection<T> getAll() {
        return List.copyOf(cache.values());
    }
}