package com.gitanjali.devassist.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gitanjali.devassist.entity.Analysis;
import com.gitanjali.devassist.service.HistoryService;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping
    public ResponseEntity<List<Analysis>> getAllHistory() {

        return ResponseEntity.ok(
                historyService.getAllHistory()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Analysis> getHistoryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                historyService.getHistoryById(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistory(
            @PathVariable Long id) {

        historyService.deleteHistory(id);

        return ResponseEntity.noContent().build();
    }
}