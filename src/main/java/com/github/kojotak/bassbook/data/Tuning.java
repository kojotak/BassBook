package com.github.kojotak.bassbook.data;

public enum Tuning implements Named {

    /**
     * Standard tuning for 4 string
     */
    EADG,

    /**
     * Half step down tuning for 4 string ♭
     */
    EbAbDbGb,

    /**
     * Drop D for 4 string
     */
    DADG,

    DbAbDbGb,

    CGCF,

    /**
     * Drop C#
     */
    CsGsCsFs,

    DGCF,

    /**
     * Standard tuning for 5 string
     */
    HEADG;

    @Override
    public String getName() {
        return this.name()
                .replaceAll("b","♭")
                .replaceAll("s", "♯");
    }
}
