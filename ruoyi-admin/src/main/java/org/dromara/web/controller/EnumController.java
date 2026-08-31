package org.dromara.web.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.enums.common.EnumInfo;
import org.dromara.common.core.enums.common.EnumScanner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/enum")
@RequiredArgsConstructor
@SaIgnore
public class EnumController {
    private final EnumScanner scanner;

    @GetMapping("all")
    public R<Map<String, Collection<EnumInfo>>> allList() {
        return R.ok(scanner.getAll());
    }

    @GetMapping("list/{model}")
    public R<Collection<EnumInfo>> list(@PathVariable String model) {
        return R.ok(scanner.getList(model));
    }

    @GetMapping("info/{model}/{key}")
    public R<EnumInfo> getByName(@PathVariable String model, @PathVariable String key) {
        return R.ok(scanner.get(model, key));
    }
}
