package com.axiom.mobilehandset.utils;

import java.util.Objects;
import java.util.function.Predicate;

public class PredicateUtil {
    public static final Predicate<String> isNull = Objects::isNull;
    public static final Predicate<String> isNullOrEmpty = isNull.or(String::isEmpty);
}
