package org.dromara.gen.domain.veriables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.gen.domain.GenTable;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenVariable {
    private GenBaseVariable base;
    private GenColumnVariable column;
    private GenOptionVariable option;
    private GenTreeVariable tree;
    private Map<String, Object> other;

    public GenVariable(GenTable table) {
        this(new GenBaseVariable(table), new GenColumnVariable(table), new GenOptionVariable(table), new GenTreeVariable(table), Map.of());
    }

    public GenVariable(GenTable table, Map<String, Object> other) {
        this(table);
        this.other = other;
    }
}
