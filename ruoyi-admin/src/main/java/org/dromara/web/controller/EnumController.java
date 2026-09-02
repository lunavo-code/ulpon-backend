package org.dromara.web.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.enums.common.EnumInfo;
import org.dromara.common.core.enums.common.EnumScanner;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enum")
@RequiredArgsConstructor
@SaIgnore
public class EnumController {
    private final EnumScanner scanner;

    @GetMapping("info/{model}")
    public R<Map<String, EnumInfo>> getByName(@PathVariable String model, @RequestParam List<String> keys) {
        return R.ok(scanner.get(model, keys));
    }
}
