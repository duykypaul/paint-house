package com.duykypaul.painthouse.common;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *Update utility class (null fields ignored)
 */
public class UpdateUtil {

    /**
     *All properties with null values are not copied
     *
     * @param source
     * @param target
     */
    public static void copyNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullField(source));
    }

    /**
     *Get empty field in property
     *
     * @param target
     * @return
     */
    private static String[] getNullField(Object target) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(target);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        Set<String> notNullFieldSet = new HashSet<>();
        if (propertyDescriptors.length > 0) {
            notNullFieldSet = Arrays.stream(propertyDescriptors)
                    .map(PropertyDescriptor::getName)
                    .filter(name -> Objects.isNull(beanWrapper.getPropertyValue(name)))
                    .collect(Collectors.toSet());
        }
        String[] notNullField = new String[notNullFieldSet.size()];
        return notNullFieldSet.toArray(notNullField);
    }

}
