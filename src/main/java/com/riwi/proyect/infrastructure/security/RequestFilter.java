package com.riwi.proyect.infrastructure.security;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class RequestFilter implements Filter {

    //ThreadLocal Almacena hilos de manera especifica
    //Hilo: unidad de ejecucion dentro de un proceso. esto perimite hacer multiples tareas simultaneamente
    // Crea un ThreadLocal para almacenar la solicitud actual.
    //HttpServletRequest: proporciona metodos especificos para manejar solicitudos HTTP.
    private static final ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Lógica de inicialización, si es necesario.
        System.out.println("Se inicio el filter");
    }

    //ServletRequest request: Esto representa la solicitud de un cliente (ejemplo: un navegador)
    //ServletResponse response: Esto representa la respuesta en el servidor que enviara de vuelta al cliente
    //FilterChain chain: Esto representa la cadena a la que se solicito y respuestas actuales que deben pasar
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Almacena la solicitud HTTP actual en el ThreadLocal.
        currentRequest.set((HttpServletRequest) request);
        try {
            // Pasa la solicitud y la respuesta al siguiente filtro o servlet en la cadena.
            chain.doFilter(request, response);
        } finally {
            // Asegúrate de limpiar el ThreadLocal después de procesar la solicitud.
            currentRequest.remove();
        }
    }

    @Override
    public void destroy() {
        // Lógica de limpieza, si es necesario.
    }

    // Método estático para obtener la solicitud actual desde cualquier lugar.
    public static HttpServletRequest getCurrentRequest() {
        return currentRequest.get();
    }
}
