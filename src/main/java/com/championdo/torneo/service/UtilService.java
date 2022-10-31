package com.championdo.torneo.service;


import com.championdo.torneo.entity.Util;

public interface UtilService {
	
	public abstract String findByClave(String clave);
	
	public abstract void add(Util util);
	
	public abstract void update(Util util);

	public abstract void delete(String clave);

}
