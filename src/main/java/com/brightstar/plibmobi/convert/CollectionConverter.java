package com.brightstar.plibmobi.convert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CollectionConverter<IN, OUT> {
    private final Converter<IN, OUT> converter;
    
    public CollectionConverter(Converter<IN, OUT> converter) {
        this.converter = converter;
    }

    public List<OUT> convertToList(Collection<IN> inputList) {
    	
        List<OUT> result = new ArrayList<OUT>(inputList.size());
        
        for(IN in : inputList) {
            result.add(converter.convert(in));
        }
        
        return result;
    }
    
    public Set<OUT> convertToSet(Collection<IN> inputList) {
        Set<OUT> result = new LinkedHashSet<OUT>();
        for(IN in : inputList) {
            result.add(converter.convert(in));
        }
        return result;
    }
}