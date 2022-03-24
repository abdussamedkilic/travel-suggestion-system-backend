package com.example.traveladvisorbackend.dto.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BaseMapper {
    protected static final ModelMapper MODEL_MAPPER = new ModelMapper();

    static {
        MODEL_MAPPER.getConfiguration().setAmbiguityIgnored(true);
    }

    protected BaseMapper() {
    }

    public static <T> T map(Object obj, Class<T> cls) {
        return MODEL_MAPPER.map(obj, cls);
    }

    public static <T> List<T> mapAll(Collection<?> sourceList, Class<T> cls) {
        List<T> mappedList = new ArrayList<>(sourceList.size());
        for (Object obj : sourceList) {
            mappedList.add(map(obj, cls));
        }
        return mappedList;
    }
}
