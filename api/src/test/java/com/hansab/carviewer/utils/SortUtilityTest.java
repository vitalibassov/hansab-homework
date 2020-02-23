package com.hansab.carviewer.utils;

import com.hansab.carviewer.exception.WrongSortSyntaxException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

public class SortUtilityTest {

    @Test
    public void shouldReturnSortObject() {
        Sort sort = SortUtility.generateSort("name:desc");
        Assertions.assertEquals(Sort.by(Sort.Direction.DESC, "name"), sort);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionOnGenerateSortWithWrongOrder() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                SortUtility.generateSort("name:asccc"));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionOnGenerateSortWithWrongSyntax() {
        Assertions.assertThrows(WrongSortSyntaxException.class, () ->
                SortUtility.generateSort("name|asc"));
    }
}
