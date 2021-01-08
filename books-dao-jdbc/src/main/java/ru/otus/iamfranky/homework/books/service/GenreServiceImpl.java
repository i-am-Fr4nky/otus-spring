package ru.otus.iamfranky.homework.books.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.iamfranky.homework.books.dao.GenreDao;
import ru.otus.iamfranky.homework.books.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public List<Genre> getAll() {
        try {
            return genreDao.getAll();
        } catch (Exception e) {
            log.error("Get genre error", e);
            throw e;
        }
    }
}
