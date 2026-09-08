package com.gitanjali.devassist.service;

import java.util.List;

import com.gitanjali.devassist.entity.Analysis;

public interface HistoryService {

    List<Analysis> getAllHistory();

    Analysis getHistoryById(Long id);

    void deleteHistory(Long id);
}