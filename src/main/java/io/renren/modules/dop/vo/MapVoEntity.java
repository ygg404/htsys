package io.renren.modules.dop.vo;

import io.renren.modules.dop.entity.DopBmapEntity;

import java.io.Serializable;

public class MapVoEntity implements Serializable {

    private DopBmapEntity dopBmapEntity;

    public DopBmapEntity getDopBmapEntity() {
        return dopBmapEntity;
    }

    public void setDopBmapEntity(DopBmapEntity dopBmapEntity) {
        this.dopBmapEntity = dopBmapEntity;
    }
}
