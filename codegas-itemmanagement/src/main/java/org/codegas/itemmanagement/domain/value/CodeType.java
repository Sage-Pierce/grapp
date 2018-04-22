package org.codegas.itemmanagement.domain.value;

public enum CodeType {
    GTIN("%013d"),
    UPC("%012d"),
    PLU("%05d"),
    NACS("%02d%02d%02d"),
    MANUAL("%s"),
    RANDOM("%s");

    private final String valueFormat;

    CodeType(String valueFormat) {
        this.valueFormat = valueFormat;
    }

    public String getValueFormat() {
        return valueFormat;
    }
}
