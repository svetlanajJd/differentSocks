package controller;

import entity.Socks;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.SocksService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService socksService;

    @Operation(summary = "поступление новых носков",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Удалось добавить приход"),
                    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
                    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны(например, база данных недоступна)")
            }
    )
    @PostMapping("/income")
    public ResponseEntity<Socks> createSocks(@RequestBody Socks socks) {
        Socks createdSocks = socksService.createSocks(socks);
        return ResponseEntity.ok(createdSocks);
    }

    @Operation(summary = "отпуск носков со склада",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Удалось отпустить носки"),
                    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
                    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны(например, база данных недоступна)")
            }
    )
    @PostMapping("/outcome")
    public ResponseEntity<?> deleteSocks(@RequestBody Socks socks) {
        socksService.deleteSocks(socks);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "остатки носков на складе",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Запрос выполнен, результат в теле ответа в виде строкового представления целого числа"),
                    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
                    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны(например, база данных недоступна)")
            }
    )
    @GetMapping("{color}/{cottonPart}/{operation}")
    public Long getSocks(@PathVariable String color, @PathVariable Long cottonPart, @PathVariable String operation) {
        return socksService.findSocks(color, cottonPart, operation);
    }
}
