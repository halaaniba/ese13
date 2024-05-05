package com.example.ese13.interceptors;

import com.example.ese13.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    public static List<Month> months = new ArrayList<>(Arrays.asList(
            new Month(1, "January", "Gennaio", "enero"),
            new Month(2, "February", "Febbraio", "Febrero"),
            new Month(3, "March", "Marzo", "Marzo"),
            new Month(4, "April", "Aprile", "Abril"),
            new Month(5, "May", "Maggio", "Mayo"),
            new Month(6, "June", "Giugno", "Junio")

    ));

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthNumberString = request.getHeader("monthNumber");
        if (monthNumberString == null || monthNumberString.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "monthNumber is missing");
            return false;
        }

        int monthNumber;
        try {
            monthNumber = Integer.parseInt(monthNumberString);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid monthId format");
            return false;
        }
        Optional<Month> optionalMonth = months.stream().filter(singleMonth -> singleMonth.getMonthNumber() == monthNumber).findFirst();
        if (optionalMonth.isPresent()) {
            request.setAttribute("MonthInterceptor-month", optionalMonth.get());
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            Month invalidMonth = new Month();
            invalidMonth.setMonthNumber(monthNumber);
            invalidMonth.setEnglishName("nope");
            invalidMonth.setItalianName("nope");
            invalidMonth.setSpanishName("nope");
            request.setAttribute("MonthInterceptor-month", invalidMonth);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        if (ex != null) {
            System.out.println("The exception is: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
