package com.portfolio.util;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapperUtil {

    public static <R, T> List<T> streamNullableList(List<R> list, Function<R, T> function) {
        if (list == null) return Collections.emptyList();
        return list.stream().map(function).collect(Collectors.toList());
    }

}
