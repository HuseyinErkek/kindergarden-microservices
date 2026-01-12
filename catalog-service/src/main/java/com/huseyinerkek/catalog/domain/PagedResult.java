package com.huseyinerkek.catalog.domain;

import java.util.List;

public record PagedResult<T>(
        List<T> data, // Asıl verimiz (Örn: 10 tane kitap)
        long totalElements, // Toplam kayıt sayısı (Örn: 1000)
        int pageNumber, // Şu anki sayfa (Örn: 1)
        int totalPages, // Toplam sayfa (Örn: 100)
        boolean isFirst, // İlk sayfa mı?
        boolean isLast, // Son sayfa mı?
        boolean hasNext, // Sonraki sayfa var mı?
        boolean hasPrevious // Önceki sayfa var mı?
        ) {}
