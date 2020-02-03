package co.com.test.dao;

public interface IPruebaSQL {

	// get
	public static String getAsesores = "{CALL PKG_PRUEBA.PRGT_ASESORES(?,?,?,?,?,?,?,?,?,?,?)}";	
	public static String getClientes = "{CALL PKG_PRUEBA.PRGT_CLIENTES(?,?,?,?,?,?)}";	
	public static String getConsumos = "{CALL PKG_PRUEBA.PRGT_CONSUMOS(?,?,?,?)}";	
	public static String getTarjetas = "{CALL PKG_PRUEBA.PRGT_TARJETAS(?,?,?,?,?,?)}";	
	
	// crud
	public static String crudAsesor = "{CALL PKG_PRUEBA.PRCT_ASESOR(?,?,?,?,?,?,?,?,?,?,?)}";
	public static String crudCliente = "{CALL PKG_PRUEBA.PRCT_CLIENTE(?,?,?,?,?,?)}";
	public static String crudConsumo = "{CALL PKG_PRUEBA.PRCT_CONSUMO(?,?,?,?,?,?,?,?)}";
	public static String crudTarjeta = "{CALL PKG_PRUEBA.PRCT_TARJETA(?,?,?,?,?,?)}";
		
}
