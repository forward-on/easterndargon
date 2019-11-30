package com.ly.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ConvertParam {

	/**
	 * 
	 * 通过反射, 将一个对象的部分属性映射给另一个类型的对象 只有属性名一致，类型一致的属性才会映射
	 * 
	 * @param o
	 *            提供属性值的对象
	 * @param oclass
	 *            目标类型
	 * @return 目标类型的对象
	 */
	public static void convert(Object o, Object target) {
		String property_name = null;
		Class<?> property_type = null;
		try {
			for (Field f : target.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				property_name = f.getName();
				property_type = f.getType();
				if ("serialVersionUID".equals(property_name)) {
					continue;
				}
				for (Field ff : o.getClass().getDeclaredFields()) {
					ff.setAccessible(true);
					if (property_name.toUpperCase().equals(ff.getName().toUpperCase())) {
						if (property_type.equals(ff.getType())) {
							if (ff.get(o) == null) {
								continue;
							}
							if (property_type.equals(List.class)) {
								List list = (ArrayList) ff.get(o);
								List outlist = new ArrayList();
								ParameterizedType aType = (ParameterizedType) f.getGenericType();
								Type[] fieldArgTypes = aType.getActualTypeArguments();
								for (int i = 0; i < list.size(); i++) {
									// Object ob =Activator.CreateInstance;
									Object ob = ((Class) fieldArgTypes[0]).newInstance();
									convert(list.get(i), ob);
									outlist.add(ob);
								}
								f.set(target, outlist);
							} else {
								f.set(target, ff.get(o));
							}
						}
					}
					ff.setAccessible(false);
				}
				if (!o.getClass().getSuperclass().equals(Object.class)) {
					for (Field ff : o.getClass().getSuperclass().getDeclaredFields()) {
						ff.setAccessible(true);
						if (property_name.equals(ff.getName())) {
							if (property_type.equals(ff.getType())) {
								if (ff.get(o) == null) {
									continue;
								}
								if (property_type.equals(List.class)) {
									List list = (ArrayList) ff.get(o);
									List outlist = new ArrayList();
									ParameterizedType aType = (ParameterizedType) f.getGenericType();
									Type[] fieldArgTypes = aType.getActualTypeArguments();
									for (int i = 0; i < list.size(); i++) {
										// Object ob =Activator.CreateInstance;
										Object ob = ((Class) fieldArgTypes[0]).newInstance();
										convert(list.get(i), ob);
										outlist.add(ob);
									}
									f.set(target, outlist);
								} else {
									f.set(target, ff.get(o));
								}
							}
						}
						ff.setAccessible(false);
					}
				}
				f.setAccessible(false);
			}
			if (!target.getClass().getSuperclass().equals(Object.class)) {
				for (Field f : target.getClass().getSuperclass().getDeclaredFields()) {
					f.setAccessible(true);
					property_name = f.getName();
					property_type = f.getType();
					if ("serialVersionUID".equals(property_name)) {
						continue;
					}
					for (Field ff : o.getClass().getDeclaredFields()) {
						ff.setAccessible(true);
						if (property_name.equals(ff.getName())) {
							if (property_type.equals(ff.getType())) {
								if (ff.get(o) == null) {
									continue;
								}
								if (property_type.equals(List.class)) {
									List list = (ArrayList) ff.get(o);
									List outlist = new ArrayList();
									ParameterizedType aType = (ParameterizedType) f.getGenericType();
									Type[] fieldArgTypes = aType.getActualTypeArguments();
									for (int i = 0; i < list.size(); i++) {
										// Object ob =Activator.CreateInstance;
										Object ob = ((Class) fieldArgTypes[0]).newInstance();
										convert(list.get(i), ob);
										outlist.add(ob);
									}
									f.set(target, outlist);
								} else {
									f.set(target, ff.get(o));
								}
							}
						}
						ff.setAccessible(false);
					}
					if (!o.getClass().getSuperclass().equals(Object.class)) {
						for (Field ff : o.getClass().getSuperclass().getDeclaredFields()) {
							ff.setAccessible(true);
							if (property_name.equals(ff.getName())) {
								if (property_type.equals(ff.getType())) {
									if (ff.get(o) == null) {
										continue;
									}
									if (property_type.equals(List.class)) {
										List list = (ArrayList) ff.get(o);
										List outlist = new ArrayList();
										ParameterizedType aType = (ParameterizedType) f.getGenericType();
										Type[] fieldArgTypes = aType.getActualTypeArguments();
										for (int i = 0; i < list.size(); i++) {
											// Object ob =Activator.CreateInstance;
											Object ob = ((Class) fieldArgTypes[0]).newInstance();
											convert(list.get(i), ob);
											outlist.add(ob);
										}
										f.set(target, outlist);
									} else {
										f.set(target, ff.get(o));
									}
								}
							}
							ff.setAccessible(false);
						}
					}
					
					f.setAccessible(false);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.print("类型转换错误------" + property_name + "(" + property_type + ")转换失败");
		}
	}
}
