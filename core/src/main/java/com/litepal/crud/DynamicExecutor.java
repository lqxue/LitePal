package com.litepal.crud;import com.litepal.exceptions.LitePalSupportException;import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;/**
 * This provides a send method to allow calling method in dynamic way. (Just
 * like Ruby, but not clean or easy as Ruby to use).
 * 
 * @author Tony Green
 * @since 1.1
 */
class DynamicExecutor {/**
	 * Disable to create an instance of DynamicExecutor.
	 */
	private DynamicExecutor() {
	}/**
	 * This method use java reflect API to execute method dynamically. Most
	 * importantly, it could access the methods with private modifier to break
	 * encapsulation.
	 * 
	 * @param object
	 *            The object to invoke method.
	 * @param methodName
	 *            The method name to invoke.
	 * @param parameters
	 *            The parameters.
	 * @param objectClass
	 *            Use objectClass to find method to invoke.
	 * @param parameterTypes
	 *            The parameter types.
	 * @return Returns the result of dynamically invoking method.
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws java.lang.reflect.InvocationTargetException
	 */
	static Object send(Object object, String methodName, Object[] parameters, Class<?> objectClass,
			Class<?>[] parameterTypes) throws SecurityException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		try {
			if (parameters == null) {
				parameters = new Object[] {};
			}
			if (parameterTypes == null) {
				parameterTypes = new Class[] {};
			}
			Method method = objectClass.getDeclaredMethod(methodName, parameterTypes);
			method.setAccessible(true);
			return method.invoke(object, parameters);
		} catch (NoSuchMethodException e) {
			throw new LitePalSupportException(LitePalSupportException.noSuchMethodException(
					objectClass.getSimpleName(), methodName), e);
		}
	}static void set(Object object, String fieldName, Object value, Class<?> objectClass)
            throws SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        Field objectField = objectClass.getDeclaredField(fieldName);
        objectField.setAccessible(true);
        objectField.set(object, value);
    }/**
	 * This method use java reflect API to set field value dynamically. Most
	 * importantly, it could access fields with private modifier to break
	 * encapsulation.
	 * 
	 * @param object
	 *            The object to access.
	 * @param fieldName
	 *            The field name to access.
	 * @param value
	 *            Assign this value to field.
	 * @param objectClass
	 *            The class of object.
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	static void setField(Object object, String fieldName, Object value, Class<?> objectClass)
			throws SecurityException, IllegalArgumentException, IllegalAccessException {
        if (objectClass == LitePalSupport.class || objectClass == Object.class) {
            throw new LitePalSupportException(LitePalSupportException.noSuchFieldExceptioin(
                    objectClass.getSimpleName(), fieldName));
        }
		try {
			set(object, fieldName, value, objectClass);
		} catch (NoSuchFieldException e) {
			setField(object, fieldName, value, objectClass.getSuperclass());
		}
	}/**
	 * This method use java reflect API to get field value dynamically. Most
	 * importantly, it could access fields with private modifier to break
	 * encapsulation.
	 * 
	 * @param object
	 *            The object to access.
	 * @param fieldName
	 *            The field name to access.
	 * @param objectClass
	 *            The class of object.
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	static Object getField(Object object, String fieldName, Class<?> objectClass)
			throws IllegalArgumentException, IllegalAccessException {
        if (objectClass == LitePalSupport.class || objectClass == Object.class) {
            throw new LitePalSupportException(LitePalSupportException.noSuchFieldExceptioin(
                    objectClass.getSimpleName(), fieldName));
        }
		try {
			Field objectField = objectClass.getDeclaredField(fieldName);
			objectField.setAccessible(true);
			return objectField.get(object);
		} catch (NoSuchFieldException e) {
			return getField(object, fieldName, objectClass.getSuperclass());
		}
	}}
