import org.apache.commons.lang3.builder.EqualsBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Tester {

    public static boolean isTypeInterface(String typeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            return clazz.isInterface();
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isTypeAbstractClass(String typeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            return Modifier.isAbstract(clazz.getModifiers()) && !Modifier.isInterface(clazz.getModifiers());
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isTypeClass(String typeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            return  !Modifier.isAbstract(clazz.getModifiers()) && !Modifier.isInterface(clazz.getModifiers());
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isTypeEnum(String typeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            return clazz.isEnum();
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isTypePublic(String typeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            return Modifier.isPublic(clazz.getModifiers());
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isTypeProtected(String typeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            return Modifier.isProtected(clazz.getModifiers());
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isTypePrivate(String typeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            return Modifier.isPrivate(clazz.getModifiers());
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isTypePackagePrivate(String typeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            return  !Modifier.isPublic(clazz.getModifiers())
                    && !Modifier.isProtected(clazz.getModifiers())
                    && !Modifier.isPrivate(clazz.getModifiers());
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isTypeStatic(String typeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            return Modifier.isStatic(clazz.getModifiers());
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isTypeFinal(String typeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            return Modifier.isFinal(clazz.getModifiers());
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean extendsTypeClass(String parentName, String childName) {
        try {
            Class<?> parentClazz = Class.forName(parentName);
            Class<?> childClazz = Class.forName(childName);
            return parentClazz.isAssignableFrom(childClazz) && (isTypeClass(parentName) || isTypeAbstractClass(parentName));
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean implementsInterface(String parentName, String childName) {
        try {
            Class<?> parentClazz = Class.forName(parentName);
            Class<?> childClazz = Class.forName(childName);
            return parentClazz.isAssignableFrom(childClazz) && parentClazz.isInterface();
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean implementsGenericInterface(String parentName, String childName, String[] parametersName) {
        try {
            Class<?> parentClazz = Class.forName(parentName);
            Class<?> childClazz = Class.forName(childName);

            if (parentClazz.isAssignableFrom(childClazz) && parentClazz.isInterface()) {
                TypeVariable<? extends Class<?>>[] typeVariables = parentClazz.getTypeParameters();
                String[] typeVariablesName = new String[typeVariables.length];
                for (int i = 0; i < typeVariables.length; i++) {
                    typeVariablesName[i] = typeVariables[i].getName();
                }
                return Arrays.equals(typeVariablesName, parametersName);
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasTypeDeclaredField(String typeName, String fieldName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isFieldPublic(String typeName, String fieldName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field field = clazz.getDeclaredField(fieldName);
            return Modifier.isPublic(field.getModifiers());
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            return false;
        }
    }

    public static boolean isFieldProtected(String typeName, String fieldName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field field = clazz.getDeclaredField(fieldName);
            return Modifier.isProtected(field.getModifiers());
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            return false;
        }
    }

    public static boolean isFieldPrivate(String typeName, String fieldName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field field = clazz.getDeclaredField(fieldName);
            return Modifier.isPrivate(field.getModifiers());
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            return false;
        }
    }

    public static boolean isFieldPackagePrivate(String typeName, String fieldName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field field = clazz.getDeclaredField(fieldName);
            return  !Modifier.isPublic(field.getModifiers())
                    && !Modifier.isProtected(field.getModifiers())
                    && !Modifier.isPrivate(field.getModifiers());
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            return false;
        }
    }

    public static boolean isFieldStatic(String typeName, String fieldName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field field = clazz.getDeclaredField(fieldName);
            return Modifier.isStatic(field.getModifiers());
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            return false;
        }
    }

    public static boolean isFieldFinal(String typeName, String fieldName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field field = clazz.getDeclaredField(fieldName);
            return Modifier.isFinal(field.getModifiers());
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            return false;
        }
    }

    public static boolean inheritsTypeField(String parentType, String childType, String fieldName) {
        try {
            Class<?> parentClazz = Class.forName(parentType);
            Class<?> childClazz = Class.forName(childType);
            Field[] parentFields = parentClazz.getDeclaredFields();
            Field[] childFields = childClazz.getDeclaredFields();
            return isFieldInClass(fieldName, parentFields)
                    && !isFieldInClass(fieldName, childFields)
                    && !isFieldPrivate(parentType, fieldName);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasTypeDeclaredMethod(String typeName, String methodName, Class<?>[] parameterTypes) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                Class<?>[] types = method.getParameterTypes();
                if (methodName.equals(method.getName()) && Arrays.equals(types, parameterTypes)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasTypeDeclaredMethod(String typeName, String methodName, String[] parameterTypesName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                Type[] types = method.getGenericParameterTypes();
                String[] parameterTypes = new String[types.length];
                for (int i = 0; i < types.length; i++) {
                    String parameterTypeName = types[i].getTypeName();
                    if (parameterTypeName.contains("<")) {
                        parameterTypeName = parameterTypeName.substring(0, types[i].getTypeName().indexOf("<"));
                    }
                    String[] parts = parameterTypeName.split("\\.");
                    parameterTypes[i] = parts[parts.length - 1];
                }
                if (methodName.equals(method.getName()) && Arrays.equals(parameterTypes, parameterTypesName)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isMethodPublic(String typeName, String methodName, Class<?>[] parameterTypes) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            return Modifier.isPublic(method.getModifiers());
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean isMethodProtected(String typeName, String methodName, Class<?>[] parameterTypes) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            return Modifier.isProtected(method.getModifiers());
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            return false;
        }
    }

    public static boolean isMethodPrivate(String typeName, String methodName, Class<?>[] parameterTypes) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            return Modifier.isPrivate(method.getModifiers());
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            return false;
        }
    }

    public static boolean isMethodPackagePrivate(String typeName, String methodName, Class<?>[] parameterTypes) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            return  !Modifier.isPublic(method.getModifiers())
                    && !Modifier.isProtected(method.getModifiers())
                    && !Modifier.isPrivate(method.getModifiers());
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            return false;
        }
    }

    public static boolean isMethodStatic(String typeName, String methodName, Class<?>[] parameterTypes) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            return Modifier.isStatic(method.getModifiers());
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            return false;
        }
    }

    public static boolean isMethodFinal(String typeName, String methodName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method method = clazz.getDeclaredMethod(methodName);
            return Modifier.isFinal(method.getModifiers());
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            return false;
        }
    }

    public static boolean inheritsTypeMethod(String parentType, String childType, String methodName) {
        try {
            Class<?> parentClazz = Class.forName(parentType);
            Class<?> childClazz = Class.forName(childType);
            Method[] parentMethods = isMethodInClass(methodName, parentClazz.getDeclaredMethods());
            Method[] childMethods = isMethodInClass(methodName, childClazz.getDeclaredMethods());
            if (parentMethods.length == 0) {
                return false;
            }
            for (Method parentMethod : parentMethods) {
                for (Method childMethod : childMethods) {
                    if (equalParamTypes(parentMethod.getParameterTypes(), childMethod.getParameterTypes())
                            || Modifier.isPrivate(parentMethod.getModifiers())) {
                        return false;
                    }
                }
                if (Modifier.isPrivate(parentMethod.getModifiers())) {
                    return false;
                }
            }
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean overridesTypeMethod(String parentType, String childType, String methodName) {
        try {
            Class<?> parentClazz = Class.forName(parentType);
            Class<?> childClazz = Class.forName(childType);
            Method[] parentMethods = isMethodInClass(methodName, parentClazz.getDeclaredMethods());
            Method[] childMethods = isMethodInClass(methodName, childClazz.getDeclaredMethods());
            for (Method parentMethod : parentMethods) {
                for (Method childMethod : childMethods) {
                    if (equalParamTypes(parentMethod.getParameterTypes(), childMethod.getParameterTypes())
                            && !Modifier.isPrivate(parentMethod.getModifiers())) {
                        return true;
                    }
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasFieldType(String typeName, String fieldName, Class<?> fieldType) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName()) && field.getType().equals(fieldType)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasFieldType(String typeName, String fieldName, String fieldTypeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName()) && field.getType().getName().equals(fieldTypeName)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasFieldDefaultValue(String typeName, String fieldName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Object object = clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName())) {
                    if(field.getType() == boolean.class && Boolean.FALSE.equals(field.getBoolean(object))) {
                        return true;
                    } else if(field.getType() == char.class && field.getChar(object) == 0) {
                        return true;
                    } else if(field.getType().isPrimitive() && ((Number) field.get(object)).doubleValue() == 0) {
                        return true;
                    } else if(field.get(object) == null) {
                        return true;
                    }
                }
            }
            return false;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            return false;
        }
    }

    public static boolean hasFieldValue(String typeName, String fieldName, Object value) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Object object = clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName())) {
                    if(field.getType() == boolean.class && field.getBoolean(object) == (boolean) value) {
                        return true;
                    } else if(field.getType() == char.class && field.getChar(object) == (char) value) {
                        return true;
                    } else if(field.getType() == byte.class && field.getByte(object) == (byte) value) {
                        return true;
                    } else if(field.getType() == short.class && field.getShort(object) == (short) value) {
                        return true;
                    } else if(field.getType() == int.class && field.getInt(object) == (int) value) {
                        return true;
                    } else if(field.getType() == long.class && field.getLong(object) == (long) value) {
                        return true;
                    } else if(field.getType() == float.class && field.getFloat(object) == (float) value) {
                        return true;
                    } else if(field.getType() == double.class && field.getDouble(object) == (double) value) {
                        return true;
                    } else if(field.get(object).equals(value)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | ClassCastException | NoSuchMethodException | InvocationTargetException e) {
            return false;
        }
    }

    public static boolean hasMethodReturnType(String typeName, String methodName, Class<?> returnType) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName()) && method.getReturnType().equals(returnType)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasTypeDeclaredConstructor(String typeName, Class<?>[] parameterTypes) {
        return declaredConstructor(typeName, parameterTypes) != null;
    }

    public static boolean hasTypeDeclaredConstructor(String typeName, String[] parameterTypesName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                Type[] types = constructor.getGenericParameterTypes();
                String[] parameterTypes = new String[types.length];
                for (int i = 0; i < types.length; i++) {
                    String[] parts = types[i].getTypeName().split("\\.");
                    parameterTypes[i] = parts[parts.length - 1];
                }
                if (Arrays.equals(parameterTypes, parameterTypesName)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isConstructorPublic(String typeName, Class<?>[] parameterTypes) {
        Constructor<?> constructor = declaredConstructor(typeName, parameterTypes);
        if (constructor != null) {
            return Modifier.isPublic(constructor.getModifiers());
        }
        return false;
    }

    public static boolean isConstructorProtected(String typeName, Class<?>[] parameterTypes) {
        Constructor<?> constructor = declaredConstructor(typeName, parameterTypes);
        if (constructor != null) {
            return Modifier.isProtected(constructor.getModifiers());
        }
        return false;
    }

    public static boolean isConstructorPrivate(String typeName, Class<?>[] parameterTypes) {
        Constructor<?> constructor = declaredConstructor(typeName, parameterTypes);
        if (constructor != null) {
            return Modifier.isPrivate(constructor.getModifiers());
        }
        return false;
    }

    public static boolean isConstructorPrivate(String typeName, String[] parameterTypesName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                Type[] types = constructor.getGenericParameterTypes();
                String[] parameterTypes = new String[types.length];
                for (int i = 0; i < types.length; i++) {
                    String[] parts = types[i].getTypeName().split("\\.");
                    parameterTypes[i] = parts[parts.length - 1];
                }
                if (Arrays.equals(parameterTypes, parameterTypesName) && Modifier.isPrivate(constructor.getModifiers())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isConstructorPackagePrivate(String typeName, Class<?>[] parameterTypes) {
        Constructor<?> constructor = declaredConstructor(typeName, parameterTypes);
        if (constructor != null) {
            return !Modifier.isPublic(constructor.getModifiers())
                    && !Modifier.isProtected(constructor.getModifiers())
                    && !Modifier.isPrivate(constructor.getModifiers());
        }
        return false;
    }

    public static boolean isConstructorFinal(String typeName, Class<?>[] parameterTypes) {
        Constructor<?> constructor = declaredConstructor(typeName, parameterTypes);
        if (constructor != null) {
            return Modifier.isFinal(constructor.getModifiers());
        }
        return false;
    }

    public static boolean hasTypeNonStaticNestedType(String outerTypeName, String innerTypeName) {
        try {
            Class<?> outerClazz = Class.forName(outerTypeName);
            Class<?> innerClazz = Class.forName(outerTypeName + "$" + innerTypeName);
            if (outerClazz.getEnclosingClass() == null) {
                if (innerClazz.getEnclosingClass() != null) {
                    return outerClazz.equals(innerClazz.getEnclosingClass()) && !Modifier.isStatic(innerClazz.getModifiers());
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasTypeStaticNestedType(String outerTypeName, String innerTypeName) {
        try {
            Class<?> outerClazz = Class.forName(outerTypeName);
            Class<?> innerClazz = Class.forName(outerTypeName + "$" + innerTypeName);
            if (innerClazz.getEnclosingClass() != null) {
                return outerClazz.equals(innerClazz.getEnclosingClass()) && Modifier.isStatic(innerClazz.getModifiers());
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasTypeAnonymousClass(String typeName, int count) {
        int counter = 0;
        try {
            Class<?> outerClazz = Class.forName(typeName);
            for (int i = 1; i < 100; i++) {
                try {
                    Class<?> innerClazz = Class.forName(typeName + "$" + i);
                    if (innerClazz.getEnclosingClass() != null
                            && outerClazz.equals(innerClazz.getEnclosingClass())
                            && Modifier.isStatic(innerClazz.getModifiers())) {
                        counter++;
                    }
                } catch (ClassNotFoundException ignored) { }
            }
            return counter == count;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isTypeParameterized(String typeName, String[] parametersName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            TypeVariable<?>[] typeVariables = clazz.getTypeParameters();
            String[] typeVariablesName = new String[typeVariables.length];
            for (int i=0; i<typeVariables.length; i++) {
                typeVariablesName[i] = typeVariables[i].getName();
            }
            return Arrays.equals(typeVariablesName, parametersName);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isFieldTypeParameterized(String typeName, String fieldName, String genericTypeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName()) && field.getGenericType().getTypeName().equals(genericTypeName)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isConstructorParameterized(String typeName, String[] parametersName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                TypeVariable<? extends Constructor<?>>[] typeVariables = constructor.getTypeParameters();
                String[] typeVariablesName = new String[typeVariables.length];
                for (int i = 0; i < typeVariables.length; i++) {
                    typeVariablesName[i] = typeVariables[i].getName();
                }
                if (Arrays.equals(typeVariablesName, parametersName)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isMethodParameterized(String typeName, String methodName, String[] parametersName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName())) {
                    TypeVariable<Method>[] typeVariables = method.getTypeParameters();
                    String[] typeVariablesName = new String[typeVariables.length];
                    for (int i = 0; i < typeVariables.length; i++) {
                        typeVariablesName[i] = typeVariables[i].getName();
                    }
                    return Arrays.equals(typeVariablesName, parametersName);
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasParameterizedMethodTypeParameterBound(String typeName, String methodName, String parameterName, String boundName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName())) {
                    TypeVariable<Method>[] typeVariables = method.getTypeParameters();
                    for (TypeVariable<Method> typeVariable : typeVariables) {
                        if (parameterName.equals(typeVariable.getName())) {
                            Type[] bounds = typeVariable.getBounds();
                            for (Type bound : bounds) {
                                return boundName.equals(bound.getTypeName());
                            }
                        }
                    }
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasMethodParameterizedReturnType(String typeName, String methodName, String genericReturnTypeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName())
                        && method.getGenericReturnType().getTypeName().equals(genericReturnTypeName)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasTypeAnnotation(String typeName, Class<?> annotationType) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Annotation[] annotations = clazz.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotationType.equals(annotation.annotationType())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasAnnotationAttributeValue(String typeName, Class<? extends Annotation> annotationType, String attributeName, Class<?> valueType, Object value) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Annotation[] annotations = clazz.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotationType.equals(annotation.annotationType())) {
                    Object instance = clazz.getAnnotation(annotationType);
                    Class<? extends Annotation> annotationClazz = annotation.annotationType();
                    Method[] attributes = annotationClazz.getDeclaredMethods();
                    for (Method attribute : attributes) {
                        if (attribute.getName().equals(attributeName)) {
                            try {
                                Object result = attribute.invoke(instance);
                                return EqualsBuilder.reflectionEquals(result, value);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Constructor<?> declaredConstructor(String typeName, Class<?>[] parameterTypes) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                Class<?>[] types = constructor.getParameterTypes();
                if(Arrays.equals(types, parameterTypes)) {
                    return constructor;
                }
            }
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private static boolean isFieldInClass(String fieldName, Field[] parentFields) {
        for (Field field : parentFields) {
            if (fieldName.equals(field.getName())) {
                return true;
            }
        }
        return false;
    }

    private static Method[] isMethodInClass(String methodName, Method[] declaredMethods) {
        List<Method> methods = new LinkedList<>();
        for (Method method : declaredMethods) {
            if (methodName.equals(method.getName())) {
                methods.add(method);
            }
        }
        return methods.toArray(new Method[0]);
    }

    private static boolean equalParamTypes(Class<?>[] params1, Class<?>[] params2) {
        if (params1.length == params2.length) {
            for (int i = 0; i < params1.length; i++) {
                if (params1[i] != params2[i])
                    return false;
            }
            return true;
        }
        return false;
    }
}
