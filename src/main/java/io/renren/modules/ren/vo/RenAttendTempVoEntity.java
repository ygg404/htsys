package io.renren.modules.ren.vo;

import io.renren.modules.ren.entity.RenAttendEntity;

import java.io.Serializable;
import java.util.List;

public class RenAttendTempVoEntity implements Serializable {

    private List<RenAttendEntity> renAttendlist;

    public void setRenAttendlist(List<RenAttendEntity> renAttendlist) {
        this.renAttendlist = renAttendlist;
    }

    public List<RenAttendEntity> getRenAttendlist(){
        return this.renAttendlist;
    }
}
