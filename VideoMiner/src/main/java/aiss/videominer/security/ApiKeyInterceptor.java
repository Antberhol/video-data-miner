package aiss.videominer.security;

import aiss.videominer.model.ApiClient;
import aiss.videominer.repository.ApiClientRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    @Autowired
    private ApiClientRepository apiClientRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        if ("/error".equals(uri) ||
            uri.startsWith("/v3/api-docs") ||
            uri.startsWith("/swagger-ui") ||
            uri.startsWith("/videominer/register") ||
            uri.startsWith("/h2-ui")) {
            return true;
        }

        if ("POST".equalsIgnoreCase(request.getMethod()) && "/videominer/channels".equals(uri)) {
            return true;
        }
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            Optional<ApiClient> apiClient = apiClientRepository.findByToken(token);
            
            if (apiClient.isPresent()) {
                return true;
            }
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        String jsonError = "{\"error\": \"Unauthorized\", \"message\": \"Acceso denegado: API Key inválida o no proporcionada\"}";
        response.getWriter().write(jsonError);
        return false;
    }
}