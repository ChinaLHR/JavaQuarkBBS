package com.quark.common.base;


import com.quark.common.dto.QuarkResult;

@FunctionalInterface
public interface ResultProcessor {
    QuarkResult process();
}
