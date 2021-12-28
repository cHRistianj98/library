package pl.distributed.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.distributed.library.dto.AuthorAssignmentDto;
import pl.distributed.library.dto.BookDto;
import pl.distributed.library.entity.AuthorAssignment;
import pl.distributed.library.entity.Book;
import pl.distributed.library.mapper.AuthorAssignmentMapper;
import pl.distributed.library.mapper.BookMapper;
import pl.distributed.library.repository.AuthorAssignmentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorAssignmentService {
    private AuthorAssignmentRepository authorAssignmentRepository;

    @Autowired
    public AuthorAssignmentService(AuthorAssignmentRepository authorAssignmentRepository) {
        this.authorAssignmentRepository = authorAssignmentRepository;
    }

    public Optional<AuthorAssignment> findById(Long id) {
        return authorAssignmentRepository.findById(id);
    }

    public List<AuthorAssignmentDto> findAll() {
        List<AuthorAssignment> authorAssignments = authorAssignmentRepository.findAll();
        return authorAssignments.stream()
                .map(AuthorAssignmentMapper::authorAssignmentToAuthorAssignmentDto)
                .collect(Collectors.toList());
    }
}
