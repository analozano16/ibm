package co.com.test.model;

public class Tarjeta {

	private long codigo;
	
	private String numeroTarjeta;
	
	private String CCV;
	
	private TipoTarjeta tipoTarjeta;
	
	private Cliente cliente;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getCCV() {
		return CCV;
	}

	public void setCCV(String cCV) {
		CCV = cCV;
	}

	public TipoTarjeta getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
