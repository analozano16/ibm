package co.com.test.ejb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.Resource.AuthenticationType;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;
import co.com.test.dao.IPruebaDAO;
import co.com.test.dao.IPruebaSQL;
import co.com.test.model.Asesor;
import co.com.test.model.Cliente;
import co.com.test.model.Consumo;
import co.com.test.model.Tarjeta;
import co.com.test.model.TipoEspecialidad;
import co.com.test.model.TipoTarjeta;

@Stateless
@Local(IPruebaDAO.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PruebaEJB implements IPruebaDAO {
	
	@Resource(mappedName = "jdbc/prueba", name = "jdbc/prueba", 
			  authenticationType = AuthenticationType.CONTAINER,
			  type = DataSource.class, shareable = true)
	private DataSource dataSource;

	// get
	
	@Override
	public List <Asesor> getAsesores(Asesor asesor) throws Exception {
	 Connection connection = null;
	 ResultSet resultSet = null;
	 CallableStatement callableStatement = null;
	 List <Asesor> asesores = new ArrayList<Asesor>();
	 try {
	     //Se establece la conexi�n
	     connection = dataSource.getConnection();
	     callableStatement = connection.prepareCall(IPruebaSQL.getAsesores);
	     
	     if (asesor.getCodigo() != 0) {
	         callableStatement.setLong("P_ID", (asesor.getCodigo()));
	     } else {
	         callableStatement.setNull("P_ID", OracleTypes.NUMBER);
	     }
	     //Se registra el cursor de salida
	     callableStatement.registerOutParameter("V_CURSOR", OracleTypes.CURSOR);
	     callableStatement.execute();
	     resultSet = (ResultSet)callableStatement.getObject("V_CURSOR");
	     while(resultSet.next()){
	         asesor = new Asesor();
	         
	         asesor.setCodigo(resultSet.getLong("ID"));
	         asesor.setNombre(resultSet.getString("DS_NOMBRE"));
	         asesor.setApellido(resultSet.getString("DS_APELLIDO"));
	         asesor.setTipoEspecialidad(new TipoEspecialidad());
	         asesor.getTipoEspecialidad().setCodigo(resultSet.getLong("TE_ID"));
	         asesor.getTipoEspecialidad().setNombre((resultSet.getString("TE_DS_NOMBRE")));
	         asesor.getTipoEspecialidad().setDescripcion(resultSet.getString("TE_DS_DESCRIPCION"));
	         asesores.add(asesor);
	     }
	 } catch (Exception e) {
	     throw new Exception(e.getMessage());
	 } finally {
         try {
             if (resultSet != null)
                 resultSet.close();
         } catch (Exception e) {
         }
         try {
             if (callableStatement != null)
                 callableStatement.close();
         } catch (Exception e) {
         }     try {
             if (connection != null)
                 connection.close();
         } catch (Exception e) {
         }
     }
	 return asesores;
	}
	
	
	@Override
	public List <Cliente> getClientes(Cliente cliente) throws Exception {
	 Connection connection = null;
	 ResultSet resultSet = null;
	 CallableStatement callableStatement = null;
	 List <Cliente> clientes = new ArrayList<Cliente>();
	 try {
	     //Se establece la conexi�n
	     connection = dataSource.getConnection();
	     callableStatement = connection.prepareCall(IPruebaSQL.getClientes);
	     
	     if (cliente.getCodigo() != 0) {
	         callableStatement.setLong("P_ID", (cliente.getCodigo()));
	     } else {
	         callableStatement.setNull("P_ID", OracleTypes.NUMBER);
	     }
	     //Se registra el cursor de salida
	     callableStatement.registerOutParameter("V_CURSOR", OracleTypes.CURSOR);
	     callableStatement.execute();
	     resultSet = (ResultSet)callableStatement.getObject("V_CURSOR");
	     while(resultSet.next()){
	         cliente = new Cliente();
	         
	         cliente.setCodigo(resultSet.getLong("ID"));
	         cliente.setNombre(resultSet.getString("DS_NOMBRE"));
	         cliente.setApellido(resultSet.getString("DS_APELLIDO"));
	         cliente.setIdentificacion(resultSet.getString("DS_IDENTIFICACION"));
	         cliente.setDireccion(resultSet.getString("DS_DIRECCION"));
	         cliente.setCiudad(resultSet.getString("DS_CIUDAD"));
	         cliente.setTelefono(resultSet.getString("DS_TELEFONO"));
	         cliente.setAsesor(new Asesor());
	         cliente.getAsesor().setCodigo(resultSet.getLong("A_ID"));
	         cliente.getAsesor().setNombre(resultSet.getString("A_DS_NOMBRE"));
	         cliente.getAsesor().setApellido(resultSet.getString("A_DS_APELLIDO"));
	         clientes.add(cliente);
	         
	     }
	 } catch (Exception e) {
	     throw new Exception(e.getMessage());
	 } finally {
         try {
             if (resultSet != null)
                 resultSet.close();
         } catch (Exception e) {
         }
         try {
             if (callableStatement != null)
                 callableStatement.close();
         } catch (Exception e) {
         }     try {
             if (connection != null)
                 connection.close();
         } catch (Exception e) {
         }
     }
	 return clientes;
	}

	@Override
	public List <Consumo> getConsumos(Consumo consumo) throws Exception {
	 Connection connection = null;
	 ResultSet resultSet = null;
	 CallableStatement callableStatement = null;
	 List <Consumo> consumos = new ArrayList<Consumo>();
	 try {
	     //Se establece la conexi�n
	     connection = dataSource.getConnection();
	     callableStatement = connection.prepareCall(IPruebaSQL.getConsumos);
	     
	     if (consumo.getCodigo() != 0) {
	         callableStatement.setLong("P_ID", (consumo.getCodigo()));
	     } else {
	         callableStatement.setNull("P_ID", OracleTypes.NUMBER);
	     }
	     //Se registra el cursor de salida
	     callableStatement.registerOutParameter("V_CURSOR", OracleTypes.CURSOR);
	     callableStatement.execute();
	     resultSet = (ResultSet)callableStatement.getObject("V_CURSOR");
	     while(resultSet.next()){
	         consumo = new Consumo();
	         
	         consumo.setCodigo(resultSet.getLong("ID"));
	         consumo.setFecha(resultSet.getDate("FE_REGISTRO"));
	         consumo.setDescripcion(resultSet.getString("DS_DESCRIPCION"));
	         consumo.setMonto(resultSet.getDouble("NM_MONTO"));
	         consumo.setTarjeta(new Tarjeta());
	         consumo.getTarjeta().setCodigo(resultSet.getLong("T_ID"));
	         consumo.getTarjeta().setCCV(resultSet.getString("T_CCV"));
	         consumo.getTarjeta().setNumeroTarjeta(resultSet.getString("T_NUMERO_TARJETA"));
	         consumos.add(consumo);
	     }
	 } catch (Exception e) {
	     throw new Exception(e.getMessage());
	 } finally {
         try {
             if (resultSet != null)
                 resultSet.close();
         } catch (Exception e) {
         }
         try {
             if (callableStatement != null)
                 callableStatement.close();
         } catch (Exception e) {
         }     try {
             if (connection != null)
                 connection.close();
         } catch (Exception e) {
         }
     }
	 return consumos;
	}
	
	
	@Override
	public List <Tarjeta> getTarjetas(Tarjeta tarjeta) throws Exception {
	 Connection connection = null;
	 ResultSet resultSet = null;
	 CallableStatement callableStatement = null;
	 List <Tarjeta> tarjetas = new ArrayList<Tarjeta>();
	 try {
	     //Se establece la conexi�n
	     connection = dataSource.getConnection();
	     callableStatement = connection.prepareCall(IPruebaSQL.getTarjetas);
	     
	     if (tarjeta.getCodigo() != 0) {
	         callableStatement.setLong("P_ID", (tarjeta.getCodigo()));
	     } else {
	         callableStatement.setNull("P_ID", OracleTypes.NUMBER);
	     }
	     //Se registra el cursor de salida
	     callableStatement.registerOutParameter("V_CURSOR", OracleTypes.CURSOR);
	     callableStatement.execute();
	     resultSet = (ResultSet)callableStatement.getObject("V_CURSOR");
	     while(resultSet.next()){
	         tarjeta = new Tarjeta();
	         
	         tarjeta.setCodigo(resultSet.getLong("ID"));
	         tarjeta.setCCV(resultSet.getString("T_CCV"));
	         tarjeta.setNumeroTarjeta(resultSet.getString("T_NUMERO_TARJETA"));
	         tarjeta.setTipoTarjeta(new TipoTarjeta());
	         tarjeta.getTipoTarjeta().setCodigo(resultSet.getLong("TT_ID"));
	         tarjeta.getTipoTarjeta().setNombre(resultSet.getString("TT_NOMBRE"));
	         tarjeta.getTipoTarjeta().setDescripcion(resultSet.getString("TT_DESCRIPCION"));
	         tarjetas.add(tarjeta);
	     }
	 } catch (Exception e) {
	     throw new Exception(e.getMessage());
	 } finally {
         try {
             if (resultSet != null)
                 resultSet.close();
         } catch (Exception e) {
         }
         try {
             if (callableStatement != null)
                 callableStatement.close();
         } catch (Exception e) {
         }     try {
             if (connection != null)
                 connection.close();
         } catch (Exception e) {
         }
     }
	 return tarjetas;
	}
	
	// insert

	@Override
	public Asesor insertAsesor(Asesor asesor) throws Exception{
	   Connection connection = null;
	   CallableStatement callableStatement = null;
	   try {
	       // se establece la la conexion
	       connection = dataSource.getConnection();
	       callableStatement = connection.prepareCall(IPruebaSQL.crudAsesor);
	       //se establece el parametro operacion
	       callableStatement.setString("P_OPERACION", "INSERT");
	       
	       if (asesor.getCodigo() != 0) {
	         callableStatement.setLong("P_ID", (asesor.getCodigo()));
	       } else {
	         callableStatement.setNull("P_ID", OracleTypes.NUMBER);
	       }
	       if (asesor.getNombre() == null) {
	         callableStatement.setNull("P_DS_NOMBRE", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_NOMBRE", (asesor.getNombre()));
	       }
	       if (asesor.getApellido() == null) {
	         callableStatement.setNull("P_DS_APELLIDO", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_APELLIDO", (asesor.getApellido()));
	       }
	       if (asesor.getTipoEspecialidad() == null) {
	         callableStatement.setNull("P_TIPO_ESPECIALIDAD", OracleTypes.NUMBER);
	       } else {
	         callableStatement.setLong("P_TIPO_ESPECIALIDAD", (asesor.getTipoEspecialidad().getCodigo()));
	       }
	       // Se registra el ID de salida
	       callableStatement.registerOutParameter("P_ID", OracleTypes.NUMBER);
	       // Se registra el mensaje de salida
	       callableStatement.registerOutParameter("MESSAGE",OracleTypes.VARCHAR);
	       // se registra el numero de registros afectados
	       callableStatement.registerOutParameter("ROWCOUNT",OracleTypes.NUMERIC);
	       //ejecuta el insert y devuelve cuantos registros se impactaron
	       callableStatement.execute();
	       //se extrae el codigo
	       asesor.setCodigo(callableStatement.getLong("P_ID"));
	       if (callableStatement.getObject("ROWCOUNT") == null) {
	           throw new Exception(callableStatement.getString("MESSAGE") + " insert");
	       }
	   } catch (Exception e) {
	       if(e instanceof Exception)
	           throw (Exception) e;
	       throw new Exception(e.getMessage());
	   } finally {
	       try {
	           if (callableStatement != null)
	               callableStatement.close();
	       } catch (Exception e) {
	       }       try {
	           if (connection != null)
	               connection.close();
	       } catch (Exception e) {
	       }
	   }
	   return asesor;
	}
	
	
	@Override
	public Cliente insertCliente(Cliente cliente) throws Exception{
	   Connection connection = null;
	   CallableStatement callableStatement = null;
	   try {
	       // se establece la la conexion
	       connection = dataSource.getConnection();
	       callableStatement = connection.prepareCall(IPruebaSQL.crudCliente);
	       //se establece el parametro operacion
	       callableStatement.setString("P_OPERACION", "INSERT");
	       
	       if (cliente.getCodigo() != 0) {
	         callableStatement.setLong("P_ID", (cliente.getCodigo()));
	       } else {
	         callableStatement.setNull("P_ID", OracleTypes.NUMBER);
	       }
	       if (cliente.getNombre() == null) {
	         callableStatement.setNull("P_DS_NOMBRE", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_NOMBRE", (cliente.getNombre()));
	       }
	       if (cliente.getApellido() == null) {
	         callableStatement.setNull("P_DS_APELLIDO", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_APELLIDO", (cliente.getApellido()));
	       }
	       if (cliente.getIdentificacion() == null) {
	         callableStatement.setNull("P_DS_IDENTIFICACION", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_IDENTIFICACION", (cliente.getIdentificacion()));
	       }
	       if (cliente.getDireccion() == null) {
	         callableStatement.setNull("P_DS_DIRECCION", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_DIRECCION", (cliente.getDireccion()));
	       }	
	       if (cliente.getCiudad() == null) {
	         callableStatement.setNull("P_DS_CIUDAD", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_CIUDAD", (cliente.getCiudad()));
	       }
	       if (cliente.getTelefono() == null) {
	         callableStatement.setNull("P_DS_TELEFONO", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_TELEFONO", (cliente.getTelefono()));
	       }
	       if (cliente.getAsesor() == null) {
	         callableStatement.setNull("P_CD_ASESOR", OracleTypes.NUMBER);
	       } else {
	         callableStatement.setLong("P_CD_ASESOR", (cliente.getAsesor().getCodigo()));
	       }
	       // Se registra el ID de salida
	       callableStatement.registerOutParameter("P_ID", OracleTypes.NUMBER);
	       // Se registra el mensaje de salida
	       callableStatement.registerOutParameter("MESSAGE",OracleTypes.VARCHAR);
	       // se registra el numero de registros afectados
	       callableStatement.registerOutParameter("ROWCOUNT",OracleTypes.NUMERIC);
	       //ejecuta el insert y devuelve cuantos registros se impactaron
	       callableStatement.execute();
	       //se extrae el codigo
	       cliente.setCodigo(callableStatement.getLong("P_ID"));
	       if (callableStatement.getObject("ROWCOUNT") == null) {
	           throw new Exception(callableStatement.getString("MESSAGE") + " insert");
	       }
	   } catch (Exception e) {
	       if(e instanceof Exception)
	           throw (Exception) e;
	       throw new Exception(e.getMessage());
	   } finally {
	       try {
	           if (callableStatement != null)
	               callableStatement.close();
	       } catch (Exception e) {
	       }       try {
	           if (connection != null)
	               connection.close();
	       } catch (Exception e) {
	       }
	   }
	   return cliente;
	}
	

	
	@Override
	public Consumo insertConsumo(Consumo consumo) throws Exception{
	   Connection connection = null;
	   CallableStatement callableStatement = null;
	   try {
	       // se establece la la conexion
	       connection = dataSource.getConnection();
	       callableStatement = connection.prepareCall(IPruebaSQL.crudConsumo);
	       //se establece el parametro operacion
	       callableStatement.setString("P_OPERACION", "INSERT");
	       
	       if (consumo.getCodigo() != 0) {
	         callableStatement.setLong("P_ID", (consumo.getCodigo()));
	       } else {
	         callableStatement.setNull("P_ID", OracleTypes.NUMBER);
	       }
	       if (consumo.getFecha() != null) {
	         callableStatement.setDate("P_FE_REGISTTRO", (consumo.getFecha()));
	       } else {
	         callableStatement.setNull("P_FE_REGISTTRO", OracleTypes.DATE);
	       }
	       if (consumo.getDescripcion() == null) {
	         callableStatement.setNull("P_DS_DESCRIPCION", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_DESCRIPCION", (consumo.getDescripcion()));
	       }
	       if (consumo.getMonto() == null) {
	         callableStatement.setNull("P_NM_MONTO", OracleTypes.NUMBER);
	       } else {
	         callableStatement.setDouble("P_NM_MONTO", (consumo.getMonto()));
	       }
	       if (consumo.getTarjeta() == null) {
	         callableStatement.setNull("P_CD_TARJETA", OracleTypes.NUMBER);
	       } else {
	         callableStatement.setDouble("P_CD_TARJETA", (consumo.getTarjeta().getCodigo()));
	       }
	       // Se registra el ID de salida
	       callableStatement.registerOutParameter("P_ID", OracleTypes.NUMBER);
	       // Se registra el mensaje de salida
	       callableStatement.registerOutParameter("MESSAGE",OracleTypes.VARCHAR);
	       // se registra el numero de registros afectados
	       callableStatement.registerOutParameter("ROWCOUNT",OracleTypes.NUMERIC);
	       //ejecuta el insert y devuelve cuantos registros se impactaron
	       callableStatement.execute();
	       //se extrae el codigo
	       consumo.setCodigo(callableStatement.getLong("P_ID"));
	       if (callableStatement.getObject("ROWCOUNT") == null) {
	           throw new Exception(callableStatement.getString("MESSAGE") + " insert");
	       }
	   } catch (Exception e) {
	       if(e instanceof Exception)
	           throw (Exception) e;
	       throw new Exception(e.getMessage());
	   } finally {
	       try {
	           if (callableStatement != null)
	               callableStatement.close();
	       } catch (Exception e) {
	       }       try {
	           if (connection != null)
	               connection.close();
	       } catch (Exception e) {
	       }
	   }
	   return consumo;
	}
	
	
	@Override
	public Tarjeta insertTarjeta(Tarjeta tarjeta) throws Exception{
	   Connection connection = null;
	   CallableStatement callableStatement = null;
	   try {
	       // se establece la la conexion
	       connection = dataSource.getConnection();
	       callableStatement = connection.prepareCall(IPruebaSQL.crudTarjeta);
	       //se establece el parametro operacion
	       callableStatement.setString("P_OPERACION", "INSERT");
	       
	       if (tarjeta.getCodigo() != 0) {
	         callableStatement.setLong("P_ID", (tarjeta.getCodigo()));
	       } else {
	         callableStatement.setNull("P_ID", OracleTypes.NUMBER);
	       }
	       if (tarjeta.getNumeroTarjeta() == null) {
	         callableStatement.setNull("P_NUMERO_TARJETA", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_NUMERO_TARJETA", (tarjeta.getNumeroTarjeta()));
	       }
	       if (tarjeta.getCCV() == null) {
	         callableStatement.setNull("P_CCV", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_CCV", (tarjeta.getCCV()));
	       }
	       if (tarjeta.getTipoTarjeta() == null) {
	         callableStatement.setNull("P_TIPO_TARJETA", OracleTypes.NUMBER);
	       } else {
	         callableStatement.setLong("P_TIPO_TARJETA", (tarjeta.getTipoTarjeta().getCodigo()));
	       }
	       if (tarjeta.getCliente() == null) {
	         callableStatement.setNull("P_CLIENTE", OracleTypes.NUMBER);
	       } else {
	         callableStatement.setLong("P_CLIENTE", (tarjeta.getCliente().getCodigo()));
	       }
	       // Se registra el ID de salida
	       callableStatement.registerOutParameter("P_ID", OracleTypes.NUMBER);
	       // Se registra el mensaje de salida
	       callableStatement.registerOutParameter("MESSAGE",OracleTypes.VARCHAR);
	       // se registra el numero de registros afectados
	       callableStatement.registerOutParameter("ROWCOUNT",OracleTypes.NUMERIC);
	       //ejecuta el insert y devuelve cuantos registros se impactaron
	       callableStatement.execute();
	       //se extrae el codigo
	       tarjeta.setCodigo(callableStatement.getLong("P_ID"));
	       if (callableStatement.getObject("ROWCOUNT") == null) {
	           throw new Exception(callableStatement.getString("MESSAGE") + " insert");
	       }
	   } catch (Exception e) {
	       if(e instanceof Exception)
	           throw (Exception) e;
	       throw new Exception(e.getMessage());
	   } finally {
	       try {
	           if (callableStatement != null)
	               callableStatement.close();
	       } catch (Exception e) {
	       }       try {
	           if (connection != null)
	               connection.close();
	       } catch (Exception e) {
	       }
	   }
	   return tarjeta;
	}
	
	@Override
	public boolean deleteAsesor(Asesor asesor) throws Exception{
	   Connection connection = null;
	   CallableStatement callableStatement = null;
	   boolean delete=false;
	   try {
	       //se establece la la conexion
	       connection = dataSource.getConnection();
	       callableStatement = connection.prepareCall(IPruebaSQL.crudAsesor);
	       //se establece el parametro operacion
	       callableStatement.setString("P_OPERACION", "DELETE");
	       
	       callableStatement.setObject("P_ID", (asesor.getCodigo() != 0) ? asesor.getCodigo():null);	     
	       callableStatement.setString("P_DS_NOMBRE", null);     
           callableStatement.setString("P_DS_APELLIDO", null);       
           callableStatement.setObject("P_TIPO_ESPECIALIDAD", null);     

	       // Se registra el ID de salida
	       callableStatement.registerOutParameter("P_ID", OracleTypes.NUMBER);
	       // Se registra el mensaje de salida
	       callableStatement.registerOutParameter("MESSAGE",OracleTypes.VARCHAR);
	       // se registra el numero de registros afectados
	       callableStatement.registerOutParameter("ROWCOUNT",OracleTypes.NUMERIC);
	       //ejecuta el delete y devuelve cuantos registros se impactaron
	       callableStatement.execute();
	       if (callableStatement.getInt("ROWCOUNT") > 0) {
	           delete=true;
	       } else {
	           throw new Exception(callableStatement.getString("MESSAGE") + " delete");
	       }
	   } catch (Exception e) {
	       if(e instanceof Exception)
	           throw (Exception)e;
	       throw new Exception(e.getMessage());
	   } finally {
	       try {
	           if (callableStatement != null)
	               callableStatement.close();
	       } catch (Exception e) {
	       }
	       try {
	           if (connection != null)
	               connection.close();
	       } catch (Exception e) {
	       }
	   }
	   return delete;
	}
	


	@Override
	public boolean deleteCliente(Cliente cliente) throws Exception{
	   Connection connection = null;
	   CallableStatement callableStatement = null;
	   boolean delete=false;
	   try {
	       //se establece la la conexion
	       connection = dataSource.getConnection();
	       callableStatement = connection.prepareCall(IPruebaSQL.crudCliente);
	       //se establece el parametro operacion
	       callableStatement.setString("P_OPERACION", "DELETE");
	       
	       callableStatement.setObject("P_ID", (cliente.getCodigo() != 0) ? cliente.getCodigo():null);     
	       callableStatement.setString("P_DS_NOMBRE", null);	      
	       callableStatement.setString("P_DS_APELLIDO", null);	      
	       callableStatement.setString("P_DS_IDENTIFICACION", null);	      
	       callableStatement.setString("P_DS_DIRECCION", null);	      
	       callableStatement.setString("P_DS_CIUDAD", null);	      
	       callableStatement.setString("P_DS_TELEFONO", null);	      
	       callableStatement.setObject("P_CD_ASESOR", null);   
	       
	       // Se registra el ID de salida
	       callableStatement.registerOutParameter("P_ID", OracleTypes.NUMBER);
	       // Se registra el mensaje de salida
	       callableStatement.registerOutParameter("MESSAGE",OracleTypes.VARCHAR);
	       // se registra el numero de registros afectados
	       callableStatement.registerOutParameter("ROWCOUNT",OracleTypes.NUMERIC);
	       //ejecuta el delete y devuelve cuantos registros se impactaron
	       callableStatement.execute();
	       if (callableStatement.getInt("ROWCOUNT") > 0) {
	           delete=true;
	       } else {
	           throw new Exception(callableStatement.getString("MESSAGE") + " delete");
	       }
	   } catch (Exception e) {
	       if(e instanceof Exception)
	           throw (Exception)e;
	       throw new Exception(e.getMessage());
	   } finally {
	       try {
	           if (callableStatement != null)
	               callableStatement.close();
	       } catch (Exception e) {
	       }
	       try {
	           if (connection != null)
	               connection.close();
	       } catch (Exception e) {
	       }
	   }
	   return delete;
	}
	

	@Override
	public boolean deleteConsumo(Consumo consumo) throws Exception{
	   Connection connection = null;
	   CallableStatement callableStatement = null;
	   boolean delete=false;
	   try {
	       //se establece la la conexion
	       connection = dataSource.getConnection();
	       callableStatement = connection.prepareCall(IPruebaSQL.crudConsumo);
	       //se establece el parametro operacion
	       callableStatement.setString("P_OPERACION", "DELETE");
	       
	       callableStatement.setObject("P_ID", (consumo.getCodigo() != 0) ? consumo.getCodigo():null);	      
	       callableStatement.setNull("P_FE_REGISTTRO", OracleTypes.DATE);       
	       callableStatement.setString("P_DS_DESCRIPCION", null); 
	       callableStatement.setObject("P_NM_MONTO", null);      
	       callableStatement.setObject("P_CD_TARJETA", null);
		    
	       callableStatement.registerOutParameter("P_ID", OracleTypes.NUMBER);
	       callableStatement.registerOutParameter("MESSAGE",OracleTypes.VARCHAR);
	       callableStatement.registerOutParameter("ROWCOUNT",OracleTypes.NUMERIC);
	       callableStatement.execute();
	       if (callableStatement.getInt("ROWCOUNT") > 0) {
	           delete=true;
	       } else {
	           throw new Exception(callableStatement.getString("MESSAGE") + " delete");
	       }
	   } catch (Exception e) {
	       if(e instanceof Exception)
	           throw (Exception)e;
	       throw new Exception(e.getMessage());
	   } finally {
	       try {
	           if (callableStatement != null)
	               callableStatement.close();
	       } catch (Exception e) {
	       }
	       try {
	           if (connection != null)
	               connection.close();
	       } catch (Exception e) {
	       }
	   }
	   return delete;
	}
	
	


	@Override
	public boolean deleteTarjeta(Tarjeta tarjeta) throws Exception{
	   Connection connection = null;
	   CallableStatement callableStatement = null;
	   boolean delete=false;
	   try {
	       //se establece la la conexion
	       connection = dataSource.getConnection();
	       callableStatement = connection.prepareCall(IPruebaSQL.crudTarjeta);
	       //se establece el parametro operacion
	       callableStatement.setString("P_OPERACION", "DELETE");
	       
	       callableStatement.setObject("P_ID", (tarjeta.getCodigo() != 0) ? tarjeta.getCodigo():null);	      
	       callableStatement.setString("P_NUMERO_TARJETA", null);      
	       callableStatement.setString("P_CCV", null);      
	       callableStatement.setObject("P_TIPO_TARJETA", null);      
	       callableStatement.setObject("P_CLIENTE", null);
	       
	       // Se registra el ID de salida
	       callableStatement.registerOutParameter("P_ID", OracleTypes.NUMBER);
	       // Se registra el mensaje de salida
	       callableStatement.registerOutParameter("MESSAGE",OracleTypes.VARCHAR);
	       // se registra el numero de registros afectados
	       callableStatement.registerOutParameter("ROWCOUNT",OracleTypes.NUMERIC);
	       //ejecuta el delete y devuelve cuantos registros se impactaron
	       callableStatement.execute();
	       if (callableStatement.getInt("ROWCOUNT") > 0) {
	           delete=true;
	       } else {
	           throw new Exception(callableStatement.getString("MESSAGE") + " delete");
	       }
	   } catch (Exception e) {
	       if(e instanceof Exception)
	           throw (Exception)e;
	       throw new Exception(e.getMessage());
	   } finally {
	       try {
	           if (callableStatement != null)
	               callableStatement.close();
	       } catch (Exception e) {
	       }
	       try {
	           if (connection != null)
	               connection.close();
	       } catch (Exception e) {
	       }
	   }
	   return delete;
	}
	
	// update
	
	@Override
	public Asesor updateAsesor(Asesor asesor) throws Exception{
	   Connection connection = null;
	   CallableStatement callableStatement = null;
	   try {
	       // se establece la la conexion
	       connection = dataSource.getConnection();
	       callableStatement = connection.prepareCall(IPruebaSQL.crudAsesor);
	       //se establece el parametro operacion
	       callableStatement.setString("P_OPERACION", "UPDATE");
	       
	       if (asesor.getCodigo() != 0) {
	         callableStatement.setLong("P_ID", (asesor.getCodigo()));
	       } else {
	         callableStatement.setNull("P_ID", OracleTypes.NUMBER);
	       }
	       if (asesor.getNombre() == null) {
	         callableStatement.setNull("P_DS_NOMBRE", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_NOMBRE", (asesor.getNombre()));
	       }
	       if (asesor.getApellido() == null) {
	         callableStatement.setNull("P_DS_APELLIDO", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_APELLIDO", (asesor.getApellido()));
	       }
	       if (asesor.getTipoEspecialidad() == null) {
	         callableStatement.setNull("P_TIPO_ESPECIALIDAD", OracleTypes.NUMBER);
	       } else {
	         callableStatement.setLong("P_TIPO_ESPECIALIDAD", (asesor.getTipoEspecialidad().getCodigo()));
	       }
	       // Se registra el ID de salida
	       callableStatement.registerOutParameter("P_ID", OracleTypes.NUMBER);
	       // Se registra el mensaje de salida
	       callableStatement.registerOutParameter("MESSAGE",OracleTypes.VARCHAR);
	       // se registra el numero de registros afectados
	       callableStatement.registerOutParameter("ROWCOUNT",OracleTypes.NUMERIC);
	       //ejecuta el insert y devuelve cuantos registros se impactaron
	       callableStatement.execute();
	       
	       if (callableStatement.getObject("ROWCOUNT") == null) {
	           throw new Exception(callableStatement.getString("MESSAGE") + " update");
	       }
	   } catch (Exception e) {
	       if(e instanceof Exception)
	           throw (Exception) e;
	       throw new Exception(e.getMessage());
	   } finally {
	       try {
	           if (callableStatement != null)
	               callableStatement.close();
	       } catch (Exception e) {
	       }       try {
	           if (connection != null)
	               connection.close();
	       } catch (Exception e) {
	       }
	   }
	   return asesor;
	}

	@Override
	public Cliente updateCliente(Cliente cliente) throws Exception{
	   Connection connection = null;
	   CallableStatement callableStatement = null;
	   try {
	       // se establece la la conexion
	       connection = dataSource.getConnection();
	       callableStatement = connection.prepareCall(IPruebaSQL.crudCliente);
	       //se establece el parametro operacion
	       callableStatement.setString("P_OPERACION", "UPDATE");
	       
	       
	       if (cliente.getCodigo() != 0) {
	         callableStatement.setLong("P_ID", (cliente.getCodigo()));
	       } else {
	         callableStatement.setNull("P_ID", OracleTypes.NUMBER);
	       }
	       if (cliente.getNombre() == null) {
	         callableStatement.setNull("P_DS_NOMBRE", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_NOMBRE", (cliente.getNombre()));
	       }
	       if (cliente.getApellido() == null) {
	         callableStatement.setNull("P_DS_APELLIDO", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_APELLIDO", (cliente.getApellido()));
	       }
	       if (cliente.getIdentificacion() == null) {
	         callableStatement.setNull("P_DS_IDENTIFICACION", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_IDENTIFICACION", (cliente.getIdentificacion()));
	       }
	       if (cliente.getDireccion() == null) {
	         callableStatement.setNull("P_DS_DIRECCION", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_DIRECCION", (cliente.getDireccion()));
	       }	
	       if (cliente.getCiudad() == null) {
	         callableStatement.setNull("P_DS_CIUDAD", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_CIUDAD", (cliente.getCiudad()));
	       }
	       if (cliente.getTelefono() == null) {
	         callableStatement.setNull("P_DS_TELEFONO", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_TELEFONO", (cliente.getTelefono()));
	       }
	       if (cliente.getAsesor() == null) {
	         callableStatement.setNull("P_CD_ASESOR", OracleTypes.NUMBER);
	       } else {
	         callableStatement.setLong("P_CD_ASESOR", (cliente.getAsesor().getCodigo()));
	       }
	       // Se registra el ID de salida
	       callableStatement.registerOutParameter("P_ID", OracleTypes.NUMBER);
	       // Se registra el mensaje de salida
	       callableStatement.registerOutParameter("MESSAGE",OracleTypes.VARCHAR);
	       // se registra el numero de registros afectados
	       callableStatement.registerOutParameter("ROWCOUNT",OracleTypes.NUMERIC);
	       //ejecuta el insert y devuelve cuantos registros se impactaron
	       callableStatement.execute();
	       
	       if (callableStatement.getObject("ROWCOUNT") == null) {
	           throw new Exception(callableStatement.getString("MESSAGE") + " update");
	       }
	   } catch (Exception e) {
	       if(e instanceof Exception)
	           throw (Exception) e;
	       throw new Exception(e.getMessage());
	   } finally {
	       try {
	           if (callableStatement != null)
	               callableStatement.close();
	       } catch (Exception e) {
	       }       try {
	           if (connection != null)
	               connection.close();
	       } catch (Exception e) {
	       }
	   }
	   return cliente;
	}
	
	@Override
	public Consumo updateConsumo(Consumo consumo) throws Exception{
	   Connection connection = null;
	   CallableStatement callableStatement = null;
	   try {
	       // se establece la la conexion
	       connection = dataSource.getConnection();
	       callableStatement = connection.prepareCall(IPruebaSQL.crudCliente);
	       //se establece el parametro operacion
	       callableStatement.setString("P_OPERACION", "UPDATE");
	       
	       
	       if (consumo.getCodigo() != 0) {
	         callableStatement.setLong("P_ID", (consumo.getCodigo()));
	       } else {
	         callableStatement.setNull("P_ID", OracleTypes.NUMBER);
	       }
	       if (consumo.getFecha() != null) {
	         callableStatement.setDate("P_FE_REGISTTRO", (consumo.getFecha()));
	       } else {
	         callableStatement.setNull("P_FE_REGISTTRO", OracleTypes.DATE);
	       }
	       if (consumo.getDescripcion() == null) {
	         callableStatement.setNull("P_DS_DESCRIPCION", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_DS_DESCRIPCION", (consumo.getDescripcion()));
	       }
	       if (consumo.getMonto() == null) {
	         callableStatement.setNull("P_NM_MONTO", OracleTypes.NUMBER);
	       } else {
	         callableStatement.setDouble("P_NM_MONTO", (consumo.getMonto()));
	       }
	       if (consumo.getTarjeta() == null) {
	         callableStatement.setNull("P_CD_TARJETA", OracleTypes.NUMBER);
	       } else {
	         callableStatement.setDouble("P_CD_TARJETA", (consumo.getTarjeta().getCodigo()));
	       }
	     
	       // Se registra el ID de salida
	       callableStatement.registerOutParameter("P_ID", OracleTypes.NUMBER);
	       // Se registra el mensaje de salida
	       callableStatement.registerOutParameter("MESSAGE",OracleTypes.VARCHAR);
	       // se registra el numero de registros afectados
	       callableStatement.registerOutParameter("ROWCOUNT",OracleTypes.NUMERIC);
	       //ejecuta el insert y devuelve cuantos registros se impactaron
	       callableStatement.execute();
	       
	       if (callableStatement.getObject("ROWCOUNT") == null) {
	           throw new Exception(callableStatement.getString("MESSAGE") + " update");
	       }
	   } catch (Exception e) {
	       if(e instanceof Exception)
	           throw (Exception) e;
	       throw new Exception(e.getMessage());
	   } finally {
	       try {
	           if (callableStatement != null)
	               callableStatement.close();
	       } catch (Exception e) {
	       }       try {
	           if (connection != null)
	               connection.close();
	       } catch (Exception e) {
	       }
	   }
	   return consumo;
	}
	

	@Override
	public Tarjeta updateTarjeta(Tarjeta tarjeta) throws Exception{
	   Connection connection = null;
	   CallableStatement callableStatement = null;
	   try {
	       // se establece la la conexion
	       connection = dataSource.getConnection();
	       callableStatement = connection.prepareCall(IPruebaSQL.crudTarjeta);
	       //se establece el parametro operacion
	       callableStatement.setString("P_OPERACION", "UPDATE");
	       if (tarjeta.getCodigo() != 0) {
	         callableStatement.setLong("P_ID", (tarjeta.getCodigo()));
	       } else {
	         callableStatement.setNull("P_ID", OracleTypes.NUMBER);
	       }
	       if (tarjeta.getNumeroTarjeta() == null) {
	         callableStatement.setNull("P_NUMERO_TARJETA", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_NUMERO_TARJETA", (tarjeta.getNumeroTarjeta()));
	       }
	       if (tarjeta.getCCV() == null) {
	         callableStatement.setNull("P_CCV", OracleTypes.NVARCHAR);
	       } else {
	         callableStatement.setString("P_CCV", (tarjeta.getCCV()));
	       }
	       if (tarjeta.getTipoTarjeta() == null) {
	         callableStatement.setNull("P_TIPO_TARJETA", OracleTypes.NUMBER);
	       } else {
	         callableStatement.setLong("P_TIPO_TARJETA", (tarjeta.getTipoTarjeta().getCodigo()));
	       }
	       if (tarjeta.getCliente() == null) {
	         callableStatement.setNull("P_CLIENTE", OracleTypes.NUMBER);
	       } else {
	         callableStatement.setLong("P_CLIENTE", (tarjeta.getCliente().getCodigo()));
	       }
	     
	       // Se registra el ID de salida
	       callableStatement.registerOutParameter("P_ID", OracleTypes.NUMBER);
	       // Se registra el mensaje de salida
	       callableStatement.registerOutParameter("MESSAGE",OracleTypes.VARCHAR);
	       // se registra el numero de registros afectados
	       callableStatement.registerOutParameter("ROWCOUNT",OracleTypes.NUMERIC);
	       //ejecuta el insert y devuelve cuantos registros se impactaron
	       callableStatement.execute();
	       
	       if (callableStatement.getObject("ROWCOUNT") == null) {
	           throw new Exception(callableStatement.getString("MESSAGE") + " update");
	       }
	   } catch (Exception e) {
	       if(e instanceof Exception)
	           throw (Exception) e;
	       throw new Exception(e.getMessage());
	   } finally {
	       try {
	           if (callableStatement != null)
	               callableStatement.close();
	       } catch (Exception e) {
	       }       try {
	           if (connection != null)
	               connection.close();
	       } catch (Exception e) {
	       }
	   }
	   return tarjeta;
	}
	
}