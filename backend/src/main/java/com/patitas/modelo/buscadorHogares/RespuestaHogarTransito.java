package com.patitas.modelo.buscadorHogares;

import java.util.List;

public class RespuestaHogarTransito {
    private int total;
    private String offset;
    private List<HogarTransito> hogares;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public List<HogarTransito> getHogares() {
        return hogares;
    }

    public void setHogares(List<HogarTransito> hogares) {
        this.hogares = hogares;
    }
}
