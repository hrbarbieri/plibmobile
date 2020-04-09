package com.brightstar.plibmobi.convert;

public interface Reverter<IN, OUT> {

    IN revert(OUT out);
}
