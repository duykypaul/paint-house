package com.duykypaul.painthouse.common;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author KyLC
 * @since 15/07/2022
 */
@Slf4j
public class MapperUtils {

    private MapperUtils() {
        throw new IllegalStateException("MapperUtils: Utility class");
    }

    /**
     * overwrite value from the fields of source object to target object.
     *
     * <p>Such as:
     * <p>target = { a: "entity", b: "entity", c: null d: null }
     * <p>source = { a: "dto", b: null, c: "dto" d: null }
     * <p>->Result:
     * <p>target = { a: "dto", b: "entity", c: "dto" d: null }
     *
     * @param source instance of Source class
     * @param target instance of Target class
     */
    public static void transformToObject(Object source, Object target) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        try {
            // obtain source class
            Class<?> sourceClass = source.getClass();
            // obtain target class
            Class<?> targetClass = target.getClass();
            List<Field> targetFields = getAllFieldsIncludeSuperClass(targetClass);
            List<Field> sourceFields = getAllFieldsIncludeSuperClass(sourceClass);
            //only in the get Method
            List<Method> sourceMethods = getAllMethodsIncludeSuperClass(sourceClass, "get");
            //only in the set Method
            List<Method> targetMethods = getAllMethodsIncludeSuperClass(targetClass, "set");

            for (Method targetMethod : targetMethods) {
                String targetFieldName = getFieldNameFromMethod(targetMethod);
                var ref = new Object() {
                    String targetFieldType = null;
                    String sourceFieldName = null;
                    String sourceFieldType = null;
                };
                targetFields.stream().filter(item -> item.getName().equals(targetFieldName)).findFirst()
                        .ifPresent(targetField -> {
                            ref.targetFieldType = getFieldType(targetField);
                            sourceFields.stream().filter(item -> item.getName().equals(targetField.getName())).findFirst()
                                    .ifPresent(sourceField -> {
                                        ref.sourceFieldType = getFieldType(sourceField);
                                        ref.sourceFieldName = sourceField.getName();
                                    });
                        });

                invokeTargetValue(source, target, sourceMethods, targetMethod, ref.targetFieldType, ref.sourceFieldName, ref.sourceFieldType);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * invoke value from source to target object
     *
     * @param source          instance of declaring class
     * @param target          instance of declaring class
     * @param sourceMethods   source methods
     * @param targetMethod    target methods
     * @param targetFieldType target field type
     * @param sourceFieldName source field name
     * @param sourceFieldType source field type
     */
    @SneakyThrows
    private static void invokeTargetValue(Object source, Object target, List<Method> sourceMethods,
                                          Method targetMethod, String targetFieldType, String sourceFieldName,
                                          String sourceFieldType) {
        if (sourceFieldName != null && !sourceFieldName.equals("null")) {
            for (Method sourceMethod : sourceMethods) {
                if (sourceMethod.getName().toUpperCase().equals("GET" + sourceFieldName.toUpperCase())) {
                    Object value = sourceMethod.invoke(source);
                    // obtain field Value adopt invoke() Assign a value to the corresponding property of the class to be transformed
                    if (value != null && targetFieldType.equals(sourceFieldType)) {
                        targetMethod.invoke(target, value);
                    }
                    break;
                }
            }
        }
    }

    private static String getFieldNameFromMethod(Method method) {
        String methodName = method.getName();
        return methodName.toLowerCase().charAt(3) + methodName.substring(4);
    }

    /**
     * obtain target Type of attribute ( Such as  private String field -> result  = String)
     *
     * @param field Class Field
     * @return Type of Field (ex: boolean , long , char , float , and double,...)
     */
    private static String getFieldType(Field field) {
        String genericType = field.getGenericType().toString();
        return genericType.substring(genericType.lastIndexOf(".") + 1);
    }

    /**
     * Returns a list of {@code Field} objects reflecting all the fields
     * declared by the class and supper class by this
     * {@code Class} object.
     *
     * @param clazz Classname of Object
     * @return the List of {@code Field} objects representing all the
     * declared fields of this class and super class
     */
    public static List<Field> getAllFieldsIncludeSuperClass(final Class<?> clazz) {
        List<Field> allFields = new ArrayList<>();
        for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            Field[] f = superClass.getDeclaredFields();
            Collections.addAll(allFields, f);
        }
        return allFields;
    }

    /**
     * Returns a list of {@code Method} objects reflecting all the methods
     * declared by the class and supper class by this
     * {@code Class} object.
     *
     * @param type  type of method ('set' or 'get' method)
     * @param clazz Classname of Object
     * @return the List of {@code Method} objects representing all the
     * declared fields of this class and super class
     */
    public static List<Method> getAllMethodsIncludeSuperClass(final Class<?> clazz, String type) {
        List<Method> allMethods = new ArrayList<>();
        for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            List<Method> methods = Arrays.stream(superClass.getDeclaredMethods()).filter(item -> item.getName().startsWith(type))
                    .collect(Collectors.toList());
            allMethods.addAll(methods);
        }
        return allMethods;
    }
}
