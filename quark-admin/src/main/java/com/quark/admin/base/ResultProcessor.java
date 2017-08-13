package com.quark.admin.base;

import com.quark.admin.dto.QuarkAdminResult;

@FunctionalInterface
public interface ResultProcessor {
    QuarkAdminResult process();
}
