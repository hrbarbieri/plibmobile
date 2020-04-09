package com.brightstar.plibmobi.convert;

public interface Converter<IN, OUT> {

	OUT convert(IN in);

}
