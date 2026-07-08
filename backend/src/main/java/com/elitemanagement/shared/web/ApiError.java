package com.elitemanagement.shared.web;

public record ApiError(
    String code,
    String message
) {}
