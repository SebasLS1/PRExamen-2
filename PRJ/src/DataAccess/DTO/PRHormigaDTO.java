package DataAccess.DTO;

public class PRHormigaDTO {
    private Integer pridHormiga;
    private Integer pridHormigaClasificacion;
    private String prCodigoHormiga;
    private String prComio;
    private String prEstado;
    private String prRecogio;
    private String prfechaModifica;
    private String prfechaCrea;


    public PRHormigaDTO() { }

    public PRHormigaDTO(Integer idHormiga, Integer idHormigaClasificacion, String CodigoHormiga, String Comio, String Estado, String Recogio, String fechaModifica, String fechaCrea) {
        this.pridHormiga = idHormiga;
        this.pridHormigaClasificacion = idHormigaClasificacion;
        this.prCodigoHormiga = CodigoHormiga;
        this.prEstado = Estado;
        this.prComio = Comio;
        this.prfechaCrea = fechaCrea;
        this.prfechaModifica = fechaModifica;
        this.prRecogio= Recogio;
    }
    public PRHormigaDTO( Integer idHormigaClasificacion, String nombre) {
        this.pridHormigaClasificacion = idHormigaClasificacion;
        this.prCodigoHormiga = prCodigoHormiga;
    }

    public Integer getIdHormiga() {
        return pridHormiga;
    }

    public void setIdHormiga(Integer idHormiga) {
        this.pridHormiga = idHormiga;
    }

    public Integer getIdHormigaClasficacion() {
        return pridHormigaClasificacion;
    }

    public void setIdHormigaClasifacacion(Integer idHormigaClasificacion) {
        this.pridHormigaClasificacion = idHormigaClasificacion;
    }

    public String getCodigoHormiga() {
        return prCodigoHormiga;
    }

    public void setCodigoHormiga(String CodigoHormiga) {
        this.prCodigoHormiga = CodigoHormiga;
    }

    public String getEstado() {
        return prEstado;
    }

    public void setEstado(String estado) {
        this.prEstado = estado;
    }

    public String getFechaCrea() {
        return prfechaCrea;
    }

    public void setFechaCrea(String fechaCrea) {
        this.prfechaCrea = fechaCrea;
    }

    public String getFechaModifica() {
        return prfechaModifica;
    }

    public void setFechaModifica(String fechaModifica) {
        this.prfechaModifica = fechaModifica;
    }

    @Override
    public String toString() {
        return getClass().getName()
        + "\n idHormiga: " + getIdHormiga()
        + "\n idHormigaTipo: " + getIdHormigaClasficacion()
        + "\n codigoHormiga: " + getCodigoHormiga()
        + "\n estado: " + getEstado()
        + "\n fechaCrea: " + getFechaCrea()
        + "\n fechaModifica: " + getFechaModifica();
    }
}
