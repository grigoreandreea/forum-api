package com.unibuc.forumApi.config;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pagination<T> {
    private final List<T> items;
    private final Integer page;
    private final Integer size;
    private final String sort;

    public Pagination(List<T> items, Integer page, Integer size, String sort) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public Pagination(Optional<List<T>> items, Integer page, Integer size, String sort) {
        if (items.isEmpty()) {
            this.items = new ArrayList<>();
        } else {
            this.items = items.get();
        }
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public List<T> paginate(Comparator<T> comparator) {
        Stream<T> filteredItems = this.items.stream();

        if (sort != null) {
            if (sort.equals("asc")) {
                filteredItems = filteredItems.sorted(comparator);
            } else {
                filteredItems = filteredItems.sorted(comparator.reversed());
            }
        }

        if (size != null) {
            if (page != null) {
                filteredItems = filteredItems.skip(size * page);
            }

            filteredItems = filteredItems.limit(size);
        }

        return filteredItems.collect(Collectors.toList());
    }


}
