package com.dlbs.modelo;

public class Detalle extends Menu{

    private Integer idDetalle;
    private Integer fkVenta;
    private Integer fkMenu;
    private Integer detCantidad;
    private Double detTotal;

    public Detalle() {
    }

    public Detalle(Integer idDetalle, Integer fkVenta, Integer fkMenu, Integer fkCantidad, Double detTotal) {
        this.idDetalle = idDetalle;
        this.fkVenta = fkVenta;
        this.fkMenu = fkMenu;
        this.detCantidad = fkCantidad;
        this.detTotal = detTotal;
    }

    public Detalle(Integer fkVenta, Integer fkMenu, Integer fkCantidad, Double detTotal) {
        this.fkVenta = fkVenta;
        this.fkMenu = fkMenu;
        this.detCantidad = fkCantidad;
        this.detTotal = detTotal;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getFkVenta() {
        return fkVenta;
    }

    public void setFkVenta(Integer fkVenta) {
        this.fkVenta = fkVenta;
    }

    public Integer getFkMenu() {
        return fkMenu;
    }

    public void setFkMenu(Integer fkMenu) {
        this.fkMenu = fkMenu;
    }

    public Integer getDetCantidad() {
        return detCantidad;
    }

    public void setDetCantidad(Integer detCantidad) {
        this.detCantidad = detCantidad;
    }

    public Double getDetTotal() {
        return detTotal;
    }

    public void setDetTotal(Double detTotal) {
        this.detTotal = detTotal;
    }

}
