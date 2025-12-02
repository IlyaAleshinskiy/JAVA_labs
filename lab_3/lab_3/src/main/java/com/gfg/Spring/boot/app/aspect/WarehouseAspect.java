package com.gfg.Spring.boot.app.aspect;

import com.gfg.Spring.boot.app.model.Book;
import com.gfg.Spring.boot.app.model.Storable;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import com.gfg.Spring.boot.app.service.Warehouse;

import java.util.Arrays;

@Aspect
@Component
public class WarehouseAspect {

    // 1. @Before — до выполнения метода
    @Before("execution(* service.Warehouse.putItem(..))")
    public void beforePutItem(JoinPoint joinPoint) {
        System.out.println("\n[BEFORE] Calling method: " + joinPoint.getSignature().getName());
        System.out.println("[BEFORE] Arguments: " + Arrays.toString(joinPoint.getArgs()));
        Storable item = (Storable) joinPoint.getArgs()[0];
        System.out.println("[BEFORE] Trying to add: " + item.getClass().getSimpleName());
    }

    // 2. @AfterReturning — после успешного завершения
    @AfterReturning(
            pointcut = "execution(* service.Warehouse.putItem(..))",
            returning = "result"
    )
    public void afterPutItemReturning(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        System.out.println("[AFTER RETURNING] Method " + signature.getName() + " completed successfully.");
        System.out.println("[AFTER RETURNING] Warehouse now has " +
                ((Warehouse) joinPoint.getTarget()).getItemCount() + " items.");
    }

    // 3. @AfterThrowing — если выброшено исключение
    @AfterThrowing(
            pointcut = "execution(* service.Warehouse.putItem(..))",
            throwing = "ex"
    )
    public void afterPutItemThrowing(JoinPoint joinPoint, IllegalArgumentException ex) {
        System.out.println("[AFTER THROWING] Exception in " + joinPoint.getSignature().getName());
        System.out.println("[AFTER THROWING] Message: " + ex.getMessage());
        // Можно логировать, но НЕ изменять исключение здесь
    }

    // 4. @After — аналог finally (выполняется всегда)
    @After("execution(* service.Warehouse.putItem(..))")
    public void afterPutItemFinally(JoinPoint joinPoint) {
        System.out.println("[AFTER] Cleanup or audit after putItem.");
    }

    // 5. @Around — до и после, можно изменить поведение
    @Around("execution(* service.Warehouse.takeItem(..))")
    public Object aroundTakeItem(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("\n[AROUND] Before takeItem: " + Arrays.toString(pjp.getArgs()));

        Object[] args = pjp.getArgs();
        String requester = (String) args[1];

        // ✅ ДЕМОНСТРАЦИЯ: изменение поведения
        if ("THIEF".equalsIgnoreCase(requester)) {
            System.out.println("[AROUND] Access denied for thief!");
            return null; // подменяем результат
        }

        try {
            Object result = pjp.proceed(); // вызываем оригинальный метод
            System.out.println("[AROUND] Successfully took: " + result);
            return result;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("[AROUND] Caught exception: " + e.getMessage());
            // ✅ ДЕМОНСТРАЦИЯ: подмена исключения или результата
            return new Book("Default Recovery Book"); // возвращаем заглушку
        } catch (SecurityException e) {
            System.out.println("[AROUND] Security violation: " + e.getMessage());
            throw new RuntimeException("Access denied: " + e.getMessage());
        } finally {
            System.out.println("[AROUND] Finally block in around advice.");
        }
    }

    // Дополнительно: логирование сигнатуры и аргументов
    @Before("execution(* service.Warehouse.takeItem(..))")
    public void logTakeItemCall(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        System.out.println("\n[LOG] Method: " + signature.getMethod().getName());
        System.out.println("[LOG] Parameters: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("[LOG] Declaring type: " + signature.getDeclaringTypeName());
    }
}