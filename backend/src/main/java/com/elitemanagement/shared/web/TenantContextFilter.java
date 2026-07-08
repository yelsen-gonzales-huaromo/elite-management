package com.elitemanagement.shared.web;

import com.elitemanagement.shared.db.TenantContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TenantContextFilter implements Filter {

    private static final String TENANT_HEADER = "X-Tenant-ID";
    private static final String DEFAULT_TENANT = "public";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpRequest) {
            String tenantId = httpRequest.getHeader(TENANT_HEADER);
            if (tenantId == null || tenantId.trim().isEmpty()) {
                tenantId = DEFAULT_TENANT;
            }
            TenantContext.setCurrentTenant(tenantId);
        }
        try {
            chain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }
}
