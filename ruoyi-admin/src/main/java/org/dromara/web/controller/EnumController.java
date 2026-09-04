package org.dromara.web.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.enums.common.EnumDefinition;
import org.dromara.common.core.enums.common.EnumScanner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enum")
@RequiredArgsConstructor
@SaIgnore
public class EnumController {
    private final EnumScanner scanner;

    @GetMapping("catalog")
    public R<List<Map<String, String>>> getCatalog() {
        return R.ok(scanner.getCatalog());
    }

    @GetMapping("type/{model}/{enumType}")
    public R<List<EnumDefinition.EnumInfo.KV>> getEnums(@PathVariable String model, @PathVariable String enumType) {
        List<EnumDefinition.EnumInfo.KV> enums = scanner.getEnums(model, enumType);
        return R.ok(enums);
    }
}
