package com.hansab.carviewer.utils;

import com.hansab.carviewer.exception.WrongSortSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;

public class SortUtility {
    private static final Logger LOG = LoggerFactory.getLogger(SortUtility.class);
    private static final int SORT_PARAMS_LENGTH = 2;

    /** Receives sort string and generates Sort object for Spring Data JPA repository usage.
     * @param sortStr string with field:order format, e.g name:asc
     * @return org.springframework.data.domain.Sort object
     * @throws WrongSortSyntaxException if sortStr is in wrong format.
     */
    public static Sort generateSort(String sortStr) {
        String[] sortParams = sortStr.split(":");
        if (sortParams.length < SORT_PARAMS_LENGTH) {
            String message = "Wrong sort param. Should be in \"fieldname:order\" format. ";
            LOG.error("{} sort={}", message, sortStr);
            throw new WrongSortSyntaxException(message, sortStr);
        }

        String property = sortParams[0];
        Sort.Direction direction = Sort.Direction.fromString(sortParams[1].toUpperCase());
        return Sort.by(direction, property);
    }
}
