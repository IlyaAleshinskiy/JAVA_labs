package com.gfg.Spring.boot.app.DemoController;

import com.gfg.Spring.boot.app.model.Book;
import com.gfg.Spring.boot.app.model.Laptop;
import com.gfg.Spring.boot.app.model.Storable;
import com.gfg.Spring.boot.app.service.Warehouse;
import com.gfg.Spring.boot.app.model.ToolBox;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private final Warehouse warehouse;

    // DI через конструктор — Spring внедряет singleton-бин Warehouse
    public DemoController(Warehouse warehouse) {
        this.warehouse = warehouse;
        // НЕ добавляем предметы в конструкторе — это может обойти AOP при некоторых сценариях.
        // Вместо этого используем отдельный эндпоинт /init.
    }

    /**
     * Эндпоинт для инициализации склада тестовыми предметами.
     */
    @GetMapping("/init")
    public String initializeWarehouse() {
        System.out.println("\n=== ИНИЦИАЛИЗАЦИЯ СКЛАДА ===");
        new Laptop("Dell").sendToWarehouse(warehouse);
        new Book("Spring in Action").sendToWarehouse(warehouse);
        new ToolBox(12).sendToWarehouse(warehouse);
        return "Склад успешно инициализирован! Добавлены: Laptop, Book, ToolBox.";
    }

    /**
     * Основной эндпоинт для демонстрации всех типов Advice.
     */
    @GetMapping("/demo")
    public String runDemo() {
        System.out.println("\n=== ЗАПУСК ДЕМОНСТРАЦИИ AOP ===");

        try {
            // 1. Успешное взятие предмета
            System.out.println("\n→ Сценарий 1: Успешное взятие (Alice)");
            warehouse.takeItem(0, "Alice");

            // 2. Попытка взять несуществующий предмет → IndexOutOfBoundsException
            System.out.println("\n→ Сценарий 2: Неверный индекс (Bob)");
            warehouse.takeItem(999, "Bob");

            // 3. Попытка взять предмет вором → доступ запрещён (возвращается null)
            System.out.println("\n→ Сценарий 3: Попытка кражи (THIEF)");
            Storable stolen = warehouse.takeItem(0, "THIEF");
            if (stolen == null) {
                System.out.println("THIEF получил null — доступ успешно заблокирован!");
            }

            // 4. Попытка добавить null → IllegalArgumentException
            System.out.println("\n→ Сценарий 4: Попытка добавить null");
            warehouse.putItem(null);

        } catch (Exception e) {
            System.out.println("Исключение перехвачено в контроллере: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        System.out.println("\n=== ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА ===");
        return "Демонстрация AOP завершена. Проверьте консоль!";
    }

    /**
     * Эндпоинт для просмотра текущего содержимого склада.
     */
    @GetMapping("/warehouse")
    public String viewWarehouse() {
        StringBuilder sb = new StringBuilder();
        sb.append("Текущее содержимое склада (").append(warehouse.getItemCount()).append(" предметов):\n");
        for (int i = 0; i < warehouse.getItems().size(); i++) {
            Storable item = warehouse.getItems().get(i);
            sb.append("  [").append(i).append("] ").append(item).append("\n");
        }
        return sb.toString().replace("\n", "<br>");
    }
}