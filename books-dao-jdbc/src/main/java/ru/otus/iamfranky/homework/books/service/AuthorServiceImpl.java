package ru.otus.iamfranky.homework.books.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.iamfranky.homework.books.dao.AuthorDao;
import ru.otus.iamfranky.homework.books.domain.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public List<Author> getAll() {
        try {
            return authorDao.getAll();
        } catch (Exception e) {
            log.error("Get authors error, e");
            throw e;
        }
    }
}
