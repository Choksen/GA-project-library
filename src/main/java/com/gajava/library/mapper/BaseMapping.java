package com.gajava.library.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Stream;

@MapperConfig
public interface BaseMapping<SOURCE, TARGET> {

    /**
     * Отображение атрибутов с тем же именем, форма сбора
     */
    @InheritConfiguration(name = "sourceToTarget")
    Set<TARGET> sourceToTarget(Set<SOURCE> var1);

    /**
     * Реверс, сопоставление атрибутов с тем же именем, форма сбора
     */
/*    @InheritConfiguration(name = "targetToSource")
    Set<SOURCE> targetToSource(Set<TARGET> var1);*/

    /**
     * Отображение атрибутов с тем же именем, в виде потока сбора
     */
    Set<TARGET> sourceToTarget(Stream<SOURCE> stream);

/*    *//**
     * Реверс, сопоставление атрибутов с тем же именем, форма потока сбора
     *//*
    Set<SOURCE> targetToSource(Stream<TARGET> stream);*/
}
