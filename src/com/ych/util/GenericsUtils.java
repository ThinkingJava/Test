<<<<<<< HEAD
package com.ych.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
/**
 * 
 * @ClassName: GenericsUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yangchenghuan
 * @date 2016年9月19日 下午4:20:29
 */
public class GenericsUtils {
	/**
	 * 获取泛型的类型
	 * @param clazz
	 * @return Class
	 */
	@SuppressWarnings("unchecked")
	public static Class getGenericType(Class clazz){

		Type genType = clazz.getGenericSuperclass();//得到泛型父类  
		Type[] types = ((ParameterizedType) genType).getActualTypeArguments();
		if (!(types[0] instanceof Class)) {
            return Object.class;   
        } 
		return (Class) types[0];
		
//		ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass(); 
//		Class entityClass = (Class) type.getActualTypeArguments()[0]; 
//		return entityClass; 
	}
	/**
	 * 获取对象的类名称
	 * @param clazz
	 * @return 类名称
	 */
	@SuppressWarnings("unchecked")
	public static String getGenericName(Class clazz){
		return clazz.getSimpleName();
	}
}
=======
package com.ych.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
/**
 * 
 * @ClassName: GenericsUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yangchenghuan
 * @date 2016年9月19日 下午4:20:29
 */
public class GenericsUtils {
	/**
	 * 获取泛型的类型
	 * @param clazz
	 * @return Class
	 */
	@SuppressWarnings("unchecked")
	public static Class getGenericType(Class clazz){

		Type genType = clazz.getGenericSuperclass();//得到泛型父类  
		Type[] types = ((ParameterizedType) genType).getActualTypeArguments();
		if (!(types[0] instanceof Class)) {
            return Object.class;   
        } 
		return (Class) types[0];
		
//		ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass(); 
//		Class entityClass = (Class) type.getActualTypeArguments()[0]; 
//		return entityClass; 
	}
	/**
	 * 获取对象的类名称
	 * @param clazz
	 * @return 类名称
	 */
	@SuppressWarnings("unchecked")
	public static String getGenericName(Class clazz){
		return clazz.getSimpleName();
	}
}
>>>>>>> eb63eded9899513af9538a187ddf701ead10b135
